package com.baidayi.activity;

import com.baidayi.adpter.CollectAdapter;
import com.baidayi.db.CollectManage;
import com.baidayi.db.DataBaseHelper;
import com.baidayi.swipback.SwipeBackActivity;
import com.baidayi.utils.ListUtil;
import com.baidayi.widget.FilpperListvew;
import com.baidayi.widget.FilpperListvew.FilpperDeleteListener;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * �������� �ҵ��ղ�
 * 
 * @author: wll
 */
public class FragmentCollectActivity extends SwipeBackActivity {

	private FilpperListvew listView;
	CollectAdapter adapter;
	private int width;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_collect);
		ListUtil.collects = new CollectManage().getProducts(this, null);
		adapter = new CollectAdapter(this, ListUtil.collects);
		listView = (FilpperListvew) findViewById(R.id.clloect_listview);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(FragmentCollectActivity.this,
						CollectDetailsActivity.class);
				intent.putExtra("index", arg2);
				startActivity(intent);
			}
		});
		listView.setFilpperDeleteListener(new FilpperDeleteListener() {

			@Override
			public void filpperDelete(float xPosition, float yPosition) {
				// listview��Ҫ��item�����򷵻�
				if (listView.getChildCount() == 0)
					return;
				// ���������û���ɾ����item��index
				final int index = listView.pointToPosition((int) xPosition,
						(int) yPosition);
				// һ�������ǻ�ø���Ŀ����Ļ��ʾ�е����λ�ã�ֱ�Ӹ���indexɾ�����ָ��쳣����Ϊlistview�е�childֻ�е�ǰ����Ļ����ʾ�ĲŲ���Ϊ��
				int firstVisiblePostion = listView.getFirstVisiblePosition();
				View view = listView.getChildAt(index - firstVisiblePostion);

				TranslateAnimation tranAnimation = new TranslateAnimation(0,
						width, 0, 0);
				tranAnimation.setDuration(500);
				tranAnimation.setFillAfter(true);
				view.startAnimation(tranAnimation);
				// ������������Ϻ�ɾ�������򲻻���ֶ���Ч�����Լ�����ģ���
				tranAnimation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {

					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// ɾ��һ��item
						String itemName = new CollectManage()
								.getProducts(getApplicationContext(), null)
								.get(index).getProductName();
						SQLiteDatabase sqLiteDatabase = DataBaseHelper
								.getInstance(getApplicationContext())
								.getWritableDatabase();
						sqLiteDatabase.execSQL("DELETE FROM " + "CollectList"
								+ " WHERE productName = '" + itemName + "'");
						sqLiteDatabase.close();
						DataBaseHelper.closeDB();
						adapter.setProducts(new CollectManage().getProducts(
								getApplicationContext(), null));
						adapter.notifyDataSetChanged();

					}
				});
			}
		});
		setEdgeFromLeft();
	}
}
