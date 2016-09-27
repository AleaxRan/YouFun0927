package com.atguigu.youfun0927.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.youfun0927.base.Basefragment;

/**
 * Created by Administrator on 2016/9/27.
 */
public class SettingFragment extends Basefragment {
    private TextView textView;

    @Override
    public View initView() {
        Log.e("TAG", "设置被初始化了");
        textView = new TextView(mContext);
        textView.setTextSize(30);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;

    }

    @Override
    public void initData() {
        super.initData();

        Log.e("TAG","设置数据被初始化了");
        textView.setText("设置内容");

    }

}
