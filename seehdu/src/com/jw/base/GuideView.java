package com.jw.base;

import com.jw.seehdu.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author w 引导页面
 */
public class GuideView extends Activity {
	private static final String SHAREDPREFERENCES_NAME = "first_pref";

	private ImageView ivGuidePicture;

	private Button btn_finish, btn_login;

	private Drawable[] pictures;

	private Animation[] animations;

	private int currentItem = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		SharedPreferences preferences = this.getSharedPreferences(
				SHAREDPREFERENCES_NAME, this.MODE_PRIVATE);
		Editor editor = preferences.edit();
		// 存入数据
		editor.putBoolean("isFirstIn", false);
		// 提交修改
		editor.commit();
		initview();
		click();
		initData();

	}

	private void initData() {

		animations[0].setDuration(1500);
		animations[1].setDuration(3000);
		animations[2].setDuration(1500);

		animations[0].setAnimationListener(new GuideAnimationListener(0));
		animations[1].setAnimationListener(new GuideAnimationListener(1));
		animations[2].setAnimationListener(new GuideAnimationListener(2));

		ivGuidePicture.setImageDrawable(pictures[currentItem]);
		ivGuidePicture.startAnimation(animations[0]);

	}

	private void initview() {
		ivGuidePicture = (ImageView) findViewById(R.id.iv_guide_picture);

		btn_finish = (Button) findViewById(R.id.btn_guide_finish);
		btn_login = (Button) findViewById(R.id.btn_guide_login);

		pictures = new Drawable[] {
				getResources().getDrawable(R.drawable.img_guide_01),
				getResources().getDrawable(R.drawable.img_guide_02),
				getResources().getDrawable(R.drawable.img_guide_03),
				getResources().getDrawable(R.drawable.img_guide_04)};

		animations = new Animation[] {
				AnimationUtils.loadAnimation(this, R.anim.guide_fade_in),
				AnimationUtils.loadAnimation(this, R.anim.guide_fade_in_scale),
				AnimationUtils.loadAnimation(this, R.anim.guide_fade_out) };

	}

	class GuideAnimationListener implements AnimationListener {
		private int index;

		public GuideAnimationListener(int index) {
			this.index = index;
		}

		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			if (index < (animations.length - 1)) {
				ivGuidePicture.startAnimation(animations[index + 1]);
			} else {
				currentItem++;
				if (currentItem > (pictures.length - 1)) {
					currentItem = 0;
				}
				ivGuidePicture.setImageDrawable(pictures[currentItem]);
				ivGuidePicture.startAnimation(animations[0]);
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

	private void click() {
		// TODO Auto-generated method stub
		btn_finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});

		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GuideView.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
