package com.king.mvpdemo.presenter;

/**
 * 基础的presenter，用于关联Activity或fragment生命周期
 * @author king
 * @date 2017/3/10 10:24
 */
public interface IBasePresenter {

    void onCreate();

    void onStart();

    void onPause();

    void onStop();

    void onDestory();
}
