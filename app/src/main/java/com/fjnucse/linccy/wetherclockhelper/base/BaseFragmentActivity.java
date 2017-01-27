package com.fjnucse.linccy.wetherclockhelper.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * @author：linchenxi on 2016/12/7 15:52
 * @description:
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
    protected ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init(savedInstanceState);
        initData();
        setHeader();
        setContentView();
    }

    protected abstract int getLayoutId();

    protected abstract void setHeader();

    protected abstract void setContentView();

    protected abstract void initData();

    protected void init(Bundle savedInstanceState) {
    }

    protected void replaceFragment(@IdRes int containerId, Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment).commit();
        invalidateOptionsMenu();
    }

    protected void makeToast(@StringRes int stringId, int gravity) {
        Toast toast = Toast.makeText(getApplicationContext(), stringId, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

//    protected void makeSnackbar(View parent, @StringRes int stringId, @StringRes int actionContent){
//        Snackbar.make(parent, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction(getString(actionContent), null).show();
//    }
}
