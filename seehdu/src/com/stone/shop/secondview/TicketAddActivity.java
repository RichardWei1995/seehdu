package com.stone.shop.secondview;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.jw.base.RefreshableView;
import com.jw.base.RefreshableView.PullToRefreshListener;
import com.jw.seehdu.R;
import com.jw.shop.adapter.TicketAdapter;
import com.jw.shop.model.Ticket;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class TicketAddActivity extends Activity implements OnItemClickListener {
	private Button back;
	private ListView lvTicket;
	private List<Ticket> mTicketList;
	private TicketAdapter ticketAdapter;
	private RefreshableView refreshableView;
	private Dialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticketadd);
		Bmob.initialize(this, "9051d37b931af2142c234ab121ce1979");
		initview();
		queryData();
		requeryData();
	}

	private void initview() {
		back = (Button) findViewById(R.id.btn_ticket_back);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		refreshableView = (RefreshableView) findViewById(R.id.ticket_refreshable_view);
		lvTicket = (ListView) findViewById(R.id.lv_ticket_main);
		mTicketList = new ArrayList<Ticket>();
		ticketAdapter = new TicketAdapter(this, mTicketList);
		lvTicket.setAdapter(ticketAdapter);
		lvTicket.setOnItemClickListener(this);

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
		showRoundProcessDialog(TicketAddActivity.this, R.layout.loding);
		BmobQuery<Ticket> query = new BmobQuery<Ticket>();
		query.order("-createdAt");
		query.findObjects(this, new FindListener<Ticket>() {

			@Override
			public void onSuccess(List<Ticket> arg0) {
				mDialog.cancel();
				if (arg0.size() > 0) {

					// 将本次查询的数据添加到bankCards中
					for (Ticket bxtNews : arg0) {
						mTicketList.add(bxtNews);
					}
					// 通知Adapter数据更新
					ticketAdapter.refresh((ArrayList<Ticket>) mTicketList);
					ticketAdapter.notifyDataSetChanged();
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
			}
		});

	}

	private void requeryData() {

		BmobQuery<Ticket> query = new BmobQuery<Ticket>();
		query.order("-createdAt");
		query.findObjects(this, new FindListener<Ticket>() {

			@Override
			public void onSuccess(List<Ticket> arg0) {
				mTicketList.clear();
				for (Ticket reNews : arg0) {

					mTicketList.add(reNews);
				}
				// 通知Adapter数据更新
				ticketAdapter.refresh((ArrayList<Ticket>) mTicketList);
				ticketAdapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int arg0, String arg1) {
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			final int position, long id) {
		// TODO Auto-generated method stub
		final Intent toticketitemActivity = new Intent(TicketAddActivity.this,
				TicketItemActivity.class);
		toticketitemActivity.putExtra("name", mTicketList.get(position).getTicketName());
		toticketitemActivity.putExtra("describe", mTicketList.get(position).getContent());
		startActivity(toticketitemActivity);

	}

	public void showRoundProcessDialog(Context mContext, int layout) {
		mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.show();
		mDialog.setContentView(layout);
	}
}
