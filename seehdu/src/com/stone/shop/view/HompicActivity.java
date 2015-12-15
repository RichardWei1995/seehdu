package com.stone.shop.view;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.GetListener;

import com.jw.seehdu.R;
import com.jw.shop.model.LoadPic;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class HompicActivity extends Activity{
         private ImageView home_pic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepic);
		initviews();

}
	private void initviews() {
		// TODO Auto-generated method stub
		home_pic = (ImageView) findViewById(R.id.pic_home);
		BmobQuery<LoadPic> bq = new BmobQuery<LoadPic>();
		bq.getObject(this,"lH7k888F", new GetListener<LoadPic>() {

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(LoadPic arg0) {
				// TODO Auto-generated method stub
				BmobFile f = arg0.getHomepic();
				f.loadImage(HompicActivity. this, home_pic);
			}
		});
		
		
	}
}