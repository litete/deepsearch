package app.home.service;

import java.util.List;

import app.home.model.Log;
import app.home.model.User;

public interface UserService {

	String selectUserHaveVector(int userid);
	
	User find(String uid);
	
	Integer save(User info);

	
	int remove(int uid);
	//查询有没有看过这篇文章
	List ifLoged(int userid,int arctileid);
	//添加到log中去
	int insertLoged(Log log);
	//插入cookid并返回自增主键
	int insertResultId(User user);

}
