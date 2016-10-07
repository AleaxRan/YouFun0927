package com.atguigu.youfun0927.fragment.lgchildfragments;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.youfun0927.base.Basefragment;

/**
 * Created by Administrator on 2016/10/6.
 */
public class LgchidFragments extends Basefragment {


    private TextView textView;

    public LgchidFragments(){


    }

    public LgchidFragments(String s) {

    }

    @Override
    public View initView() {
        Log.e("TAG", "灵感子页面被初始化了");
        textView = new TextView(mContext);
        textView.setTextSize(30);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;

    }

    @Override
    public void initData() {
        super.initData();

        Log.e("TAG","灵感子fragment数据被初始化了");
        textView.setText("灵感子fragment内容");

    }



}
