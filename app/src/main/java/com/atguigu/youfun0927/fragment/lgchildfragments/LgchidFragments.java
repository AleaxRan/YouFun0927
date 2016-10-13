package com.atguigu.youfun0927.fragment.lgchildfragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.adapter.inspiration.LgRecyclerView;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.LGbean;
import com.atguigu.youfun0927.utils.LogUtil;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/6.
 */
public class LgchidFragments extends Basefragment {


    @Bind(R.id.recyclerview_lgchild)
    RecyclerView recyclerviewLgchild;
    @Bind(R.id.refreshlayout_inspiration_child)
    MaterialRefreshLayout refreshlayoutInspirationChild;

    String url;

    public LgchidFragments() {


    }

    public LgchidFragments(String url) {

        this.url = url;

    }

    @Override
    public View initView() {
        Log.e("TAG", "灵感子页面被初始化了");
        View view = LayoutInflater.from(mContext).inflate(R.layout.lgchild_fragments, null);
        ButterKnife.bind(this, view);

        refreshlayoutInspirationChild.autoRefresh();

        initRefresh();
        return view;

    }

    private void initRefresh() {

        refreshlayoutInspirationChild.setMaterialRefreshListener(new MaterialRefreshListener() {
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

            LogUtil.e("灵感子页面加载失败");
        }

        @Override
        public void onResponse(String response, int id) {
            if(refreshlayoutInspirationChild == null) {
                return;
            }

            refreshlayoutInspirationChild.finishRefresh();
            processData(response);

        }

    }

    private void processData(String response) {
        Gson gson = new Gson();
        LGbean lGbean = gson.fromJson(response, LGbean.class);
        List<LGbean.DataBean.ListBean> list = lGbean.getData().getList();
        if(recyclerviewLgchild==null) {
            LogUtil.e("111111111111");
            return;
        }

        recyclerviewLgchild.setAdapter(new LgRecyclerView(mContext,list));

        recyclerviewLgchild.setLayoutManager(new LinearLayoutManager(mContext));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
