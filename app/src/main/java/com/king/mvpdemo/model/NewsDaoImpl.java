package com.king.mvpdemo.model;

import com.king.mvpdemo.net.HttpHelper;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author king
 * @date 2017/3/10 10:06
 */

public class NewsDaoImpl implements INewsDao {
    public static final String BASE_URL = " http://apis.haoservice.com/lifeservice/news?";
    public static final String APP_KEY = "6a74ccfde5e04f27b344718ba923fe3f";

    @Override public void getNews(String keyWords, final OnDataGetListener listener) {
        try {
            keyWords = URLEncoder.encode(keyWords, "utf-8");

            StringBuilder sb = new StringBuilder();
            sb.append(BASE_URL);
            sb.append("key=");
            sb.append(APP_KEY);
            sb.append("&q=");
            sb.append(keyWords);

            //创建一个请求
            final Request request = new Request.Builder().url(sb.toString()).build();
            //创建一个Call
            final Call call = HttpHelper.getInstance().getOkHttpClient().newCall(request);

            //执行请求
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (listener != null) {
                        listener.onError();
                    }
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (listener != null) {
                        listener.onSuccess(response.body().string());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
