package com.atguigu.youfun0927.fragment.classifychildfragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.youfun0927.base.Basefragment;

/**
 * Created by Administrator on 2016/10/5.
 */
public class DynamicFragment extends Basefragment {
    private TextView textView;

    @Override
    public View initView() {
        Log.e("TAG", "动态被初始化了");
        textView = new TextView(mContext);
        textView.setTextSize(30);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;

    }

    @Override
    public void initData() {
        super.initData();

        Log.e("TAG","动态数据被初始化了");
        textView.setText("动态内容");

    }
}
