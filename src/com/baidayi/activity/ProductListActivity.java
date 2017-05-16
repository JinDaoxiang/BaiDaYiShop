package com.baidayi.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baidayi.adpter.ProductAdapter;
import com.baidayi.domain.Product;
import com.baidayi.service.GetProductService;
import com.baidayi.swipback.SwipeBackActivity;
import com.baidayi.utils.HttpsUtil;
import com.baidayi.utils.ListUtil;
import com.baidayi.widget.XListView;
import com.baidayi.widget.XListView.IXListViewListener;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * ��ʾ��Ʒ�б�
 * 
 * @author: wll
 */
public class ProductListActivity extends SwipeBackActivity implements
		IXListViewListener {
	private XListView productListView;
	// private View footView;
	private List<Product> products = new ArrayList<Product>();
	private ProductAdapter adapter;
	private int page = 1;
	private int menu = 0;
	private String search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_list_activity);
		productListView = (XListView) findViewById(R.id.product_listview);

		adapter = new ProductAdapter(this, products);
		productListView.setPullLoadEnable(true);
		productListView.setXListViewListener(this);
		productListView.setAdapter(adapter);

		productListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ProductListActivity.this,
						ProductDetailsActivity.class);
				intent.putExtra("index", arg2 - 1);
				intent.putExtra("flag", "productlist");
				startActivity(intent);
			}
		});
		menu = getIntent().getIntExtra("position", 0);
		search = getIntent().getStringExtra("search");
		if (HttpsUtil.isConnect(getApplicationContext())) {
			new MyAsyncTask().execute("");
		} else {
			Toast.makeText(getApplicationContext(), "���������������",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * �첽����
	 * 
	 * @author: wll
	 */
	public class MyAsyncTask extends AsyncTask<String, String, List<Product>> {

		@Override
		protected List<Product> doInBackground(String... params) {
			List<Product> tempProducts = new GetProductService().getProduct(
					page, menu, search);
			return tempProducts;
		}

		@Override
		protected void onPostExecute(List<Product> product) {
			ListUtil.products = products;
			adapter.setProducts(product);
			adapter.notifyDataSetChanged();
			onLoad();//
			if (!ListUtil.elementkey.equals("")) {
				Toast.makeText(getApplicationContext(), "��~~��Ʒ�Ѿ���������Ŷ������",
						Toast.LENGTH_SHORT).show();
			} else if (ListUtil.products.size() == 0) {
				Toast.makeText(getApplicationContext(), "��~~����ò�Ƴ��˵�����Ŷ������",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onRefresh() {
		if (HttpsUtil.isConnect(getApplicationContext())) {
			new MyAsyncTask().execute("");
		} else {
			Toast.makeText(getApplicationContext(), "���������������",
					Toast.LENGTH_SHORT).show();
			onLoad();//
		}
	}

	@Override
	public void onLoadMore() {
		if (HttpsUtil.isConnect(getApplicationContext())) {
			new MyAsyncTask().execute("");
		} else {
			Toast.makeText(getApplicationContext(), "���������������",
					Toast.LENGTH_SHORT).show();
			onLoad();//
		}
		page++;
	}

	/**
	 * ˢ�½���
	 */
	private void onLoad() {
		long now = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(now);
		String time = format.format(date);
		productListView.setRefreshTime(time);
		productListView.stopRefresh();
		productListView.stopLoadMore();
	}

}
