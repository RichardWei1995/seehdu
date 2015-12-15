package com.stone.shop.secondview;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.listener.FindListener;

import com.jw.base.MainActivity1;
import com.jw.base.MyViewPagerCotroller;
import com.jw.base.MyViewPagerHr;
import com.jw.base.RefreshableView;
import com.jw.base.MyViewPagerHr.OnPageChange;
import com.jw.base.RefreshableView.PullToRefreshListener;
import com.jw.seehdu.R;
import com.jw.shop.adapter.HrListAdapter;
import com.jw.shop.adapter.LectureListAdapter;
import com.jw.shop.model.Hr;
import com.jw.shop.model.Lecture;
import com.jw.shop.model.Union;
import com.stone.shop.view.NewsDaylyActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HrLectureActivity extends Activity implements OnClickListener,
		OnItemClickListener, OnTouchListener {
	private view2Cotroller1 cot1;
	private view2Cotroller2 cot2;
	private Dialog mDialog;
	private Button btnBack;
	private TextView tvFabu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hrlecture);

		initview();
		clickListener();

		cot1 = new view2Cotroller1(this);
		cot2 = new view2Cotroller2(this);

		ArrayList<MyViewPagerCotroller> views = new ArrayList<MyViewPagerCotroller>();
		views.add(cot1);
		views.add(cot2);

		MyViewPagerHr myviewpager = (MyViewPagerHr) findViewById(R.id.myiewpager);
		myviewpager.setViews(views, 0);
		myviewpager.setOnPageChangeListener(new OnPageChange() {
			@Override
			public void onPageSelected(int currindex) {
				// 初始化时不会触发
			}
		});
	}

	private void clickListener() {
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		tvFabu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HrLectureActivity.this,
						HrAddActivity.class);
				startActivity(intent);
			}
		});

	}

	private void initview() {
		btnBack = (Button) findViewById(R.id.hrlecture_back);
		tvFabu = (TextView) findViewById(R.id.tv_hr_fabu);
	}

	class view2Cotroller1 extends MyViewPagerCotroller {
		private Activity mactivity;
		private View mview;
		private RefreshableView refreshableView;
		private ListView lvHr;
		private List<Hr> mHrList;
		private HrListAdapter mHrListAdapter;

		public view2Cotroller1(Activity activity) {
			super(activity);
			mactivity = activity;
			mview = LayoutInflater.from(mactivity).inflate(
					R.layout.activity_hr, null);
			refreshableView = (RefreshableView) mview
					.findViewById(R.id.hr_refreshable_view);
			lvHr = (ListView) mview.findViewById(R.id.lv_hr_main);

			mHrList = new ArrayList<Hr>();
			mHrListAdapter = new HrListAdapter(HrLectureActivity.this, mHrList);
			lvHr.setAdapter(mHrListAdapter);
			lvHr.setOnItemClickListener(HrLectureActivity.this);
			lvHr.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						final int position, long id) {
					final Intent toBXTNewsActivity = new Intent(
							HrLectureActivity.this, HrItemActivity.class);
					toBXTNewsActivity.putExtra("hrname", mHrList.get(position)
							.getHrname());
					toBXTNewsActivity.putExtra("hrobj", mHrList.get(position)
							.getHrobj());
					toBXTNewsActivity.putExtra("hrrank", mHrList.get(position)
							.getHrrank());
					toBXTNewsActivity.putExtra("hrneed", mHrList.get(position)
							.getHrneed());
					toBXTNewsActivity.putExtra("hrtime", mHrList.get(position)
							.getHrtime());
					toBXTNewsActivity.putExtra("hrplace", mHrList.get(position)
							.getHrplace());
					toBXTNewsActivity.putExtra("hrbeizhu", mHrList
							.get(position).getHrbeizhu());
					toBXTNewsActivity.putExtra("introduction", mHrList
							.get(position).getIntroduction());

					startActivity(toBXTNewsActivity);
				}
			});

			queryData1();
			requeryData1();

			refreshableView.setOnRefreshListener(new PullToRefreshListener() {
				@Override
				public void onRefresh() {
					try {
						Thread.sleep(2000);
						requeryData1();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					refreshableView.finishRefreshing();
				}
			}, 0);
		}

		private void queryData1() {
			showRoundProcessDialog(HrLectureActivity.this, R.layout.loding);
			BmobQuery<Hr> query = new BmobQuery<Hr>();
			query.order("-createdAt");
			query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);   
			query.findObjects(HrLectureActivity.this, new FindListener<Hr>() {

				@Override
				public void onSuccess(List<Hr> arg0) {
					 mDialog.cancel();
					if (arg0.size() > 0) {

						// 将本次查询的数据添加到bankCards中
						for (Hr News : arg0) {
							mHrList.add(News);
						}
						// 通知Adapter数据更新
						mHrListAdapter.refresh((ArrayList<Hr>) mHrList);
						mHrListAdapter.notifyDataSetChanged();
					} else {
					}
				}

				@Override
				public void onError(int arg0, String arg1) {
				}
			});

		}

		private void requeryData1() {

			BmobQuery<Hr> query = new BmobQuery<Hr>();
			query.order("-createdAt");
			query.findObjects(HrLectureActivity.this, new FindListener<Hr>() {

				@Override
				public void onSuccess(List<Hr> arg0) {
					mHrList.clear();
					for (Hr reNews : arg0) {

						mHrList.add(reNews);
					}
					// 通知Adapter数据更新
					mHrListAdapter.refresh((ArrayList<Hr>) mHrList);
					mHrListAdapter.notifyDataSetChanged();
				}

				@Override
				public void onError(int arg0, String arg1) {
				}
			});
		}

		@Override
		public View getView() {
			return mview;
		}

		@Override
		public String getTitle() {
			return "招聘信息";
		}

		@Override
		public void onshow() {
		}

		public void dosth() {
		}

	}

	class view2Cotroller2 extends MyViewPagerCotroller {
		private Activity mactivity;
		private View mview;
		private RefreshableView refreshableView;
		private ListView lvLecture;
		private List<Lecture> mLectureList;
		private LectureListAdapter mLectureListAdapter;

		public view2Cotroller2(Activity activity) {
			super(activity);
			mactivity = activity;
			mview = LayoutInflater.from(mactivity).inflate(
					R.layout.activity_lecture, null);
			refreshableView = (RefreshableView) mview
					.findViewById(R.id.lecture_refreshable_view);
			
			lvLecture = (ListView) mview.findViewById(R.id.lv_lecture_main);

			mLectureList = new ArrayList<Lecture>();
			mLectureListAdapter = new LectureListAdapter(
					HrLectureActivity.this, mLectureList);
			lvLecture.setAdapter(mLectureListAdapter);
			lvLecture.setOnItemClickListener(HrLectureActivity.this);
			lvLecture.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						final int position, long id) {
					final Intent toNewsActivity = new Intent(
							HrLectureActivity.this, LectureItemActivity.class);
					toNewsActivity.putExtra("name", mLectureList.get(position)
							.getName());
					toNewsActivity.putExtra("teacher",
							mLectureList.get(position).getTeacher());
					toNewsActivity.putExtra("time", mLectureList.get(position)
							.getTime());
					toNewsActivity.putExtra("place", mLectureList.get(position)
							.getPlace());
					toNewsActivity.putExtra("content",
							mLectureList.get(position).getContent());

					startActivity(toNewsActivity);
				}
			});

			queryData1();

			refreshableView.setOnRefreshListener(new PullToRefreshListener() {
				@Override
				public void onRefresh() {
					try {
						Thread.sleep(2000);
						requeryData1();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					refreshableView.finishRefreshing();
				}
			}, 0);
		}

		private void queryData1() {
			BmobQuery<Lecture> query = new BmobQuery<Lecture>();
			query.order("-createdAt");
			query.findObjects(HrLectureActivity.this,
					new FindListener<Lecture>() {

						@Override
						public void onSuccess(List<Lecture> arg0) {
							if (arg0.size() > 0) {

								// 将本次查询的数据添加到bankCards中
								for (Lecture News : arg0) {
									mLectureList.add(News);
								}
								// 通知Adapter数据更新
								mLectureListAdapter
										.refresh((ArrayList<Lecture>) mLectureList);
								mLectureListAdapter.notifyDataSetChanged();
							} else {
							}
						}

						@Override
						public void onError(int arg0, String arg1) {
						}
					});

		}

		private void requeryData1() {

			BmobQuery<Lecture> query = new BmobQuery<Lecture>();
			query.order("-createdAt");
			query.findObjects(HrLectureActivity.this,
					new FindListener<Lecture>() {

						@Override
						public void onSuccess(List<Lecture> arg0) {
							mLectureList.clear();
							for (Lecture reNews : arg0) {

								mLectureList.add(reNews);
							}
							// 通知Adapter数据更新
							mLectureListAdapter
									.refresh((ArrayList<Lecture>) mLectureList);
							mLectureListAdapter.notifyDataSetChanged();
						}

						@Override
						public void onError(int arg0, String arg1) {
						}
					});
		}

		@Override
		public View getView() {
			return mview;
		}

		@Override
		public String getTitle() {
			return "讲座";
		}

		@Override
		public void onshow() {
		}

		public void dosth() {
		}

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	public void showRoundProcessDialog(Context mContext, int layout) {
		mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.show();
		mDialog.setContentView(layout);
	}
}
