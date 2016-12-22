package app.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import app.home.dao.ArctileMapper;
import app.home.model.ArctileFew;
import app.home.model.ArctileLittle;
import app.home.model.ArctileRecommend;
import app.home.model.Log;
import app.home.model.User;
import app.home.service.ArctileService;
@Service("arctileservice")
public class ArctileServiceImpl implements ArctileService {

	private ArctileMapper thismapper;

	public ArctileMapper getThismapper() {
		return thismapper;
	}
	@Autowired
	public void setThismapper(ArctileMapper thismapper) {
		this.thismapper = thismapper;
	}
	//没有vector用户的推荐文章
	@Override
	public List<ArctileLittle> SelectRecommendArctile() {
		return thismapper.SelectRecommendArctile();
	}
	//根据id找到对应的vector
	@Override
	public String selecetUserVectorById(int userid) {
		// TODO Auto-generated method stub
		return thismapper.selecetUserVectorById(userid);
	}
	//查询已经看过的文章
	@Override
	public List<Integer> selectloged(int userid) {
		// TODO Auto-generated method stub
		return thismapper.selectloged(userid);
	}
	//查询文章的id、title以及vector
	@Override
	public List<ArctileLittle> selectArctileAllVector() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ArctileFew> selectArctileOutRead(int userid) {
		// TODO Auto-generated method stub
		return thismapper.selectArctileOutRead(userid);
	}
	@Override
	public ArctileLittle SelectRecommendArctileById(int arctileid) {
		// TODO Auto-generated method stub
		return thismapper.SelectRecommendArctileById(arctileid);
	}
	@Override
	public String selectArctileVectorById(int arctileid) {
		// TODO Auto-generated method stub
		return thismapper.selecetArctileVectorById(arctileid);
	}
	@Override
	public ArctileLittle arctileRecommend(int arctileid) {
		// TODO Auto-generated method stub
		return thismapper.arctileRecommend(arctileid);
	}
	@Override
	public int deleteSendArctileByUserid(int userid, int arctileid) {
		// TODO Auto-generated method stub
		return thismapper.deleteSendArctileByUserid(userid, arctileid) ;
	}
	////更新用户向量
	@Override
	public int updateUserVector(User user) {
		return thismapper.updateUserVector(user);
		
	}
	@Override
	public int insertLogByRecommendarctile(Log log) {
		return thismapper.insertLogByRecommendarctile(log);
		
	}
	@Override
	public List<ArctileLittle> selectArctileOutReadWhenNoArctole(int haveRead) {
		// TODO Auto-generated method stub
		return thismapper.selectArctileOutReadWhenNoArctole(haveRead);
	}





}
