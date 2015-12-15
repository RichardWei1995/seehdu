package com.jw.shop.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Lost extends BmobObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type; // 丢失
	private String title; // 标题
	private String describe; // 描述
	private String time; //丢失时间
	private String connect;//联系方式
	private BmobFile pic;
	public User author;
	private Lostcomment comment;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BmobFile getPic() {
		return pic;
	}
	public void setPic(BmobFile pic) {
		this.pic = pic;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Lostcomment getComment() {
		return comment;
	}
	public void setComment(Lostcomment comment) {
		this.comment = comment;
	}

}
