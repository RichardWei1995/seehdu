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
import com.jw.shop.adapter.FindLoveAdapter;
import com.jw.shop.model.Love;
import com.stone.util.SystemStatusManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ToloveActivity extends Activity implements OnItemClickListener {

	private ListView lvBXTNews;
	private FindLoveAdapter mBxtListAdapter;
	private List<Love> mBXTNewsList;
	private Button btnAdd, btnback;
	private RefreshableView refreshableView;
	private Dialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_love);
		Bmob.initialize(this, "9051d37b931af2142c234ab121ce1979");

		// 标题栏统一：libs：nineoldandroids ， utils:screen , system
		setTranslucentStatus();

		initView();
		queryData();
		clickListener();

	}

	private void initView() {

		refreshableView = (RefreshableView) findViewById(R.id.refreshable_love);

		btnAdd = (Button) findViewById(R.id.btn_love_add);
		btnback = (Button) findViewById(R.id.btn_love_back);

		lvBXTNews = (ListView) findViewById(R.id.lv_bxt_news);
		mBXTNewsList = new ArrayList<Love>();
		mBxtListAdapter = new FindLoveAdapter(this, mBXTNewsList);
		lvBXTNews.setAdapter(mBxtListAdapter);
		lvBXTNews.setOnItemClickListener(this);
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

	private void clickListener() {
		// TODO Auto-generated method stub
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ToloveActivity.this,
						LoveAddActivity.class);
				startActivity(intent);
			}
		});
		btnback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void requeryData() {
		BmobQuery<Love> query = new BmobQuery<Love>();
		query.findObjects(this, new FindListener<Love>() {

			@Override
			public void onSuccess(List<Love> arg0) {

				mBXTNewsList.clear();
				for (Love bxtNews : arg0) {
					mBXTNewsList.add(bxtNews);
				}
				// 通知Adapter数据更新
				mBxtListAdapter.refresh((ArrayList<Love>) mBXTNewsList);
				mBxtListAdapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int arg0, String arg1) {
			}
		});
	}

	private void queryData() {
		showRoundProcessDialog(ToloveActivity.this, R.layout.loding);
		BmobQuery<Love> query = new BmobQuery<Love>();
		query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		query.findObjects(this, new FindListener<Love>() {

			@Override
			public void onSuccess(List<Love> arg0) {
				mDialog.cancel();
				if (arg0.size() > 0) {

					// 将本次查询的数据添加到bankCards中
					for (Love bxtNews : arg0) {
						mBXTNewsList.add(bxtNews);
					}
					// 通知Adapter数据更新
					mBxtListAdapter.refresh((ArrayList<Love>) mBXTNewsList);
					mBxtListAdapter.notifyDataSetChanged();
				} else {
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent toBXTNewsActivity = new Intent(ToloveActivity.this,
				ToloveItemActivity.class);
		toBXTNewsActivity.putExtra("toname", mBXTNewsList.get(position)
				.getToname());
		toBXTNewsActivity.putExtra("fromname", mBXTNewsList.get(position)
				.getFromname());
		toBXTNewsActivity
				.putExtra("love", mBXTNewsList.get(position).getLove());
		toBXTNewsActivity.putExtra("id", mBXTNewsList.get(position)
				.getObjectId());
		startActivity(toBXTNewsActivity);
	}

	public void showRoundProcessDialog(Context mContext, int layout) {
		mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.show();
		mDialog.setContentView(layout);
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

}
