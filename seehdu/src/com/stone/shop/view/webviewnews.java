package com.stone.shop.view;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import com.jw.seehdu.R;
import com.jw.shop.model.DalyNews;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * @author w layout:activity_webview
 */
public class webviewnews extends Activity {
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		webView = (WebView) findViewById(R.id.webView1);
		BmobQuery<DalyNews> query = new BmobQuery<DalyNews>();
		query.getObject(this, "PJRE0001", new GetListener<DalyNews>() {
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onSuccess(DalyNews arg0) {
				// TODO Auto-generated method stub
				String a = arg0.getContent();
				webView.loadDataWithBaseURL(null, a, "text/html", "UTF-8", null);
				
			}
		});
		
		
	}
}
