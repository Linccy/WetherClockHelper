package com.fjnucse.linccy.wetherclockhelper.activity;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import com.fjnucse.linccy.wetherclockhelper.MainActivity;
import com.fjnucse.linccy.wetherclockhelper.R;
import com.fjnucse.linccy.wetherclockhelper.base.BaseActivity;

import butterknife.BindView;

/**
 * 欢迎页，延迟2秒启动
 */

public class SplashActivity extends BaseActivity {
    private static final long SPLASH_TIME = 2000;
    private long mBeforTime;
    private long mAfterTime;

    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setHeader() {

    }

    @Override
    protected void setContentView() {
        toMainActivity();
    }

    /**
     * 开启主页
     * */
    private void toMainActivity() {
        mAfterTime = System.currentTimeMillis();
        if (mAfterTime - mBeforTime < SPLASH_TIME) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME - (mAfterTime - mBeforTime));
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void initData() {
        mBeforTime = System.currentTimeMillis();
    }
}
