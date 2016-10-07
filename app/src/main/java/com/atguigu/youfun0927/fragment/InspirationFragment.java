package com.atguigu.youfun0927.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.LGbean;
import com.atguigu.youfun0927.fragment.lgchildfragments.LgchidFragments;
import com.atguigu.youfun0927.utils.Constants;
import com.atguigu.youfun0927.utils.LogUtil;
import com.google.gson.Gson;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/27.
 */
public class InspirationFragment extends Basefragment {


    @Bind(R.id.tv_zixun)
    TextView tvZixun;
    @Bind(R.id.top_search_main)
    ImageButton topSearchMain;
    @Bind(R.id.rla_title)
    RelativeLayout rlaTitle;
    @Bind(R.id.smarttl_inspiration)
    SmartTabLayout smarttlInspiration;
    @Bind(R.id.viewpager_inspiration)
    ViewPager viewpagerInspiration;

    private List<Basefragment> lglistchildfragments;

    private List<LGbean.DataBean.AttrBean> attrtitles;

    @Override
    public View initView() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.inspiration, null);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "灵感数据被初始化了");

        getDataFromNet();

    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(Constants.LG_1)
                .id(100)
                .build()
                .execute(new MyStringCallBack());



    }

    class MyStringCallBack extends StringCallback{


        @Override
        public void onError(Call call, Exception e, int id) {

            LogUtil.e("链接失败");

        }

        @Override
        public void onResponse(String response, int id) {

            processData(response);
        }

    }

    private void processData(String response) {

        Gson gson = new Gson();
        LGbean lGbean = gson.fromJson(response, LGbean.class);
        attrtitles = lGbean.getData().getAttr();
        //List<LGbean.DataBean.ListBean> list = lGbean.getData().getList();

        initViewPager();

    }

    private void initViewPager() {

        lglistchildfragments = new ArrayList<>();
        String [] urlArr = {Constants.LG_1,Constants.LG_2,Constants.LG_3,Constants.LG_4,Constants.LG_5,Constants.LG_6,Constants.LG_7,Constants.LG_8};

        for(int i = 0; i < attrtitles.size(); i++) {

            lglistchildfragments.add(new LgchidFragments(urlArr[i]));
        }

        viewpagerInspiration.setAdapter(new MyFragmentPagerAdapter(getFragmentManager()));

        smarttlInspiration.setViewPager(viewpagerInspiration);

    }
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {


        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {


            return attrtitles.get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {
            return lglistchildfragments.get(position);
        }

        @Override
        public int getCount() {
            return lglistchildfragments.size();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
