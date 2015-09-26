package com.kyleduo.mdialog.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kyleduo.mdialog.MDialog;

/**
 * Created by kyle on 15/9/26.
 */
public class CompareActivity extends AppCompatActivity {


	private EditText mTitleEt, mMessageEt, mPosEt, mNegEt, mOtherEt;

	private DialogInterface.OnClickListener posClickListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			toast("click positive button");
		}
	};
	private DialogInterface.OnClickListener negClickListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			toast("click negative button");
		}
	};
	private DialogInterface.OnClickListener otherClickListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			toast("click other button");
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compare);

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayShowHomeEnabled(true);
			actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		mTitleEt = (EditText) findViewById(R.id.et_title);
		mMessageEt = (EditText) findViewById(R.id.et_message);
		mPosEt = (EditText) findViewById(R.id.et_pos);
		mNegEt = (EditText) findViewById(R.id.et_neg);
		mOtherEt = (EditText) findViewById(R.id.et_other);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void toast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	private String getDialogTitle() {
		return mTitleEt.getText().toString().trim();
	}

	private String getDialogMessage() {
		return mMessageEt.getText().toString().trim();
	}

	private String getDialogPosText() {
		return mPosEt.getText().toString().trim();
	}

	private String getDialogNegText() {
		return mNegEt.getText().toString().trim();
	}

	private String getDialogOtherText() {
		return mOtherEt.getText().toString().trim();
	}

	public void showMDialog(View view) {
		String title = getDialogTitle();
		String msg = getDialogMessage();
		String pos = getDialogPosText();
		String neg = getDialogNegText();
		String other = getDialogOtherText();

		MDialog.Builder builder = new MDialog.Builder(this);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		if (!TextUtils.isEmpty(msg)) {
			builder.setMessage(msg);
		}
		if (!TextUtils.isEmpty(pos)) {
			builder.setPositiveButton(pos, posClickListener);
		}
		if (!TextUtils.isEmpty(neg)) {
			builder.setNegativeButton(neg, negClickListener);
		}
		if (!TextUtils.isEmpty(other)) {
			builder.setOtherButton(other, otherClickListener);
		}
		MDialog md = builder.create();
		md.show();
	}

	public void showSysDialog(View view) {
		String title = getDialogTitle();
		String msg = getDialogMessage();
		String pos = getDialogPosText();
		String neg = getDialogNegText();
		String other = getDialogOtherText();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		if (!TextUtils.isEmpty(msg)) {
			builder.setMessage(msg);
		}
		if (!TextUtils.isEmpty(pos)) {
			builder.setPositiveButton(pos, posClickListener);
		}
		if (!TextUtils.isEmpty(neg)) {
			builder.setNegativeButton(neg, negClickListener);
		}
		if (!TextUtils.isEmpty(other)) {
			builder.setNeutralButton(other, otherClickListener);
		}
		AlertDialog ad = builder.create();
		ad.show();
	}


	public void showCompatDialog(View view) {
		String title = getDialogTitle();
		String msg = getDialogMessage();
		String pos = getDialogPosText();
		String neg = getDialogNegText();
		String other = getDialogOtherText();

		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		if (!TextUtils.isEmpty(msg)) {
			builder.setMessage(msg);
		}
		if (!TextUtils.isEmpty(pos)) {
			builder.setPositiveButton(pos, posClickListener);
		}
		if (!TextUtils.isEmpty(neg)) {
			builder.setNegativeButton(neg, negClickListener);
		}
		if (!TextUtils.isEmpty(other)) {
			builder.setNeutralButton(other, otherClickListener);
		}
		android.support.v7.app.AlertDialog ad = builder.create();
		ad.show();
	}


	@Override
	protected void onStop() {
		super.onStop();
		SPUtil.saveString("title", getDialogTitle());
		SPUtil.saveString("message", getDialogMessage());
		SPUtil.saveString("pos_button", getDialogPosText());
		SPUtil.saveString("neg_button", getDialogNegText());
		SPUtil.saveString("other_button", getDialogOtherText());
	}

	@Override
	protected void onStart() {
		super.onStart();
		mTitleEt.setText(SPUtil.getString("title"));
		mMessageEt.setText(SPUtil.getString("message"));
		mPosEt.setText(SPUtil.getString("pos_button"));
		mNegEt.setText(SPUtil.getString("neg_button"));
		mOtherEt.setText(SPUtil.getString("other_button"));
	}

}
