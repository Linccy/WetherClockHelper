package com.fjnucse.linccy.wetherclockhelper;


import android.content.Context;
import android.text.TextUtils;

import com.fjnucse.linccy.wetherclockhelper.base.BaseResponse;
import com.fjnucse.linccy.wetherclockhelper.bean.IdBean;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by lcx on 12/17/16.
 */

public class WebService {
    public static final String BASE_URL = "http://192.168.1.19:8080/DispatcherServlet/";
    private static final int DEFAULT_TIMEOUT = 5;
    private static Client CLIENT;

    public static void init(Context context) {
        CLIENT = getRetrofit(context).create(Client.class);
    }


    public static Retrofit getRetrofit(Context context){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(genericClient(context))
                .build();
    }
    private static OkHttpClient genericClient(final Context context) {

        //设置缓存路径
        File httpCacheDirectory = new File(context.getCacheDir(), "http");
        //设置缓存 10M
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);


        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS).writeTimeout(10000, TimeUnit.MILLISECONDS).readTimeout(10000, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String session = MainConstants.SESSIONID;
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Cookie", "sessionid=" + session)
                                .addHeader("accesstoken", "Wfn5v1KqbheKX47Twgd")
                                .url(chain.request().url().toString().replace("\\u003d", "%3d"))
                                .build();
                        return chain.proceed(request);
                    }
                }).addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        String cacheControl = request.cacheControl().toString();
                        if (TextUtils.isEmpty(cacheControl)) {
                            cacheControl = "public, max-age=60*10";
                        }
                        Request new_request = request.newBuilder().header("Cache-Control", cacheControl).build();
                        return chain.proceed(new_request);
                    }
                }).cache(cache).build();
        return httpClient;
    }

    public static Client get(){
        return CLIENT;
    }

    public interface Client {
        @GET("id")
        Observable<IdBean> getCommunicateById(@Header("apikey") String apiKey,@QueryMap Map<String, String> params);

        /**
         * 用户登录
         *http://localhost:8080/DispatcherServlet/api/user/admin?pass=e10adc3949ba59abbe56e057f20f883e
         * */
        @FormUrlEncoded
        @POST("user/login")
        Observable<BaseResponse> userLogin(@FieldMap Map<String, String> fields);
    }
}
