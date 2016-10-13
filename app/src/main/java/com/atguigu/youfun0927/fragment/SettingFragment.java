package com.atguigu.youfun0927.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.Basefragment;

/**
 * Created by Administrator on 2016/9/27.
 */
public class SettingFragment extends Basefragment {


    @Override
    public View initView() {
        Log.e("TAG", "设置被初始化了");

        View view = LayoutInflater.from(mContext).inflate(R.layout.setting_fragment, null);

        return view;

    }

    @Override
    public void initData() {
        super.initData();

        Log.e("TAG", "设置数据被初始化了");


    }

}
