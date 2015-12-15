package com.stone.shop.thirdview;

import java.util.List;


import com.jw.seehdu.R;
import com.jw.shop.model.Lostcomment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LostItemActivity extends Activity implements OnClickListener , OnItemClickListener{
	private TextView type;
	private TextView describe;
	private TextView connect;
	private TextView time;
	private TextView name;
	private Button back;
	private List<Lostcomment> lostcomments;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lost_news);
		initview();
	}
	
	private void initview() {
		// TODO Auto-generated method stub
		type =(TextView) findViewById(R.id.tv_lost_type);
		describe =(TextView) findViewById(R.id.tv_lost_ds);
		connect =(TextView) findViewById(R.id.tv_lost_con);
		time =(TextView) findViewById(R.id.tv_lost_time);
		name = (TextView) findViewById(R.id.tx_name);
		
		back = (Button) findViewById(R.id.lo_back);
		back.setOnClickListener(this);
		
		type.setText(getIntent().getStringExtra("type"));
		describe.setText(getIntent().getStringExtra("describe"));
		connect.setText(getIntent().getStringExtra("connect"));
		time.setText(getIntent().getStringExtra("time"));
		name.setText(getIntent().getStringExtra("author"));
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.lo_back:
			finish();
		}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
