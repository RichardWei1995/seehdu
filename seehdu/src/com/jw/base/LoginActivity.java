package com.jw.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.update.BmobUpdateAgent;

import com.jw.seehdu.R;
import com.jw.shop.model.User;
import com.stone.shop.view.ResetPsdActivity;
import com.stone.util.Util;
import com.tencent.connect.UserInfo;
import com.umeng.analytics.MobclickAgent;

/**
 * 登陆界面
 */
public class LoginActivity extends Activity implements OnClickListener {

	@SuppressWarnings("unused")
	private static final String TAG = "LoginActicity";
	private static String APPID = "9051d37b931af2142c234ab121ce1979";

	private TextView btnLogin , btnResetPsd , btnReg;
	private EditText etUsername;
	private EditText etPassword;

	private String username;
	private String password;

	private Dialog mDialog;

	@SuppressWarnings("unused")
	private static final String APP_ID = "101080318";
	@SuppressWarnings("unused")
	private TextView backInfo;
	@SuppressWarnings("unused")
	private UserInfo mInfo;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login)
		;
		MobclickAgent.updateOnlineConfig(this);
		
		
		// 初始化BmobSDK
		Bmob.initialize(this, APPID);

		btnLogin = (TextView) findViewById(R.id.btn_login);
		btnReg = (TextView) findViewById(R.id.btn_register);
		btnResetPsd = (TextView) findViewById(R.id.btn_reset_psd);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		btnLogin.setOnClickListener(this);
		btnReg.setOnClickListener(this);
		btnResetPsd.setOnClickListener(this);


		getUserInfo();
		initfocus();

	}

	private void initfocus() {
		// TODO Auto-generated method stub
		etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocused) {
                ImageView imageView = (ImageView) findViewById(R.id.img_login_user);
                if (isFocused){
                    // 焦点
                    imageView.setImageResource(R.drawable.login_icon_user_f);
                }else{
                    // 失去焦点
                    imageView.setImageResource(R.drawable.login_icon_user);
                }
            }
        });
		etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocused) {
                ImageView imageView = (ImageView) findViewById(R.id.img_login_passward);
                if (isFocused){
                    // 焦点
                    imageView.setImageResource(R.drawable.login_icon_password_f);
                }else{
                    // 失去焦点
                    imageView.setImageResource(R.drawable.login_icon_password);
                }
            }
        });
		
	}

	private void getUserInfo() {
		SharedPreferences sp = getSharedPreferences("UserInfo", 0);
		etUsername.setText(sp.getString("username", null));
		etPassword.setText(sp.getString("password", null));
	}

	// 保存用户的登陆记录
	private void saveUserInfo(String username, String password) {
		SharedPreferences sp = getSharedPreferences("UserInfo", 0);
		Editor editor = sp.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}

	//登录进度
	public void showRoundProcessDialog(Context mContext, int layout) {
		mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.show();
		mDialog.setContentView(layout);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 登陆
		case R.id.btn_login:
			username = etUsername.getText().toString();
			password = etPassword.getText().toString();
			 showRoundProcessDialog(LoginActivity.this, R.layout.login_loding);
			if (!Util.isNetworkConnected(this)) {
				toast("亲, 木有网络 ( ⊙ o ⊙ ) ");
			} else if (username.equals("") || password.equals("")) {
				toast("亲, 请输入账号和密码");
				break;
			} else {
				User bu2 = new User();
				bu2.setUsername(username);
				bu2.setPassword(password);
				bu2.login(this, new SaveListener() {
					@Override
					public void onSuccess() {
						// 保存用户信息
						saveUserInfo(username, password);
						// 跳转到主页
						Intent toHome = new Intent(LoginActivity.this,
								MainActivity1.class);
						startActivity(toHome);
						btnLogin.setClickable(false);
						mDialog.cancel();
						finish();
					}

					@Override
					public void onFailure(int arg0, String msg) {
						toast("亲, 用户名或密码错误");
						mDialog.cancel();
					}
				});
			}
			break;

		case R.id.btn_reset_psd:
			Intent toResetPsdActivity = new Intent(LoginActivity.this,
					ResetPsdActivity.class);
			startActivity(toResetPsdActivity);
			break;

		case R.id.btn_register:
			Intent toReg = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(toReg);
			
			break;
		// case R.id.new_login_btn:
		// onClickLogin();
		// break;
		default:
			break;

		}
	}

	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		}
		public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
		}
}
