package com.stone.shop.view;

import com.jw.appsetview.FixPsdActivity;
import com.jw.seehdu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ActivityAppset extends Activity {
	private LinearLayout lnFixPsd;
	private Button btnBack;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appset);
		
		initview();
		click();
		
	}


	private void click() {
		// TODO Auto-generated method stub
		lnFixPsd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ActivityAppset.this , FixPsdActivity.class);
				startActivity(intent);
			}
		});
		
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}


	private void initview() {
		// TODO Auto-generated method stub
		lnFixPsd = (LinearLayout) findViewById(R.id.ln_appset_fixpsd);
		
		btnBack = (Button) findViewById(R.id.btn_appset_back);
		
	}
}
