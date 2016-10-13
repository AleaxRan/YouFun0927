package com.atguigu.youfun0927;

import android.app.Application;

import com.atguigu.youfun0927.db.Model;
import com.atguigu.youfun0927.utils.lodingpager.LoadingAndRetryManager;

/**
 * Created by Administrator on 2016/10/9.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化LoadingAndRetryManager
        LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.base_retry;
        LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_loading;
        LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.base_empty;

        initDB();
    }

    private void initDB() {
        Model.getInstance().init(this,"tk");

    }
}
