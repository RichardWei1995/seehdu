package com.jw.base;

import java.util.Set;

import com.umeng.analytics.MobclickAgent;
import com.igexin.sdk.PushManager;
import com.jw.seehdu.R;
import com.jw.shop.model.Splash;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author w 闪屏界面
 */
@SuppressWarnings("unused")
public class SplashActivity extends Activity {

	private String user, pass;

	private ImageView imgSpash;

	private boolean isFirstIn = false;

	private static final String APPID = "9051d37b931af2142c234ab121ce1979";

	private static final long SPLASH_DELAY_MILLIS = 5000;

	private static final String SHAREDPREFERENCES_NAME = "first_pref";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MobclickAgent.updateOnlineConfig(this);
		// 初始化 Bmob SDK
		// 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
		Bmob.initialize(this, APPID);
		setContentView(R.layout.activity_splash);

		PushManager.getInstance().initialize(this.getApplicationContext());
		// 使用推送服务时的初始化操作
		BmobInstallation.getCurrentInstallation(this).save();
		// // 启动推送服务
		BmobPush.startWork(this, APPID);
		// 判断是否已经保存用户信息
		SharedPreferences spi = getSharedPreferences("UserInfo",
				this.MODE_PRIVATE);
		user = spi.getString("username", null);
		pass = spi.getString("password", null);

		BmobQuery<Splash> query = new BmobQuery<Splash>();
		query.getObject(SplashActivity.this, "aVWm333D",
				new GetListener<Splash>() {

					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						Toast.makeText(SplashActivity.this, "failed!",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onSuccess(Splash object) {
						// TODO Auto-generated method stub
						BmobFile img = object.getImg();
						img.loadImage(SplashActivity.this, imgSpash);

					}
				});

		initview();
		queryData();

	}

	private void queryData() {
		// TODO Auto-generated method stub

	}

	private void initview() {

		imgSpash = (ImageView) findViewById(R.id.img_splash);

		SharedPreferences preferences = getSharedPreferences(
				SHAREDPREFERENCES_NAME, MODE_PRIVATE);

		// 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
		isFirstIn = preferences.getBoolean("isFirstIn", true);

		// 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
		if (!isFirstIn) {
			// 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity

			if (user == null || pass == null) {
				mHandler.sendEmptyMessageDelayed(GO_Login, 1000);

			} else {

				mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);

			}

		} else {
			mHandler.sendEmptyMessageDelayed(GO_Guide, SPLASH_DELAY_MILLIS);

		}

	}

	private static final int GO_HOME = 100;
	private static final int GO_Guide = 200;
	private static final int GO_Login = 300;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case GO_HOME:
				Intent intenthome = new Intent(SplashActivity.this,
						MainActivity1.class);
				startActivity(intenthome);
				finish();
				break;
			case GO_Guide:
				Intent intent = new Intent(SplashActivity.this, GuideView.class);
				startActivity(intent);
				finish();
				break;
			case GO_Login:
				Intent intent1 = new Intent(SplashActivity.this,
						LoginActivity.class);
				startActivity(intent1);
				finish();
				break;
			}
		}
	};

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
