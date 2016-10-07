package com.atguigu.youfun0927.adapter.classify;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.Brand;
import com.atguigu.youfun0927.bean.HomeMen;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Administrator on 2016/10/6.
 */
public class BradViewHolder extends BaseViewHolder {


    private final ImageView img_brand;

    public BradViewHolder(Context context, View itemView) {
        super(context, itemView);
        img_brand = (ImageView) itemView.findViewById(R.id.img_brand);
    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

    }

    public void setBrandData(Brand brandData){

        Glide.with(context)
                .load(brandData.getLogo_img())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_brand);

    }
}
