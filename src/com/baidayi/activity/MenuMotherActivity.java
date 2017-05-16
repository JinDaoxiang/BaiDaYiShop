package com.baidayi.activity;

import com.baidayi.adpter.MenuViewAdapter;
import com.baidayi.swipback.SwipeBackActivity;
import com.baidayi.widget.MyGridView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 侧滑菜单----母婴用品分类
 * 
 * @author: wll
 */
public class MenuMotherActivity extends SwipeBackActivity {

	private MyGridView menu_mother_gridview;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_mother);
		menu_mother_gridview = (MyGridView) findViewById(R.id.menu_mother_gridview);
		int[] images = new int[] { R.drawable.t5, R.drawable.t6, R.drawable.t7,
				R.drawable.t8, R.drawable.t9, R.drawable.t10, R.drawable.t5,
				R.drawable.t6 };
		menu_mother_gridview.setAdapter(new MenuViewAdapter(this, images));
		menu_mother_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					intent = new Intent();
					intent.setClass(MenuMotherActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 37);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent();
					intent.setClass(MenuMotherActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 37);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent();
					intent.setClass(MenuMotherActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 37);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent();
					intent.setClass(MenuMotherActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 37);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent();
					intent.setClass(MenuMotherActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 37);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent();
					intent.setClass(MenuMotherActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 37);
					startActivity(intent);
					break;
				case 6:
					intent = new Intent();
					intent.setClass(MenuMotherActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 37);
					startActivity(intent);
					break;
				case 7:
					intent = new Intent();
					intent.setClass(MenuMotherActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 37);
					startActivity(intent);
					break;
				}
			}
		});
	}

}
