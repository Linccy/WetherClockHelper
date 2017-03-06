package com.fjnucse.linccy.wetherclockhelper.base;

/**
 * @author：linchenxi on 2017/3/1 16:13
 * @description:
 */
public class BaseResponse {

    /**
     * success : true
     * token : abcdefg1234567
     */

    private boolean success;
    private String token;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
