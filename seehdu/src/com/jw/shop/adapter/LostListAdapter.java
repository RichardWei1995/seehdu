package com.jw.shop.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.bmob.v3.BmobQuery;

import com.jw.seehdu.R;
import com.jw.shop.model.Lost;

/**
 * @author w 失物招领界面适配器
 */
public class LostListAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context mContext;

	private List<Lost> mNewsList; // 新闻列表
	private LayoutInflater mInflater = null;

	public LostListAdapter(Context context, List<Lost> newsList) {

		mNewsList = newsList;
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mNewsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mNewsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<Lost> list) {
		Log.i("BXTNewsAdapter", "Adapter刷新数据");
		mNewsList = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final LostNewsHolder newsHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.lost_item, null);
			newsHolder = new LostNewsHolder();
			newsHolder.type = (TextView) convertView
					.findViewById(R.id.tv_lostitem_type);
			newsHolder.describe = (TextView) convertView
					.findViewById(R.id.tv_lostitem_ds);
			newsHolder.time = (TextView) convertView
					.findViewById(R.id.tv_lostitem_time);
			newsHolder.connect = (TextView) convertView
					.findViewById(R.id.tv_lostitem_con);
			newsHolder.name = (TextView) convertView
					.findViewById(R.id.fromuser);
			newsHolder.lnb = (LinearLayout) convertView
					.findViewById(R.id.ln_lost_item);
			convertView.setTag(newsHolder);
		} else {

			newsHolder = (LostNewsHolder) convertView.getTag();
		}

		newsHolder.type.setText(mNewsList.get(position).getType());
		newsHolder.describe.setText(mNewsList.get(position).getDescribe());
		newsHolder.time.setText(mNewsList.get(position).getTime());
		newsHolder.connect.setText(mNewsList.get(position).getConnect());

		switch(position){
		
		case 0 :
			newsHolder.lnb.setBackgroundResource(R.drawable.card_new_found);
			
		case 2 :
			newsHolder.lnb.setBackgroundResource(R.drawable.card_new_lost);
		}
		
		
		return convertView;
	}

	protected static void startActivity(Intent intent) {
		// TODO Auto-generated method stub

	}

}
