package com.atguigu.youfun0927.fragment;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.adapter.home.HomeMenRecyclerViewAdapter;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.HomeMen;
import com.atguigu.youfun0927.utils.CacheUtils;
import com.atguigu.youfun0927.utils.Constants;
import com.atguigu.youfun0927.utils.LogUtil;
import com.atguigu.youfun0927.utils.lodingpager.LoadingAndRetryManager;
import com.atguigu.youfun0927.utils.lodingpager.OnLoadingAndRetryListener;
import com.atguigu.youfun0927.view.MyPopupWindow;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
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
    private MaterialRefreshLayout refreshlayout;
    private LoadingAndRetryManager loadingAndRetryManager;
    private TextView tv_men;
    private RelativeLayout rla_title;
    private String url;



    @Override
    public View initView() {
        Log.e("TAG", "主页被初始化了");

        View view = LayoutInflater.from(mContext).inflate(R.layout.home_fragment, null);

        rc_home = (RecyclerView) view.findViewById(R.id.rc_home);
        refreshlayout = (MaterialRefreshLayout) view.findViewById(R.id.refreshlayout);

        tv_men = (TextView) view.findViewById(R.id.tv_men_women);
        rla_title = (RelativeLayout) view.findViewById(R.id.rla_title);

        tv_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });

        initRefresh();


        return view;

    }

    private void showPopupWindow() {

        final MyPopupWindow popupWindow  = new MyPopupWindow(mContext);

        popupWindow.setOnPopupClickListener(new MyPopupWindow.OnPopupClickListener() {
            @Override
            public void onClick(View view, int index) {
                switch (index) {
                    case 0:
                        url = Constants.HOME_MEN;
                        tv_men.setText("男生");

                        break;
                    case 1:

                        url = Constants.HOME_WOMEN;

                        tv_men.setText("女生");
                        break;
                    case 2:
                        url = Constants.HOME_LIFE;

                        tv_men.setText("生活");
                        break;
                }

                //Toast.makeText(getActivity(),index+"",Toast.LENGTH_SHORT).show();

                CacheUtils.putInt(mContext, Constants.SEXURL, index);

                getDataFromNet();

                popupWindow.dismiss();

                //发送广播让分类界面加载不同的url
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);

            }
        });



        popupWindow.showAsDropDown(rla_title);
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
        Log.e("TAG", "主页数据被初始化了");

        loadingAndRetryManager = new LoadingAndRetryManager(refreshlayout, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {

                HomeFragment.this.setRetryEvent(retryView);

            }
        });
        loadingAndRetryManager.showLoading();

        getDataFromNet();
    }

    private void setRetryEvent(View retryView) {
        View view = retryView.findViewById(R.id.id_btn_retry);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingAndRetryManager.showLoading();
                getDataFromNet();
            }
        });


    }

    private void getDataFromNet() {

       if(CacheUtils.getInt(mContext,Constants.SEXURL) != -1) {

           int sexurl = CacheUtils.getInt(mContext, Constants.SEXURL);

           switch (sexurl){
               case 0:
                   url = Constants.HOME_MEN;
                   tv_men.setText("男生");
                   break;
               case 1:
                   url = Constants.HOME_WOMEN;
                   tv_men.setText("女生");
                   break;
               case 2:
                   url = Constants.HOME_LIFE;
                   tv_men.setText("生活");
                   break;
           }


       }else{

           url = Constants.HOME_MEN;

           tv_men.setText("男生");
       }

        OkHttpUtils.get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallBack());


    }


    private class MyStringCallBack extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
            loadingAndRetryManager.showRetry();

            LogUtil.e("请求失败");
        }

        @Override
        public void onResponse(String response, int id) {
            refreshlayout.finishRefresh();

            processData(response);

        }
    }

    private void processData(String json) {
        if(TextUtils.isEmpty(json)) {
            loadingAndRetryManager.showEmpty();
            LogUtil.e("主页数据为空");
        }



        Gson gson = new Gson();
        HomeMen homeMen = gson.fromJson(json, HomeMen.class);

        List<HomeMen.DataBean.ModuleBean> module = homeMen.getData().getModule();

        HomeMenRecyclerViewAdapter adapter = new HomeMenRecyclerViewAdapter(module, mContext);

        LogUtil.e(adapter + ".........");

        rc_home.setAdapter(adapter);

        //布局管理器
        rc_home.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        loadingAndRetryManager.showContent();
    }
}
