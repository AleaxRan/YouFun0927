package com.atguigu.youfun0927.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.BrandStory;
import com.atguigu.youfun0927.fragment.branddetailfragments.BrandDetailFragments;
import com.atguigu.youfun0927.utils.Constants;
import com.atguigu.youfun0927.utils.LogUtil;
import com.atguigu.youfun0927.view.ObservableScrollView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class BrandDetailActivity extends FragmentActivity implements ObservableScrollView.Callbacks {

    @Bind(R.id.top_cebian_brand_detail)
    ImageButton topCebianBrandDetail;
    @Bind(R.id.tv_men_women_brand_detail)
    TextView tvMenWomenBrandDetail;
    @Bind(R.id.top_search_brand_detail)
    ImageButton topSearchBrandDetail;
    @Bind(R.id.top_share_brand_detail)
    ImageButton topShareBrandDetail;
    @Bind(R.id.rla_title)
    RelativeLayout rlaTitle;
    @Bind(R.id.img_header_brand_detail)
    ImageView imgHeaderBrandDetail;
    @Bind(R.id.tv_brand_name_detail)
    TextView tvBrandNameDetail;
    @Bind(R.id.btn_collect_brand)
    Button btnCollectBrand;
    @Bind(R.id.img_logo)
    ImageView imgLogo;
    @Bind(R.id.stopView)
    View stopView;
    @Bind(R.id.viewpager_brand_detail)
    ViewPager viewpagerBrandDetail;
    @Bind(R.id.smart_tablayout)
    SmartTabLayout smartTablayout;
    @Bind(R.id.tv_select)
    TextView tvSelect;
    @Bind(R.id.stickyView)
    LinearLayout stickyView;
    @Bind(R.id.scrollView_brand_detail)
    ObservableScrollView scrollViewBrandDetail;
    private String url;
    private BrandStory.DataBean data;
    private List<Basefragment> basefragmentList;
    private String brandJumpName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);
        ButterKnife.bind(this);

        initData();

        initListener();
    }

    private void initListener() {
        ViewGroup.LayoutParams params = viewpagerBrandDetail.getLayoutParams();
        params.height = 10000;
        viewpagerBrandDetail.setLayoutParams(params);
        scrollViewBrandDetail.setCallbacks(this);
        scrollViewBrandDetail.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        onScrollChanged(scrollViewBrandDetail.getScrollY());
                    }
                });
        scrollViewBrandDetail.scrollTo(0, 0);
        scrollViewBrandDetail.smoothScrollTo(0, 0);

    }


    @Override
    public void onScrollChanged(int scrollY) {
        //设置浮动view相对于父视图的位置
        ((LinearLayout) this.findViewById(R.id.stickyView))
                .setTranslationY(Math.max(stopView.getTop(), scrollY));

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent() {

    }



    private void initData() {

        brandJumpName = getIntent().getStringExtra("brandJumpName");

        //url = Constants.BRANDDETAIL_PRE + brandJumpName + Constants.BRANDDETAIL_TAIL;

        url = Constants.BRAND_STORY_PRE + brandJumpName + Constants.BRAND_STORY_TAIL;

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
            LogUtil.e("品牌点击页面加载失败");

        }

        @Override
        public void onResponse(String response, int id) {

            processData(response);
        }
    }

    private void processData(String response) {

        Gson gson = new Gson();
        BrandStory brandStory = gson.fromJson(response, BrandStory.class);
        data = brandStory.getData();

        if(data !=null) {
            setData();
        }

    }

    private void setData() {

        Glide.with(this)
                .load(data.getYoufan_img())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgHeaderBrandDetail);

        Glide.with(this)
                .load(data.getLogo_img())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgLogo);

        tvBrandNameDetail.setText(data.getEname());

        initViewPager();
    }

    //添加到viewpager里
    private void initViewPager() {

        basefragmentList = new ArrayList<>();

        String brand_url = Constants.BRANDDETAIL_PRE + brandJumpName + Constants.BRANDDETAIL_TAIL;
        String brandprice_url = Constants.BRANDDETAIL_PRICE_PRE + brandJumpName + Constants.BRANDDETAIL_PRICE_TAIL;

        basefragmentList.add(new BrandDetailFragments(brand_url));
        basefragmentList.add(new BrandDetailFragments(brand_url));
        basefragmentList.add(new BrandDetailFragments(brandprice_url));

       viewpagerBrandDetail.setAdapter(new MyAdapter(getSupportFragmentManager()));

        smartTablayout.setViewPager(viewpagerBrandDetail);

    }
    class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return basefragmentList.get(position);
        }

        @Override
        public int getCount() {
            return basefragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            switch (position) {
                case 0:
                    title = "上新";
                    break;
                case 1:
                    title = "热销";
                    break;
                case 2:
                    title = "价格";
                    break;
            }
            return title;

        }
    }

}
