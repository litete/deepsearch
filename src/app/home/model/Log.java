package app.home.model;

import java.sql.Timestamp;

public class Log {
private int userid;
private int arctileid;
private String updatetime;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public int getArctileid() {
	return arctileid;
}
public void setArctileid(int arctileid) {
	this.arctileid = arctileid;
}
public String getUpdatetime() {
	return updatetime;
}
public void setUpdatetime(String updatetime) {
	this.updatetime = updatetime;
}



}
