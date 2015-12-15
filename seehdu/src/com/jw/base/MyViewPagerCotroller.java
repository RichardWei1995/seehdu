package com.jw.base;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

/**
 * viewpager view控制
 * 
 * @author jw
 *
 */
public abstract class MyViewPagerCotroller {
	public MyViewPagerCotroller(Activity activity) {
	};

	public abstract View getView();// viewpager获取view

	public abstract String getTitle();// viewpager获取标题名称

	public abstract void onshow();// 显示界面触发事件

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}
}
