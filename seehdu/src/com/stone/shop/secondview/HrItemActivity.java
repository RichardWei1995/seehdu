package com.stone.shop.secondview;

import com.jw.seehdu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class HrItemActivity extends Activity {

	private TextView tvHrName, tvHrObj, tvHrRank, tvHrNeed, tvHrBeizhu,
			tvHrPlace, tvHrTime;
	private String sHrName, sHrObj, sHrRank, sHrNeed, sHrBeizhu, sHrTime,
			sHrPlace , sIntroduction;
	private Button btnBack;
	
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hr_item);

		initview();
		clicklistener();
	}

	private void clicklistener() {
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	private void initview() {
		tvHrName = (TextView) findViewById(R.id.tv_hritem_name);
		tvHrObj = (TextView) findViewById(R.id.tv_hr_obj);
		tvHrRank = (TextView) findViewById(R.id.tv_hr_rank);
		tvHrNeed = (TextView) findViewById(R.id.tv_hr_need);
		tvHrBeizhu = (TextView) findViewById(R.id.tv_hr_beizhu);
		tvHrTime = (TextView) findViewById(R.id.tv_hritem_time);
		tvHrPlace = (TextView) findViewById(R.id.tv_hritem_place);
		webView = (WebView) findViewById(R.id.web_hr_item);

		sHrName = getIntent().getStringExtra("hrname");
		tvHrName.setText(sHrName);
		sHrObj = getIntent().getStringExtra("hrobj");
		tvHrObj.setText(sHrObj);
		sHrRank = getIntent().getStringExtra("hrrank");
		tvHrRank.setText(sHrRank);
		sHrNeed = getIntent().getStringExtra("hrneed");
		tvHrNeed.setText(sHrNeed);
		sHrBeizhu = getIntent().getStringExtra("hrbeizhu");
		tvHrBeizhu.setText(sHrBeizhu);
		sHrTime = getIntent().getStringExtra("hrtime");
		tvHrTime.setText(sHrTime);
		sHrPlace = getIntent().getStringExtra("hrplace");
		tvHrPlace.setText(sHrPlace);
		
		sIntroduction = getIntent().getStringExtra("introduction");
		webView.loadDataWithBaseURL(null, sIntroduction, "text/html", "UTF-8", null);
		webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		webView.getSettings().setDomStorageEnabled(true);

		btnBack = (Button) findViewById(R.id.hritem_back);

	}
}
