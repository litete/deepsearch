package app.home.service;

import java.util.List;

import app.home.model.ArctileFew;
import app.home.model.ArctileLittle;
import app.home.model.ArctileRecommend;
import app.home.model.Log;
import app.home.model.User;

public interface ArctileService {

	//没有vector用户的推荐文章
	 List<ArctileLittle> SelectRecommendArctile();
	 //根据id找到对应的vector
	 String selecetUserVectorById(int userid);
	 //查询已经看过的文章
	 List<Integer> selectloged(int userid);
	 //查询文章的id、title以及vector
	 List<ArctileLittle> selectArctileAllVector();
	 //查询出还没有看过的文章
	 List<ArctileFew>  selectArctileOutRead(int userid);
	 ArctileLittle SelectRecommendArctileById(int arctileid);
	 String selectArctileVectorById(int arctileid);
	 //有推荐文章，根据其id找出其属性
	 ArctileLittle arctileRecommend(int arctileid);
	//删除给前段的文章
	int deleteSendArctileByUserid(int userid,int arctileid);
	//更新用户向量
	int updateUserVector(User user);
	//将推荐的文章写到log表里
	int insertLogByRecommendarctile(Log log);
	
	List<ArctileLittle> selectArctileOutReadWhenNoArctole(int haveRead);
}
