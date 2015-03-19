package com.example.teamlab.myapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.teamlab.myapplication.BuildConfig;

public class NetworkUtil {


	public static final String TAG = NetworkUtil.class.getSimpleName();

	/**
	 * 判断网络是否链接状态（wifi或3g）
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isOnline(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (BuildConfig.DEBUG) {
			NetworkInfo networkInfo = cm.getActiveNetworkInfo();
			if (null != networkInfo) {
				Log.i(TAG, networkInfo.getTypeName());
			}
		}

		return cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}

	/**
	 * 判断当前网络是否是Wifi网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifi(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = cm.getActiveNetworkInfo();

		if (null != networkInfo && networkInfo.isConnectedOrConnecting()) {
			return networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
		}

		return false;
	}
	
}
