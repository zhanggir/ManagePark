package com.wislight.parkmanage.model.loginRegiste;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wislight.parkmanage.R;
import com.wislight.parkmanage.config.Constant;
import com.wislight.parkmanage.config.Tools;
import com.wislight.parkmanage.utils.Util;

/**
 * 新密码
 * 
 * @author zzx
 * 
 */
public class NewPasswordActivity extends AppCompatActivity implements OnClickListener {

	private String UpdatePasswrod = Constant.mainPath + "findPwd.do"; // update
																					// password
																					// URL

	private EditText et_new_password; // new password

	private Button bt_finish; // button finish

	private Context mContext; // context

	private Intent mIntent;

	private String account;

	private void accountCheck(Intent intent) {
		String account = intent.getStringExtra("account");
		if (Util.isNull(account)) {
			Tools.toast(mContext, "用户信息错误,请返回重新操作!");
			finish();
		} else {
			this.account = account;
		}
	}

	private void initViews() {
		et_new_password = (EditText) findViewById(R.id.et_new_password);
		bt_finish = (Button) findViewById(R.id.bt_finish);
		bt_finish.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		int viewId = view.getId();
		switch (viewId) {
		case R.id.bt_finish:
			String password = et_new_password.getText().toString();
			// TODO: 2017/6/14
			//updatePass(account, password);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_password);
		mContext = this;
		initViews();
		mIntent = getIntent();
		accountCheck(mIntent);
	}

	/**
	 * 访问服务器更新密码
	 * 
	 * @param account
	 *            用户名
	 * @param password
	 *            密码
	 */
	private void updatePass(final String account, String password) {
		HttpUtils http = new HttpUtils();
		http.configTimeout(3000);
		RequestParams params = new RequestParams();
		params.addBodyParameter("account", account);
		params.addBodyParameter("mac", password);
//		http.send(HttpRequest.HttpMethod.POST, UpdatePasswrod, params,
//				new RequestCallBack<String>() {
//
//					@Override
//					public void onFailure(HttpException arg0, String arg1) {
//						Tools.toast(mContext, "网络错误,请检查网络是否畅通!");
//					}
//
//					@Override
//					public void onLoading(long total, long current,
//							boolean isUploading) {
//						super.onLoading(total, current, isUploading);
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> info) {
//						String json = info.result;
//						checkNewPwdResult(account, json);
//					}
//				});
	}

	/**
	 * 验证修改密码是否成功
	 * 
	 * @param account
	 * @param json
	 */
	private void checkNewPwdResult(final String account, String json) {

		String result = null;
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
			result = jsonObject.getString("result");
		} catch (JSONException e) {
			e.printStackTrace();
			String jsonStr = "{\"result\": \"fail\"}";
			try {
				jsonObject = new JSONObject(jsonStr);
				result = jsonObject.getString("result");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		if (!Util.isNull(result)) {
			if (result.equals("success")) {
				Tools.toast(mContext, "密码重置成功,请使用新密码登录!");

				Intent intent = new Intent(mContext, LoginActivity.class);
				intent.putExtra("account", account);
				startActivity(intent);
			} else {
				Tools.toast(mContext, "修改失败,请稍后重试!");
			}

		}
	}
}
