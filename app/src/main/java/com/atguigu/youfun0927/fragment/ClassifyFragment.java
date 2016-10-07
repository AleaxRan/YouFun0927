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

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.fragment.classifychildfragment.BrandFragment;
import com.atguigu.youfun0927.fragment.classifychildfragment.CategoryFragment;
import com.atguigu.youfun0927.fragment.classifychildfragment.DynamicFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/27.
 */
public class ClassifyFragment extends Basefragment {


    @Bind(R.id.top_cehua_main)
    ImageButton topCehuaMain;
    @Bind(R.id.smarttl_classify)
    SmartTabLayout smarttl_classify;
    @Bind(R.id.top_search_main)
    ImageButton topSearchMain;
    @Bind(R.id.classify_title)
    RelativeLayout classifyTitle;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private List<Basefragment> basefragments;

    @Override
    public View initView() {
        Log.e("TAG", "分类被初始化了");
        View view = LayoutInflater.from(mContext).inflate(R.layout.classify_fragment, null);
        ButterKnife.bind(this, view);


        return view;

    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "分类数据被初始化了");

        initFragmentPager();


    }

    private void initFragmentPager() {

        basefragments = new ArrayList<>();

        basefragments.add(new CategoryFragment());
        basefragments.add(new BrandFragment());
        basefragments.add(new DynamicFragment());

        viewpager.setAdapter(new MyAdapter(getFragmentManager()));

        smarttl_classify.setViewPager(viewpager);
    }


    class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence title = "";
            switch (position){
                case 0:
                    title = "品类";
                    break;
                case 1:
                    title = "品牌";
                    break;
                case 2:
                    title = "动态";
                    break;

            }

            return title;
        }



        @Override
        public Fragment getItem(int position) {
            return basefragments.get(position);
        }

        @Override
        public int getCount() {
            return basefragments.size();
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
