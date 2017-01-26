package com.fjnucse.linccy.wetherclockhelper;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by lcx on 12/17/16.
 */

public class MyApplacation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        WebService.init();
    }
}
