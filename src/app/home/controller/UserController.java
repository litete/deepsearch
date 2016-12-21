package app.home.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import app.common.base.ExecuteResult;
import app.home.Singleton.SingleTon;
import app.home.model.Log;
import app.home.model.User;
import app.home.service.UserService;
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
		//@RequestBody不能省，因为前台发过来的数据是json数据，得用这个注解去解析该怎么接收这些数据给pojo类的对象
		@ResponseBody
		private String selectUserHaveVector(String test ) {
			System.out.println("--------"+test);
			Log logg=new Log();
		    ObjectMapper obj=SingleTon.getObjectMapper();
			try {
				 logg=obj.readValue(test,Log.class);
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
			int userid=logg.getArctileid();
			int arctileid=logg.getArctileid();
			String updatetime=logg.getUpdatetime();
			User user=new User();
			user.setVector("");
		        if(userid==0000){
		         userid = thisService.insertResultId(user);
		         System.out.println("cookie值："+userid);
		        }
//		        System.out.println("user值"+userid);
//		        int arctileid=log.getArctile_id();
//		        String  updattime=log.getUpdatetime();
			//查询有没有看过
		      //  System.out.println("查询有没有看过");
			/*Log logg=new Log();
			         logg.setArctileid(arctileid);
			         logg.setUserid(userid);
			         logg.setUpdatetime(updatetime);*/
			//	System.out.println("已经运行到这了");
//			System.out.println("接受到的参数为："+logg.getArctileid());
//				int s=thisService.insertLoged(logg);
//				//System.out.println("过来了");
//				System.out.println("s:"+s);
			return "redirect:/arctile/haveVector.do?userid="+userid
							+"&&arctileid="+arctileid+"&&updatetime="+updatetime;
	
		}
	
/*	//判断用户是否为第一次进入,根据vector是否为空
	@RequestMapping(value="/userid",produces="application/json;charset=UTF-8")
	//@RequestBody不能省，因为前台发过来的数据是json数据，得用这个注解去解析该怎么接收这些数据给pojo类的对象。  
	private String selectUserHaveVector(HttpServletRequest request,@RequestParam int  userid) { 
		String vector =  thisService.selectUserHaveVector(userid);
		if(vector==null){
			return "redirect:"+arctileString+"nullVector.do";
		}else{
			
			return "redirect:"+arctileString+"haveVector.do?id="+userid;
		}*/





	
}