package com.kyleduo.mdialog.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by kyle on 15/8/14.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

	private static String[] sContents = new String[]{"Default style", "Custom stye", "http://kyleduo.com", "@Kyle_DUO\nApache License 2.0"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				sContents));

		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
			case 0:
				startActivity(new Intent(this, CompareActivity.class));
				break;

			case 1:
				startActivity(new Intent(this, CustomActivity.class));
				break;

			case 2: {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://kyleduo.com"));
				startActivity(intent);
				break;
			}
			case 3: {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://weibo.com/u/1762403573"));
				startActivity(intent);
				break;
			}
		}
	}
}
