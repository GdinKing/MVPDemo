package com.king.mvpdemo.model;

/**
 * @author king
 * @date 2017/3/10 10:10
 */

public interface OnDataGetListener {
    void onSuccess(String response); //请求成功
    void onError(); //请求失败
}
