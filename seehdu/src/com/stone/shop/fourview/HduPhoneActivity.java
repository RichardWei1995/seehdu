package com.stone.shop.fourview;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.jw.seehdu.R;
import com.jw.shop.adapter.HduPhoneAdapter;
import com.jw.shop.model.HduPhone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class HduPhoneActivity extends Activity {
	private ListView lvmain;
	private Button btnBack, btnSearch;
	private List<HduPhone> mListHduPhone;
	private HduPhoneAdapter mhduPhoneAdapter;
	private EditText etSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hduphone);

		initview();
		queryData();
		click();

	}

	private void queryData() {
		// TODO Auto-generated method stub
		BmobQuery<HduPhone> query = new BmobQuery<HduPhone>();
		query.order("-createdAt");
		query.findObjects(this, new FindListener<HduPhone>() {

			@Override
			public void onSuccess(List<HduPhone> object) {

				if (object.size() > 0) {
					for (HduPhone hduPhone : object) {
						mListHduPhone.add(hduPhone);

					}
					mhduPhoneAdapter
							.refresh((ArrayList<HduPhone>) mListHduPhone);
					mhduPhoneAdapter.notifyDataSetChanged();

				} else {
					Toast.makeText(HduPhoneActivity.this, "没有更多数据！",
							Toast.LENGTH_SHORT).show();
				}

			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

				Toast.makeText(HduPhoneActivity.this, "服务器出错！",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void initview() {

		lvmain = (ListView) findViewById(R.id.lv_hduphone);
		btnBack = (Button) findViewById(R.id.btn_hduphone_back);
		btnSearch = (Button) findViewById(R.id.btn_hduphone_search);

		etSearch = (EditText) findViewById(R.id.et_search);

		mListHduPhone = new ArrayList<HduPhone>();
		mhduPhoneAdapter = new HduPhoneAdapter(this, mListHduPhone);
		lvmain.setAdapter(mhduPhoneAdapter);

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

		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String phoneName = etSearch.getText().toString();
				
				if(phoneName != ""){
					BmobQuery<HduPhone> query = new BmobQuery<HduPhone>();
					query.order("-createdAt");
					query.addWhereEqualTo("name", phoneName);
					query.findObjects(HduPhoneActivity.this,
							new FindListener<HduPhone>() {

								@Override
								public void onSuccess(List<HduPhone> object) {
									// TODO Auto-generated method stub
									mListHduPhone.clear();
									if (object.size() > 0) {

										for (HduPhone hduPhone : object) {
											mListHduPhone.add(hduPhone);

										}
										mhduPhoneAdapter
												.refresh((ArrayList<HduPhone>) mListHduPhone);
										mhduPhoneAdapter.notifyDataSetChanged();

									} else {

										Toast.makeText(HduPhoneActivity.this,
												"未查询到相关信息！", Toast.LENGTH_SHORT)
												.show();
										btnSearch.setClickable(false);
										btnSearch.setVisibility(View.GONE);
									}

								}

								@Override
								public void onError(int arg0, String arg1) {
									// TODO Auto-generated method stub
									Toast.makeText(HduPhoneActivity.this,
											"null", Toast.LENGTH_SHORT).show();
								}
							});
					
				}else{
					return;
					
					
				}
			
					
				}

			private void requeryData() {
				// TODO Auto-generated method stub
				
				BmobQuery<HduPhone> query2 = new BmobQuery<HduPhone>();
				query2.order("-createdAt");
				query2.findObjects(HduPhoneActivity.this, new FindListener<HduPhone>() {

					@Override
					public void onSuccess(List<HduPhone> object) {

						mListHduPhone.clear();
						if (object.size() > 0) {
							for (HduPhone hduPhone : object) {
								mListHduPhone.add(hduPhone);

							}
							mhduPhoneAdapter
									.refresh((ArrayList<HduPhone>) mListHduPhone);
							mhduPhoneAdapter.notifyDataSetChanged();

						} else {
							Toast.makeText(HduPhoneActivity.this, "没有更多数据！",
									Toast.LENGTH_SHORT).show();
						}

					}

					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub

						Toast.makeText(HduPhoneActivity.this, "服务器出错！",
								Toast.LENGTH_SHORT).show();
					}
				});
			}

			
		});

	}

}
