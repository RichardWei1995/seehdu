package com.jw.shop.model;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

/**
 * @author w 首页每日新闻列表bean
 */
public class DalyNews extends BmobObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String time;
	private String from;
	private String type;
	private String content;
	private String url;
	private Integer see;

	public Integer getSee() {
		return see;
	}

	public void setSee(Integer see) {
		this.see = see;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private File img;
	private BmobPointer point;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BmobPointer getPoint() {
		return point;
	}

	public void setPoint(BmobPointer point) {
		this.point = point;
	}


}
