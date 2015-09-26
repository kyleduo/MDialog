package com.kyleduo.mdialog.demo;

import android.app.Application;

/**
 * Created by kyle on 15/9/24.
 */
public class KApplication extends Application {

	private static KApplication sKApplication;

	public static KApplication getApplication() {
		return sKApplication;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		sKApplication = this;
	}
}
