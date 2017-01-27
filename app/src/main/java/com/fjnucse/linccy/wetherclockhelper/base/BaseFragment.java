package com.fjnucse.linccy.wetherclockhelper.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fjnucse.linccy.wetherclockhelper.R;

import butterknife.ButterKnife;

/**
 * @author：linchenxi on 2016/12/7 16:39
 * @description:
 */
public abstract class BaseFragment extends Fragment {
    protected Toolbar mToolbar;
    protected BaseFragmentActivity mActivity;

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void initData();


    //获取布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected BaseFragmentActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseFragmentActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        initData();
        initView(rootView, savedInstanceState);
        setToolbar();
        return rootView;
    }

    protected void setToolbar() {
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);  //当继承BaseFragment的fragment没有toolbar时为空
        if (mToolbar != null) {
            ((Drawer) getActivity()).initDrawer(mToolbar);
        }
    }

}
