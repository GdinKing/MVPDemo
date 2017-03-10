package com.king.mvpdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.king.mvpdemo.bean.News;
import com.king.mvpdemo.presenter.NewsPresenter;
import com.king.mvpdemo.view.INewsView;

public class MainActivity extends AppCompatActivity implements INewsView {

    private ProgressDialog mProgressDialog;

    private Button btn_get;
    private TextView tv_data;

    private NewsPresenter mNewsPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_get = (Button) findViewById(R.id.btn_get);
        tv_data = (TextView) findViewById(R.id.tv_data);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("加载中");
        mNewsPresenter = new NewsPresenter(this);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsPresenter.getNews("三星电视变CIA窃听器");
            }
        });
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onSuccess(String response) {
        try {
            News news = parseJson(response);
            String code = news.getErrorCode();
            if("0".equals(code)) {
                String text = news.getResult().get(0).getTitle()+"\n\n"+news.getResult().get(0).getContent();
                tv_data.setText(text);
            }else{
                Toast.makeText(this,news.getReason(),Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){


        }
    }

    @Override
    public void onError() {

    }

    private News parseJson(String json) {
        return new Gson().fromJson(json, News.class);
    }
}
