package com.wislight.parkmanage.model.loginRegiste;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.wislight.parkmanage.R;
import com.wislight.parkmanage.config.Constant;
import com.wislight.parkmanage.config.Tools;
import com.wislight.parkmanage.utils.Util;
/**
 * @create 2017年6月14日
 * @author zzx
 *登录
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

	private String mUserPhone;// 用户账号信息
	private String mUserPass;// 用户密码
	// private ProgressBar progressBar1;

	private static String connectUrl = Constant.mainPath
			+ "mobileLogin.do";

	private Context mContext;

	private TextView tv_forget_password;// 忘记密码

	private EditText et_login_phone;// 用户登录电话号码

	private EditText et_login_password;// 登录密码

	private TextView tv_login_register;// 注册

	private Button bt_login_login;// 登录按钮
	private SharedPreferences userinfoSP;
	/*
	 * LoginActivity所有的点击事件
	 */
	private View.OnClickListener mClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.bt_login_login:
				// 登录
				toLogin();

				break;

			case R.id.tv_login_register:
				// 注册
				toRegirst();

				break;
			default:
				break;
			}
		}

	};
	private String account;

	private String mac;

	private Intent mIntent;

	public String getmUserPass() {
		return mUserPass;
	}

	public String getmUserPhone() {
		return mUserPhone;
	}

	/**
	 * init
	 */
	private void initViews() {
		et_login_phone = (EditText) findViewById(R.id.et_login_phone);
		et_login_password = (EditText) findViewById(R.id.et_login_password);
		tv_login_register = (TextView) findViewById(R.id.tv_login_register);
		bt_login_login = (Button) findViewById(R.id.bt_login_login);

		tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
		tv_forget_password.setOnClickListener(this);
		tv_login_register.setOnClickListener(mClickListener);
		bt_login_login.setOnClickListener(mClickListener);

	}

	@Override
	public void onBackPressed() {
		moveTaskToBack(true);
	}

	@Override
	public void onClick(View view) {

		int viewId = view.getId();

		switch (viewId) {
		case R.id.tv_forget_password:
			Intent intent = new Intent(mContext, ForgetActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.activity_login);
		mContext = this;
		initViews();
		mIntent = getIntent();
		String account = mIntent.getStringExtra("account");
		if (!Util.isNull(account)) {
			et_login_phone.setText(account);
		}

	}

	@Override
	protected void onResume() {
		
		/* 启动记录身姿的Service */
		// startPecordPostureService();
		// 自动登录
		userinfoSP = getSharedPreferences("info", Context.MODE_PRIVATE);
		String username = userinfoSP.getString("username", null);
		String password = userinfoSP.getString("password", null);
		if (Tools.isHaveSP(username, password)) {
			et_login_phone.setText(username);
			et_login_password.setText(password);
//			toLogin();
		}
		super.onResume();
	}
	
	private void SendPostRequest(final String account, final String mac) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("account", account);
		params.addBodyParameter("mac", mac);
		HttpUtils httpUtils = new HttpUtils();

		Tools.toast(mContext, "请稍后...");

		bt_login_login.setEnabled(false);
		// TODO: 2017/6/14 访问网络
//		httpUtils.send(HttpRequest.HttpMethod.POST, connectUrl, params,
//				new RequestCallBack<String>() {
//					@Override
//					public void onFailure(HttpException arg0, String arg1) {
//						Tools.toast(LoginActivity.this, "登录失败，请检查网络");
//						bt_login_login.setEnabled(true);
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> reponse) {
//						String result = reponse.result;
//						try {
//							JSONObject object = new JSONObject(result);
//							String msg = object.getString("msg");
//							if (msg != null && msg.equals("success")) {
//								String role = object.getString("role");
//								SharedPreferences.Editor editor = userinfoSP.edit();
//								editor.putString("username", account);
//								editor.putString("password", mac);
//								editor.commit();// 提交修改
//								toMainActivity(role);
//							} else {
//								Tools.toast(mContext, "登录失败,请检查用户名或密码!");
//								bt_login_login.setEnabled(true);
//								return;
//							}
//						} catch (JSONException e) {
//							e.printStackTrace();
//						}
//					}
//				});
	}

	public void setmUserPass(String mUserPass) {
		this.mUserPass = mUserPass;
	}

	public void setmUserPhone(String mUserPhone) {
		this.mUserPhone = mUserPhone;
	}

	/**
	 * 去登录
	 */
	private void toLogin() {

		account = et_login_phone.getText().toString().trim();

		mac = et_login_password.getText().toString().trim();

		boolean isPhone = Tools.isPhone(account);

		if (!isPhone) {
			Tools.toast(mContext, R.string.toast_phone_error);
			return;
		}

		if (mac.length() < 6) {
			Tools.toast(mContext, R.string.toast_pass_error);
			return;
		}
		/**
		 * 使用框架发送POST请求 周 修改的地方
		 */
		//SendPostRequest(account, mac);

	}

	private void toMainActivity(String role) {

		if (role == null || role.equals("")) {
			Tools.toast(mContext, "info  error");
			return;
		}
	}

	/** 登录判断的handler */
	/*
	 * Handler mLoginHandler = new Handler() {
	 * 
	 * public void handleMessage(android.os.Message msg) {
	 * 
	 * if (msg.what == 200) { String account =
	 * et_login_phone.getText().toString().trim(); String resultLogin = null;
	 * String role = null; String mac =
	 * et_login_password.getText().toString().trim(); String result =
	 * msg.obj.toString(); Log.i("resu", result);
	 * 
	 * try { JSONObject jsonObject = new JSONObject(result); role =
	 * jsonObject.getString("role"); resultLogin = jsonObject.getString("msg");
	 * 
	 * } catch (JSONException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * if (resultLogin != null && resultLogin.equals("success")) { Editor editor
	 * = userinfoSP.edit(); editor.putString("username", account);
	 * editor.putString("password", mac); editor.commit();// 提交修改
	 * toMainActivity(role);
	 * 
	 * } else { Tools.toast(mContext, "登录失败,请检查用户名或密码!"); return; }
	 * 
	 * }
	 * 
	 * }; };
	 */

	private void toRegirst() {

		startActivity(new Intent(mContext, RegisterActivity.class));
	}

}
