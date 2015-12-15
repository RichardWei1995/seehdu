package com.jw.shop.model;

import cn.bmob.v3.BmobObject;

@SuppressWarnings("serial")
public class Lostcomment extends BmobObject{
	String lostID;
    String content;
    User user;
    Lost lost;
	public String getLostID() {
		return lostID;
	}
	public void setLostID(String lostID) {
		this.lostID = lostID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Lost getLost() {
		return lost;
	}
	public void setLost(Lost lost) {
		this.lost = lost;
	}
}
