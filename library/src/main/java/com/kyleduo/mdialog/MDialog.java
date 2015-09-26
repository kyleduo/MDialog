package com.kyleduo.mdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MDialog extends Dialog {

	@SuppressWarnings("unused")
	private static final String TAG = "MDialog";

	protected MDialog(Context context) {
		super(context);
	}

	protected MDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void setTitle(CharSequence title) {
		TextView titleTextView = ((TextView) findViewById(R.id.mdialog_title_tv));
		if (titleTextView.getVisibility() != View.VISIBLE) {
			titleTextView.setVisibility(View.VISIBLE);
		}
		titleTextView.setText(title);
	}

	public static class Builder implements OnShowListener, View.OnClickListener, View.OnTouchListener {

		private Context mContext;
		private String mTitleText;
		private String mMessageText;
		private View mContentView;
		private View mRootView;
		private TextView mTitleTextView;
		private TextView mMessageTextView;
		private Button mPositiveButton, mNegativeButton, mOtherButton;
		private String mPositiveButtonText, mNegativeButtonText, mOtherButtonText;
		private OnClickListener mPositiveClickListener, mNegativeClickListener, mOtherClickListener;
		private OnDismissListener mOnDismissListener;
		private OnCancelListener mOnCancelListener;
		private OnShowListener mOnShowListener;
		private ViewGroup mContentWrapper;
		private ViewGroup mButtonWrapper;
		private ViewGroup mContentTitleWrapper;
		private boolean mCanceledOnTouchOutside;
		private boolean mCancelable;
		private MDialog mDialog;
		private boolean mPosAutoDismiss, mNegAutoDismiss, mOtherAutoDismiss;
		private int mTitleMaxLines;
		private boolean mContentPadding;

		private int mNormalButtonBackgroundRes, mPrimaryButtonBackgroundRes, mNormalButtonTextColor, mPrimaryButtonTextColor;

		private int mDialogLayoutRes;

		public Builder(Context context) {
			this(context, R.layout.layout_mdialog);
		}

		public Builder(Context context, int dialogLayoutRes) {
			this.mContext = context;
			this.mTitleText = "";
			this.mCancelable = true;
			this.mCanceledOnTouchOutside = true;
			this.mPosAutoDismiss = this.mNegAutoDismiss = this.mOtherAutoDismiss = true;
			this.mTitleMaxLines = 1;
			this.mContentPadding = true;
			this.mDialogLayoutRes = dialogLayoutRes;
			this.mNormalButtonBackgroundRes = R.drawable.mdialog_button_bg;
			this.mPrimaryButtonBackgroundRes = R.drawable.mdialog_button_primary_bg;
			this.mNormalButtonTextColor = Integer.MAX_VALUE;
			this.mPrimaryButtonTextColor = Integer.MAX_VALUE;
			this.mNormalButtonBackgroundRes = 0;
			this.mPrimaryButtonBackgroundRes = 0;
		}

		public Builder setTitle(String title) {
			this.mTitleText = title;
			return this;
		}

		public Builder setTitle(int titleRes) {
			this.mTitleText = this.mContext.getString(titleRes);
			return this;
		}

		public Builder setMessage(String message) {
			this.mMessageText = message;
			return this;
		}

		public Builder setMessage(int messageRes) {
			String message = this.mContext.getString(messageRes);
			return setMessage(message);
		}

		public Builder setContentView(View contentView) {
			this.mContentView = contentView;
			return this;
		}

		public Builder setContentView(int contentViewRes) {
			View contentView = LayoutInflater.from(this.mContext).inflate(contentViewRes, null);
			return setContentView(contentView);
		}

		public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
			this.mCanceledOnTouchOutside = canceledOnTouchOutside;
			return this;
		}

		public Builder setCancelable(boolean cancelable) {
			this.mCancelable = cancelable;
			return this;
		}

		/**
		 * 设置对话框在点击按钮的时候，自动dismiss
		 *
		 * @param autoDismiss 应用于全部按钮
		 * @return
		 */
		public Builder setAutoDismiss(boolean autoDismiss) {
			return setAutoDismiss(autoDismiss, autoDismiss, autoDismiss);
		}

		/**
		 * 设置对话框在点击按钮的时候，自动dismiss
		 *
		 * @param posAutoDismiss   POS按钮
		 * @param negAutoDismiss   NEG按钮
		 * @param otherAutoDismiss OTHER按钮
		 * @return
		 */
		public Builder setAutoDismiss(boolean posAutoDismiss, boolean negAutoDismiss, boolean otherAutoDismiss) {
			this.mPosAutoDismiss = posAutoDismiss;
			this.mNegAutoDismiss = negAutoDismiss;
			this.mOtherAutoDismiss = otherAutoDismiss;
			return this;
		}

		/**
		 * 设置标题最多有几行
		 *
		 * @param lines
		 * @return
		 */
		public Builder setTitleMaxLines(int lines) {
			this.mTitleMaxLines = lines;
			return this;
		}

		public Builder setPositiveButton(String text, OnClickListener positiveClickListener) {
			this.mPositiveButtonText = text;
			this.mPositiveClickListener = positiveClickListener;
			return this;
		}

		public Builder setPositiveButton(int textRes, OnClickListener positiveClickListener) {
			String positiveButtonText = this.mContext.getString(textRes);
			return this.setPositiveButton(positiveButtonText, positiveClickListener);
		}

		public Builder setNegativeButton(String text, OnClickListener negativeClickListener) {
			this.mNegativeButtonText = text;
			this.mNegativeClickListener = negativeClickListener;
			return this;
		}

		public Builder setNegativeButton(int textRes, OnClickListener negativeClickListener) {
			String negativeButtonText = this.mContext.getString(textRes);
			return this.setNegativeButton(negativeButtonText, negativeClickListener);
		}

		public Builder setOtherButton(int textRes, OnClickListener otherClickListener) {
			String otherButtonText = this.mContext.getString(textRes);
			return this.setOtherButton(otherButtonText, otherClickListener);
		}

		public Builder setOtherButton(String text, OnClickListener otherClickListener) {
			this.mOtherButtonText = text;
			this.mOtherClickListener = otherClickListener;
			return this;
		}

		public Builder setOnDismissListener(OnDismissListener listener) {
			this.mOnDismissListener = listener;
			return this;
		}

		public Builder setOnCancelListener(OnCancelListener listener) {
			this.mOnCancelListener = listener;
			return this;
		}

		public Builder setOnShowListener(OnShowListener listener) {
			this.mOnShowListener = listener;
			return this;
		}

		public Builder setContentPadding(boolean padding) {
			this.mContentPadding = padding;
			return this;
		}

		public int getPrimaryButtonTextColor() {
			return mPrimaryButtonTextColor;
		}

		public Builder setPrimaryButtonTextColor(int primaryButtonTextColor) {
			mPrimaryButtonTextColor = primaryButtonTextColor;
			return this;
		}

		public int getNormalButtonTextColor() {
			return mNormalButtonTextColor;
		}

		public Builder setNormalButtonTextColor(int normalButtonTextColor) {
			mNormalButtonTextColor = normalButtonTextColor;
			return this;
		}

		public int getPrimaryButtonBackgroundRes() {
			return mPrimaryButtonBackgroundRes;
		}

		public Builder setPrimaryButtonBackgroundRes(int primaryButtonBackgroundRes) {
			mPrimaryButtonBackgroundRes = primaryButtonBackgroundRes;
			return this;
		}

		public int getNormalButtonBackgroundRes() {
			return mNormalButtonBackgroundRes;
		}

		public Builder setNormalButtonBackgroundRes(int normalButtonBackgroundRes) {
			mNormalButtonBackgroundRes = normalButtonBackgroundRes;
			return this;
		}

		public MDialog create() {
			prepareView();
			this.mDialog = new MDialog(this.mContext, R.style.MDialog_Dialog);
			mDialog.setContentView(mRootView);
			this.mDialog.setOnShowListener(this);

			// title
			if (this.mTitleTextView != null) {
				if (TextUtils.isEmpty(this.mTitleText)) {
					if (this.mTitleTextView.getVisibility() != View.GONE) {
						this.mTitleTextView.setVisibility(View.GONE);
					}
				} else {
					if (this.mTitleTextView.getVisibility() != View.VISIBLE) {
						this.mTitleTextView.setVisibility(View.VISIBLE);
					}
					if (this.mTitleMaxLines <= 1) {
						this.mTitleTextView.setSingleLine(true);
					} else {
						this.mTitleTextView.setSingleLine(false);
						this.mTitleTextView.setMaxLines(this.mTitleMaxLines);
					}
					this.mTitleTextView.setText(this.mTitleText);
				}
			}

			// buttons
			if (!TextUtils.isEmpty(this.mPositiveButtonText) || !TextUtils.isEmpty(this.mNegativeButtonText)) {

			} else {
				this.mCanceledOnTouchOutside = true;
			}

			// cancel
			this.mDialog.setCancelable(this.mCancelable);
			// if mCanceledOnTouchOutside == true, mDialog is always cancelable;
			this.mDialog.setCanceledOnTouchOutside(this.mCanceledOnTouchOutside);

			// positive
			if (mPositiveButton != null) {
				mPositiveButton.setAllCaps(false);
				if (!TextUtils.isEmpty(this.mPositiveButtonText)) {
					this.mPositiveButton.setText(mPositiveButtonText);
					this.mPositiveButton.setOnClickListener(this);
					if (!this.mPositiveButton.isShown()) {
						this.mPositiveButton.setVisibility(View.VISIBLE);
						if (mPrimaryButtonTextColor != Integer.MAX_VALUE) {
							this.mPositiveButton.setTextColor(mPrimaryButtonTextColor);
						}
						if (mPrimaryButtonBackgroundRes != 0) {
							this.mPositiveButton.setBackgroundResource(mPrimaryButtonBackgroundRes);
						}
					}
				} else {
					if (this.mPositiveButton.getVisibility() != View.GONE) {
						this.mPositiveButton.setVisibility(View.GONE);
					}
				}
			}

			// negative
			if (mNegativeButton != null) {
				mNegativeButton.setAllCaps(false);
				if (!TextUtils.isEmpty(this.mNegativeButtonText)) {
					this.mNegativeButton.setText(mNegativeButtonText);
					this.mNegativeButton.setOnClickListener(this);
					if (!this.mNegativeButton.isShown()) {
						this.mNegativeButton.setVisibility(View.VISIBLE);
						if (mNormalButtonTextColor != Integer.MAX_VALUE) {
							this.mNegativeButton.setTextColor(mNormalButtonTextColor);
						}
						if (mNormalButtonBackgroundRes != 0) {
							this.mNegativeButton.setBackgroundResource(mNormalButtonBackgroundRes);
						}
					}
				} else {
					if (this.mNegativeButton.getVisibility() != View.GONE) {
						this.mNegativeButton.setVisibility(View.GONE);
					}
				}
			}

			// other only shown with pos or neg
			if (this.mOtherButton != null) {
				mOtherButton.setAllCaps(false);
				if (!TextUtils.isEmpty(this.mOtherButtonText) && (!TextUtils.isEmpty(this.mPositiveButtonText) || !TextUtils.isEmpty(this.mNegativeButtonText))) {
					this.mOtherButton.setText(mOtherButtonText);
					this.mOtherButton.setOnClickListener(this);
					if (!this.mOtherButton.isShown()) {
						this.mOtherButton.setVisibility(View.VISIBLE);
						if (mNormalButtonTextColor != Integer.MAX_VALUE) {
							this.mOtherButton.setTextColor(mNormalButtonTextColor);
						}
						if (mNormalButtonBackgroundRes != 0) {
							this.mOtherButton.setBackgroundResource(mNormalButtonBackgroundRes);
						}
					}
				} else {
					if (this.mOtherButton.getVisibility() != View.GONE) {
						this.mOtherButton.setVisibility(View.GONE);
					}
				}
			}

			// content padding
			if (!this.mContentPadding) {
				this.mContentTitleWrapper.setPadding(0, 0, 0, 0);
			}

			this.mRootView.setMinimumWidth((int) this.mContext.getResources().getDimension(R.dimen.mdialog_min_width));
			this.mRootView.setOnTouchListener(this);

			// content view
			if (this.mContentView == null) {
				if (TextUtils.isEmpty(this.mMessageText)) {
					if (mContentWrapper != null) {
						mContentWrapper.setVisibility(View.GONE);
					}
				} else {
					if (mContentWrapper != null) {
						if (!mContentWrapper.isShown()) {
							mContentWrapper.setVisibility(View.VISIBLE);
						}
					}
					mMessageTextView.setText(this.mMessageText);
				}
			} else {
				if (this.mContentView.getParent() != null) {
					((ViewGroup) this.mContentView.getParent()).removeView(this.mContentView);
				}
				if (mContentWrapper != null) {
					this.mContentWrapper.removeAllViews();
					LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					this.mContentWrapper.addView(mContentView, params);
				}
			}

			// listener
			if (this.mOnCancelListener != null) {
				mDialog.setOnCancelListener(this.mOnCancelListener);
			}

			if (this.mOnDismissListener != null) {
				mDialog.setOnDismissListener(this.mOnDismissListener);
			}

			return this.mDialog;
		}

		private void prepareView() {
			LayoutInflater inflater = LayoutInflater.from(this.mContext);
			this.mRootView = inflater.inflate(this.mDialogLayoutRes, new LinearLayout(this.mContext), false);
			this.mTitleTextView = (TextView) this.mRootView.findViewById(R.id.mdialog_title_tv);
			this.mContentTitleWrapper = (ViewGroup) this.mRootView.findViewById(R.id.mdialog_content_title_wrapper);
			this.mMessageTextView = (TextView) this.mRootView.findViewById(R.id.mdialog_message_tv);
			this.mContentWrapper = (ViewGroup) this.mRootView.findViewById(R.id.mdialog_content_wrapper);
			this.mButtonWrapper = (ViewGroup) this.mRootView.findViewById(R.id.mdialog_bt_wrapper);
			this.mPositiveButton = (Button) this.mRootView.findViewById(R.id.mdialog_positive_bt);
			this.mNegativeButton = (Button) this.mRootView.findViewById(R.id.mdialog_negative_bt);
			this.mOtherButton = (Button) this.mRootView.findViewById(R.id.mdialog_other_bt);
		}

		@Override
		public void onShow(DialogInterface dialog) {

			WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
			params.width = mContext.getResources().getDisplayMetrics().widthPixels;
			mDialog.getWindow().setAttributes(params);

			if (mOnShowListener != null) {
				mOnShowListener.onShow(dialog);
			}
		}

		@Override
		public void onClick(View v) {
			boolean autoDismiss = true;
			if (v.getId() == R.id.mdialog_positive_bt) {
				if (this.mPositiveClickListener != null) {
					this.mPositiveClickListener.onClick(mDialog, BUTTON_POSITIVE);
				}
				autoDismiss = this.mPosAutoDismiss;
			} else if (v.getId() == R.id.mdialog_negative_bt) {
				if (this.mNegativeClickListener != null) {
					this.mNegativeClickListener.onClick(mDialog, BUTTON_NEGATIVE);
				}
				autoDismiss = this.mNegAutoDismiss;
			} else if (v.getId() == R.id.mdialog_other_bt) {
				if (this.mOtherClickListener != null) {
					this.mOtherClickListener.onClick(mDialog, BUTTON_NEUTRAL);
				}
				autoDismiss = this.mOtherAutoDismiss;
			}
			if (autoDismiss && mDialog != null && mDialog.isShowing()) {
				mDialog.dismiss();
			}
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (this.mCanceledOnTouchOutside && mDialog != null && mDialog.isShowing()) {
					mDialog.dismiss();
				}
				return true;
			}
			return false;
		}
	}

}
