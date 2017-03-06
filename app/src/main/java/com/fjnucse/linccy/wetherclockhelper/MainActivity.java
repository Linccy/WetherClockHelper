package com.fjnucse.linccy.wetherclockhelper;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fjnucse.linccy.wetherclockhelper.base.BaseFragmentActivity;
import com.fjnucse.linccy.wetherclockhelper.base.BaseResponse;
import com.fjnucse.linccy.wetherclockhelper.base.Drawer;
import com.fjnucse.linccy.wetherclockhelper.bean.IdBean;
import com.fjnucse.linccy.wetherclockhelper.fragment.ClockFragment;
import com.fjnucse.linccy.wetherclockhelper.fragment.WeatherFragment;
import com.fjnucse.linccy.wetherclockhelper.info.IdInput;
import com.fjnucse.linccy.wetherclockhelper.info.LoginInput;
import com.fjnucse.linccy.wetherclockhelper.utils.ToastUtil;

import butterknife.BindView;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseFragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener, Drawer {
    private long mOnBackDownTime;
    private static final long ON_BACK_EXIT_TIME = 2000;

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    private SimpleDraweeView mAvatar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setHeader() {

    }

    @Override
    protected void setContentView() {
        navView.setNavigationItemSelectedListener(this);
        mAvatar = (SimpleDraweeView) navView.getHeaderView(0).findViewById(R.id.imageView);
        mAvatar.setImageURI("https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=758b33e74e10b912a0c1f1fef3fcfcb5/8326cffc1e178a82019b0bfcff03738da877e8c3.jpg");
        WeatherFragment weatherFragment = new WeatherFragment();
//        replaceFragment(R.id.container, weatherFragment);
//        getSupportFragmentManager().beginTransaction().show(weatherFragment).commit();
    }

    @Override
    protected void initData() {
        replaceFragment(R.id.container, new ClockFragment());
//        IdInput input = new IdInput();
//        input.id = "350721199311290510";
//        WebService.get().getCommunicateById("7ee4db22ac82d07de07019c5c7bade8f", input.toMap())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<IdBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(IdBean idBean) {
//                        int id = idBean.getErrNum();
//                    }
//                });
        LoginInput input = new LoginInput();
        input.loginName = "admin";
        input.pass = "e10adc3949ba59abbe56e057f20f883e";
        WebService.get().userLogin(input.toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(getApplicationContext(),"失败了"+((e instanceof HttpException)?((HttpException)e).code():""));
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        String token = baseResponse.getToken();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - mOnBackDownTime < ON_BACK_EXIT_TIME) {
                super.onBackPressed();
            } else {
                ToastUtil.showToast(getApplicationContext(), R.string.press_one_again_to_exist);
                mOnBackDownTime = System.currentTimeMillis();
            }
        }
    }

    public void showDrawer() {
        if (drawer != null) {
            if (!drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.openDrawer(GravityCompat.START);
            } else {
                drawer.closeDrawer(GravityCompat.START);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
        }
    }
}
