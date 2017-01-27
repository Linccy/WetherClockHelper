package com.fjnucse.linccy.wetherclockhelper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fjnucse.linccy.wetherclockhelper.MainActivity;
import com.fjnucse.linccy.wetherclockhelper.R;
import com.fjnucse.linccy.wetherclockhelper.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lcc on 2017/1/26.
 */

public class WeatherFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.menu_show_nav)
    ImageButton menuShowNav;
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.share_weather)
    ImageButton shareWeather;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        menuShowNav.setOnClickListener(this);
        shareWeather.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wether;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_show_nav:
                ((MainActivity) getActivity()).showDrawer();
                break;
            case R.id.share_weather:
                Intent intent1=new Intent(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_TEXT,"This is my text to send");
                intent1.setType("text/plain");
                startActivity(Intent.createChooser(intent1,"share"));
                break;
            default:
                break;
        }
    }
}
