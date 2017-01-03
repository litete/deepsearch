package app.home.model;

public class ArctileLittleAndUserId {
	private int userid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setArctileid(int arctileid) {
		this.arctileid = arctileid;
	}

	private int arctileid;
	private String title;
	private String url;
	private String thumbnail_url;

	public int getArctileid() {
		return arctileid;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
