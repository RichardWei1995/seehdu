package com.jw.shop.model;

import cn.bmob.v3.BmobObject;

public class SaveTicketList extends BmobObject{
	private String name;
	private String check;
	private String sort;
	private String checknum;
	public String getChecknum() {
		return checknum;
	}
	public void setChecknum(String checknum) {
		this.checknum = checknum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

}
