package com.stone.shop.thirdview;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.jw.seehdu.R;
import com.jw.shop.model.Love;
import com.jw.shop.model.LoveItemComment;
import com.jw.shop.model.User;

public class ToloveItemActivity extends Activity {
	@SuppressWarnings("unused")
	private static final String TAG = "BXTNewsActivity";

	private TextView tvLoveto, tvLovefrom, tvlove , tvComment , tvReturn;
	private LinearLayout relativeLayout;
	private Button btnBack;
	private EditText edText;
	private String sText ;
	private User cur_user;
	private List<LoveItemComment> LoveComment;
	private int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_love_news);

		initView();
		querydata();
		click();
	}

	private void initView() {
		relativeLayout = (LinearLayout) findViewById(R.id.lin_loveitem_wall);
		tvLoveto = (TextView) findViewById(R.id.tv_loveitem_to);
		tvLovefrom = (TextView) findViewById(R.id.tv_loveitem_from);
		tvlove = (TextView) findViewById(R.id.tx_loveitem_text);
		tvReturn = (TextView) findViewById(R.id.tv_loveitem_return);
		btnBack = (Button) findViewById(R.id.btn_loveitem_back);
		tvComment = (TextView) findViewById(R.id.tv_loveitem_comment);
		edText = (EditText) findViewById(R.id.ed_loveitem_comment);
		
		tvLoveto.setText( getIntent().getStringExtra("toname"));
		tvLovefrom.setText( getIntent().getStringExtra("fromname"));
		tvlove.setText( getIntent().getStringExtra("love"));
		
	}

	private void click() {
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		tvComment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				cur_user = BmobUser.getCurrentUser(ToloveItemActivity.this, User.class);
				LoveItemComment loveItemComment = new LoveItemComment();
				Love mlove = new Love();
				String getloveitemid = getIntent().getStringExtra("id");
				String user_loveitemname = cur_user.getUsername();
				TextView tx_loveitem = new TextView(ToloveItemActivity.this);
				tx_loveitem.setTextSize(15);
				tx_loveitem.setPadding(20, 15, 5, 7);
				String content_loveitem = edText.getText().toString();
				tx_loveitem.setText("用户：" + user_loveitemname + "\n" + content_loveitem);
				mlove.setObjectId(getloveitemid);
				loveItemComment.setContent(content_loveitem);
				loveItemComment.setLove(mlove);
				loveItemComment.setUser(cur_user);
				loveItemComment.setCommentname(user_loveitemname);
				loveItemComment.save(ToloveItemActivity.this, new SaveListener() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						Toast.makeText(ToloveItemActivity.this, "评论已添加",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						Toast.makeText(ToloveItemActivity.this, "服务器暂未响应！",
								Toast.LENGTH_SHORT).show();
					}
				});

				relativeLayout.addView(tx_loveitem);

			
			}
		});
	}
	
	private void querydata() {
		BmobQuery<LoveItemComment> query = new BmobQuery<LoveItemComment>();
		String i = getIntent().getStringExtra("id");
		Love mlove = new Love();
		mlove.setObjectId(i);
		query.addWhereEqualTo("love", new BmobPointer(mlove));
		query.findObjects(this, new FindListener<LoveItemComment>() {

			@Override
			public void onSuccess(List<LoveItemComment> object) {
				// TODO Auto-generated method stub
				for (LoveItemComment content : object) {
					
					String c = content.getContent();
					String name = content.getCommentname();
					if(c == null || name == null){
						tvReturn.setText("  ");
					}
				
					TextView tx = new TextView(ToloveItemActivity.this);
					tx.setTextSize(15);
					tx.setPadding(20, 15, 5, 7);
					tx.setText("用户：" + name + "\n" + c);
					relativeLayout.addView(tx);
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(ToloveItemActivity.this, "服务器暂未响应！",
						Toast.LENGTH_SHORT).show();
				tvReturn.setText("");
			}
		});

	}

	
	
	
	@Override
	public void onContentChanged() {
		// TODO Auto-generated method stub
		super.onContentChanged();
		count++;
	}
}
