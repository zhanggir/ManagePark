package com.wislight.parkmanage.config;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.wislight.parkmanage.model.loginRegiste.LoginActivity;
import com.wislight.parkmanage.utils.Util;

/**
 * 
 * 应用工具类
 *@create 2017/06/14
 * 
 */
public class Tools {

	private static Context mContext;

	static Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 200) {

				String result = msg.obj.toString();

				if (result.equals("\"error\"")) {

					Tools.toast(mContext, "身份信息有误,请重新登录!");
					Tools.clearSPDataWithInfo(mContext);
					mContext.startActivity(new Intent(mContext,
							LoginActivity.class));
					return;
				}

				String json = (String) msg.obj;

				JSONObject jo = null;
				try {
					jo = new JSONObject(json);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					String nickname = jo.getString("nickname");
					/* 昵称 */
					if (nickname == null || nickname.equals("")) {
						Log.v("nickname = null", "yes");
					} else {
						Log.v("nickname = null", "no");
						Log.v("nickname =  ", nickname);

						/* 将用户名保存到SP里 */
						SharedPreferences userinfoSP = mContext
								.getSharedPreferences("info",
										Context.MODE_PRIVATE);
						Editor editor = userinfoSP.edit();
						editor.putString("nickname", nickname);
						editor.commit();// 提交修改
					}
					/* 性别 */
					String sex = jo.getString("sex");
					if (sex == null || sex.equals("")) {

						Log.v("sex = null", "yes");
					} else {
						Log.v("sex = null", "no");
						Log.v("sex =  ", sex);

						/* 将用户名保存到SP里 */
						SharedPreferences userinfoSP = mContext
								.getSharedPreferences("info",
										Context.MODE_PRIVATE);
						Editor editor = userinfoSP.edit();
						editor.putString("sex", sex);
						editor.commit();// 提交修改
					}

					/* 生日 */
					String birthday = jo.getString("birthday");
					if (birthday == null || birthday.equals("")) {

						Log.v("birthday = null", "yes");
					} else {
						Log.v("birthday = null", "no");
						Log.v("birthday =  ", birthday);

						/* 将用户名保存到SP里 */
						SharedPreferences userinfoSP = mContext
								.getSharedPreferences("info",
										Context.MODE_PRIVATE);
						Editor editor = userinfoSP.edit();
						editor.putString("birthday", birthday);
						editor.commit();// 提交修改
					}
					/* 体重 */
					String weight = jo.getString("weight");
					if (weight == null || weight.equals("")) {

						Log.v("weight = null", "yes");
					} else {
						Log.v("weight = null", "no");
						Log.v("weight =  ", weight);

						/* 将用户名保存到SP里 */
						SharedPreferences userinfoSP = mContext
								.getSharedPreferences("info",
										Context.MODE_PRIVATE);
						Editor editor = userinfoSP.edit();
						editor.putString("weight", weight);
						editor.commit();// 提交修改
					}

					/* 身高 */
					String height = jo.getString("height");
					if (height == null || height.equals("")) {

						Log.v("height = null", "yes");
					} else {
						Log.v("height = null", "no");
						Log.v("height =  ", height);

						/* 将用户名保存到SP里 */
						SharedPreferences userinfoSP = mContext
								.getSharedPreferences("info",
										Context.MODE_PRIVATE);
						Editor editor = userinfoSP.edit();
						editor.putString("height", height);
						editor.commit();// 提交修改
					}

					/* 身高 */
					String stepnum = jo.getString("stepnum");
					if (stepnum == null || stepnum.equals("")) {
						/* 默认步数 */
						stepnum = "0";
						Log.v("stepnum = null", "yes");
					} else {
						Log.v("stepnum = null", "no");
						Log.v("stepnum =  ", stepnum);

						/* 将用户名保存到SP里 */
						SharedPreferences userinfoSP = mContext
								.getSharedPreferences("info",
										Context.MODE_PRIVATE);
						Editor editor = userinfoSP.edit();
						editor.putString("stepnum", stepnum);
						editor.commit();// 提交修改
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};
	};

	private static Toast toast;

	/**
	 * 直接拨打电话
	 * 
	 * @param phone
	 *            电话号码
	 * @param mContext
	 *            context对象
	 */
	public static void call(String phone, Context mContext) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.CALL");
		intent.setData(Uri.parse("tel:" + phone));
		mContext.startActivity(intent);
	}

	/**
	 * 清除SP中保存的个人登录信息
	 * 
	 * @param mContext
	 */
	public static void clearSPDataWithInfo(Context mContext) {
		SharedPreferences userinfoSP = mContext.getSharedPreferences("info",
				Context.MODE_PRIVATE);
		userinfoSP.edit().clear().commit();
	}

	/**
	 * 比较日期大小
	 * 
	 * @param DATE1
	 * @param DATE2
	 * @return 1前2后返回false
	 */
	public static boolean compare_date(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return true;
			} else if (dt1.getTime() < dt2.getTime()) {
				return false;
			} else {
				return false;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	/**
	 * @return 6位随机的数字
	 */
	public static String createRandom() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		return result;
	};

	public static String formatDate(Date date) {

		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(date);

	};

	public static String formatDate(String str) {

		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(str);

	};

	public static String getCircleFaceImgPath(Context mContext) {
		SharedPreferences userinfoSP = mContext.getSharedPreferences("info",
				Context.MODE_PRIVATE);
		String circlefaceImgPath = userinfoSP.getString("circlefaceImgPath",
				null);

		return circlefaceImgPath;

	};

	/**
	 * 获取用户保存在SharedPreference中的手机号(用户登录号码)
	 * 
	 * @param mContext
	 * @return
	 */
	public static String getInfosByString(Context mContext, String body) {
		SharedPreferences userinfoSP = mContext.getSharedPreferences("info",
				Context.MODE_PRIVATE);
		String phone = userinfoSP.getString(body, null);

		Log.v("Tools---This user " + body + " is ", phone + "");

		return phone;

	};

	/**
	 * 返回一个字符串开头有几个字母
	 * 
	 * @param str
	 * @return
	 */
	public static int getStrHandLetterNum(String str) {

		int num = 0;
		for (int i = 0; i < str.length(); i++) {

			char x = str.charAt(i);
			if ((x > 'a' && x < 'z') || (x > 'A' && x < 'Z')) {
				num++;
			} else {
				break;
			}
		}
		return (num);

	}

	/**
	 * 获取用户保存在SharedPreference中的头像路劲
	 * 
	 * @param mContext
	 * @return
	 */
	public static String getUserFaceImgPath(Context mContext) {
		SharedPreferences userinfoSP = mContext.getSharedPreferences("info",
				Context.MODE_PRIVATE);
		String faceImgPath = userinfoSP.getString("faceImgPath", null);

		return faceImgPath;

	}

	/**
	 * 
	 * 网络交互
	 * 
	 * @param phone
	 * @param mac
	 * @param connectUrl
	 * @return
	 */
//	public static String getUserInfo(String phone, String connectUrl) {
//		String result = null;
//		HttpPost httpPost = new HttpPost(connectUrl);
//		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("phone", phone));
//		try {
//			HttpEntity httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
//			httpPost.setEntity(httpEntity);
//			HttpResponse response = new DefaultHttpClient().execute(httpPost);
//			if (response.getStatusLine().getStatusCode() == 200) {
//				HttpEntity entity = response.getEntity();
//				result = EntityUtils.toString(entity);
//				Log.v("result= ", result);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	};

	/**
	 * 获取用户保存在SharedPreference中的用户昵称
	 * 
	 * @param mContext
	 * @return
	 */
	public static String getUserNickName(Context mContext) {
		SharedPreferences userinfoSP = mContext.getSharedPreferences("info",
				Context.MODE_PRIVATE);
		String nickname = userinfoSP.getString("nickname", null);


		return nickname;

	}

	/**
	 * 获取用户保存在SharedPreference中的手机号(用户登录号码)
	 * 
	 * @param mContext
	 * @return
	 */
	public static String getUserPhone(Context mContext) {
		/* 游客默认账号 */
		String phone = "13888888888";

		try {
			SharedPreferences userinfoSP = mContext.getSharedPreferences(
					"info", Context.MODE_PRIVATE);
			phone = userinfoSP.getString("username", null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return phone;

	}

	/**
	 * 获取wifi的mac地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getWifiMac(Context context) {

		WifiManager manager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		int state = manager.getWifiState();
		if (state != WifiManager.WIFI_STATE_ENABLED
				&& state != WifiManager.WIFI_STATE_ENABLING) {
			manager.setWifiEnabled(true);
		}
		WifiInfo info = manager.getConnectionInfo();
		return info.getMacAddress();
	}

//	public static void initUserInfo(final Context context) {
//
//		mContext = context;
//
//		/* 查询即将显示的个人信息 */
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				String result = getUserInfo(Tools.getUserPhone(mContext),
//						Constant.mainPath + "mobileGetUserInfo.do");
//				Message msg = new Message();
//				msg.what = 200;
//				msg.obj = result;
//				handler.sendMessage(msg);
//
//			}
//		}).start();
//
//	}

	public static boolean isPhone(String phonenumber) {
		boolean b = false;
		String phone = "^[1][345789][0-9]{9}$";
		Pattern p = Pattern.compile(phone);
		Matcher m = p.matcher(phonenumber);
		b = m.matches();
		return b;
	}

	/**
	 * @param context
	 * @param msg
	 */
	public static void toast(Context context, Object msg) {
		if (!Util.isNull(toast)) {
			toast.cancel();
			toast = null;
		}
		toast = Toast.makeText(context, msg.toString(), Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * 转换图片成圆形
	 * 
	 * @param bitmap
	 *            传入Bitmap对象
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;

			left = 0;
			top = 0;
			right = width;
			bottom = width;

			height = width;

			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;

			float clip = (width - height) / 2;

			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;

			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);// 设置画笔无锯齿

		canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas

		// 以下有两种方法画圆,drawRounRect和drawCircle
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
		// canvas.drawCircle(roundPx, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
		canvas.drawBitmap(bitmap, src, dst, paint); // 以Mode.SRC_IN模式合并bitmap和已经draw了的Circle

		return output;
	}

	@SuppressWarnings("unused")
	private boolean isHaveSP(Context context) {
		SharedPreferences userinfoSP = context.getSharedPreferences("info",
				Context.MODE_PRIVATE);
		boolean b = false;
		String username = userinfoSP.getString("username", null);
		String password = userinfoSP.getString("password", null);
		// 不等于空并且非空字符串
		if (username != null && password != null && !username.equals("")
				&& !password.equals("")) {
		}
		return b;
	}

	public static boolean isHaveSP(String username, String password) {
		boolean b = false;

		// 不等于空并且非空字符串
		if (username != null && password != null && !username.equals("")
				&& !password.equals("")) {
			b = true;
			Log.v("LoginActivity", "SP is not null! now is goLogin!");
		}

		return b;
	}

}
