package com.wislight.parkmanage.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class HttpUtil {
	static String result = "";
	
	public static String httpGet(String url, String userkey, String str,
			String sign) {

		RequestParams params = new RequestParams();
		params.addQueryStringParameter("userkey", userkey);
		params.addQueryStringParameter("str", str);
		params.addQueryStringParameter("sign", sign);
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 10); // 设置超时时间 10s
//		http.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//			@Override
//			public void onLoading(long total, long current, boolean isUploading) {
//
//			}
//
//			@Override
//			public void onSuccess(ResponseInfo<String> responseInfo) {
//				result = responseInfo.result.toString();
//			}
//
//			@Override
//			public void onStart() {
//
//			}
//
//			@Override
//			public void onFailure(HttpException error, String msg) {
//			}
//		});
//
	return result;
	}
	
	public static String httpPost(String url, String userkey, String str,
			String sign) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("userkey", userkey);
		params.addQueryStringParameter("str", str);
		params.addQueryStringParameter("sign", sign);

		// 只包含字符串参数时默认使用BodyParamsEntity，
		// 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
		// params.addBodyParameter("name", "value");

		// 加入文件参数后默认使用MultipartEntity（"multipart/form-data"），
		// 如需"multipart/related"，xUtils中提供的MultipartEntity支持设置subType为"related"。
		// 使用params.setBodyEntity(httpEntity)可设置更多类型的HttpEntity（如：
		// MultipartEntity,BodyParamsEntity,FileUploadEntity,InputStreamUploadEntity,StringEntity）。
		// 例如发送json参数：params.setBodyEntity(new StringEntity(jsonStr,charset));

		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 10); // 设置超时时间 10s
//		http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
//
//			@Override
//			public void onStart() {
//			}
//
//			@Override
//			public void onLoading(long total, long current, boolean isUploading) {
//			}
//
//			@Override
//			public void onSuccess(ResponseInfo<String> responseInfo) {
//				result = responseInfo.result.toString();
//			}
//
//			@Override
//			public void onFailure(HttpException error, String msg) {
//
//			}
//		});

		return result;
	}
}
