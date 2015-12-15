package com.stone.shop.thirdview;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.jw.seehdu.R;
import com.jw.shop.model.Lost;
import com.jw.shop.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author w
 *失物招领发布页面
 */
public class LostAdd extends Activity implements OnClickListener {
	private Button loback, lofb;
	private EditText edds, edcon;
	private TextView txtype, txtime;
	private Spinner spinner;
	private static final String m[] = { "启事类型", "寻物启事", "失物招领" };
	private ArrayAdapter<String> adapter;
	private String time = null;
	private String describe = null;
	private String connection = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lost_add);

		initview();
	}

	private void initview() {
		// TODO Auto-generated method stub
		edds = (EditText) findViewById(R.id.tv_lost_ds);
		edcon = (EditText) findViewById(R.id.tv_lost_con);
		txtime = (TextView) findViewById(R.id.tv_lost_time);
		txtype = (TextView) findViewById(R.id.tv_lost_type);
		spinner = (Spinner) findViewById(R.id.spinner1);
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日    HH:mm:ss     ");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		txtime.setText(str);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				txtype.setText(m[arg2]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		loback = (Button) findViewById(R.id.btnback);
		lofb = (Button) findViewById(R.id.btn_fb);
		loback.setOnClickListener(this);
		lofb.setOnClickListener(this);
	}

	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btnback:
			finish();
			break;
		case R.id.btn_fb:
			describe = edds.getText().toString();
			connection = edcon.getText().toString();

			time = txtime.getText().toString();
			User author = BmobUser.getCurrentUser(this, User.class);
			Lost lonews = new Lost();
			if (time.equals("")) {
				toast("请输入时间名称哦！");
				break;
			} else if (describe.equals("")) {
				toast("请输入详细信息哦！");
				break;
			} else if (connection.equals("")) {
				toast("请输入联系方式！");
				break;
			} else {
				lonews.setTime(time);
				lonews.setDescribe(describe);
				lonews.setConnect(connection);
				lonews.setAuthor(author);

				lonews.save(this, new SaveListener() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						LostAdd.this.finish();
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
					}
				});

				break;

				// }
			}
		}
	}
}
