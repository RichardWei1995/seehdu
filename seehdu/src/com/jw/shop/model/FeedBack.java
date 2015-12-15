package com.jw.shop.model;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 用户反馈信息
 * @date 2014-5-27
 * @author Stone
 */
@SuppressWarnings("serial")
public class FeedBack extends BmobObject {

	private String username;
	private String phone;
	private String email;
	private String content;

	public String getUsername() {
		return username;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getContent() {
		return content;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContent(String content) {
		this.content = content;
	}


}
