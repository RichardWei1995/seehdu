package com.stone.shop.secondview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.listener.FindListener;

import com.jw.base.RefreshableView;
import com.jw.base.RefreshableView.PullToRefreshListener;
import com.jw.seehdu.R;
import com.jw.shop.adapter.UnionListAdapter;
import com.jw.shop.model.Union;

public class UnionActivity extends Activity implements OnItemClickListener,
		OnClickListener {
	private Button back;
	private ListView lvUnoin;
	private List<Union> munionList;
	private UnionListAdapter munionListAdapter;
	private RefreshableView refreshableView;
	private Dialog mDialog;
	private PopupWindow popupWindow;
	private ImageView imgMore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_union);
		Bmob.initialize(this, "9051d37b931af2142c234ab121ce1979");
		initview();
		queryData();
		requeryData();
	}

	private void initview() {
		back = (Button) findViewById(R.id.union_back);
		imgMore = (ImageView) findViewById(R.id.img_union_more);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		refreshableView = (RefreshableView) findViewById(R.id.union_refreshable_view);
		lvUnoin = (ListView) findViewById(R.id.lv_union_main);
		munionList = new ArrayList<Union>();
		munionListAdapter = new UnionListAdapter(this, munionList);
		lvUnoin.setAdapter(munionListAdapter);
		lvUnoin.setOnItemClickListener(this);
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

		imgMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				View popupWindow_view = getLayoutInflater().inflate(
						R.layout.activity_menu_union, null, false);
				LinearLayout lnTicket = (LinearLayout) popupWindow_view
						.findViewById(R.id.la_union_ticket);
				LinearLayout lnShare = (LinearLayout) popupWindow_view
						.findViewById(R.id.la_union_share);

				popupWindow = new PopupWindow(popupWindow_view, 200,
						LayoutParams.WRAP_CONTENT, true);
				popupWindow.showAsDropDown(imgMore);
				popupWindow_view.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						if (popupWindow != null && popupWindow.isShowing())
							popupWindow.dismiss();
						popupWindow = null;

						return false;
					}
				});

				lnTicket.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(UnionActivity.this,TicketAddActivity.class);
						startActivity(intent);
						finish();

					}
				});

				lnShare.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub

					}
				});

			}
		});

	}

	private void queryData() {
		showRoundProcessDialog(UnionActivity.this, R.layout.loding);
		BmobQuery<Union> query = new BmobQuery<Union>();
		query.order("-createdAt");
		//缓存数据
		query.findObjects(this, new FindListener<Union>() {

			@Override
			public void onSuccess(List<Union> arg0) {
				mDialog.cancel();
				if (arg0.size() > 0) {

					// 将本次查询的数据添加到bankCards中
					for (Union bxtNews : arg0) {
						munionList.add(bxtNews);
					}
					// 通知Adapter数据更新
					munionListAdapter.refresh((ArrayList<Union>) munionList);
					munionListAdapter.notifyDataSetChanged();
				} else {
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
			}
		});

	}

	private void requeryData() {

		BmobQuery<Union> query = new BmobQuery<Union>();
		query.order("-createdAt");
		query.findObjects(this, new FindListener<Union>() {

			@Override
			public void onSuccess(List<Union> arg0) {
				munionList.clear();
				for (Union reNews : arg0) {

					munionList.add(reNews);
				}
				// 通知Adapter数据更新
				munionListAdapter.refresh((ArrayList<Union>) munionList);
				munionListAdapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int arg0, String arg1) {
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			final int position, long id) {
		// TODO Auto-generated method stub
		final Intent tolostNewsActivity = new Intent(UnionActivity.this,
				UnionItemActivity.class);
		tolostNewsActivity.putExtra("content", munionList.get(position)
				.getContent());
		tolostNewsActivity.putExtra("name", munionList.get(position).getName());
		tolostNewsActivity.putExtra("url", munionList.get(position).getUrl());
		startActivity(tolostNewsActivity);
	}

	public void showRoundProcessDialog(Context mContext, int layout) {
		mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.show();
		mDialog.setContentView(layout);
	}

}
