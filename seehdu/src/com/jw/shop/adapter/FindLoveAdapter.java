package com.jw.shop.adapter;

import java.util.List;

import com.jw.seehdu.R;
import com.jw.shop.model.Love;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FindLoveAdapter extends BaseAdapter implements OnClickListener {

	@SuppressWarnings("unused")
	private Context mContext;

	private List<Love> mNewsList;
	private LayoutInflater mInflater = null;

	public FindLoveAdapter(Context context, List<Love> newsList) {

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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		FindLoverHolder newsHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.find_love_item, null);
			newsHolder = new FindLoverHolder();
			newsHolder.to = (TextView) convertView.findViewById(R.id.to);
			newsHolder.from = (TextView) convertView.findViewById(R.id.from);
			newsHolder.content_love = (TextView) convertView.findViewById(R.id.love_content);
			newsHolder.love_item = (LinearLayout) convertView.findViewById(R.id.ln_love_item);
			
			
			convertView.setTag(newsHolder);
		} else {

			newsHolder = (FindLoverHolder) convertView.getTag();
		}

		newsHolder.to.setText(mNewsList.get(position).getToname());
		newsHolder.from.setText(mNewsList.get(position).getFromname());
		newsHolder.content_love.setText(mNewsList.get(position).getLove());
		switch(position){
		case 0 :
			newsHolder.love_item.setBackgroundResource(R.drawable.paper_new_boy);
		case 3 :
			newsHolder.love_item.setBackgroundResource(R.drawable.paper_new_girl);
		
		}
		return convertView;
	}

	// 刷新列表中的数据
	public void refresh(List<Love> list) {
		Log.i("BXTNewsAdapter", "Adapter刷新数据");
		mNewsList = list;
		notifyDataSetChanged();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
