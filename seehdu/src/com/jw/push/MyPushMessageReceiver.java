package com.jw.push;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.jw.seehdu.R;

import cn.bmob.push.PushConstants;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyPushMessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String message = "";
		// TODO Auto-generated method stub
		if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
			String msg = intent
					.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
			JSONTokener jsonTokener = new JSONTokener(msg);
			try {
				JSONObject object = (JSONObject) jsonTokener.nextValue();
				message = object.getString("alert");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			NotificationManager manager = (NotificationManager) context
					.getSystemService(context.NOTIFICATION_SERVICE);
			Notification notification = new Notification(R.drawable.ic_seehdu,
					"来自看杭电的新消息！", System.currentTimeMillis());//
			notification.setLatestEventInfo(context, "看杭电", message, null);
			manager.notify(R.drawable.ic_seehdu, notification);
		}
	}

}
