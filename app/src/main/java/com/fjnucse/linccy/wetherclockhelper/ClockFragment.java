package com.fjnucse.linccy.wetherclockhelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.fjnucse.linccy.wetherclockhelper.base.BaseActivity;
import com.fjnucse.linccy.wetherclockhelper.base.BaseFragment;

import butterknife.BindView;

/**
 * @author：linchenxi on 2016/12/7 16:51
 * @description:
 */
public class ClockFragment extends BaseFragment {

    @BindView(R.id.content_main2)
    RelativeLayout contentMain2;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_clock;
    }
}
