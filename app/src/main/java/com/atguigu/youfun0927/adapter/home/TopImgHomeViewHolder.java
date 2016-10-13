package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

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
 * Created by Administrator on 2016/9/29.
 */
public class TopImgHomeViewHolder extends BaseViewHolder {


    private final BGABanner banner;

    public TopImgHomeViewHolder(Context context, View itemView) {
        super(context, itemView);
        banner = (BGABanner) itemView.findViewById(R.id.banner_guide_content);
    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        List<HomeMen.DataBean.ModuleBean.DataBean2> data = modulelist.getData();

        List<View> viewList = new ArrayList<>();

        for(int i = 0; i < data.size(); i++) {

            ImageView imageView = new ImageView(context);

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            final HomeMen.DataBean.ModuleBean.DataBean2 dataBean2 = data.get(i);

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

        banner.setData(viewList);

    }




}
