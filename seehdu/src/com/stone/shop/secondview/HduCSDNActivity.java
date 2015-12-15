package com.stone.shop.secondview;

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

public class HduCSDNActivity extends Activity{
	
	private LJWebView mLJWebView = null;
	private Button btnBack;
	private static final String URL_WSQ = "http://1.2013csdn.sinaapp.com/html/index.html";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hducsdn);
		initView();
	    click();
		
	}


	private void click() {
		
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				finish();
				
			}
		});
		
	}


	private void initView() {
		
		btnBack = (Button) findViewById(R.id.btn_hducsdn_back);
		mLJWebView = (LJWebView) findViewById(R.id.hducsdn_web);
		
		
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
