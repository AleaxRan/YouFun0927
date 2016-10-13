package com.atguigu.youfun0927.fragment.goodsdetailfragments;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.GoodDetailBean;

/**
 * Created by Administrator on 2016/10/11.
 */
public class AllEvaluteFragment extends Basefragment {

    private TextView textView;

    public AllEvaluteFragment(GoodDetailBean goodDetailBean) {

    }

    @Override
    public View initView() {
        Log.e("TAG", "全部评价初始化了");
        textView = new TextView(mContext);
        textView.setTextSize(30);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;

    }

    @Override
    public void initData() {
        super.initData();

        Log.e("TAG","全部评价被初始化了");
        textView.setText("全部评价内容");

    }
}
