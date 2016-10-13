package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.activity.JumpWebviewHomeActivity;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.HomeMen;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by Administrator on 2016/10/5.
 */
public class ImgHomeViewHolder extends BaseViewHolder {


    private final TextView c_title;
    private final TextView e_title;
    private final TextView bottom_title;
    private final BGABanner banner;

    public ImgHomeViewHolder(Context context, View itemView) {
        super(context, itemView);

        c_title = (TextView) itemView.findViewById(R.id.c_title);
        e_title = (TextView) itemView.findViewById(R.id.e_title);
        bottom_title = (TextView) itemView.findViewById(R.id.bottom_title);
        banner = (BGABanner) itemView.findViewById(R.id.banner_top_home);
    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        final List<HomeMen.DataBean.ModuleBean.DataBean2> datalist = modulelist.getData();

        List<View> viewList = new ArrayList<>();

        c_title.setText(modulelist.getC_title());

        e_title.setText(modulelist.getE_title());

        for(int i = 0; i < datalist.size(); i++) {

            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            final HomeMen.DataBean.ModuleBean.DataBean2 dataBean2 = datalist.get(i);
            Glide.with(context)
                    .load(dataBean2.getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

            viewList.add(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context,JumpWebviewHomeActivity.class);

                    intent.putExtra("jumpurl",dataBean2.getJump().getUrl());
                    intent.putExtra("title",dataBean2.getTitle());
                    intent.putExtra("jumpname",dataBean2.getJump().getName());

                    context.startActivity(intent);
                }
            });

        }

//        banner.setAdapter(new BGABanner.Adapter() {
//            @Override
//            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
//                ((ImageView)view).setImageResource((int)model);
//            }
//        });
//
//        banner.setData(viewList,null);


        banner.setData(viewList);

        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                bottom_title.setText(datalist.get(position).getTitle());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
