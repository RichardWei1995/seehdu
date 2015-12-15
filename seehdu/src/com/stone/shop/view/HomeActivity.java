package com.stone.shop.view;

import java.util.Calendar;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.GetListener;

import com.ab.activity.AbActivity;
import com.ab.view.AbPlayView;
import com.jw.date.TypeDef;
import com.jw.seehdu.R;
import com.jw.shop.model.LoadPic;
import com.stone.shop.fourview.PlaySong;

/**
 * 主界面
 * 
 */
public class HomeActivity extends AbActivity implements OnClickListener,
		OnItemClickListener {
	@SuppressWarnings("unused")
	private static final String TAG = "HomeActivity";
	// 公告
	private TextView tvHomebroadcast;
	// 校历
	private TextView tvWeek; // 周次和星期
	private TextView tvDay; // 年月日


	AbPlayView mAbAutoPlayView = null;


	private PopupWindow popupWindow;
	private ImageView imgMore, mPlayImage1, mPlayImage2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Bmob.initialize(this, "9051d37b931af2142c234ab121ce1979");

		// 校历
		tvWeek = (TextView) findViewById(R.id.tv_week);
		tvDay = (TextView) findViewById(R.id.tv_day);

		imgListener();
		setTime();
		// setimage();
		setViewPager();

	}

	private void imgListener() {
		imgMore = (ImageView) findViewById(R.id.img_more);
		imgMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				View popupWindow_view = getLayoutInflater().inflate(
						R.layout.activity_menu_unionitem, null, false);
				LinearLayout messege = (LinearLayout) popupWindow_view
						.findViewById(R.id.la_messege);
				LinearLayout see = (LinearLayout) popupWindow_view
						.findViewById(R.id.la_feedback);
				LinearLayout feedback = (LinearLayout) popupWindow_view
						.findViewById(R.id.la_see);
				messege.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {

					}
				});
				see.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub

					}
				});
				feedback.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(HomeActivity.this,
								FeedBackActivity.class);
						startActivity(intent);

					}
				});

				popupWindow = new PopupWindow(popupWindow_view, 300,
						LayoutParams.WRAP_CONTENT, true);
				popupWindow.showAsDropDown(imgMore);
				popupWindow_view.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						if (popupWindow != null && popupWindow.isShowing())
							popupWindow.dismiss();
						popupWindow = null;

						return false;
					}
				});

			}
		});
	}

	private void setViewPager() {

		mAbAutoPlayView = (AbPlayView) findViewById(R.id.mAbAutoPlayView);

		final View mPlayView = mInflater.inflate(R.layout.play_view_item, null);
		final ImageView mPlayImage = (ImageView) mPlayView
				.findViewById(R.id.mPlayImage);
		TextView mPlayText = (TextView) mPlayView.findViewById(R.id.mPlayText);
		mPlayText.setText("杭州电子科技大学");

		final View mPlayView1 = mInflater
				.inflate(R.layout.play_view_item, null);
		mPlayImage1 = (ImageView) mPlayView1.findViewById(R.id.mPlayImage);
		TextView mPlayText1 = (TextView) mPlayView1
				.findViewById(R.id.mPlayText);
		mPlayText1.setText("2222222222222");

		final View mPlayView2 = mInflater
				.inflate(R.layout.play_view_item, null);
		mPlayImage2 = (ImageView) mPlayView2.findViewById(R.id.mPlayImage);
		TextView mPlayText2 = (TextView) mPlayView2
				.findViewById(R.id.mPlayText);
		mPlayText2.setText("33333333333333333");

		mAbAutoPlayView.setPageLineHorizontalGravity(Gravity.RIGHT);
		mAbAutoPlayView.addView(mPlayView);
		mAbAutoPlayView.addView(mPlayView1);
		mAbAutoPlayView.addView(mPlayView2);

		// 首页图片更新查询
		BmobQuery<LoadPic> query1 = new BmobQuery<LoadPic>();
		query1.getObject(this, "lH7k888F", new GetListener<LoadPic>() {

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(LoadPic arg0) {
				// TODO Auto-generated method stub
				BmobFile icon = arg0.getHomepic();
				icon.loadImage(HomeActivity.this, mPlayImage);
			}
		});

		BmobQuery<LoadPic> query2 = new BmobQuery<LoadPic>();
		query2.getObject(this, "0B5qaaaf", new GetListener<LoadPic>() {

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(LoadPic arg0) {
				// TODO Auto-generated method stub
				BmobFile icon = arg0.getHomepic();
				icon.loadImage(HomeActivity.this, mPlayImage1);
			}
		});
		BmobQuery<LoadPic> query3 = new BmobQuery<LoadPic>();
		query3.getObject(this, "0B5qaaaf", new GetListener<LoadPic>() {

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(LoadPic arg0) {
				// TODO Auto-generated method stub
				BmobFile icon = arg0.getHomepic();
				icon.loadImage(HomeActivity.this, mPlayImage2);
			}
		});
		// 点击图片跳转，设置监听器
		mPlayImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent(HomeActivity.this, PlaySong.class);
				startActivity(intent1);
			}
		});
		mPlayView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
		});

	}





	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}



	/**
	 * 设置校历中日期的时间
	 */
	public void setTime() {
		Calendar calendar = Calendar.getInstance();
		String year = calendar.get(Calendar.YEAR) + "";
		String month = calendar.get(Calendar.MONTH) + 1 + "";
		String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
		String week = calendar.get(Calendar.WEEK_OF_YEAR) - 9 + "";
		String dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) + "";
		String chDayOfWeek = TypeDef.chDayOfWeek[Integer.parseInt(dayOfWeek) - 1];
		toast(year + "-" + month + "-" + day + " " + " 第 " + week + " 周 " + " "
				+ " 星期 " + chDayOfWeek);
		tvWeek.setText(" 第 " + week + " 周 " + " " + " 星期 " + chDayOfWeek);
		tvDay.setText(year + " 年 " + month + " 月 " + day + " 日");
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		}

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		PackageManager pm = getPackageManager();
		ResolveInfo homeInfo = pm.resolveActivity(
				new Intent(Intent.ACTION_MAIN)
						.addCategory(Intent.CATEGORY_HOME), 0);
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ActivityInfo ai = homeInfo.activityInfo;
			Intent startIntent = new Intent(Intent.ACTION_MAIN);
			startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			startIntent
					.setComponent(new ComponentName(ai.packageName, ai.name));
			startActivitySafely(startIntent);
			return true;
		} else
			return super.onKeyDown(keyCode, event);
	}

	private void startActivitySafely(Intent intent) {
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
		} catch (SecurityException e) {
			Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
