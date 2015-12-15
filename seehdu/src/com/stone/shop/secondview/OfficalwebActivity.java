package com.stone.shop.secondview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.jw.seehdu.R;

/**
 * 
 * 杭电官网
 */
public class OfficalwebActivity extends Activity {

	@SuppressWarnings("unused")
	private static final String TAG = "CarActivity";

	private static final String URL_WSQ = "http:\\m.hdu.edu.cn";
	private WebView wsqWebView;
	private Button officalweb_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_officalweb);

		wsqWebView = (WebView) findViewById(R.id.wv_wsq);
		officalweb_back = (Button) findViewById(R.id.officalweb_back);
		officalweb_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}
		});
		// -----------------------------------------------------------------

		wsqWebView.getSettings().setJavaScriptEnabled(true); // 设置使用够执行JS脚本
		 wsqWebView.getSettings().setBuiltInZoomControls(true); // 设置使支持缩放
		wsqWebView.getSettings().setDefaultFontSize(12);
		wsqWebView.setWebChromeClient(new WebChromeClient());
		wsqWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);// 使用当前WebView处理跳转
				return true;// true表示此事件在此处被处理，不需要再广播
			}

			@Override
			// 转向错误时的处理
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(OfficalwebActivity.this,
						"Oh no! " + description, Toast.LENGTH_SHORT).show();
			}
		});
		wsqWebView.loadUrl(URL_WSQ);

	}

}
