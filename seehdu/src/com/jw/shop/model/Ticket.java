package com.jw.shop.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Ticket extends BmobObject {

	private String ticketName;
	private Integer ticketReNum;
	private Integer ticketHaNum;
	private String content;
	private BmobFile img;

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public Integer getTicketReNum() {
		return ticketReNum;
	}

	public void setTicketReNum(Integer ticketReNum) {
		this.ticketReNum = ticketReNum;
	}

	public Integer getTicketHaNum() {
		return ticketHaNum;
	}

	public void setTicketHaNum(Integer ticketHaNum) {
		this.ticketHaNum = ticketHaNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BmobFile getImg() {
		return img;
	}

	public void setImg(BmobFile img) {
		this.img = img;
	}


}
