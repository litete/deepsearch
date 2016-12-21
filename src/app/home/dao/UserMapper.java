package app.home.dao;

import java.util.List;

import app.home.model.Log;
import app.home.model.User;

public interface UserMapper {
    String selectUserHaveVector(int userid);
    //查询有没有看过这篇文章
	List ifLogged(int userid,int arctileid);
	//插入看过的文章
	int insertLog(Log log);
	//插入cookid并返回自增主键
	int insertResultId(User user);
}