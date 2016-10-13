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

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class BannerHomeViewHolder extends BaseViewHolder {

    private final ImageView banner_img;

    public BannerHomeViewHolder(Context context, View itemView) {
        super(context, itemView);

        banner_img = (ImageView) itemView.findViewById(R.id.banner_img);

    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        List<HomeMen.DataBean.ModuleBean.DataBean2> data = modulelist.getData();

        final HomeMen.DataBean.ModuleBean.DataBean2 dataBean2 = data.get(0);
        String img = dataBean2.getImg();

        Glide.with(context)
                .load(img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(banner_img);

        banner_img.setOnClickListener(new View.OnClickListener() {
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

}
