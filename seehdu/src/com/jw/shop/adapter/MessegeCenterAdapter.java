package com.jw.shop.adapter;

import java.util.List;

import com.jw.seehdu.R;
import com.jw.shop.model.MessegeCenter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessegeCenterAdapter extends BaseAdapter {
	private Context mContext;
	private List<MessegeCenter> mCenters;
	private LayoutInflater mInflater = null;

	public MessegeCenterAdapter(Context context, List<MessegeCenter> list) {
		mCenters = list;
		mContext = context;
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCenters.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mCenters.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<MessegeCenter> list) {
		Log.i("MessegeAdapter", "Adapter刷新数据");
		mCenters = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View conconvertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder viewHolder;
		if (conconvertView == null) {
			conconvertView = mInflater.inflate(R.layout.item_messegecenter,
					null);
			viewHolder = new ViewHolder();
			viewHolder.txAuthor = (TextView) conconvertView
					.findViewById(R.id.tv_messege_author);
			viewHolder.txContent = (TextView) conconvertView
					.findViewById(R.id.tv_messege_content);
			viewHolder.txTime = (TextView) conconvertView
					.findViewById(R.id.tv_messege_time);

			conconvertView.setTag(viewHolder);
		} else {

			viewHolder = (ViewHolder) conconvertView.getTag();
		}

		viewHolder.txAuthor.setText(mCenters.get(position).getAuthor());
		viewHolder.txContent.setText(mCenters.get(position).getContent());
		viewHolder.txTime.setText(mCenters.get(position).getTime());

		return conconvertView;
	}

	private class ViewHolder {
		private TextView txAuthor, txContent, txTime;

	}

}
