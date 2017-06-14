package com.wislight.parkmanage.model.loginRegiste;



import android.os.Bundle;
import android.os.CountDownTimer;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.wislight.parkmanage.R;
import com.wislight.parkmanage.config.Tools;
import com.wislight.parkmanage.utils.Util;
import com.wislight.parkmanage.utils.ViewUtil;

/**
 * @create 2017/06/14
 * 忘记密码 * @author zzx
 * 
 */
public class ForgetActivity extends AppCompatActivity implements OnClickListener {

	private EditText et_forget_code; // code

	private Button bt_forget_getCode;// get code button

	private Button bt_forget_next; // next button

	private EditText et_forget_phone;// phone

	private CountDownTimer timer;

	private void initViews() {
		et_forget_code = (EditText) findViewById(R.id.et_forget_code);
		et_forget_phone = (EditText) findViewById(R.id.et_forget_phone);
		bt_forget_getCode = (Button) findViewById(R.id.bt_forget_getCode);
		bt_forget_next = (Button) findViewById(R.id.bt_forget_next);

		bt_forget_getCode.setOnClickListener(this);
		bt_forget_next.setOnClickListener(this);
	}

	private String findPwdCode;

	public String getFindPwdCode() {
		return findPwdCode;
	}

	public void setFindPwdCode(String findPwdCode) {
		this.findPwdCode = findPwdCode;
	}

	private String account;

	@Override
	public void onClick(View view) {
		int viewId = view.getId();

		switch (viewId) {
		case R.id.bt_forget_getCode:
			String account = et_forget_phone.getText().toString();
			if (!Tools.isPhone(account)) {
				Tools.toast(mContext, "手机号格式不正确!");
				return;
			}
			if (!Util.isNull(timer)) {
				timer.start();
				setFindPwdCode(Tools.createRandom());
				this.account = account;
				et_forget_code.setText(getFindPwdCode());
			}
			break;
		case R.id.bt_forget_next:
			String code = et_forget_code.getText().toString();
			if (Util.isNull(getFindPwdCode())) {
				Tools.toast(mContext, "验证码错误!");
				return;
			}
			if (!getFindPwdCode().equals(code)) {
				Tools.toast(mContext, "请填写正确的验证码!");
				return;
			}
			Intent intent = new Intent(mContext, NewPasswordActivity.class);
			intent.putExtra("account", this.account);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget);
		mContext = this;
		initViews();
		initCodeTimer();

	}

	/**
	 * 实例化获取验证码倒计时
	 */
	private void initCodeTimer() {
		timer = ViewUtil.daojishi(this.bt_forget_getCode, 60000);
	}
}
