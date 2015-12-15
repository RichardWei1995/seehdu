package com.stone.shop.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;
import cn.bmob.v3.update.UpdateStatus;

import com.jw.seehdu.R;

/**
 * author:jw
 * 
 * 软件相关
 */
public class MineSoftActivity extends Activity {
	private Button back, update;
	private TextView txvison;
	private LinearLayout introduce_seehdu, introduce_people, feedback,
			bugfeedback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft);

		initview();
		click();

	}

	private void initview() {
		// TODO Auto-generated method stub
		back = (Button) findViewById(R.id.btn_re_back);
		update = (Button) findViewById(R.id.btn_check_update);

		txvison = (TextView) findViewById(R.id.tv_vison);

		introduce_seehdu = (LinearLayout) findViewById(R.id.seehdu_introduce);
		introduce_people = (LinearLayout) findViewById(R.id.people_introduce);
		feedback = (LinearLayout) findViewById(R.id.feedback_app);
		bugfeedback = (LinearLayout) findViewById(R.id.bug_feedback);
	}

	private void click() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BmobUpdateAgent.setUpdateListener(new BmobUpdateListener() {

					@Override
					public void onUpdateReturned(int updateStatus,
							UpdateResponse updateInfo) {
						if (updateStatus == UpdateStatus.Yes) {// 版本有更新

						} else if (updateStatus == UpdateStatus.No) {
							Toast.makeText(MineSoftActivity.this, "版本无更新",
									Toast.LENGTH_SHORT).show();
						} else if (updateStatus == UpdateStatus.IGNORED) {
							Toast.makeText(MineSoftActivity.this, "该版本已被忽略更新",
									Toast.LENGTH_SHORT).show();
						} else if (updateStatus == UpdateStatus.TimeOut) {
							Toast.makeText(MineSoftActivity.this, "查询出错或查询超时",
									Toast.LENGTH_SHORT).show();
						}
					}

				});
				// 发起自动更新
				BmobUpdateAgent.update(MineSoftActivity.this);
			}
		});

		feedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MineSoftActivity.this,
						FeedBackActivity.class);
				startActivity(intent);
			}
		});

	}

	public void clickSoftBack(View v) {
		finish();
	}

}
