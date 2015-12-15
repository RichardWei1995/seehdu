package com.jw.shop.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

@SuppressWarnings("serial")
public class Love extends BmobObject{
	private String toname;
	private String sex;
	private String love;
	private String fromname;
	private String agree;
	private BmobPointer pointer;
	public String getToname() {
		return toname;
	}
	public void setToname(String toname) {
		this.toname = toname;
	}
	public String getFromname() {
		return fromname;
	}
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	public String getLove() {
		return love;
	}
	public void setLove(String love) {
		this.love = love;
	}
	public String getAgree() {
		return agree;
	}
	public void setAgree(String agree) {
		this.agree = agree;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public BmobPointer getPointer() {
		return pointer;
	}
	public void setPointer(BmobPointer pointer) {
		this.pointer = pointer;
	}

}
