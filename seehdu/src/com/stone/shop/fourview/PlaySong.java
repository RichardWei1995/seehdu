package com.stone.shop.fourview;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

import com.jw.base.MainActivity1;
import com.jw.seehdu.R;
import com.jw.shop.adapter.RadioAdapter;
import com.jw.shop.model.DalyNews;
import com.jw.shop.model.Loadsong;
import com.jw.shop.model.Union;
import com.stone.shop.thirdview.LostActivity;
import com.stone.shop.view.NewsDaylyActivity;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PlaySong extends Activity implements OnItemClickListener {

	private ListView listView;
	private List<Loadsong> mLoadsong;
	private RadioAdapter adapter;
	private Button btnBack;
	private TextView tvTitle;
	private Dialog mDialog;

	private static final String APP_ID = "wx9622f3e3368d5c25";
	private IWXAPI api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playsong);
		api = WXAPIFactory.createWXAPI(this, APP_ID);
		api.registerApp(APP_ID);

		initview();
		queryData();
		click();

	}

	private void click() {
		// TODO Auto-generated method stub
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});

	}

	private void queryData() {
		// TODO Auto-generated method stub
		BmobQuery<Loadsong> query = new BmobQuery<Loadsong>();
		query.order("-createdAt");
		showRoundProcessDialog(this, R.layout.loding);
		query.findObjects(this, new FindListener<Loadsong>() {

			@Override
			public void onSuccess(List<Loadsong> object) {
				// TODO Auto-generated method stub
				mDialog.cancel();
				if (object.size() > 0) {
					for (Loadsong loadsong : object) {
						mLoadsong.add(loadsong);

					}
					adapter.refresh((ArrayList<Loadsong>) mLoadsong);
					adapter.notifyDataSetChanged();

				} else {

				}

			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void initview() {
		listView = (ListView) findViewById(R.id.lv_radio);
		btnBack = (Button) findViewById(R.id.btn_radio_back);
		tvTitle = (TextView) findViewById(R.id.tv_radio_title);
		tvTitle.setText("英语在线Radio");

		mLoadsong = new ArrayList<Loadsong>();
		adapter = new RadioAdapter(this, mLoadsong);
		listView.setAdapter(adapter);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, final int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "暂仅提供试听！", Toast.LENGTH_SHORT).show();

	}

	//
	// WXWebpageObject webpage = new WXWebpageObject();
	// webpage.webpageUrl = url;
	// WXMediaMessage msg = new WXMediaMessage(webpage);
	// msg.title = title;
	// msg.description = "application test";
	// msg.mediaObject = webpage;
	// Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
	// R.drawable.ic_seehdu);
	// Bitmap thumbitmap = Bitmap.createScaledBitmap(bitmap, 120, 150,
	// true);
	// bitmap.recycle();
	// msg.thumbData = bmpToArray(thumbitmap, true);
	// SendMessageToWX.Req req = new SendMessageToWX.Req();
	// req.transaction = String.valueOf(System.currentTimeMillis());
	// req.message = msg;
	// req.scene = SendMessageToWX.Req.WXSceneTimeline;
	//
	// api.sendReq(req);
	//
	// }
	// }
	//
	// // 二进制缩略图
	// private byte[] bmpToArray(final Bitmap bitmap, final Boolean needrecycle)
	// {
	// ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	// bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
	// if (needrecycle) {
	// bitmap.recycle();
	// }
	// byte[] result = outputStream.toByteArray();
	// try {
	// outputStream.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return result;
	// }

	public void showRoundProcessDialog(Context mContext, int layout) {
		mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.show();
		mDialog.setContentView(layout);
	}

}
