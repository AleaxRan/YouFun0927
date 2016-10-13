package com.atguigu.youfun0927.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.GoodDetailBean;
import com.atguigu.youfun0927.fragment.goodsdetailfragments.AllEvaluteFragment;
import com.atguigu.youfun0927.fragment.goodsdetailfragments.BuyConsultFragment;
import com.atguigu.youfun0927.fragment.goodsdetailfragments.SingleGoodsFragment;
import com.atguigu.youfun0927.utils.Constants;
import com.atguigu.youfun0927.view.ShopbagPopupWindow;
import com.atguigu.youfun0927.view.verticalsilde.DragLayout;
import com.atguigu.youfun0927.view.verticalsilde.DrayScrollView;
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
import cn.bingoogolapple.bgabanner.BGABanner;
import okhttp3.Call;

public class GoodsDetailActivity extends FragmentActivity {


    @Bind(R.id.banner_guide)
    BGABanner bannerGuide;
    @Bind(R.id.tv_show_name)
    TextView tvShowName;
    @Bind(R.id.tv_sale_price)
    TextView tvSalePrice;
    @Bind(R.id.tv_market_price)
    TextView tvMarketPrice;
    @Bind(R.id.lin_activity)
    LinearLayout linActivity;
    @Bind(R.id.img_brand_dispatch)
    ImageView imgBrandDispatch;
    @Bind(R.id.tv_show_bottom)
    TextView tvShowBottom;
    @Bind(R.id.observable_scrollview)
    DrayScrollView observableScrollview;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.img_share)
    ImageView imgShare;
    @Bind(R.id.imgb_back_jump)
    ImageButton imgbBackJump;
    @Bind(R.id.tv_title_jump)
    TextView tvTitleJump;
    @Bind(R.id.imgb_share_jump)
    ImageButton imgbShareJump;
    @Bind(R.id.smart_tablayout)
    SmartTabLayout smartTablayout;
    @Bind(R.id.viewpager_content)
    ViewPager viewpagerContent;
    @Bind(R.id.slidedetails)
    DragLayout slidedetails;
    @Bind(R.id.img_purchase_bag)
    ImageView imgPurchaseBag;
    @Bind(R.id.btn_purchase_bag)
    Button btnPurchaseBag;
    @Bind(R.id.img_collect)
    ImageView imgCollect;
    @Bind(R.id.lin_top)
    LinearLayout linTop;
    @Bind(R.id.lin_all)
    LinearLayout linAll;
    @Bind(R.id.img_baoyou)
    TextView imgBaoyou;
    @Bind(R.id.rla_title)
    RelativeLayout rlaTitle;

    private String goods_code;
    private GoodDetailBean.DataBean.ClsInfoBean clsInfo;
    private List<GoodDetailBean.DataBean.ProPicUrlBean> proPicUrl;
    private GoodDetailBean goodDetailBean;
    private List<Basefragment> basefragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        goods_code = getIntent().getStringExtra("goods_code");

        getDataFromNet();

        btnPurchaseBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopupWidow();
            }
        });
    }

    private void showPopupWidow() {

        ShopbagPopupWindow shopbagPopupWindow = new ShopbagPopupWindow(this,goods_code);

        shopbagPopupWindow.showAsDropDown(rlaTitle,0,-1500);
    }

    private void getDataFromNet() {

        String url = Constants.GOODS_DETAIL_PRE + goods_code + Constants.GOODS_DETAIL_TAIL;
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new MyStringCallBack());

    }

    class MyStringCallBack extends StringCallback {


        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {

            ProcessData(response);

        }

    }

    private void ProcessData(String response) {
        Gson gson = new Gson();
        goodDetailBean = gson.fromJson(response, GoodDetailBean.class);

        clsInfo = goodDetailBean.getData().getClsInfo();

        proPicUrl = goodDetailBean.getData().getProPicUrl();

        //添加第一布局数据
        initData();
    }

    private void initData() {
        tvShowName.setText(clsInfo.getShowName());
        String sale_price = clsInfo.getSale_price();
        tvSalePrice.setText("￥" + sale_price.substring(0, sale_price.lastIndexOf(".")));
        String marketPrice = clsInfo.getMarketPrice();
        tvMarketPrice.setText("￥" + marketPrice.substring(0, marketPrice.lastIndexOf(".")));
        tvMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //添加顶部展示图
        initBanner();

        Glide.with(this)
                .load("http://metersbonwe.qiniucdn.com/youfanchengnuo_shangjia.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgBrandDispatch);

        String name = goodDetailBean.getData().getActivity().get(0).getName();
        imgBaoyou.setText(name);

        initSecondPage();

    }

    private void initSecondPage() {

        basefragmentList = new ArrayList<>();

        basefragmentList.add(new SingleGoodsFragment(goodDetailBean));
        basefragmentList.add(new AllEvaluteFragment(goodDetailBean));
        basefragmentList.add(new BuyConsultFragment(goodDetailBean));

        viewpagerContent.setAdapter(new MyAdapter(getSupportFragmentManager()));

        smartTablayout.setViewPager(viewpagerContent);
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
                    title = "单品详情";
                    break;
                case 1:
                    title = "全部评价";
                    break;
                case 2:
                    title = "购买咨询";
                    break;
            }
            return title;
        }
    }

    private void initBanner() {

        List<View> viewList = new ArrayList<>();

        for (int i = 0; i < proPicUrl.size(); i++) {

            ImageView imageView = new ImageView(this);

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            GoodDetailBean.DataBean.ProPicUrlBean proPicUrlBean = proPicUrl.get(i);

            Glide.with(this)
                    .load(proPicUrl.get(i).getFilE_PATH())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            viewList.add(imageView);

        }
        bannerGuide.setData(viewList);
    }

}
