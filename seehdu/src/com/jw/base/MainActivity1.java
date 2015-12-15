package com.jw.base;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;

import com.example.qr_codescan.CenterScanActivity;
import com.example.qr_codescan.MipcaActivityCapture;
import com.jw.base.RefreshableView.PullToRefreshListener;
import com.jw.base.MyViewPager.OnPageChange;
import com.jw.date.TypeDef;
import com.jw.seehdu.R;
import com.jw.shop.adapter.NewsDalyAdapter;
import com.jw.shop.model.DalyNews;
import com.jw.shop.model.User;
import com.jw.tryitem.ZhaoxinHduHelpActivity;
import com.stone.shop.fourview.Game01Activity;
import com.stone.shop.fourview.HduPhoneActivity;
import com.stone.shop.fourview.PlaySong;
import com.stone.shop.secondview.HduCSDNActivity;
import com.stone.shop.secondview.HrLectureActivity;
import com.stone.shop.secondview.OfficalwebActivity;
import com.stone.shop.secondview.TicketAddActivity;
import com.stone.shop.secondview.UnionActivity;
import com.stone.shop.thirdview.LostActivity;
import com.stone.shop.thirdview.ToloveActivity;
import com.stone.shop.view.ActivityAppset;
import com.stone.shop.view.MessegeCenterActivity;
import com.stone.shop.view.MineSoftActivity;
import com.stone.shop.view.NewsDaylyActivity;
import com.stone.util.CacheUtils;
import com.stone.util.SystemStatusManager;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author jw 看杭电基本框架{侧滑菜单、viewpager、titleBar的基本实现}
 */
public class MainActivity1 extends Activity implements OnClickListener,
		OnItemClickListener, OnTouchListener {
	private static final String APP_ID = "wx9622f3e3368d5c25";
	private IWXAPI api;

	String TAG = "MyViewPager";
	view2Cotroller1 cot1;
	view2Cotroller2 cot2;
	view2Cotroller3 cot3;;
	view2Cotroller4 cot4;
	private PopupWindow popupWindow;
	private Button btnMore , btnLocalImg;
	// 点击弹出策划菜单
	private ImageView imgslidemenue, img_menu_user;

	private EditText user_name, college, tel, dormitory;
	private TextView editor;
	private User user;
	private LinearLayout soft_in, out, set;

	private int iscontent = 1;
	private int screenWidth;
	private View content;
	private View menu;
	private LinearLayout.LayoutParams menuParams;
	private int menuPadding = 150;
	private int leftEdge;
	private int rightEdge = 0;
	private float xDown;
	public static final int SNAP_VELOCITY = 200;

	// 记录手指移动时的横坐标。
	private float xMove;
	// 记录手机抬起时的横坐标。
	private float xUp;
	// menu当前是显示还是隐藏。只有完全显示或隐藏menu时才会更改此值，滑动过程中此值无效。
	private boolean isMenuVisible;
	// 用于计算手指滑动的速度。
	private VelocityTracker mVelocityTracker;

	// 校历
	private TextView tvWeek; // 周次和星期

	private String if_editor = "no";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 检查自动更新
		BmobUpdateAgent.forceUpdate(this);

		// 标题栏统一：libs：nineoldandroids ， utils:screen , system
		setTranslucentStatus();

		// 微信注册
		api = WXAPIFactory.createWXAPI(this, APP_ID, true);
		api.registerApp(APP_ID);

		init();
		initmenudata();
		click();

		// 侧滑菜单监听
		content.setOnTouchListener(this);
		menu.setOnClickListener(this);

		MyViewPager myviewpager = (MyViewPager) findViewById(R.id.myiewpager);
		// myviewpager.setCurrcolor(255);// 设置选中的字体颜色
		// myviewpager.setnoCurrcolor(0xffAFAFAF);// 设置未选中的字体颜色
		// myviewpager.setCurrimage(R.drawable.ic_launcher);//设置选中时的下划线图片

		cot1 = new view2Cotroller1(this);
		cot2 = new view2Cotroller2(this);
		cot3 = new view2Cotroller3(this);
		cot4 = new view2Cotroller4(this);

		// 增加view
		ArrayList<MyViewPagerCotroller> views = new ArrayList<MyViewPagerCotroller>();
		views.add(cot1);
		views.add(cot2);
		views.add(cot3);
		views.add(cot4);
		myviewpager.setViews(views, 0);

		myviewpager.setOnPageChangeListener(new OnPageChange() {
			@Override
			public void onPageSelected(int currindex) {
				// 初始化时不会触发
				Log.i(TAG, "onPageSelected:" + currindex);
			}
		});
	}

	/**
	 * 通知栏适配
	 */
	private void setTranslucentStatus() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window win = getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			winParams.flags |= bits;
			win.setAttributes(winParams);
		}
		SystemStatusManager tintManager = new SystemStatusManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(0);
	}

	private void click() {

		btnMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				View popupWindow_view = getLayoutInflater().inflate(
						R.layout.activity_menu_home, null, false);
				LinearLayout messege = (LinearLayout) popupWindow_view
						.findViewById(R.id.ln_center_messege);
				LinearLayout centerShare = (LinearLayout) popupWindow_view
						.findViewById(R.id.ln_center_share);
				LinearLayout centersao = (LinearLayout) popupWindow_view
						.findViewById(R.id.ln_center_sao);

				messege.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(MainActivity1.this,
								MessegeCenterActivity.class);
						startActivity(intent);
					}
				});

				// 微信分享
				centerShare.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						WXWebpageObject webpage = new WXWebpageObject();
						webpage.webpageUrl = "http://hduxiaoka.bmob.cn/";
						WXMediaMessage msg = new WXMediaMessage(webpage);
						msg.title = "下载看杭电，玩转你的大学！";
						msg.description = "null";
						msg.mediaObject = webpage;
						Bitmap bitmap = BitmapFactory.decodeResource(
								getResources(), R.drawable.ic_seehdu);
						Bitmap thumbitmap = Bitmap.createScaledBitmap(bitmap,
								120, 150, true);
						bitmap.recycle();
						msg.thumbData = bmpToArray(thumbitmap, true);
						SendMessageToWX.Req req = new SendMessageToWX.Req();
						req.transaction = String.valueOf(System
								.currentTimeMillis());
						req.message = msg;
						req.scene = SendMessageToWX.Req.WXSceneTimeline;

						api.sendReq(req);
					}
				});
				
				centersao.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						
						Intent intent = new Intent(MainActivity1.this , CenterScanActivity.class);
						startActivity(intent);
					}
				});
				
				
				

				//popupwindow初始化
				popupWindow = new PopupWindow(popupWindow_view,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						true);
				popupWindow.showAsDropDown(btnMore);
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

	// 二进制缩略图
	private byte[] bmpToArray(final Bitmap bitmap, final Boolean needrecycle) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
		if (needrecycle) {
			bitmap.recycle();
		}
		byte[] result = outputStream.toByteArray();
		try {
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 侧滑菜单信息的初始化
	 */
	private void initmenudata() {
		user_name = (EditText) findViewById(R.id.mine_name);
		college = (EditText) findViewById(R.id.ed_college);
		dormitory = (EditText) findViewById(R.id.ed_dormitory);
		tel = (EditText) findViewById(R.id.ed_tel);
		img_menu_user = (ImageView) findViewById(R.id.im_main_userphoto);

		editor = (TextView) findViewById(R.id.tx_main_editor);

		user = BmobUser.getCurrentUser(this, User.class);
		user_name.setText(user.getName());
		user_name.setEnabled(false);
		college.setText(user.getCademy());
		college.setEnabled(false);
		dormitory.setText(user.getDorNum());
		dormitory.setEnabled(false);
		tel.setText(user.getPhone());
		tel.setEnabled(false);

		soft_in = (LinearLayout) findViewById(R.id.soft_relate);
		out = (LinearLayout) findViewById(R.id.move_out);
		set = (LinearLayout) findViewById(R.id.soft_set);

		set.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity1.this,
						ActivityAppset.class);
				startActivity(intent);
			}
		});

		soft_in.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent toMineSoft = new Intent(MainActivity1.this,
						MineSoftActivity.class);
				startActivity(toMineSoft);
			}
		});
		out.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity1.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});

		editor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("当前用户的ID: ", user.getObjectId());
				if (if_editor == "no") {
					user_name.setEnabled(true);
					user_name.setHint("修改姓名");
					college.setEnabled(true);
					college.setHint("修改学院");
					tel.setEnabled(true);
					tel.setHint("修改电话");
					dormitory.setEnabled(true);
					dormitory.setHint("修改寝室号");
					editor.setText("保存");
					if_editor = "yes";
				} else if (if_editor == "yes") {
					user.setName(user_name.getText().toString());
					user.setCademy(college.getText().toString());
					user.setDorNum(dormitory.getText().toString());
					user.setPhone(tel.getText().toString());
					user.update(MainActivity1.this, user.getObjectId(),
							new UpdateListener() {

								@Override
								public void onSuccess() {
									// TODO Auto-generated method stub
									Toast.makeText(MainActivity1.this, "更新成功!",
											Toast.LENGTH_SHORT).show();
								}

								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO Auto-generated method stub

								}
							});

					user_name.setEnabled(false);
					user_name.setHint(null);
					college.setEnabled(false);
					college.setHint(null);
					tel.setEnabled(false);
					tel.setHint(null);
					dormitory.setEnabled(false);
					dormitory.setHint(null);
					editor.setText("编辑");
					if_editor = "no";
				} else {
					Toast.makeText(MainActivity1.this, "data wrong",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		// 自定义头像

		img_menu_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				showDialog();
			}

			private void showDialog() {
				// TODO Auto-generated method stub
				View view = getLayoutInflater().inflate(
						R.layout.photo_choose_dialog, null);
				final Dialog dialog = new Dialog(MainActivity1.this,
						R.style.transparentFrameWindowStyle);
				
				Button btnCancle = (Button) view
						.findViewById(R.id.btn_photochoose_cancle);

				// 传入界面
				dialog.setContentView(view, new LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				Window window = dialog.getWindow();
				// 设置显示动画
				window.setWindowAnimations(R.style.main_menu_animstyle);
				WindowManager.LayoutParams wl = window.getAttributes();
				wl.x = 0;
				wl.y = getWindowManager().getDefaultDisplay().getHeight();
				// 以下这两句是为了保证按钮可以水平满屏
				wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
				wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

				// 设置显示位置
				dialog.onWindowAttributesChanged(wl);
				// 设置点击外围解散
				dialog.setCanceledOnTouchOutside(true);
				dialog.show();
				

				btnCancle.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
				
				
			}
		});
	}

	String dateTime;

	public String saveToSdCard(Bitmap bitmap) {
		String files = CacheUtils.getCacheDirectory(this, true, "icon")
				+ dateTime + "_12";
		File file = new File(files);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == menu.getId()) {
		}

	}

	/**
	 * 基本框架初始化
	 */
	public void init() {
		// popwindow初始化
		btnMore = (Button) findViewById(R.id.btn_home_more);

		WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth = window.getDefaultDisplay().getWidth();
		content = findViewById(R.id.lin_main_content);
		menu = findViewById(R.id.main_menu);
		Log.d("+++++++++++", String.valueOf(menu.getId()));
		Log.d("===========", String.valueOf(content.getId()));
		menuParams = (LinearLayout.LayoutParams) menu.getLayoutParams();
		menuParams.width = screenWidth - menuPadding;
		leftEdge = -menuParams.width;
		menuParams.leftMargin = leftEdge;
		content.getLayoutParams().width = screenWidth;

		imgslidemenue = (ImageView) findViewById(R.id.slidemunue);
		imgslidemenue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (iscontent == 1) {
					scrollToMenu();
					iscontent = 0;
				} else {
					scrollToContent();
					iscontent = 1;
				}

			}
		});

	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		Log.d("--------------", String.valueOf(view.getId()));
		createVelocityTracker(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 手指按下时，记录按下时的横坐标
			xDown = event.getRawX();
			break;
		case MotionEvent.ACTION_MOVE:
			// 手指移动时，对比按下时的横坐标，计算出移动的距离，来调整menu的leftMargin值，从而显示和隐藏menu
			xMove = event.getRawX();
			int distanceX = (int) (xMove - xDown);
			if (isMenuVisible) {
				menuParams.leftMargin = distanceX;
			} else {
				menuParams.leftMargin = leftEdge + distanceX;
			}
			if (menuParams.leftMargin < leftEdge) {
				menuParams.leftMargin = leftEdge;
			} else if (menuParams.leftMargin > rightEdge) {
				menuParams.leftMargin = rightEdge;
			}
			menu.setLayoutParams(menuParams);
			break;
		case MotionEvent.ACTION_UP:
			// 手指抬起时，进行判断当前手势的意图，从而决定是滚动到menu界面，还是滚动到content界面
			xUp = event.getRawX();
			if (wantToShowMenu()) {
				if (shouldScrollToMenu()) {
					scrollToMenu();
				} else {
					scrollToContent();
				}
			} else if (wantToShowContent()) {
				if (shouldScrollToContent()) {
					scrollToContent();
				} else {
					scrollToMenu();
				}
			}
			recycleVelocityTracker();
			break;
		}
		return true;
	}

	private void createVelocityTracker(MotionEvent event) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}

	private int getScrollVelocity() {
		mVelocityTracker.computeCurrentVelocity(100);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}

	private void recycleVelocityTracker() {
		mVelocityTracker.recycle();
		mVelocityTracker = null;
	}

	class ScrollTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... speed) {
			int leftMargin = menuParams.leftMargin;
			// 根据传入的速度来滚动界面，当滚动到达左边界或右边界时，跳出循环。
			while (true) {
				leftMargin = leftMargin + speed[0];
				if (leftMargin > rightEdge) {
					leftMargin = rightEdge;
					break;
				}
				if (leftMargin < leftEdge) {
					leftMargin = leftEdge;
					break;
				}
				publishProgress(leftMargin);
				// 为了要有滚动效果产生，每次循环使线程睡眠20毫秒，这样肉眼才能够看到滚动动画。
				sleep(1);
			}
			if (speed[0] > 0) {
				isMenuVisible = true;
			} else {
				isMenuVisible = false;
			}
			return leftMargin;
		}

		@Override
		protected void onProgressUpdate(Integer... leftMargin) {
			menuParams.leftMargin = leftMargin[0];
			menu.setLayoutParams(menuParams);
		}

		@Override
		protected void onPostExecute(Integer leftMargin) {
			menuParams.leftMargin = leftMargin;
			menu.setLayoutParams(menuParams);
		}
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean wantToShowMenu() {
		return xUp - xDown > 0 && !isMenuVisible;
	}

	private boolean shouldScrollToMenu() {
		return xUp - xDown > screenWidth / 2
				|| getScrollVelocity() > SNAP_VELOCITY;
	}

	private boolean wantToShowContent() {
		return xUp - xDown < 0 && isMenuVisible;
	}

	private void scrollToMenu() {
		new ScrollTask().execute(30);
	}

	private void scrollToContent() {
		new ScrollTask().execute(-30);
	}

	private boolean shouldScrollToContent() {
		return xDown - xUp + menuPadding > screenWidth / 2
				|| getScrollVelocity() > SNAP_VELOCITY;
	}

	@Override
	protected void onDestroy() {
		cot1.dosth();
		cot2.dosth();
		cot3.dosth();
		cot4.dosth();
		super.onDestroy();
	}

	/**
	 * @author w 第一页实现
	 */
	class view2Cotroller1 extends MyViewPagerCotroller {
		private Activity mactivity;
		private View mview;
		private ListView lvBXTNews;
		private NewsDalyAdapter mBxtListAdapter;
		private List<DalyNews> mBXTNewsList;
		private RefreshableView refreshableView;
		private int count;
		private Integer seenum = null;
		private String sid;

		public view2Cotroller1(Activity activity) {
			super(activity);
			mactivity = activity;
			mview = LayoutInflater.from(mactivity).inflate(
					R.layout.activity_base, null);

			// 下拉刷新
			refreshableView = (RefreshableView) mview
					.findViewById(R.id.refreshable_main);
			refreshableView.setOnRefreshListener(new PullToRefreshListener() {

				@Override
				public void onRefresh() {
					try {
						Thread.sleep(1500);
						requaryData();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					refreshableView.finishRefreshing();
				}

			}, 0);

			// 校历
			tvWeek = (TextView) mview.findViewById(R.id.tv_week);

			lvBXTNews = (ListView) mview.findViewById(R.id.lv_lost_main);
			mBXTNewsList = new ArrayList<DalyNews>();
			mBxtListAdapter = new NewsDalyAdapter(MainActivity1.this,
					mBXTNewsList);
			lvBXTNews.setAdapter(mBxtListAdapter);
			lvBXTNews.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						final int position, long id) {

					sid = mBXTNewsList.get(position).getObjectId();
					Integer see = mBXTNewsList.get(position).getSee();
					see++;
					DalyNews dalyNews = new DalyNews();
					dalyNews.setSee(see);
					dalyNews.update(MainActivity1.this, sid,
							new UpdateListener() {

								@Override
								public void onSuccess() {
									// TODO Auto-generated method stub

								}

								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO Auto-generated method stub

								}
							});

					final Intent toBXTNewsActivity = new Intent(
							MainActivity1.this, NewsDaylyActivity.class);

					seenum = mBXTNewsList.get(position).getSee();

					toBXTNewsActivity.putExtra("title",
							mBXTNewsList.get(position).getTitle());
					toBXTNewsActivity.putExtra("time",
							mBXTNewsList.get(position).getTime());
					toBXTNewsActivity.putExtra("content",
							mBXTNewsList.get(position).getContent());
					toBXTNewsActivity.putExtra("from",
							mBXTNewsList.get(position).getFrom());
					toBXTNewsActivity.putExtra("id", mBXTNewsList.get(position)
							.getObjectId());
					toBXTNewsActivity.putExtra("url", mBXTNewsList
							.get(position).getUrl());
					toBXTNewsActivity.putExtra("seenum", "" + seenum);

					startActivity(toBXTNewsActivity);

				}
			});
			setTime();
			queryData();
		}

		private void requaryData() {
			// TODO Auto-generated method stub
			BmobQuery<DalyNews> query = new BmobQuery<DalyNews>();
			query.order("-createdAt");
			query.findObjects(MainActivity1.this, new FindListener<DalyNews>() {

				@Override
				public void onSuccess(List<DalyNews> arg0) {
					mBXTNewsList.clear();

					// 将本次查询的数据添加到bankCards中
					for (DalyNews bxtNews : arg0) {
						mBXTNewsList.add(bxtNews);
					}
					// 通知Adapter数据更新
					mBxtListAdapter.refresh((ArrayList<DalyNews>) mBXTNewsList);
					mBxtListAdapter.notifyDataSetChanged();
				}

				@Override
				public void onError(int arg0, String arg1) {
				}
			});
		}

		/**
		 * 获取数据
		 * 
		 */
		private void queryData() {

			BmobQuery<DalyNews> query = new BmobQuery<DalyNews>();
			query.order("-createdAt");
			query.findObjects(MainActivity1.this, new FindListener<DalyNews>() {

				@Override
				public void onSuccess(List<DalyNews> arg0) {

					if (arg0.size() > 0) {

						// 将本次查询的数据添加到bankCards中
						for (DalyNews bxtNews : arg0) {
							mBXTNewsList.add(bxtNews);
						}
						// 通知Adapter数据更新
						mBxtListAdapter
								.refresh((ArrayList<DalyNews>) mBXTNewsList);
						mBxtListAdapter.notifyDataSetChanged();
					} else {
					}
				}

				@Override
				public void onError(int arg0, String arg1) {
				}
			});
		}

		public void setTime() {
			Calendar calendar = Calendar.getInstance();
			String year = calendar.get(Calendar.YEAR) + "";
			String month = calendar.get(Calendar.MONTH) + 1 + "";
			String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
			String week = calendar.get(Calendar.WEEK_OF_YEAR) - 9 + "";
			String dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) + "";
			String chDayOfWeek = TypeDef.chDayOfWeek[Integer
					.parseInt(dayOfWeek) - 1];
			tvWeek.setText(year + " 年 " + month + " 月 " + day + " 日" + " 第 "
					+ week + " 周 " + " " + " 星期 " + chDayOfWeek);
		}

		@Override
		public View getView() {
			return mview;
		}

		@Override
		public String getTitle() {
			return "首页新闻";
		}

		@Override
		public void onshow() {
			Log.i(TAG, "onPageSelected:" + "view1 显示啦 ~(≧▽≦)~");
		}

		public void dosth() {
			Log.i(TAG, "do something (*^__^*) ");
		}

	}

	class view2Cotroller2 extends MyViewPagerCotroller {
		private Activity mactivity;
		private View mview;
		private LinearLayout union, messege, exame, express, relate, csdn, web;

		public view2Cotroller2(Activity activity) {
			super(activity);
			mactivity = activity;
			mview = LayoutInflater.from(mactivity).inflate(
					R.layout.activity_college, null);
			union = (LinearLayout) mview.findViewById(R.id.ln_organization);
			messege = (LinearLayout) mview.findViewById(R.id.ln_lecture);
			exame = (LinearLayout) mview.findViewById(R.id.ln_exame);
			express = (LinearLayout) mview.findViewById(R.id.ln_express);
			relate = (LinearLayout) mview.findViewById(R.id.ln_hdu);
			csdn = (LinearLayout) mview.findViewById(R.id.ln_csdn);
			web = (LinearLayout) mview.findViewById(R.id.ln_hduweb);
			Onclick();

		}

		private void Onclick() {
			union.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainActivity1.this,
							ZhaoxinHduHelpActivity.class);
					startActivity(intent);
				}
			});
			messege.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainActivity1.this,
							HrLectureActivity.class);
					startActivity(intent);
				}
			});
			exame.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity1.this, "功能暂未推出",
							Toast.LENGTH_SHORT).show();
				}
			});
			express.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity1.this,
							TicketAddActivity.class);
					startActivity(intent);
				}
			});
			relate.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

				}
			});
			csdn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainActivity1.this , HduCSDNActivity.class);
					startActivity(intent);
				}
			});
			web.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intentweb = new Intent(MainActivity1.this,
							OfficalwebActivity.class);
					startActivity(intentweb);
				}
			});

		}

		@Override
		public View getView() {
			return mview;
		}

		@Override
		public String getTitle() {
			return "校园服务";
		}

		@Override
		public void onshow() {
			Log.i(TAG, "进入第二界面");
		}

		public void dosth() {
			Log.i(TAG, "do something2 (*^__^*) ");
		}

	}

	/**
	 * @author w 生活关怀界面
	 */
	class view2Cotroller3 extends MyViewPagerCotroller {
		private Activity mactivity;
		private View mview;
		private LinearLayout washing, lost_found, sunnysport, resource, love,
				dinner;

		public view2Cotroller3(Activity activity) {
			super(activity);
			mactivity = activity;
			mview = LayoutInflater.from(mactivity).inflate(
					R.layout.try_item_view3, null);

			washing = (LinearLayout) mview.findViewById(R.id.ln_clothes_washer);
			lost_found = (LinearLayout) mview
					.findViewById(R.id.ln_lost_and_found);
			sunnysport = (LinearLayout) mview.findViewById(R.id.ln_run);
			resource = (LinearLayout) mview.findViewById(R.id.ln_resources);
			love = (LinearLayout) mview.findViewById(R.id.ln_love);
			dinner = (LinearLayout) mview.findViewById(R.id.ln_item3_dinner);

			onclick();
		}

		private void onclick() {
			// TODO Auto-generated method stub
			washing.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					// Intent intent = new Intent(MainActivity1.this,
					// WashTotal.class);
					// startActivity(intent);
					Toast.makeText(MainActivity1.this, "功能暂未推出！",
							Toast.LENGTH_SHORT).show();
				}
			});
			lost_found.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent1 = new Intent(MainActivity1.this,
							LostActivity.class);
					startActivity(intent1);
				}
			});
			sunnysport.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity1.this, "功能暂未推出",
							Toast.LENGTH_SHORT).show();
				}
			});

			resource.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity1.this, "功能暂未推出",
							Toast.LENGTH_SHORT).show();

				}
			});
			love.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent4 = new Intent(MainActivity1.this,
							ToloveActivity.class);
					startActivity(intent4);
				}
			});
			dinner.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity1.this, "功能暂未推出！",
							Toast.LENGTH_SHORT).show();
				}
			});
		}

		@Override
		public View getView() {
			return mview;
		}

		@Override
		public String getTitle() {
			return "生活关怀";
		}

		@Override
		public void onshow() {
		}

		public void dosth() {
			Log.i(TAG, "do something 3 (*^__^*) ");
		}

	}

	class view2Cotroller4 extends MyViewPagerCotroller {
		private Activity mactivity;
		private View mview;
		private Button button;
		private LinearLayout lnHduMap, lnHduPhone, lnHduRadio , lnGame01;

		public view2Cotroller4(Activity activity) {
			super(activity);
			mactivity = activity;
			mview = LayoutInflater.from(mactivity).inflate(
					R.layout.try_item_view4, null);

			lnHduMap = (LinearLayout) mview.findViewById(R.id.ln_item4_hdumap);
			lnHduPhone = (LinearLayout) mview.findViewById(R.id.ln_item4_phone);
			lnHduRadio = (LinearLayout) mview.findViewById(R.id.ln_item4_radio);
			lnGame01 = (LinearLayout) mview.findViewById(R.id.ln_item4_game01);

			click();
		}

		private void click() {
			lnHduMap.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

				}
			});
			lnHduPhone.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainActivity1.this,
							HduPhoneActivity.class);
					startActivity(intent);

				}
			});

			lnHduRadio.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainActivity1.this,
							PlaySong.class);
					startActivity(intent);

				}
			});
			lnGame01.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity1.this,
							Game01Activity.class);
					startActivity(intent);
					
				}
			});

		}

		@Override
		public View getView() {
			return mview;
		}

		@Override
		public String getTitle() {
			return "更多功能";
		}

		@Override
		public void onshow() {
			Log.i(TAG, "更多功能 显示啦 ~(≧▽≦)~");
		}

		public void dosth() {
			Log.i(TAG, "do something4 (*^__^*) ");
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
