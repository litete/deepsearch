package app.home.model;

public class ArctileBuffer {
private int  userid; 
private int  arctileid;
private double arctileweight;
private  String updatetime;

public double getArctileweight() {
	return arctileweight;
}
public void setArctileweight(double arctileweight) {
	this.arctileweight = arctileweight;
}
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
