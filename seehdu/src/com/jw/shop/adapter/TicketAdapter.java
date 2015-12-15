package com.jw.shop.adapter;

import java.util.List;
import java.util.Random;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.jw.seehdu.R;
import com.jw.shop.model.SaveTicketList;
import com.jw.shop.model.Ticket;
import com.jw.shop.model.User;
import com.stone.shop.secondview.TicketItemActivity;

/**
 * @author jiangwei
 * @see在控件属性查找中发现了问题；
 */
public class TicketAdapter extends BaseAdapter {
	private Context mContext;

	private List<Ticket> mTicketList; // 新闻列表
	private LayoutInflater mInflater = null;
	private Integer ihasnum = null, irenum;
	private String saveuser, scheck;

	public TicketAdapter(Context context, List<Ticket> newsList) {

		mTicketList = newsList;
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTicketList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mTicketList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<Ticket> list) {
		mTicketList = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final TicketListHolder listHolder;
		if (convertView == null) {
			convertView = mInflater
					.inflate(R.layout.activity_item_ticket, null);
			listHolder = new TicketListHolder();
			listHolder.name = (TextView) convertView
					.findViewById(R.id.tv_ticket_name);
			listHolder.hasnum = (TextView) convertView
					.findViewById(R.id.tv_ticket_hasnum);
			listHolder.renum = (TextView) convertView
					.findViewById(R.id.tv_ticket_renum);
			listHolder.content = (TextView) convertView
					.findViewById(R.id.tv_ticket_content);
			listHolder.rob = (TextView) convertView.findViewById(R.id.tv_rob);
			listHolder.img = (ImageView) convertView
					.findViewById(R.id.img_ticket);

			convertView.setTag(listHolder);
		} else {

			listHolder = (TicketListHolder) convertView.getTag();
		}
		listHolder.name.setText(mTicketList.get(position).getTicketName());
		listHolder.content.setText(mTicketList.get(position).getContent());

		ihasnum = mTicketList.get(position).getTicketHaNum();
		irenum = mTicketList.get(position).getTicketReNum();

		if (ihasnum < irenum) {
			listHolder.hasnum.setText("" + ihasnum);
			listHolder.renum.setText("" + irenum);
			listHolder.rob.setClickable(true);
			listHolder.rob.setTag(position);
			listHolder.rob.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					showInfo(position);
					listHolder.rob.setText("已抢到");
					listHolder.rob.setClickable(false);
				}
			});

		} else {

			listHolder.hasnum.setText("" + irenum);
			listHolder.renum.setText("" + irenum);
			listHolder.rob.setText("-已抢完");
			listHolder.rob.setClickable(false);
		}

		BmobFile bm = mTicketList.get(position).getImg();
		bm.loadImage(mContext, listHolder.img);

		return convertView;
	}

	public void showInfo(final int position) {

		User bmobuser = BmobUser.getCurrentUser(mContext, User.class);
		saveuser = bmobuser.getName();
		scheck = bmobuser.getStunum();

		Ticket ticket = new Ticket();
		ihasnum++;
		ticket.setTicketHaNum(ihasnum);
		ticket.update(mContext, mTicketList.get(position).getObjectId(),
				new UpdateListener() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub

					}
				});
		
		//生成随机数效验码
		
		String s = "";
		Random ran = new Random(System.currentTimeMillis());
		for (int i = 0; i < 1; i++) {
			s = s + ran.nextInt(1000000);
		}
		SaveTicketList saveTicketList = new SaveTicketList();
		saveTicketList.setName(saveuser);
		saveTicketList.setCheck(scheck);
		saveTicketList.setSort(mTicketList.get(position).getTicketName());
		saveTicketList.setChecknum(s);
		saveTicketList.save(mContext, new SaveListener() {

			@Override
			public void onSuccess() {

			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});

		new AlertDialog.Builder(mContext)
				.setTitle("详情")
				.setMessage(
						"活动:" + mTicketList.get(position).getTicketName()
								+ "\n时间、地点:"
								+ mTicketList.get(position).getContent()
								+ "\n请记住您的效验码：" + s)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).show();
	}

	protected class TicketListHolder {
		private TextView name;
		private TextView hasnum;
		private TextView renum;
		private TextView content;
		private TextView rob;
		private ImageView img;
		// private ImageView imageview;
	}
}