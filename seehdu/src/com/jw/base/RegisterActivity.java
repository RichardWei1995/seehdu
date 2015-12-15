package com.jw.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;

import com.jw.seehdu.R;
import com.jw.shop.model.MessegeCenter;
import com.jw.shop.model.User;
import com.stone.shop.view.RegesterAfterActivity;
import com.stone.util.Util;

/**
 * 注册界面
 * 
 */
public class RegisterActivity extends Activity implements OnClickListener ,OnTouchListener{

	@SuppressWarnings("unused")
	private static final String TAG = "RegisterActivity";

	private TextView btnReg, btncancle;
	private EditText etUsername;
	private EditText etPassword;
	private EditText etComfirmPsd;
	private EditText etPhone;
	private EditText etstunum;
	private EditText etname;

	private String username = null;
	private String password = null;
	private String comfirmPsd = null;
	private String phone = null;
	private String stunum = null;
	private String name = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		etComfirmPsd = (EditText) findViewById(R.id.et_comfirm_psd);
		etPhone = (EditText) findViewById(R.id.et_phone);
		etstunum = (EditText) findViewById(R.id.et_studynum);
		etname = (EditText) findViewById(R.id.et_name);

		btnReg = (TextView) findViewById(R.id.btn_reg_now);
		btncancle = (TextView) findViewById(R.id.tx_cancle);

		initfocus();
	

		btnReg.setOnClickListener(this);
	}

	private void initfocus() {
		// TODO Auto-generated method stub
		etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocused) {
                ImageView imageView = (ImageView) findViewById(R.id.img_user);
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
                ImageView imageView = (ImageView) findViewById(R.id.img_passward);
                if (isFocused){
                    // 焦点
                    imageView.setImageResource(R.drawable.login_icon_password_f);
                }else{
                    // 失去焦点
                    imageView.setImageResource(R.drawable.login_icon_password);
                }
            }
        });
		etComfirmPsd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocused) {
                ImageView imageView = (ImageView) findViewById(R.id.img_comfirm_psd);
                if (isFocused){
                    // 焦点
                    imageView.setImageResource(R.drawable.login_icon_password_f);
                }else{
                    // 失去焦点
                    imageView.setImageResource(R.drawable.login_icon_password);
                }
            }
        });
		etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocused) {
                ImageView imageView = (ImageView) findViewById(R.id.img_phone);
                if (isFocused){
                    // 焦点
                    imageView.setImageResource(R.drawable.login_icon_tel_f);
                }else{
                    // 失去焦点
                    imageView.setImageResource(R.drawable.login_icon_tel);
                }
            }
        });
		etstunum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocused) {
                ImageView imageView = (ImageView) findViewById(R.id.img_studynum);
                if (isFocused){
                    // 焦点
                    imageView.setImageResource(R.drawable.login_icon_stunum_f);
                }else{
                    // 失去焦点
                    imageView.setImageResource(R.drawable.login_icon_stunum);
                }
            }
        });
		etname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocused) {
                ImageView imageView = (ImageView) findViewById(R.id.img_name);
                if (isFocused){
                    // 焦点
                    imageView.setImageResource(R.drawable.login_icon_name_f);
                }else{
                    // 失去焦点
                    imageView.setImageResource(R.drawable.login_icon_name);
                }
            }
        });

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg_now:
			username = etUsername.getText().toString();
			password = etPassword.getText().toString();
			comfirmPsd = etComfirmPsd.getText().toString();
			phone = etPhone.getText().toString();
			stunum = etstunum.getText().toString();
			name = etname.getText().toString();
			if (!Util.isNetworkConnected(this)) {
				toast("亲, 木有网络");
			} else if (username.equals("") || password.equals("")
					|| comfirmPsd.equals("") || phone.equals("")) {
				toast("亲, 不填完整, 不能拿到身份证, ~~~~(>_<)~~~~ ");
			} else if (!comfirmPsd.equals(password)) {
				toast("亲,你手抖了下,两次密码输入不一致!");
			} else if (!Util.isPhoneNumberValid(phone)) {
				toast("亲, 请输入正确的手机号码!");
			} else if (stunum.length() != 8) {
				toast("亲，你输入的学号不正确哦！");
			} else {
				// 开始提交注册信息
				User bu = new User();
				MessegeCenter center = new MessegeCenter();
				center.setObjectId("4rUPKKKh");
				bu.setUsername(username);
				bu.setPassword(password);
				bu.setPhone(phone);
				bu.setName(name);
				bu.setStunum(stunum);
				bu.setMessege(center);
				bu.signUp(this, new SaveListener() {
					@Override
					public void onSuccess() {
						Intent backLogin = new Intent(RegisterActivity.this,
								RegesterAfterActivity.class);
						startActivity(backLogin);
						RegisterActivity.this.finish();
					}

					@Override
					public void onFailure(int arg0, String msg) {
						toast("亲, 被人捷足先登了, 换个名字吧.");
					}

				});
			}
			break;
		case R.id.tx_cancle:
			Intent intent = new Intent(this ,LoginActivity.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}

	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	};

}
