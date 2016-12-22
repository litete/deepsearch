package app.home.dao;

import java.util.List;

import app.home.model.ArctileFew;
import app.home.model.ArctileLittle;
import app.home.model.ArctileRecommend;
import app.home.model.Log;
import app.home.model.User;

public interface ArctileMapper {
	//用户vector为null时的推荐文章
		List<ArctileLittle> SelectRecommendArctile();
		//根据userid查出user的vector
		String selecetUserVectorById(int userid);
		//查出用户看过的文章
		List<Integer> selectloged(int userid);
		 //查询出还没有看过的文章
		List<ArctileFew> selectArctileOutRead(int userid);
		//根据文章id查询出推荐文章
		ArctileLittle SelectRecommendArctileById(int arctileid);
		//根据文章id查询出文章vector
		String selecetArctileVectorById(int arctileid);
		//根据文章id查出要给前端的文章属性
		ArctileLittle arctileRecommend(int arctileid);
		//删除发送给前端的文章
		int deleteSendArctileByUserid(int userid,int arctileid);
		////更新用户向量
		void updateUserVector(User user);
		//将推荐的文章发送给log
		int insertLogByRecommendarctile(Log log);
		//查询除还没有看过的文章，当没有推荐文章时 
		List<ArctileLittle> selectArctileOutReadWhenNoArctole(int haveRead);
}
