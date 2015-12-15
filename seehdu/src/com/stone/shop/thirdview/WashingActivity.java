package com.stone.shop.thirdview;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.ab.view.chart.BarChart;
import com.ab.view.chart.CategorySeries;
import com.ab.view.chart.ChartFactory;
import com.ab.view.chart.PointStyle;
import com.ab.view.chart.XYMultipleSeriesDataset;
import com.ab.view.chart.XYMultipleSeriesRenderer;
import com.ab.view.chart.XYSeriesRenderer;
import com.jw.seehdu.R;
import com.stone.util.HttpUtils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WashingActivity extends Activity {
	// shengyu：剩余时间说明 ， stime：剩余时间倒计时
	private TextView tx1, content, danwei, stime, cutime, type, ctime, txceshi;
	private Button query, kaiqi, back;
	// 新建一个线程
	private Handler mHandler;
	// yeelink上的数据显示
	public String shuju = "1";
	int time = 10;
	// 剩余时间类型倒计时
	int ltime = 300;
	// 判断倒计时是否结束
	boolean terminateCount = false;

	// 此处需要修改为自己的服务器地址
	public static final String GET_NEWS_URL = "http://www.yeelink.net/devices/20686";
	Handler getNewsHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String jsonData = (String) msg.obj;
			Document doc = Jsoup.parse(jsonData);
			Elements items = doc.getElementsByClass("current_date");
			Elements items1 = doc.getElementsByClass("current_value");
			Elements items2 = doc.getElementsByClass("span12 small-form");

			String today = items.get(0).text();
			String ceshi = items.get(1).text();
			String shuju = items1.get(0).text();
			txceshi.setText(ceshi);

			String[] j1 = today.split("更新时间：");
			String date_ = j1[1].toString();
			String[] j2 = date_.split(":");
			String h_01 = j2[0].toString(); // yeelink上的hour

			String m_01 = j2[1].toString(); // yeelink 上的minute

			String s_ = j2[2].toString();
			String[] s2 = s_.split(" ");
			String s_01 = s2[0].toString(); // yeelink上的second

			System.out.println(jsonData);

			Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料
			t.setToNow(); // 取得系统时间。
			int date = t.monthDay;
			int hour = t.hour;
			int minute = t.minute;
			int second = t.second;

			cutime.setText(date + "日" + "  " + hour + ":" + minute + ":"
					+ second); // 当前系统时间

			int ih = Integer.parseInt(h_01);
			int im = Integer.parseInt(m_01);
			int is = Integer.parseInt(s_01);

			int ysum = ih * 3600 + im * 60 + is; // yeelink更新秒
			int csum = hour * 3600 + minute * 60 + second; // 当前更新秒

			int cusum = csum - ysum;

			int type1 = 480 - ysum;
			tx1.setText(ih + ":" + im + ":" + is);

			if (shuju.equals("0")) {
				kaiqi.setText("洗衣0");
				type.setText("未在洗衣");
				ctime.setText("0s");
			}
			if (shuju.equals("1")) {
				kaiqi.setText("洗衣1");
				if (cusum > 0 && cusum < 480) {
					type.setText("还在洗衣中");
					ctime.setText(csum + "s");
				} else {
					type.setText("洗衣完毕");
					ctime.setText("0s");
				}
			} else if (shuju.equals("2")) {
				kaiqi.setText("洗衣2");
				if (cusum > 0 && cusum < 1380) {
					type.setText("还在洗衣中");
					ctime.setText(csum + "s");
				} else {
					type.setText("洗衣完毕");
					ctime.setText("0s");
				}

			} else if (shuju.equals("3")) {
				kaiqi.setText("洗衣3");
				type.setText("正在洗衣");
				if (cusum > 0 && cusum < 2400) {
					type.setText("还在洗衣中");
					ctime.setText(csum + "s");
				} else {
					type.setText("洗衣完毕");
					ctime.setText("0s");
				}
			} else if (shuju.equals("4")) {
				kaiqi.setText("洗衣4");
				type.setText("正在洗衣");
				if (cusum > 0 && cusum < 2700) {
					type.setText("还在洗衣中");
					ctime.setText(csum + "s");
				} else {
					type.setText("洗衣完毕");
					ctime.setText("0s");
				}
			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_washing);

		tx1 = (TextView) findViewById(R.id.tx1); // 更新时间
		cutime = (TextView) findViewById(R.id.current_time); // 当前时间
		ctime = (TextView) findViewById(R.id.ctime); // 相差时间
		type = (TextView) findViewById(R.id.text_type);
		txceshi = (TextView) findViewById(R.id.ceshi);

		kaiqi = (Button) findViewById(R.id.open);
		back = (Button) findViewById(R.id.lo_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});
		kaiqi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				HttpUtils.getNewsJSON(GET_NEWS_URL, getNewsHandler);
				kaiqi.setText("正在查询");

			}
		});

		HandlerThread mHandlerThread = new HandlerThread("count", 5);
		mHandlerThread.start();
		mHandler = new Handler(mHandlerThread.getLooper());

		// 要显示图形的View
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.chart01);
		// 说明文字
		String[] titles = new String[] { "机1", "" };
		// 数据
		List<double[]> values = new ArrayList<double[]>();
		// 每个数据点的颜色
		List<int[]> colors = new ArrayList<int[]>();
		// 每个数据点的简要 说明
		List<String[]> explains = new ArrayList<String[]>();

		values.add(new double[] { 14230, 0, 0, 0, 15900, 17200, 12030 });
		values.add(new double[] { 5230, 0, 0, 0, 7900, 9200, 13030 });

		colors.add(new int[] { Color.RED, 0, 0, 0, 0, 0, 0 });
		colors.add(new int[] { 0, 0, Color.BLUE, 0, Color.GREEN, 0, 0 });

		explains.add(new String[] { "红色", "点2", "点3", "点4", "", "点6", "" });
		explains.add(new String[] { "1 ", "2", "3", "4", "5", "6", "" });

		// 柱体或者线条颜色
		int[] mSeriescolors = new int[] { Color.rgb(153, 204, 0),
				Color.rgb(51, 181, 229) };
		// 创建渲染器
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		int length = mSeriescolors.length;
		for (int i = 0; i < length; i++) {
			// 创建SimpleSeriesRenderer单一渲染器
			XYSeriesRenderer r = new XYSeriesRenderer();
			// SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			// 设置渲染器颜色
			r.setColor(mSeriescolors[i]);
			r.setFillPoints(true);
			r.setPointStyle(PointStyle.CIRCLE);
			r.setLineWidth(1);
			r.setChartValuesTextSize(16);
			// 加入到集合中
			renderer.addSeriesRenderer(r);
		}
		// 坐标轴标题文字大小
		renderer.setAxisTitleTextSize(16);
		// 图形标题文字大小
		renderer.setChartTitleTextSize(25);
		// 轴线上标签文字大小
		renderer.setLabelsTextSize(15);
		// 说明文字大小
		renderer.setLegendTextSize(15);
		// 图表标题
		renderer.setChartTitle("历史工作记录");
		// X轴标题
		renderer.setXTitle("日期");
		// Y轴标题
		renderer.setYTitle("类型");
		// X轴最小坐标点
		renderer.setXAxisMin(0.5);
		// X轴最大坐标点
		renderer.setXAxisMax(7.5);
		// Y轴最小坐标点
		renderer.setYAxisMin(-1000);
		// Y轴最大坐标点
		renderer.setYAxisMax(24000);
		// 坐标轴颜色
		renderer.setAxesColor(Color.rgb(51, 181, 229));
		renderer.setXLabelsColor(Color.rgb(51, 181, 229));
		renderer.setYLabelsColor(0, Color.rgb(51, 181, 229));
		// 设置图表上标题与X轴与Y轴的说明文字颜色
		renderer.setLabelsColor(Color.GRAY);
		// renderer.setGridColor(Color.GRAY);
		// 设置字体加粗
		renderer.setTextTypeface("sans_serif", Typeface.BOLD);
		// 设置在图表上是否显示值标签
		renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
		renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
		// 显示屏幕可见取区的XY分割数
		renderer.setXLabels(7);
		renderer.setYLabels(10);
		// X刻度标签相对X轴位置
		renderer.setXLabelsAlign(Align.CENTER);
		// Y刻度标签相对Y轴位置
		renderer.setYLabelsAlign(Align.LEFT);
		renderer.setPanEnabled(true, true);
		renderer.setZoomEnabled(true);
		renderer.setZoomButtonsVisible(true);
		renderer.setZoomRate(1.1f);
		renderer.setBarSpacing(0.5f);

		// 标尺开启
		renderer.setScaleLineEnabled(true);
		// 设置标尺提示框高
		renderer.setScaleRectHeight(10);
		// 设置标尺提示框宽
		renderer.setScaleRectWidth(150);
		// 设置标尺提示框背景色
		renderer.setScaleRectColor(Color.argb(150, 52, 182, 232));
		renderer.setScaleLineColor(Color.argb(175, 150, 150, 150));
		renderer.setScaleCircleRadius(35);
		// 第一行文字的大小
		renderer.setExplainTextSize1(20);
		// 第二行文字的大小
		renderer.setExplainTextSize2(20);

		// 临界线
		double[] limit = new double[] { 15000, 12000, 4000, 9000 };
		renderer.setmYLimitsLine(limit);
		int[] colorsLimit = new int[] { Color.rgb(100, 255, 255),
				Color.rgb(100, 255, 255), Color.rgb(0, 255, 255),
				Color.rgb(0, 255, 255) };
		renderer.setmYLimitsLineColor(colorsLimit);

		// 显示表格线
		renderer.setShowGrid(true);
		// 如果值是0是否要显示
		renderer.setDisplayValue0(true);
		// 创建渲染器数据填充器
		XYMultipleSeriesDataset mXYMultipleSeriesDataset = new XYMultipleSeriesDataset();
		for (int i = 0; i < length; i++) {
			CategorySeries series = new CategorySeries(titles[i]);
			double[] v = values.get(i);
			int[] c = colors.get(i);
			String[] e = explains.get(i);
			int seriesLength = v.length;
			for (int k = 0; k < seriesLength; k++) {
				// 设置每个点的颜色
				series.add(v[k], c[k], e[k]);
			}
			mXYMultipleSeriesDataset.addSeries(series.toXYSeries());
		}
		// 背景
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.rgb(222, 222, 200));
		renderer.setMarginsColor(Color.rgb(222, 222, 200));

		// 线图
		View chart = ChartFactory.getLineChartView(this,
				mXYMultipleSeriesDataset, renderer);
		linearLayout.addView(chart);

		// 获取图形View
		/*
		 * View chart =
		 * ChartFactory.getBarChartView(this,mXYMultipleSeriesDataset,renderer,
		 * Type.DEFAULT); linearLayout.addView(chart);
		 */

		/*
		 * Intent intent = ChartFactory.getLineChartIntent(this,
		 * mXYMultipleSeriesDataset, renderer); startActivity(intent);
		 */
	}

	Thread oneSecondThread = new Thread(
	/**
	 * @author w 一直在子线程中执行
	 */
	new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				System.out.println("time = " + time);
				time--;
				Thread.sleep(1000);// 设置刷新时间，一直在后台运行
				HttpUtils.getNewsJSON(GET_NEWS_URL, getNewsHandler);
				Message msg = new Message();
				msg.arg1 = time;
				uiHandler.sendMessage(msg);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	});

	// 剩余时间子线程
	Thread sThread = new Thread(
	/**
	 * @author w 一直在子线程中执行
	 */
	new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				System.out.println("time = " + ltime);
				ltime--;
				Thread.sleep(1000);// 设置刷新时间，一直在后台运行
				Message msg = new Message();
				msg.arg1 = ltime;
				stimeHandler.sendMessage(msg);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	});

	// 判断投币类型的剩余时间线程
	Handler uiHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (time > 0 && !terminateCount) {
				query.setText(msg.arg1 + "s");
				mHandler.post(oneSecondThread);
			} else {
				query.setText("判断结束");
				query.setEnabled(true);
				if (shuju.equals("0")) {
					stime.setText("洗衣未开始");
				} else if (shuju.equals("1")) {
					mHandler.post(sThread);
				} else if (shuju.equals("2")) {
					kaiqi.setText("洗衣类型为：2");
					mHandler.post(sThread);
				}

			}
		}

	};

	// stime显示判断线程
	Handler stimeHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (ltime > 0 && !terminateCount) {
				stime.setText(msg.arg1 + "s");
				mHandler.post(sThread);
			} else {
				stime.setText("洗衣结束");
			}
		}

	};

}
