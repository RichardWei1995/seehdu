package com.stone.shop.secondview;

import cn.bmob.v3.listener.SaveListener;

import com.jw.seehdu.R;
import com.jw.shop.model.Hr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HrAddActivity extends Activity {

	private TextView tvBack;
	private Button btnYes;
	private EditText edName, edObj, edRank, edNeed, edTime, edPlace, edBeizhu,
			edDescribe;
	private Hr hr;
	private String sName, sObj, sRank, sNeed, sBeizhu, sTime, sPlace,
			sDescribe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hr_fabu);
		initview();
		clicklistener();
	}

	private void clicklistener() {
		// TODO Auto-generated method stub
		tvBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		btnYes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				hr = new Hr();
				sName = edName.getText().toString();
				sObj = edObj.getText().toString();
				sRank = edRank.getText().toString();
				sNeed = edNeed.getText().toString();
				sBeizhu = edBeizhu.getText().toString();
				sTime = edTime.getText().toString();
				sPlace = edPlace.getText().toString();
				sDescribe = edDescribe.getText().toString();
				if (sName.equals("") || sObj.equals("") || sRank.equals("")
						|| sNeed.equals("") || sBeizhu.equals("")
						|| sPlace.equals("") || sTime.equals("")
						|| sDescribe.equals("")) {
					Toast.makeText(HrAddActivity.this, "请填写完整信息!",
							Toast.LENGTH_SHORT).show();
				} else {

					hr.setHrname(sName);
					hr.setHrdescribe(sDescribe);
					hr.setHrobj(sObj);
					hr.setHrrank(sRank);
					hr.setHrneed(sNeed);
					hr.setHrbeizhu(sBeizhu);
					hr.setHrtime(sTime);
					hr.setHrplace(sPlace);
					hr.save(HrAddActivity.this, new SaveListener() {

						@Override
						public void onSuccess() {
							// TODO Auto-generated method stub
							Toast.makeText(HrAddActivity.this, "发布成功!",
									Toast.LENGTH_SHORT).show();
							finish();
						}

						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO Auto-generated method stub

						}
					});
				}
			}
		});

	}

	private void initview() {
		tvBack = (TextView) findViewById(R.id.btn_hradd_back);
		btnYes = (Button) findViewById(R.id.btn_hradd_fly);

		edName = (EditText) findViewById(R.id.ed_hradd_name);
		edObj = (EditText) findViewById(R.id.ed_hradd_obj);
		edRank = (EditText) findViewById(R.id.ed_hradd_rank);
		edNeed = (EditText) findViewById(R.id.ed_hradd_need);
		edBeizhu = (EditText) findViewById(R.id.ed_hradd_beizhu);
		edTime = (EditText) findViewById(R.id.ed_hradd_time);
		edPlace = (EditText) findViewById(R.id.ed_hradd_place);
		edDescribe = (EditText) findViewById(R.id.ed_hradd_describe);
	}

}
