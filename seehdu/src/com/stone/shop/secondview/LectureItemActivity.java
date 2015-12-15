package com.stone.shop.secondview;

import com.jw.seehdu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class LectureItemActivity extends Activity {

	private TextView tvLecName, tvLectime, tvLecplace  ,tvLecteacher ;
	private String sLecName, sLectime, sLecplace ,sLecteacher ;
	private Button btnBack;
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lec_item);

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
		tvLecName = (TextView) findViewById(R.id.tv_lecitem_name);
		tvLectime = (TextView) findViewById(R.id.tv_lecitem_time);
		tvLecplace = (TextView) findViewById(R.id.tv_lecitem_place);
		tvLecteacher = (TextView) findViewById(R.id.tv_lecitem_teacher);
		webView = (WebView) findViewById(R.id.lec_webView);
		
		sLecName = getIntent().getStringExtra("name");
		tvLecName.setText(sLecName);
		sLectime = getIntent().getStringExtra("time");
		tvLectime.setText(sLectime);
		sLecplace = getIntent().getStringExtra("palce");
		tvLecplace.setText(sLecplace);
		sLecteacher = getIntent().getStringExtra("teacher");
		tvLecteacher.setText(sLecteacher);

		btnBack = (Button) findViewById(R.id.lecitem_back);
		String a = getIntent().getStringExtra("content");
		webView.loadDataWithBaseURL(null, a, "text/html", "UTF-8", null);

	}
}
