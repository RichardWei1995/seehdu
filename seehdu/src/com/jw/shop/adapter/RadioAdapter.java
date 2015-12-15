package com.jw.shop.adapter;

import java.util.List;

import com.jw.seehdu.R;
import com.jw.shop.model.HduPhone;
import com.jw.shop.model.Loadsong;
import com.stone.shop.fourview.PlaySong;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RadioAdapter extends BaseAdapter {

	private Context mContext;
	private List<Loadsong> mLists;
	private LayoutInflater inflater = null;
	private Boolean radio = true;

	public RadioAdapter(Context context, List<Loadsong> list) {
		mContext = context;
		mLists = list;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mLists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<Loadsong> list) {
		Log.i("HduAdapter", "Adapter刷新数据");
		mLists = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertview, ViewGroup parent) {
		// TODO Auto-generated method stub
		final RadioHolder radioHolder;
		if (convertview == null) {
			radioHolder = new RadioHolder();
			convertview = inflater.inflate(R.layout.item_radio, null);

			radioHolder.tvTitle = (TextView) convertview
					.findViewById(R.id.tv_radioitem_title);
			radioHolder.tvContent = (TextView) convertview
					.findViewById(R.id.tv_radioitem_content);
			radioHolder.btnRadio = (Button) convertview
					.findViewById(R.id.btn_radio_start);
			radioHolder.lnclick = (LinearLayout) convertview
					.findViewById(R.id.ln_radioitem_click);

			convertview.setTag(radioHolder);

		} else {
			radioHolder = (RadioHolder) convertview.getTag();
		}

		radioHolder.tvTitle.setText(mLists.get(position).getTitle());
		radioHolder.tvContent.setText(mLists.get(position).getContent());
		String path = (String) ("http://file.bmob.cn/" + mLists.get(position)
				.getSong().getUrl());
		Uri uri = Uri.parse(path);
		new MediaPlayer();
		final MediaPlayer mp = MediaPlayer.create(mContext, uri);

		radioHolder.btnRadio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (radio) {

					mp.start();
					radio = false;
					radioHolder.btnRadio
							.setBackgroundResource(R.drawable.img_pause);

				} else {
					mp.pause();
					radio = true;
					radioHolder.btnRadio
							.setBackgroundResource(R.drawable.img_paly);
				}

			}
		});

		radioHolder.lnclick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "暂仅提供试听服务！", Toast.LENGTH_SHORT)
						.show();
			}
		});

		return convertview;
	}

	protected static class RadioHolder {
		public TextView tvTitle, tvContent;
		public Button btnRadio;
		public LinearLayout lnclick;

	}

}
