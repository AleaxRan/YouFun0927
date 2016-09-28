package com.atguigu.youfun0927.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.utils.LogUtil;

/**
 * Created by Administrator on 2016/9/28.
 */
public class LeftmenuFragment extends Basefragment {


    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(30);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;


    }

    @Override
    public void initData() {
        super.initData();

        LogUtil.e("左侧菜单数据被初始化了");
        textView.setText("左侧菜单页面");

    }
}
