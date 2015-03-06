package com.example.teamlab.myapplication.volley;

public interface VolleyConstant {
	public interface Network {
		// 网络请求超时设定
		int TIMEOUT = 10000; // 10 seconds
		// 网络请求失败重试
		int RETRY = 3; // 0: never retry
	}
}
