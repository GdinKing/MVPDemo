package com.king.mvpdemo.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author king
 * @date 2017/3/10 09:48
 */

public class HttpHelper {

    private static HttpHelper mInstance;

    private static OkHttpClient mOkHttpClient;

    private HttpHelper() {
        //初始化OkHttpClient
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();
    }

    public static HttpHelper getInstance() {
        if (mInstance == null) {
            synchronized (HttpHelper.class) {
                if (mInstance == null) {
                    mInstance = new HttpHelper();
                }
            }
        }
        return mInstance;
    }


    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

}
