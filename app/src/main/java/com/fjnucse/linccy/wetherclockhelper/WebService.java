package com.fjnucse.linccy.wetherclockhelper;


import com.fjnucse.linccy.wetherclockhelper.bean.IdBean;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by lcx on 12/17/16.
 */

public class WebService {
    public static final String BASE_URL = "http://apis.baidu.com/apistore/idservice/";
    private static final int DEFAULT_TIMEOUT = 5;
    private static Client CLIENT;

    public static void init() {
        CLIENT = getRetrofit().create(Client.class);
    }


    public static Retrofit getRetrofit(){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static Client get(){
        return CLIENT;
    }

    public interface Client {
        @GET("id")
        Observable<IdBean> getCommunicateById(@Header("apikey") String apiKey,@QueryMap Map<String, String> params);
    }
}
