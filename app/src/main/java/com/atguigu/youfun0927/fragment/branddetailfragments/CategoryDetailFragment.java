package com.atguigu.youfun0927.fragment.branddetailfragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.adapter.classify.CategoryDetailRecyclerView;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.BrandDeatil;
import com.atguigu.youfun0927.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/9.
 */
public class CategoryDetailFragment extends Basefragment {


    String url;
    private RecyclerView recyclerview;

    public CategoryDetailFragment(){


    }

    public CategoryDetailFragment(String url) {
        this.url = url;
    }

    @Override
    public View initView() {
        Log.e("TAG", "category初始化了");

        View view = LayoutInflater.from(mContext).inflate(R.layout.category_detail_fragment,null);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "category数据被初始化了");

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
            LogUtil.e("category链接错误");
        }

        @Override
        public void onResponse(String response, int id) {

            processData(response);

        }
    }

    private void processData(String response) {
        Gson gson = new Gson();
        BrandDeatil brandDeatil = gson.fromJson(response, BrandDeatil.class);

        List<BrandDeatil.ResultsBean> results = brandDeatil.getResults();

        CategoryDetailRecyclerView adapter = new CategoryDetailRecyclerView(mContext,results);

        recyclerview.setAdapter(adapter);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setLayoutManager(new GridLayoutManager(mContext,2));
    }

}
