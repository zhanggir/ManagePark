package com.wislight.parkmanage.model.loginRegiste;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.wislight.parkmanage.R;
import com.wislight.parkmanage.config.Tools;
import com.wislight.parkmanage.model.home.MainActivity;
import com.wislight.parkmanage.utils.Util;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private long mStartTime;
    private boolean mTouched = false;
    private Timer mTimer;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        mTimer = new Timer(true);
        mStartTime = System.currentTimeMillis();
        mTimer.schedule(task, 0, 1);
    }

    private final TimerTask task = new TimerTask() {
        public void run() {
            if (task.scheduledExecutionTime() - mStartTime > 2000 || mTouched) {
                Message message = new Message();
                message.what = 0;
                timerHandler.sendMessage(message);
                mTimer.cancel();
                this.cancel();
            }

        }
    };
    private final Handler timerHandler = new Handler() {
        public void handleMessage(Message msg) {
            SharedPreferences userinfoSP;
            switch (msg.what) {
                case 0:
                    SplashActivity.this.finish();
                    // 跳转到新的 activity
                    userinfoSP = getSharedPreferences("info", Context.MODE_PRIVATE);
                    if (Util.isNull(userinfoSP))
                        return;
                    String username = userinfoSP.getString("username", null);
                    String password = userinfoSP.getString("password", null);
                    if (Tools.isHaveSP(username, password)) {
                        Intent intent = new Intent(mContext, MainActivity.class);
                        SplashActivity.this.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        SplashActivity.this.startActivity(intent);
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 点击直接跳转
     */
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTouched = true;
        }
        return true;
    }

}
