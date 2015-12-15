package com.stone.shop.thirdview;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.jw.seehdu.R;
import com.jw.shop.model.Love;
import com.jw.shop.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoveAddActivity extends Activity{
	private EditText edto, edlove;
	private TextView tx_user , btn_loveadd_back;
	private User cur_user;
	public Button tv_go;
	private String toname;
	private String love;
	private String fromname;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loveadd);
		tx_user = (TextView) findViewById(R.id.tv_addlove_name);
		edto = (EditText) findViewById(R.id.ed_toname);
		edlove = (EditText) findViewById(R.id.ed_love);
		tv_go = (Button) findViewById(R.id.btn_loveadd_fly);
		btn_loveadd_back = (TextView) findViewById(R.id.btn_loveadd_back);

		querydata();
		clickListener();
	}
	private void clickListener() {
		// TODO Auto-generated method stub
		btn_loveadd_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LoveAddActivity.this.finish();
			}
		});
		tv_go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				toname = edto.getText().toString();
				love = edlove.getText().toString();
				fromname = cur_user.getName();

				Love lv = new Love();
				lv.setToname(toname);
				lv.setLove(love);
				lv.setFromname(fromname);

				lv.save(LoveAddActivity.this, new SaveListener() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						LoveAddActivity.this.finish();
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub

					}
				});

			}
		});
	}

	private void querydata() {
		cur_user = BmobUser.getCurrentUser(this, User.class);
		tx_user.setText(cur_user.getName());
	}


}
