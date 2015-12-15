package com.jw.shop.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bmob.v3.datatype.BmobFile;

import com.jw.seehdu.R;
import com.jw.shop.model.Hr;

public class HrListAdapter extends BaseAdapter {
	private Context mContext;

	private List<Hr> mHrList; // 新闻列表
	private LayoutInflater mInflater = null;
	

	public HrListAdapter(Context context, List<Hr> newsList) {

		mHrList = newsList;
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mHrList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mHrList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<Hr> list) {
		mHrList = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final HrListHolder listHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_hr, null);
			listHolder = new HrListHolder();
			listHolder.name = (TextView) convertView
					.findViewById(R.id.tx_hr_name);
			listHolder.content = (TextView) convertView
					.findViewById(R.id.tx_hr_content);
			listHolder.imageview = (ImageView) convertView
					.findViewById(R.id.img_hr);
			convertView.setTag(listHolder);
		} else {

			listHolder = (HrListHolder) convertView.getTag();
		}
		
		
			listHolder.name.setText(mHrList.get(position).getHrname());
			listHolder.content.setText(mHrList.get(position).getHrdescribe());
			// 添加图片
			BmobFile bm = mHrList.get(position).getHrpic();
			bm.loadImage(mContext, listHolder.imageview);
		
		

		
		return convertView;
	}

	protected class HrListHolder {
		private TextView name;
		private TextView content;
		private ImageView imageview;
	}
}