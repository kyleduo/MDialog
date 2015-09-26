package com.kyleduo.mdialog.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

@SuppressLint("CommitPrefEdits")
public class SPUtil {

	private static final String SP_NAME = "mdialog";

	private static Context getContext() {
		return KApplication.getApplication();
	}

	private static SharedPreferences getSP() {
		return getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	}

	public static void saveString(String key, String value) {
		Editor editor = getSP().edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(String key) {
		String value = getSP().getString(key, "");
		if (TextUtils.isEmpty(value)) {
			return "";
		}
		return value;
	}

	public static void saveBoolean(String key, boolean value) {
		Editor editor = getSP().edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		boolean value = getSP().getBoolean(key, defaultValue);
		return value;
	}

	public static int getInt(String key) {
		return getInt(key, 0);
	}

	public static int getInt(String key, int defaultValue) {
		int value = getSP().getInt(key, defaultValue);
		return value;
	}

	public static void saveInt(String key, int value) {
		Editor editor = getSP().edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static long getLong(String key) {
		return getLong(key, 0);
	}

	public static long getLong(String key, long defaultValue) {
		long value = getSP().getLong(key, defaultValue);
		return value;
	}

	public static void saveLong(String key, long value) {
		Editor editor = getSP().edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public static void remove(String key) {
		if (key == null || key.isEmpty()) {
			return;
		}
		Editor editor = getSP().edit();
		editor.remove(key);
		editor.commit();
	}
}
