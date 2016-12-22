package app.home.controller;

import app.home.model.ArctileBuffer;
import app.home.service.ArctileBufferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/arctilebuffer")
public class ArctileBufferController {
	private ArctileBufferService thisService;

	public ArctileBufferService getThisService() {
		return thisService;
	}
    @Autowired
	public void setThisService(ArctileBufferService thisService) {
		this.thisService = thisService;
	}
	
    @RequestMapping(value="/selectIfRecommendArctile")
	private String selectIfRecommendArctile (HttpServletRequest request,HttpServletResponse response,
			@RequestParam int userid ){
		System.out.println("之前");
		List<ArctileBuffer> ifHaveRecommendArctileList=thisService.selectIfRecommendArctile(userid);
		System.out.println("之后");
		System.out.println(ifHaveRecommendArctileList.size());
		if (ifHaveRecommendArctileList.isEmpty()) {
			//说明没有推荐文章
			//System.out.println("说明没有推荐文章");
			return "redirect:/arctile/noArctileBuffer.do?userid="+userid;
		}else{
			//有推荐文章时
			List list=new ArrayList();
			for (int i = 0; i < ifHaveRecommendArctileList.size(); i++) {
				ArctileBuffer arctileBuffer=ifHaveRecommendArctileList.get(i);
				int arctileBufferid=arctileBuffer.getArctileid();
				System.out.println("arctileBufferid"+arctileBufferid);
				list.add(arctileBufferid);
			}
			System.out.println("ArctileBufferController的list"+list.size());
			request.setAttribute("arctileBufferidlist", list);
			request.setAttribute("userid", userid);
          try {
			request.getRequestDispatcher("/arctile/arctileRecommend.do").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//	return "redirect:/arctile/arctileRecommend.do";
			return null;
}
		
	}

    
	}
    
