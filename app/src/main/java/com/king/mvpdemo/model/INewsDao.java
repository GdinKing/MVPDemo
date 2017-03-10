package com.king.mvpdemo.model;

/**
 * @author king
 * @date 2017/3/10 10:05
 */

public interface INewsDao {
    void getNews(String key,OnDataGetListener listener);
}
