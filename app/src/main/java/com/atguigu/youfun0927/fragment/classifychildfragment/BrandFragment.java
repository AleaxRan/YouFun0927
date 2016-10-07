package com.atguigu.youfun0927.fragment.classifychildfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.adapter.classify.BrandRecyclerView;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.Brand;
import com.atguigu.youfun0927.utils.Constants;
import com.atguigu.youfun0927.utils.LogUtil;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/5.
 */
public class BrandFragment extends Basefragment {


    @Bind(R.id.recyclerview_brand)
    RecyclerView recyclerviewBrand;
    @Bind(R.id.refreshlayout)
    MaterialRefreshLayout refreshlayout;
    private List<Brand> brandList;

    @Override
    public View initView() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.brand_fragment, null);
        ButterKnife.bind(this, view);

        initRefresh();

        return view;

    }

    private void initRefresh() {

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

        Log.e("TAG", "品牌数据被初始化了");
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(Constants.KIND_PINPAI_MEN)
                .id(100)
                .build()
                .execute(new MyStringCallBack());

    }



    class MyStringCallBack extends StringCallback {


        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("brand链接失败");
        }

        @Override
        public void onResponse(String response, int id) {
            refreshlayout.finishRefresh();
            processData(response);
        }
    }

    private void processData(String response) {

        JSONObject jsonObject = JSON.parseObject(response);
        String data = jsonObject.getString("data");
        brandList = JSON.parseArray(data, Brand.class);

        BrandRecyclerView brandRecyclerView = new BrandRecyclerView(brandList,mContext);

        recyclerviewBrand.setAdapter(brandRecyclerView);

        recyclerviewBrand.setLayoutManager(new GridLayoutManager(mContext,3));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
