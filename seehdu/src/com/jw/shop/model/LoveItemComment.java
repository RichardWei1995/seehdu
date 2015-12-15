package com.jw.shop.model;

import cn.bmob.v3.BmobObject;

public class LoveItemComment extends BmobObject{
      private String content;
      private Love love;
      private User user;
      private String commentname;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Love getLove() {
		return love;
	}
	public void setLove(Love love) {
		this.love = love;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCommentname() {
		return commentname;
	}
	public void setCommentname(String commentname) {
		this.commentname = commentname;
	}
}
