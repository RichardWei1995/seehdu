package com.stone.shop.thirdview;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.listener.FindListener;

import com.jw.base.RefreshableView;
import com.jw.base.RefreshableView.PullToRefreshListener;
import com.jw.seehdu.R;
import com.jw.shop.adapter.LostListAdapter;
import com.jw.shop.model.Lost;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class LostActivity extends Activity implements OnItemClickListener,
		OnClickListener {
	private ListView lvLONews;
	private Button loAdd, loback;
	private LostListAdapter mloListAdapter;
	private List<Lost> mloNewsList;
	private RefreshableView refreshableView;
	private Dialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost);
		Bmob.initialize(this, "9051d37b931af2142c234ab121ce1979");

		initView();
		queryData();
	}

	private void initView() {
		// TODO Auto-generated method stub
		loAdd = (Button) findViewById(R.id.lo_add);
		loback = (Button) findViewById(R.id.lo_back);
		loAdd.setOnClickListener(this);
		loback.setOnClickListener(this);

		refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
		lvLONews = (ListView) findViewById(R.id.lv_lost_main);
		mloNewsList = new ArrayList<Lost>();
		mloListAdapter = new LostListAdapter(this, mloNewsList);
		lvLONews.setAdapter(mloListAdapter);
		lvLONews.setOnItemClickListener(this);
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				try {
					Thread.sleep(2000);
					requeryData();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);
	}

	private void queryData() {

		BmobQuery<Lost> query = new BmobQuery<Lost>();
		showRoundProcessDialog(LostActivity.this, R.layout.loding);
		query.order("-createdAt");
		query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);   
		query.include("author");
		query.findObjects(this, new FindListener<Lost>() {

			@Override
			public void onSuccess(List<Lost> arg0) {

				mDialog.cancel();
				if (arg0.size() > 0) {

					// 将本次查询的数据添加到bankCards中
					for (Lost bxtNews : arg0) {
						mloNewsList.add(bxtNews);
					}
					// 通知Adapter数据更新
					mloListAdapter.refresh((ArrayList<Lost>) mloNewsList);
					mloListAdapter.notifyDataSetChanged();
				} else {
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
			}
		});
	}

	private void requeryData() {

		BmobQuery<Lost> query = new BmobQuery<Lost>();
		query.order("-createdAt");
		query.include("author");
		query.findObjects(this, new FindListener<Lost>() {

			@Override
			public void onSuccess(List<Lost> arg0) {
				mloNewsList.clear();
				for (Lost reNews : arg0) {

					mloNewsList.add(reNews);
				}
				// 通知Adapter数据更新
				mloListAdapter.refresh((ArrayList<Lost>) mloNewsList);
				mloListAdapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int arg0, String arg1) {
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			final int position, long id) {
		final Intent tolostNewsActivity = new Intent(LostActivity.this,
				LostItemActivity.class);
		tolostNewsActivity.putExtra("title", mloNewsList.get(position)
				.getTitle());
		tolostNewsActivity
				.putExtra("type", mloNewsList.get(position).getType());
		tolostNewsActivity.putExtra("connect", mloNewsList.get(position)
				.getConnect());
		tolostNewsActivity.putExtra("describe", mloNewsList.get(position)
				.getDescribe());
		tolostNewsActivity
				.putExtra("time", mloNewsList.get(position).getTime());

		tolostNewsActivity.putExtra("author", mloNewsList.get(position)
				.getAuthor().getName());
		startActivity(tolostNewsActivity);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lo_add:
			Intent add = new Intent(LostActivity.this, LostAdd.class);
			startActivity(add);
			break;
		case R.id.lo_back:
			finish();
			break;
		}

	}

	public void showRoundProcessDialog(Context mContext, int layout) {
		mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.show();
		mDialog.setContentView(layout);
	}

}
