package com.atguigu.youfun0927.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.adapter.home.HomeMenRecyclerViewAdapter;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.HomeMen;
import com.atguigu.youfun0927.utils.Constants;
import com.atguigu.youfun0927.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/27.
 */
public class HomeFragment extends Basefragment {


    private RecyclerView rc_home;

    @Override
    public View initView() {
        Log.e("TAG", "主页被初始化了");

        View view = LayoutInflater.from(mContext).inflate(R.layout.home_fragment, null);

        rc_home = (RecyclerView) view.findViewById(R.id.rc_home);

        return view;

    }

    @Override
    public void initData() {
        super.initData();

        Log.e("TAG", "主页数据被初始化了");

        getDataFromNet();
    }

    private void getDataFromNet() {

        OkHttpUtils.get()
                .url(Constants.HOME_MEN)
                .id(100)
                .build()
                .execute(new MyStringCallBack());


    }




    private class MyStringCallBack extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("请求失败");
        }

        @Override
        public void onResponse(String response, int id) {
            processData(response);

        }
    }

    private void processData(String json) {

        Gson gson = new Gson();
        HomeMen homeMen = gson.fromJson(json, HomeMen.class);

        List<HomeMen.DataBean.ModuleBean> module = homeMen.getData().getModule();

        HomeMenRecyclerViewAdapter adapter = new HomeMenRecyclerViewAdapter(module, mContext);

        LogUtil.e(adapter + ".........");

        rc_home.setAdapter(adapter);

        //布局管理器
        rc_home.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    }
}
