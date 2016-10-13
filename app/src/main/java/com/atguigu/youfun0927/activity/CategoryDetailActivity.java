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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.fragment.branddetailfragments.CategoryDetailFragment;
import com.atguigu.youfun0927.utils.Constants;
import com.atguigu.youfun0927.view.ObservableScrollView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryDetailActivity extends FragmentActivity implements ObservableScrollView.Callbacks {

    @Bind(R.id.top_cebian_brand_detail)
    ImageButton topCebianBrandDetail;
    @Bind(R.id.tv_category_title_detail)
    TextView tv_category_title_detail;
    @Bind(R.id.top_shopbag_brand_detail)
    ImageButton topShopbagBrandDetail;
    @Bind(R.id.rla_title)
    RelativeLayout rlaTitle;
    @Bind(R.id.img_header_category_detail)
    ImageView imgHeaderCategoryDetail;
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
    private List<Basefragment> basefragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
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

        String category_img = getIntent().getStringExtra("category_img");
        String category_name = getIntent().getStringExtra("category_name");
        Glide.with(this)
                .load(category_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgHeaderCategoryDetail);

        tv_category_title_detail.setText(category_name);

        initViewPager();
    }

    private void initViewPager() {
        basefragmentList = new ArrayList<>();
        String category_id = getIntent().getStringExtra("category_id");

        String newCategory = Constants.PINLEI_NEW_PRE + category_id + Constants.PINLEI_NEW_TAIL;
        String hotCategory = Constants.PINLEI_HOT_SALE_PRE + category_id + Constants.PINLEI_HOT_SALE_TAIL;
        String priceCategory = Constants.PINLEI_PRICE_PRE + category_id + Constants.PINLEI_PRICE_TAIL;

        basefragmentList.add(new CategoryDetailFragment(newCategory));
        basefragmentList.add(new CategoryDetailFragment(hotCategory));
        basefragmentList.add(new CategoryDetailFragment(priceCategory));

        viewpagerBrandDetail.setAdapter(new MyAdapter(getSupportFragmentManager() ));

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
