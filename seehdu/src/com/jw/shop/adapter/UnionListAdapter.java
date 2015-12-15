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
import com.jw.shop.model.Union;

public class UnionListAdapter extends BaseAdapter {
	private Context mContext;

	private List<Union> mUnionList; // 新闻列表
	private LayoutInflater mInflater = null;

	public UnionListAdapter(Context context, List<Union> newsList) {

		mUnionList = newsList;
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mUnionList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mUnionList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<Union> list) {
		Log.i("BXTNewsAdapter", "Adapter刷新数据");
		mUnionList = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final UnionListHolder listHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_union, null);
			listHolder = new UnionListHolder();
			listHolder.name = (TextView) convertView
					.findViewById(R.id.tx_union_name);
			listHolder.content = (TextView) convertView
					.findViewById(R.id.tx_union_content);
			listHolder.belong = (TextView) convertView
					.findViewById(R.id.tx_union_belong);
			listHolder.rank = (TextView) convertView
					.findViewById(R.id.tx_union_rank);
			listHolder.imageview = (ImageView) convertView
					.findViewById(R.id.img_union);
			convertView.setTag(listHolder);
		} else {

			listHolder = (UnionListHolder) convertView.getTag();
		}
		listHolder.name.setText(mUnionList.get(position).getName());
		listHolder.content.setText(mUnionList.get(position).getDescribe());
		listHolder.belong.setText(mUnionList.get(position).getBelong());
		listHolder.rank.setText(mUnionList.get(position).getRank());
		//添加图片
		BmobFile bm = mUnionList.get(position).getImage();
		bm.loadImage(mContext, 	listHolder.imageview);
		return convertView;
	}



	protected static void startActivity(Intent intent) {

	}

	protected class UnionListHolder {
		private TextView name;
		private TextView content;
		private TextView belong;
		private TextView rank;
		private ImageView imageview;
	}
}