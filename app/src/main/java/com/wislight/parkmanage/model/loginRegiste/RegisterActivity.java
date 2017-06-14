package com.wislight.parkmanage.model.loginRegiste;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wislight.parkmanage.R;
import com.wislight.parkmanage.config.Constant;
import com.wislight.parkmanage.config.Tools;

/**
 * 
 * 注册的Activity
 * date 2017年06月14日
 * @author ZZX
 * 
 */

public class RegisterActivity extends Activity {
	private SharedPreferences userinfoSP;
	private static String connectUrl = Constant.mainPath
			+ "mobileRegist.do";

	// private static final int USER_TYPE_IS_PARENT = 0;
	private static final int USER_TYPE_IS_STUDENT = 1;

	// private final static int SCANNIN_GREQUEST_CODE = 2;

	private String code;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	private Context mContext;
	@SuppressWarnings("unused")
	private ArrayAdapter<String> userTypeSpinnerAdapter;

	private ImageButton ibt_reg_back; // 后退按钮
	private EditText et_reg_phone; // 手机号
	private EditText et_reg_password;// 密码
	private EditText et_reg_code;// 验证码
	private Button bt_reg_getCode;// 获取验证码

	private Button bt_reg_rec;// 注册



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mContext = this;
		initViews();
	}

	/**
	 * init
	 */
	private void initViews() {

		userinfoSP = getSharedPreferences("info", Context.MODE_PRIVATE);

		ibt_reg_back = (ImageButton) findViewById(R.id.ibt_reg_back);
		et_reg_phone = (EditText) findViewById(R.id.et_reg_phone);
		et_reg_password = (EditText) findViewById(R.id.et_reg_password);
		et_reg_code = (EditText) findViewById(R.id.et_reg_code);
		bt_reg_getCode = (Button) findViewById(R.id.bt_reg_getCode);
		bt_reg_rec = (Button) findViewById(R.id.bt_reg_rec);


		ibt_reg_back.setOnClickListener(mClickListener);
		bt_reg_getCode.setOnClickListener(mClickListener);
		bt_reg_rec.setOnClickListener(mClickListener);
	}

	/**
	 * initSpinner
	 */
	@SuppressLint("ResourceAsColor")
//	private void initSpinner() {
//		/* 用户类型 */
//		String[] userTypeArr = {
//				(String) this.getResources().getText(R.string.type_parent),
//				(String) this.getResources().getText(R.string.type_student) };
//		userTypeSpinnerAdapter = new ArrayAdapter<String>(this,
//				R.drawable.spinner_regirst_user_type, userTypeArr);
//	}

	/*
	 * RegisterActivity所有的点击事件
	 */
	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.bt_reg_getCode:
				// 获取短信验证码

				toGetCode();

				break;
			case R.id.bt_reg_rec:

				toReg();
				break;
			case R.id.ibt_reg_back:
				finish();
				break;
			default:
				break;
			}
		}

	};

	/**
	 * 获取验证码
	 */
	private void toGetCode() {
		setCode(Tools.createRandom());
		countDownTimer.start();
		et_reg_code.setText(getCode());

	}

	CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {

		@Override
		public void onTick(long millisUntilFinished) {
			bt_reg_getCode.setClickable(false);
			bt_reg_getCode.setText(millisUntilFinished / 1000 + "");
		}

		@Override
		public void onFinish() {
			bt_reg_getCode.setText(R.string.peompt_getCode);
			bt_reg_getCode.setClickable(true);
		}
	};

	/**
	 * 注册
	 */
	private void toReg() {

		String account = et_reg_phone.getText().toString().trim();

		String mac = et_reg_password.getText().toString().trim();

		String code = et_reg_code.getText().toString().trim();

		if (code.equals(getCode())) {

		} else {
			// 提示验证码错误
			Tools.toast(mContext, R.string.toast_code_error);
			return;
		}

		boolean isPhone = Tools.isPhone(account);

		if (!isPhone) {
			Tools.toast(mContext, R.string.toast_phone_error);
			return;
		}

		if (mac.length() < 6) {
			Tools.toast(mContext, R.string.toast_pass_error);
			return;
		}

		account = et_reg_phone.getText().toString().trim();

		mac = et_reg_password.getText().toString().trim();

		// 预留短信验证码
		code = et_reg_code.getText().toString().trim();
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("account", account);
		params.addBodyParameter("mac", mac);
//		http.send(HttpRequest.HttpMethod.POST, connectUrl, params,
//				new RequestCallBack<String>() {
//
//					@Override
//					public void onLoading(long total, long current,
//							boolean isUploading) {
//
//						Tools.toast(mContext, "请稍后！");
//
//						super.onLoading(total, current, isUploading);
//					}
//
//					@Override
//					public void onFailure(HttpException arg0, String info) {
//						Tools.toast(mContext, "服务器错误，请稍后重试！");
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> info) {
//
//						String jsonStr = info.result;
//
//						String account = et_reg_phone.getText().toString()
//								.trim();
//						String mac = et_reg_password.getText().toString()
//								.trim();
//
//						String result = null;
//						try {
//							JSONObject json = new JSONObject(jsonStr);
//
//							result = json.getString("result");
//						} catch (JSONException e) {
//							result = "fail";
//							e.printStackTrace();
//						}
//						if (result.equals("success")) {
//							Editor editor = userinfoSP.edit();
//							editor.putString("username", account);
//							editor.putString("password", mac);
//							editor.commit();// 提交修改
//
//						} else {
//							Tools.toast(mContext, "注册失败,此用户已存在!");
//							return;
//						}
//					}
//				});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
	}

}
