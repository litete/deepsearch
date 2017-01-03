package app.home.controller;

import app.common.base.ExecuteResult;
import app.home.Singleton.SingleTon;
import app.home.model.Log;
import app.home.model.User;
import app.home.service.UserService;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private UserService thisService;
	private ExecuteResult executeResult = new ExecuteResult();
	private String viewPath = "App/Admin/User/";
	private String arctileString="/arctile/";

	public UserService getUserService() {
		return thisService;
	}

	@Autowired
	public void setUserService(UserService thisService) {
		this.thisService = thisService;
	}

	//先进入查询是否有推荐文章
		@RequestMapping(value="/userid",method=RequestMethod.GET)
		private String selectRecommendArctile(@RequestParam int userid){
			
			System.out.println(userid+"有userid啊");
	        if(userid==0){
	        	 User user=new User();
	        	 user.setVector("0");
	        	 //插入自增并返回主键id
				thisService.insertResultId(user);
               userid=user.getUser_id();
		        }
	        System.out.println("userid"+userid);
			return "redirect:/arctilebuffer/"+"selectIfRecommendArctile.do?userid="+userid;
		}
		
		//接受用户arctile的json 
		/*先查有没有看过，没有看过就添加loged，然后给用户设置vector，再把推荐文章写到推荐文章里去*/
		@RequestMapping(value="/uaid",method=RequestMethod.POST)
		@ResponseBody
		private void selectUserHaveVector(String data,HttpServletRequest request,HttpServletResponse response) {
			System.out.println("--------"+data);
			Log logg=new Log();
		    ObjectMapper obj=SingleTon.getObjectMapper();
			try {
				 logg=obj.readValue(data,Log.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int userid=logg.getUserid();
			int arctileid=logg.getArctileid();
			String updatetime=logg.getUpdatetime();
			User user=new User();
			user.setVector("");
//		        if(userid==0000){
//		         userid = thisService.insertResultId(user);
//		         System.out.println("cookie值："+userid);
//		        }
//		        System.out.println("user值"+userid);
			//查询有没有看过
			//Log log=new Log();
			         logg.setUserid(userid);
				//int s=thisService.insertLoged(logg);
			request.setAttribute("log",logg);
			try {
				request.getRequestDispatcher("/arctile/haveVector.do").forward(request,response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//return "";
//			return "redirect:/arctile/haveVector.do?userid="+userid
//							+"&&arctileid="+arctileid+"&&updatetime="+updatetime;
	
		}
	






	
}