package app.home.dao;

import java.util.List;

import app.home.model.ArctileBuffer;

public interface ArctileBufferMapper {
	//将userid和arctileid写入表里
		int saveUseridAndArctileid(ArctileBuffer arctileBuffer);
		int insertLoged(int userid, int arctileid, String date );
		//检查是否有推荐文章
		List<ArctileBuffer> selectIfRecommendArctiled(int useriid);
}
