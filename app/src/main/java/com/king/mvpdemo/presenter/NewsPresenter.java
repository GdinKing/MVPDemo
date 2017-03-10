package com.king.mvpdemo.presenter;

import android.os.Handler;
import android.os.Message;

import com.king.mvpdemo.model.NewsDaoImpl;
import com.king.mvpdemo.model.OnDataGetListener;
import com.king.mvpdemo.view.INewsView;

/**
 * @author king
 * @date 2017/3/10 10:16
 */

public class NewsPresenter implements IBasePresenter{

    private INewsView mNewsView;
    private NewsDaoImpl newsDao;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    mNewsView.onSuccess((String) msg.obj);//因为是异步，所以需要handler
                    break;
                case 201:
                    mNewsView.onError();
                    break;
            }
        }
    };

    public NewsPresenter(INewsView view){
        this.mNewsView = view;
        this.newsDao = new NewsDaoImpl();
    }

    /**
     * 获取新闻数据
     * @param key
     */
    public void getNews(String key){
        mNewsView.showProgress();
        newsDao.getNews(key, new OnDataGetListener() {
            @Override
            public void onSuccess(String response) {
                mNewsView.hideProgress();
                Message msg = Message.obtain();
                msg.what = 200;
                msg.obj = response;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onError() {
                mNewsView.hideProgress();
                Message msg = Message.obtain();
                msg.what = 201;
                mHandler.sendMessage(msg);
            }
        });

    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestory() {

    }
}
