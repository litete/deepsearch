package app.home.service;

import java.util.List;

import app.home.model.ArctileBuffer;

public interface ArctileBufferService {
	 int insertarcilebuffer (ArctileBuffer arctileBuffer);
	 //写入推荐文章
	 int insertLoged (int userid,int arctileid,String date);
	 
	 //查询是否有推鉴文章
	 List<ArctileBuffer> selectIfRecommendArctile(int useriid);
}
