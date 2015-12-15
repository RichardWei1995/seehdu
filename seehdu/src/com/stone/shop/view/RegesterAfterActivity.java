package com.stone.shop.view;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

import com.jw.base.LoginActivity;
import com.jw.seehdu.R;
import com.jw.shop.model.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class RegesterAfterActivity extends Activity {
	private TextView username;
	private User curUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regester_after);
		username = (TextView) findViewById(R.id.re_username);
		Bmob.initialize(this, "9051d37b931af2142c234ab121ce1979");

		curUser = BmobUser.getCurrentUser(this, User.class);
		username.setText(curUser.getUsername());
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
		LinearInterpolator lir = new LinearInterpolator();
		anim.setInterpolator(lir);
		findViewById(R.id.img).startAnimation(anim);

		final Intent localIntent = new Intent(this, LoginActivity.class);
		Timer timer = new Timer();
		TimerTask tast = new TimerTask() {
			@Override
			public void run() {
				startActivity(localIntent);
				finish();
			}
		};
		timer.schedule(tast, 3000);
	}
}
