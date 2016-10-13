package com.atguigu.youfun0927.fragment.branddetailfragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.adapter.classify.BrandDetailRecyclerView;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.BrandDeatil;
import com.atguigu.youfun0927.utils.LogUtil;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/8.
 */
public class BrandDetailFragments extends Basefragment {

    String url;

    private RecyclerView recyclerview;
    private MaterialRefreshLayout refreshlayout;

    public BrandDetailFragments(String brand_url) {
        this.url = brand_url;

    }

    @Override
    public View initView() {
        Log.e("TAG", "品牌展示被初始化了");
        View view = LayoutInflater.from(mContext).inflate(R.layout.brand_detail_fragment,null);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        refreshlayout = (MaterialRefreshLayout) view.findViewById(R.id.refreshlayout);

        initRefresh();
        return view;

    }

    private void initRefresh() {
        refreshlayout.autoRefresh();

        refreshlayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getDataFromNet();
            }
        });

    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();

    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallBack());

    }

    class MyStringCallBack extends StringCallback{


        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("BrandDeatilFragment链接错误");
        }

        @Override
        public void onResponse(String response, int id) {
            refreshlayout.finishRefresh();
            processData(response);

        }
    }

    private void processData(String response) {
        Gson gson = new Gson();

        BrandDeatil brandDeatil = gson.fromJson(response, BrandDeatil.class);

        List<BrandDeatil.ResultsBean> results = brandDeatil.getResults();

        BrandDetailRecyclerView adapter = new BrandDetailRecyclerView(mContext,results);

        recyclerview.setAdapter(adapter);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setLayoutManager(new GridLayoutManager(mContext,2));
    }

}
