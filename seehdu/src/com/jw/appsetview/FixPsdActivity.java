package com.jw.appsetview;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

import com.jw.seehdu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FixPsdActivity extends Activity {

	private EditText edpsdsure, edpsdnew;
	private Button btnBack, btnOk;
	private String spsdSure, spsdNew;
	private static final String APPID = "9051d37b931af2142c234ab121ce1979";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appset_fixpsd);
		
		Bmob.initialize(this, APPID);

		initview();
		click();

	}

	private void click() {

		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});
		

		btnOk.setOnClickListener(new OnClickListener() {

			
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				spsdSure = edpsdsure.getText().toString();
				spsdNew = edpsdnew.getText().toString();
				
				BmobUser.updateCurrentUserPassword(FixPsdActivity.this,
						spsdSure, spsdNew, new UpdateListener() {

							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								Toast.makeText(FixPsdActivity.this,
										"密码修改成功！请保管好您的密码！", Toast.LENGTH_SHORT)
										.show();
							}

							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub

								Toast.makeText(FixPsdActivity.this, "密码修改失败! \n"+arg1,
										Toast.LENGTH_SHORT).show();
								

							}
						});

			}
		});

	}

	private void initview() {
		edpsdnew = (EditText) findViewById(R.id.ed_fixpsd_new);
		edpsdsure = (EditText) findViewById(R.id.ed_fixpsd_sure);

		btnBack = (Button) findViewById(R.id.btn_fixpasd_back);
		btnOk = (Button) findViewById(R.id.btn_fixpsd_ok);

		spsdSure = edpsdsure.getText().toString();
		spsdNew = edpsdnew.getText().toString();

	}

}
