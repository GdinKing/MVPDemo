package com.king.mvpdemo.view;

/**
 * @author king
 * @date 2017/3/10 10:14
 */

public interface INewsView extends IViewBinder {

    void showProgress();//显示进度条

    void hideProgress();//隐藏进度条

    void onSuccess(String response);//请求成功

    void onError();//请求失败

}
