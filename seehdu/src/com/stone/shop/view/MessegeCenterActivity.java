package com.stone.shop.view;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

import com.jw.seehdu.R;
import com.jw.shop.adapter.MessegeCenterAdapter;
import com.jw.shop.model.MessegeCenter;
import com.jw.shop.model.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author jiangwei
 * @category个人中心消息
 */
public class MessegeCenterActivity extends Activity implements
		OnItemClickListener , OnItemLongClickListener{
	private ListView lvMain;
	private TextView mtv;
	private Button btnBack;
	private List<MessegeCenter> list;
	private MessegeCenterAdapter messegeCenterAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messegecenter);

		initview();
		click();
		querydata();

	}

	private void querydata() {
		// TODO Auto-generated method stub
		BmobQuery<MessegeCenter> query = new BmobQuery<MessegeCenter>();
		User author1 = BmobUser.getCurrentUser(this, User.class);
		query.addWhereEqualTo("user", author1);
		query.order("-createdAt");
		query.findObjects(this, new FindListener<MessegeCenter>() {

			@Override
			public void onSuccess(List<MessegeCenter> object) {

				if (object.size() > 0) {
					for (MessegeCenter center : object) {
						list.add(center);
					}
					messegeCenterAdapter
							.refresh((ArrayList<MessegeCenter>) list);
					messegeCenterAdapter.notifyDataSetChanged();
				}

			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});
		
		BmobQuery<User> bmobQuery = new BmobQuery<User>();
		User author = BmobUser.getCurrentUser(this, User.class);
		bmobQuery.include("messege");
        final String id = author.getObjectId();
        bmobQuery.getObject(MessegeCenterActivity.this, id, new GetListener<User>() {
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MessegeCenterActivity.this, "failure!", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onSuccess(User arg0) {
				// TODO Auto-generated method stub
				String a = arg0.getMessege().getContent();
				mtv.setText(a);
				
				
			}
		});
	}

	private void initview() {
		// TODO Auto-generated method stub
		lvMain = (ListView) findViewById(R.id.lv_messegecenter);

		btnBack = (Button) findViewById(R.id.btn_messege_back);
		
		mtv = (TextView) findViewById(R.id.tv_messege_try);

		list = new ArrayList<MessegeCenter>();
		messegeCenterAdapter = new MessegeCenterAdapter(this, list);
		lvMain.setAdapter(messegeCenterAdapter);
		lvMain.setOnItemClickListener(this);

	}

	private void click() {
		// TODO Auto-generated method stub
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {

		new AlertDialog.Builder(this).setTitle("消息").setMessage(
				list.get(position).getContent()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).show();

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		lvMain.removeView(view);
		
		
		return false;
	}

}
