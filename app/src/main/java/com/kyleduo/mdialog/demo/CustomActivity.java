package com.kyleduo.mdialog.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.kyleduo.mdialog.MDialog;

/**
 * Created by kyle on 15/9/26.
 */
public class CustomActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom);

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayShowHomeEnabled(true);
			actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		findViewById(R.id.custom_button_bt).setOnClickListener(this);
		findViewById(R.id.custom_dialog_bt).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.custom_button_bt:
				showCustomButton();
				break;
			case R.id.custom_dialog_bt:
				showCustomDialog();
				break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showCustomButton() {
		new MDialog.Builder(this).setTitle("Custom Button").setMessage("Easily customize buttons in Dialog with background and text color.")
				.setPositiveButton("OK", null)
				.setNegativeButton("Cancel", null)
				.setNormalButtonTextColor(Color.parseColor("#666666"))
				.setPrimaryButtonTextColor(Color.parseColor("#666666"))
				.setNormalButtonBackgroundRes(R.drawable.dialog_bt_bg_neg)
				.setPrimaryButtonBackgroundRes(R.drawable.dialog_bt_bg_pos)
				.setCancelable(false)
				.create().show();
	}

	private void showCustomDialog() {
		new MDialog.Builder(this, R.layout.layout_dialog).setTitle("Custom Dialog").setMessage("Customize MDialog with a new layout.xml file and use ids start with \"mdialog_\", everything changed with nothing change.\n:D")
				.setPositiveButton("OK", null)
				.setNegativeButton("Cancel", null)
				.setCancelable(false)
				.create().show();
	}
}
