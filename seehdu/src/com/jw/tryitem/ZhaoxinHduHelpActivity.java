package com.jw.tryitem;

import com.jw.seehdu.R;
import com.stone.util.LJWebView;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class ZhaoxinHduHelpActivity extends Activity {

	private LJWebView mLJWebView = null;
	private Button btnBack;
	private static final String URL_WSQ = "http://zhaoxin.hduhelp.com/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_zhaoxinhduhelp);

		mLJWebView = (LJWebView) findViewById(R.id.zhaoxin_web);
		btnBack = (Button) findViewById(R.id.btn_zhaoxin_back);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		mLJWebView.setBarHeight(8);
		mLJWebView.setClickable(true);
		mLJWebView.setUseWideViewPort(true);
		mLJWebView.setSupportZoom(true);
		mLJWebView.setBuiltInZoomControls(true);
		mLJWebView.setJavaScriptEnabled(true);
		mLJWebView.setCacheMode(WebSettings.LOAD_NO_CACHE);
		mLJWebView.setWebViewClient(new WebViewClient() {

			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				view.loadUrl(url);
				return true;
			}
		});

		mLJWebView.loadUrl(URL_WSQ);

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		   if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
			   mLJWebView.back();
	  
	        }
		
		return false;
	}
	

}
