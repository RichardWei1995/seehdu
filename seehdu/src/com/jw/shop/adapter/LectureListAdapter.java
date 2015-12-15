package com.jw.shop.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jw.seehdu.R;
import com.jw.shop.model.Lecture;

public class LectureListAdapter extends BaseAdapter {
	private Context mContext;

	private List<Lecture> mLecList; // 新闻列表
	private LayoutInflater mInflater = null;

	public LectureListAdapter(Context context, List<Lecture> newsList) {

		mLecList = newsList;
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mLecList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mLecList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<Lecture> list) {
		mLecList = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final LecListHolder listHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_lecture, null);
			listHolder = new LecListHolder();
			listHolder.name = (TextView) convertView
					.findViewById(R.id.tx_lec_name);
			listHolder.teacher = (TextView) convertView
					.findViewById(R.id.tx_lec_teacher);
			listHolder.time = (TextView) convertView
					.findViewById(R.id.tx_lec_time);
			listHolder.place = (TextView) convertView
					.findViewById(R.id.tx_lec_place);
			
			convertView.setTag(listHolder);
		} else {

			listHolder = (LecListHolder) convertView.getTag();
		}

		listHolder.name.setText(mLecList.get(position).getName());
		listHolder.teacher.setText(mLecList.get(position).getTeacher());
		listHolder.time.setText(mLecList.get(position).getTime());
		listHolder.place.setText(mLecList.get(position).getPlace());
		return convertView;
	}

	protected class LecListHolder {
		private TextView name;
		private TextView teacher;
		private TextView time;
		private TextView place;
	}
}
