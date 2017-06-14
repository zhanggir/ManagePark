package com.wislight.parkmanage.utils;

import android.os.CountDownTimer;
import android.widget.Button;

import com.wislight.parkmanage.R;

public class ViewUtil {
	
	public static CountDownTimer daojishi(final Button bt_reg_getCode,
			int downtimer) {
		
		CountDownTimer countDownTimer = new CountDownTimer(downtimer, 1000) {
			
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
		return countDownTimer;

	}

}
