package com.wislight.parkmanage.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.wislight.parkmanage.utils.LogWrrap;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/14.
 * @author zzx
 * explain:   activity的基类
 */

public abstract class BaseActivity extends AppCompatActivity{
    private static final String EXTRA_KEY_STRING = "bundle";

    private static final String TAG = "PARK_MANAGER";
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        //ButterKnife绑定
        mUnbinder = ButterKnife.bind(this);
        //初始化视图
        init(savedInstanceState);
    }

    protected abstract void init(Bundle savedInstanceState);

    @LayoutRes
    protected abstract int getContentView();

    /**
     * skip to other activity
     *
     * @param cla class object  where you want skip
     */
    public void toActivity(Class<?> cla) {
        toActivity(cla, null, null);
    }

    /**
     * skip to other activity and take extra data
     *
     * @param cla    class object  where you want skip
     * @param bundle extra bundle data
     */
    public void toActivity(Class<?> cla, Bundle bundle) {
        toActivity(cla, bundle, null);
    }

    /**
     * skip to other activity and take extra data and uri data
     *
     * @param cla    class object  where you want skip
     * @param bundle extra bundle data
     * @param data   uri data
     */
    public void toActivity(Class<?> cla, Bundle bundle, Uri data) {
        Intent intent = new Intent(this, cla);
        if (null != bundle)
            intent.putExtra(EXTRA_KEY_STRING, bundle);
        if (null != data)
            intent.setData(data);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogWrrap.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogWrrap.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogWrrap.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogWrrap.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mUnbinder = null;
        LogWrrap.e(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogWrrap.e(TAG, "onRestart");
    }

    /**
     * get bundle extra
     *
     * @return bundle extra
     */
    public Bundle getBundle() {
        return this.getIntent().getBundleExtra(EXTRA_KEY_STRING);
    }
}
