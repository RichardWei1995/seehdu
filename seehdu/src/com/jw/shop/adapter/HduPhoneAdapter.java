package com.jw.shop.adapter;

import java.util.List;

import com.jw.seehdu.R;
import com.jw.shop.model.HduPhone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HduPhoneAdapter extends BaseAdapter {

	private Context mContext;
	private List<HduPhone> mListHduPhone;
	private LayoutInflater mInflater = null;

	public HduPhoneAdapter(Context context, List<HduPhone> mlist) {
		mContext = context;
		mListHduPhone = mlist;
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListHduPhone.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListHduPhone.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<HduPhone> list) {
		Log.i("HduAdapter", "Adapter刷新数据");
		mListHduPhone = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final HduPhoneHolder mHduPhoneHolder ;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_hduphone, null);
			mHduPhoneHolder  = new HduPhoneHolder();
			mHduPhoneHolder.tvName = (TextView) convertView
					.findViewById(R.id.tv_hduphone_mname);
			mHduPhoneHolder.tvNumber = (TextView) convertView
					.findViewById(R.id.tv_hduphone_number);
			mHduPhoneHolder.lnItem = (LinearLayout) convertView
					.findViewById(R.id.ln_hduphoneitem);
			convertView.setTag(mHduPhoneHolder);

		} else {
			mHduPhoneHolder = (HduPhoneHolder) convertView.getTag();

		}
		
		mHduPhoneHolder.tvName.setText(mListHduPhone.get(position).getName());
		
		mHduPhoneHolder.lnItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(mContext)
				.setTitle("详情")
				.setMessage(
						"姓名:" + mListHduPhone.get(position).getName()
								+ "\n电话号码:"
								+ mListHduPhone.get(position).getNumber())
				.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						   String phone_number = mListHduPhone.get(position).getNumber();
			                if(phone_number.equals("")) {
			                } else {
			                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone_number));
			                    mContext.startActivity(intent);
			                }

					}
				}).show();
				
			}
		});

		return convertView;
	}

	protected class HduPhoneHolder {

		private TextView tvName, tvNumber;
		private LinearLayout lnItem;

	}

}
