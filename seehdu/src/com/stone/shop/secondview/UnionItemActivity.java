package com.stone.shop.secondview;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.jw.seehdu.R;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @author w 社团联合具体信息
 */
public class UnionItemActivity extends Activity {
	private WebView webView;
	private TextView tx_name;
	private Button btn_nuion_back;
	private ImageView imgMore;
	private PopupWindow popupWindow;

	private String stitle, surl;

	private static final String APP_ID = "wx9622f3e3368d5c25";
	private IWXAPI api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_union);

		imgMore = (ImageView) findViewById(R.id.img_unionitem_more);

		btn_nuion_back = (Button) findViewById(R.id.unionitem_back);
		btn_nuion_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		webView = (WebView) findViewById(R.id.web_union);
		tx_name = (TextView) findViewById(R.id.tv_unionitem_name);

		final String co = getIntent().getStringExtra("name");
		tx_name.setText(co);

		// 微信注册
		api = WXAPIFactory.createWXAPI(this, APP_ID);
		api.registerApp(APP_ID);
		stitle = getIntent().getStringExtra("name");
		surl = getIntent().getStringExtra("url");

		String a = getIntent().getStringExtra("content");
		webView.loadDataWithBaseURL(null, a, "text/html", "UTF-8", null);

		clickListener();

	}

	private void clickListener() {
		// TODO Auto-generated method stub
		imgMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				View popupWindow_view = getLayoutInflater().inflate(
						R.layout.activity_menu_unionitem, null, false);
				LinearLayout messege = (LinearLayout) popupWindow_view
						.findViewById(R.id.la_messege);
				LinearLayout see = (LinearLayout) popupWindow_view
						.findViewById(R.id.la_feedback);
				LinearLayout feedback = (LinearLayout) popupWindow_view
						.findViewById(R.id.la_see);
				messege.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {

					}
				});
				see.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub

						WXWebpageObject webpage = new WXWebpageObject();
						webpage.webpageUrl = surl;
						WXMediaMessage msg = new WXMediaMessage(webpage);
						msg.title = stitle;
						msg.description = "application test";
						msg.mediaObject = webpage;
						Bitmap bitmap = BitmapFactory.decodeResource(
								getResources(), R.drawable.ic_seehdu);
						Bitmap thumbitmap = Bitmap.createScaledBitmap(bitmap,
								120, 150, true);
						bitmap.recycle();
						msg.thumbData = bmpToArray(thumbitmap, true);
						SendMessageToWX.Req req = new SendMessageToWX.Req();
						req.transaction = String.valueOf(System
								.currentTimeMillis());
						req.message = msg;
						req.scene = SendMessageToWX.Req.WXSceneTimeline;

						api.sendReq(req);

					}
				});
				feedback.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent tolostNewsActivity = new Intent(
								UnionItemActivity.this,
								UnionSignUpActivity.class);
						tolostNewsActivity.putExtra("name", tx_name.getText()
								.toString());
						startActivity(tolostNewsActivity);

					}
				});

				popupWindow = new PopupWindow(popupWindow_view, 300,
						LayoutParams.WRAP_CONTENT, true);
				popupWindow.showAsDropDown(imgMore);
				popupWindow_view.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						if (popupWindow != null && popupWindow.isShowing())
							popupWindow.dismiss();
						popupWindow = null;

						return false;
					}
				});

			}
		});
	}

	// 二进制缩略图
	private byte[] bmpToArray(final Bitmap bitmap, final Boolean needrecycle) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
		if (needrecycle) {
			bitmap.recycle();
		}
		byte[] result = outputStream.toByteArray();
		try {
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
