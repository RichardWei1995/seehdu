package com.stone.shop.secondview;


import com.jw.seehdu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class TicketItemActivity extends Activity {
	private WebView webView;
	private TextView txName;
	private Button btnBack;
	private String sName , sContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticket_item);
		initview();
		click();

	}

	private void initview() {
		// TODO Auto-generated method stub
		btnBack = (Button) findViewById(R.id.ticketitem_back);
		webView = (WebView) findViewById(R.id.web_ticketitem);
		txName = (TextView) findViewById(R.id.tv_ticketitem_name);

		 sName = getIntent().getStringExtra("name");
		txName.setText(sName);
		

		 sContent = getIntent().getStringExtra("content");
		webView.loadDataWithBaseURL(null, sContent, "text/html", "UTF-8", null);

	}

	private void click() {
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		

	}

}
