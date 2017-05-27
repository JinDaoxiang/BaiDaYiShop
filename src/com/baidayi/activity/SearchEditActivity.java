package com.baidayi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchEditActivity extends Activity {

	private EditText search_edit_box = null;
	
	//private ImageButton search_image_btn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_search);
		
		search_edit_box = (EditText) findViewById(R.id.SearchEdittext);
		
		//search_image_btn = (ImageButton) findViewById(R.id.SearchImage);

	}
}
