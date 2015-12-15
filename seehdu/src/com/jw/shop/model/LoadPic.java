package com.jw.shop.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class LoadPic extends BmobObject{
       private BmobFile homepic;

	public BmobFile getHomepic() {
		return homepic;
	}

	public void setHomepic(BmobFile homepic) {
		this.homepic = homepic;
	}
}
