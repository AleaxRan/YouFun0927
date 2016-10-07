package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.atguigu.youfun0927.R;
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

            Glide.with(context)
                    .load(data.get(i).getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            viewList.add(imageView);

        }

        banner.setData(viewList);

    }
}
