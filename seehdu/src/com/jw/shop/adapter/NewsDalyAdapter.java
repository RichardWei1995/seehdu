package com.jw.shop.adapter;

import java.util.List;

import cn.bmob.v3.listener.UpdateListener;

import com.jw.base.MainActivity1;
import com.jw.seehdu.R;
import com.jw.shop.model.DalyNews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewsDalyAdapter extends BaseAdapter {
	private Context mContext;

	private List<DalyNews> mNews;
	private LayoutInflater minflater = null;

	public NewsDalyAdapter(Context myFragementLiterature1,
			List<DalyNews> newsList) {
		mContext = myFragementLiterature1;
		mNews = newsList;
		minflater = LayoutInflater.from(myFragementLiterature1);
	}

	@Override
	public int getCount() {
		return mNews.size();
	}

	@Override
	public Object getItem(int position) {
		return mNews.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<DalyNews> list) {
		Log.i("NewsDalyAdapter", "Adapter刷新数据");
		mNews = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final NewsDalyHolder mNewsHolder;
		if (convertView == null) {
			convertView = minflater.inflate(R.layout.adapter_newsdaly, null);
			mNewsHolder = new NewsDalyHolder();
			mNewsHolder.mtvtitle = (TextView) convertView
					.findViewById(R.id.news_title);
			mNewsHolder.mtvtime = (TextView) convertView
					.findViewById(R.id.news_time);
			mNewsHolder.mtvtype = (TextView) convertView
					.findViewById(R.id.news_type);
			mNewsHolder.mtvfrom = (TextView) convertView
					.findViewById(R.id.news_from);
			convertView.setTag(mNewsHolder);
		} else {
			mNewsHolder = (NewsDalyHolder) convertView.getTag();
		}
		mNewsHolder.mtvtitle.setText(mNews.get(position).getTitle());
		mNewsHolder.mtvtime.setText(mNews.get(position).getTime());
		mNewsHolder.mtvtype.setText(mNews.get(position).getType());
		mNewsHolder.mtvfrom.setText(mNews.get(position).getFrom());

		if (mNews.get(position).getType().equals("推广")) {
			mNewsHolder.mtvtype.setBackgroundResource(R.color.caolv);
		} else if (mNews.get(position).getType().equals("新闻")) {
			mNewsHolder.mtvtype.setBackgroundResource(R.color.zise);
		} else if (mNews.get(position).getType().equals("活动")) {
			mNewsHolder.mtvtype.setBackgroundResource(R.color.juhuang);
		} else if (mNews.get(position).getType().equals("官方")) {
			mNewsHolder.mtvtype.setBackgroundResource(R.color.qianlan);
		} else if (mNews.get(position).getType().equals("通知")) {
			mNewsHolder.mtvtype.setBackgroundResource(R.color.pink);
		}


		return convertView;
	}
}
