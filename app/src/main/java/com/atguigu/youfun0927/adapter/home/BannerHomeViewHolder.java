package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.atguigu.youfun0927.R;
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

        String img = data.get(0).getImg();

        Glide.with(context)
                .load(img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(banner_img);

    }
}
