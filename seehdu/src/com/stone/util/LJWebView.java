package com.stone.util;


import com.jw.seehdu.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class LJWebView extends RelativeLayout{
	
	public static int Circle = 0x01;
	public static int Horizontal = 0x02;
	
	private Context context;
	
	private WebView mWebView = null;  
	private ProgressBar progressBar = null;  
	private int barHeight = 8; 
	private boolean isAdd = false; 
	private int progressStyle = Horizontal; 
	

	
	public LJWebView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	public LJWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	public LJWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}
	
	private void init(){
		mWebView = new WebView(context);
		this.addView(mWebView, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		
		mWebView.setWebChromeClient(new WebChromeClient(){

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				if(newProgress == 100){
					if(progressStyle == Horizontal){
						progressBar.setVisibility(View.GONE);
					}
				}else{
					if(!isAdd){
						if(progressStyle == Horizontal){
							progressBar = (ProgressBar) LayoutInflater.from(context).inflate(R.layout.progress_horizontal, null);
							progressBar.setMax(100);
							progressBar.setProgress(0);
							LJWebView.this.addView(progressBar, LayoutParams.FILL_PARENT, barHeight);
						}
						isAdd = true;
					}
					
					if(progressStyle == Horizontal){
						progressBar.setVisibility(View.VISIBLE);
						progressBar.setProgress(newProgress);
					}
				}
			}
		});
	}
	
	public void setBarHeight(int height){
		barHeight = height;
	}
	
	public void setProgressStyle(int style){
		progressStyle = style;
	}
	
	public void setClickable(boolean value){
		mWebView.setClickable(value);
	}
	
	public void setUseWideViewPort(boolean value){
		mWebView.getSettings().setUseWideViewPort(value);
	}
	
	public void setSupportZoom(boolean value){
		mWebView.getSettings().setSupportZoom(value);
	}
	
	public void setBuiltInZoomControls(boolean value){
		mWebView.getSettings().setBuiltInZoomControls(value);
	}
	
	public void setJavaScriptEnabled(boolean value){
		mWebView.getSettings().setJavaScriptEnabled(value);
	}
	
	public void setCacheMode(int value){
		mWebView.getSettings().setCacheMode(value);
	}

	public void setWebViewClient(WebViewClient value){
		mWebView.setWebViewClient(value);
	}
	
	public void loadUrl(String url){
		mWebView.loadUrl(url);
	}
	
	public void back(){
		mWebView.goBack();
	}
	
}
