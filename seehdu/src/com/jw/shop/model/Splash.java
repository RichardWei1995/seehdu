package com.jw.shop.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Splash extends BmobObject{
	private BmobFile img;

	public BmobFile getImg() {
		return img;
	}

	public void setImg(BmobFile img) {
		this.img = img;
	}

}
