package com.stone.shop.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.jw.seehdu.R;
import com.jw.shop.model.DalyNews;
import com.jw.shop.model.NewsdalyComment;
import com.jw.shop.model.User;
import com.stone.util.SystemStatusManager;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author w 主页新闻详情
 * 
 */
public class NewsDaylyActivity extends Activity implements OnClickListener {
	private static final String APP_ID = "wx9622f3e3368d5c25";
	private IWXAPI api;

	private TextView txTitle, txTime, txFrom, tvSeenum;
	private LinearLayout layout_content;
	private Button back, btn_newscommentadd, btnShare;
	private EditText ed_newscomment;
	private WebView webView;
	private User cur_user;
	private int count = 0;
	private String stitle, sdescribe, surl, a, id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsdaly);

		// 标题栏统一：libs：nineoldandroids ， utils:screen , system
		setTranslucentStatus();

		initview();
		querydata();


	}

	/**
	 * 标题栏统一
	 */
	private void setTranslucentStatus() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window win = getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			winParams.flags |= bits;
			win.setAttributes(winParams);
		}
		SystemStatusManager tintManager = new SystemStatusManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(0);
	}

	private void initview() {
		// TODO Auto-generated method stub
		txTitle = (TextView) findViewById(R.id.title);
		txTime = (TextView) findViewById(R.id.time);
		txFrom = (TextView) findViewById(R.id.from);
		tvSeenum = (TextView) findViewById(R.id.tv_newsdaly_seenum);

		back = (Button) findViewById(R.id.btn_back);
		btnShare = (Button) findViewById(R.id.btn_newsdayly_share);

		webView = (WebView) findViewById(R.id.webView1);
		layout_content = (LinearLayout) findViewById(R.id.ln_content);

		btn_newscommentadd = (Button) findViewById(R.id.btn_newsdalycomment_add);
		ed_newscomment = (EditText) findViewById(R.id.ed_newsdalycomment_comment);

		back.setOnClickListener(this);
		btn_newscommentadd.setOnClickListener(this);
		btnShare.setOnClickListener(this);

		txTitle.setText(getIntent().getStringExtra("title"));
		txTime.setText(getIntent().getStringExtra("time"));
		txFrom.setText(getIntent().getStringExtra("from"));
		tvSeenum.setText(getIntent().getStringExtra("seenum"));

		a = getIntent().getStringExtra("content");
		webView.loadDataWithBaseURL(null, a, "text/html", "UTF-8", null);
		webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		webView.getSettings().setDomStorageEnabled(true);

		// 微信注册
		api = WXAPIFactory.createWXAPI(this, APP_ID);
		api.registerApp(APP_ID);

		stitle = getIntent().getStringExtra("title");
		surl = getIntent().getStringExtra("url");
		
		


	}

	private void querydata() {
		BmobQuery<NewsdalyComment> query = new BmobQuery<NewsdalyComment>();
		DalyNews dalyNews = new DalyNews();
		String i = getIntent().getStringExtra("id");
		dalyNews.setObjectId(i);
		query.addWhereEqualTo("dalyNews", new BmobPointer(dalyNews));
		query.findObjects(this, new FindListener<NewsdalyComment>() {

			@Override
			public void onSuccess(List<NewsdalyComment> object) {
				// TODO Auto-generated method stub
				for (NewsdalyComment content : object) {
					String c = content.getComment();
					String name = content.getCommentname();
					TextView tx = new TextView(NewsDaylyActivity.this);
					tx.setTextSize(15);
					tx.setText("用户：" + name + "\n" + c);
					layout_content.addView(tx);
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(NewsDaylyActivity.this, "服务器暂未响应！",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_newsdalycomment_add:
			addComment();
			break;
		case R.id.btn_newsdayly_share:
			// 启动微信
			// Toast.makeText(this, String.valueOf(api.openWXApp()),
			// Toast.LENGTH_LONG)
			// .show();
			// 发送文本到朋友圈
			//
			// String text = "application test";
			// WXTextObject textObj = new WXTextObject();
			// textObj.text = text;
			//
			// WXMediaMessage msg = new WXMediaMessage(textObj);
			// msg.mediaObject = textObj;
			// msg.description = text;
			//
			// SendMessageToWX.Req req = new SendMessageToWX.Req();
			// req.transaction = String.valueOf(System.currentTimeMillis());
			// req.message = msg;
			// req.scene = SendMessageToWX.Req.WXSceneTimeline;
			//
			// api.sendReq(req);

			// Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
			// R.drawable.ic_seehdu);
			// WXImageObject imgobj = new WXImageObject(bitmap);
			// WXMediaMessage msg = new WXMediaMessage();
			// msg.mediaObject = imgobj;
			// Bitmap thumbitmap = Bitmap.createScaledBitmap(bitmap, 120, 150,
			// true);
			// bitmap.recycle();
			// msg.thumbData = bmpToArray(thumbitmap, true);
			// SendMessageToWX.Req req = new SendMessageToWX.Req();
			// req.transaction = String.valueOf(System.currentTimeMillis());
			// req.message = msg;
			// req.scene = SendMessageToWX.Req.WXSceneTimeline;
			// api.sendReq(req);
			//
			WXWebpageObject webpage = new WXWebpageObject();
			webpage.webpageUrl = surl;
			WXMediaMessage msg = new WXMediaMessage(webpage);
			msg.title = stitle;
			msg.description = "application test";
			msg.mediaObject = webpage;
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_seehdu);
			Bitmap thumbitmap = Bitmap.createScaledBitmap(bitmap, 120, 150,
					true);
			bitmap.recycle();
			msg.thumbData = bmpToArray(thumbitmap, true);
			SendMessageToWX.Req req = new SendMessageToWX.Req();
			req.transaction = String.valueOf(System.currentTimeMillis());
			req.message = msg;
			req.scene = SendMessageToWX.Req.WXSceneTimeline;

			api.sendReq(req);

		}
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

	private void addComment() {
		NewsdalyComment comment = new NewsdalyComment();
		DalyNews dalyNews = new DalyNews();
		cur_user = BmobUser.getCurrentUser(this, User.class);
		String getid = getIntent().getStringExtra("id");
		String user_name = cur_user.getUsername();
		TextView tx = new TextView(this);
		tx.setTextSize(15);
		tx.setPadding(5, 15, 5, 7);
		String content_c = ed_newscomment.getText().toString();
		tx.setText("用户：" + user_name + "\n" + content_c);
		dalyNews.setObjectId(getid);
		comment.setComment(content_c);
		comment.setDalyNews(dalyNews);
		comment.setCommenter(cur_user);
		comment.setCommentname(user_name);
		comment.save(this, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(NewsDaylyActivity.this, "评论已添加",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(NewsDaylyActivity.this, "服务器暂未响应！",
						Toast.LENGTH_SHORT).show();
			}
		});

		layout_content.addView(tx);

	}

	@Override
	public void onContentChanged() {
		// TODO Auto-generated method stub
		super.onContentChanged();
		count++;
	}
}
