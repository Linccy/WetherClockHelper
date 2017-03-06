package com.fjnucse.linccy.wetherclockhelper.info;

import com.fjnucse.linccy.wetherclockhelper.base.BaseInput;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：linchenxi on 2017/3/1 16:18
 * @description:
 */
public class LoginInput extends BaseInput {
    public String loginName;
    public String pass;

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("loginName", loginName);
        map.put("pass", pass);
        map.put("accesstoken", "Wfn5v1KqbheKX47Twgd");
        return map;
    }
}
