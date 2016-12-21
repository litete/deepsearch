package app.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.home.dao.UserMapper;
import app.home.model.User;
import app.home.service.UserService;
import app.home.model.Log;;

@Service("userService")
public class UserServiceImpl implements UserService {

private UserMapper thisMapper;
	
	public UserMapper getUserMapper() {
		return thisMapper;
	}
	
	@Autowired
	public void setUserMapper(UserMapper thisMapper){
		this.thisMapper = thisMapper;
	}
	
	public String selectUserHaveVector(int userid) {
		return thisMapper.selectUserHaveVector(userid);
	}

	@Override
	public List ifLoged(int userid, int arctileid) {
		// TODO Auto-generated method stub
		return thisMapper.ifLogged(userid, arctileid);
	}

	@Override
	public User find(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(User info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remove(int uid) {
		// TODO Auto-generated method stub
		return 0;
	}
      //插入看过的文章
	@Override
	public int insertLoged(Log log) {
		// TODO Auto-generated method stub
		return thisMapper.insertLog(log);
	}

	@Override
	public int insertResultId(User user) {
		// TODO Auto-generated method stub
		return thisMapper.insertResultId(user);
	}





}
