package com.stone.shop.secondview;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.jw.seehdu.R;
import com.jw.shop.model.UnionSignUp;
import com.jw.shop.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UnionSignUpActivity extends Activity {
	private TextView tvSignName;
	private Button btnBack, btnFly;
	private EditText edName, edNum, edXueyuan, edZhuanye, edPhone;
	private String sUnion , sName, sNum, sXueyuan, sZhuanye, sPhone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unionsingup);
		initview();
		clickListener();
	}

	private void initview() {
		// TODO Auto-generated method stub
		tvSignName = (TextView) findViewById(R.id.tv_unionsignup_name);
		String a = getIntent().getStringExtra("name");
		tvSignName.setText(a);

		btnBack = (Button) findViewById(R.id.union_signup_back);
		btnFly = (Button) findViewById(R.id.btn_reg_now);

		edName = (EditText) findViewById(R.id.ed_unionsign_name);
		edNum = (EditText) findViewById(R.id.ed_unionsign_num);
		edXueyuan = (EditText) findViewById(R.id.ed_unionsign_xueyuan);
		edZhuanye = (EditText) findViewById(R.id.ed_unionsign_zhuanye);
		edPhone = (EditText) findViewById(R.id.ed_unionsign_phone);
	}

	private void clickListener() {
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btnFly.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				sUnion = tvSignName.getText().toString();
				sName = edName.getText().toString();
				sNum = edNum.getText().toString();
				sXueyuan = edXueyuan.getText().toString();
				sZhuanye = edZhuanye.getText().toString();
				sPhone = edPhone.getText().toString();
				if (sName.equals("") || sNum.equals("") || sXueyuan.equals("")
						|| sZhuanye.equals("") || sPhone.equals("")) {
					Toast.makeText(UnionSignUpActivity.this, "内容不完整",
							Toast.LENGTH_SHORT).show();
				} else {
					UnionSignUp unionSignUp = new UnionSignUp();
					User author = BmobUser.getCurrentUser(UnionSignUpActivity.this, User.class);
					author.getObjectId();
					unionSignUp.setSignunion(sUnion);
					unionSignUp.setSignname(sName);
					unionSignUp.setSignnum(sNum);
					unionSignUp.setSignxueyuan(sXueyuan);
					unionSignUp.setSignzhuanye(sZhuanye);
					unionSignUp.setSignphone(sPhone);
					unionSignUp.setUser(author);
					unionSignUp.save(UnionSignUpActivity.this,
							new SaveListener() {

								@Override
								public void onSuccess() {
									// TODO Auto-generated method stub
									finish();
									Toast.makeText(UnionSignUpActivity.this,
											"提交成功！", Toast.LENGTH_SHORT).show();
								}

								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO Auto-generated method stub

								}
							});

				}

			}
		});
	}
}
