package com.jw.shop.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

public class NewsdalyComment extends BmobObject {
	public static final String TAG = "NewsdalyComment";
	private String comment;
	private User commenter;
	private DalyNews dalyNews;
	private String commentname;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

	public DalyNews getDalyNews() {
		return dalyNews;
	}

	public void setDalyNews(DalyNews dalyNews) {
		this.dalyNews = dalyNews;
	}

	public static String getTag() {
		return TAG;
	}

	public String getCommentname() {
		return commentname;
	}

	public void setCommentname(String commentname) {
		this.commentname = commentname;
	}

}
