package com.jw.base;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import com.jw.seehdu.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidet extends AppWidgetProvider{
	private static final String CLICK_NAME_ACTION = "com.shop.widget.click";
	
	private RemoteViews mRemoteViews;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new MyTime(context , appWidgetManager),1,60000);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}
	private class MyTime extends TimerTask{
		RemoteViews remoteViews; 
		AppWidgetManager appWidgetManager;
		ComponentName thiswidget;

		public MyTime(Context context, AppWidgetManager appWidgetManager) {
			this.appWidgetManager = appWidgetManager;
			remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget);
			
			thiswidget = new ComponentName(context, AppWidet.class);
		}

		@Override
		public void run() {
			Date  date = new Date();
			Calendar calendar = new GregorianCalendar(2015,6,25);
			long days = (((calendar.getTimeInMillis() - date.getTime())/1000))/86400;
			remoteViews.setTextViewText(R.id.tx_timer, "距离期末考试还有" + days + "天");
			appWidgetManager.updateAppWidget(thiswidget, remoteViews);
			
		}
		
	}

}
