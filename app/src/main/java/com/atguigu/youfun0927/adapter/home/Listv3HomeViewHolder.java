package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.HomeMen;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class Listv3HomeViewHolder extends BaseViewHolder {


    private final LinearLayout linearLayout;

    public Listv3HomeViewHolder(Context context, View itemView) {
        super(context, itemView);

        linearLayout = (LinearLayout) itemView;
    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        List<HomeMen.DataBean.ModuleBean.DataBean2> dataList = modulelist.getData();

        linearLayout.removeAllViews();
        for(int i = 0;i<dataList.size();i++) {
            ImageView img_little;

            View view = LayoutInflater.from(context).inflate(R.layout.item_home_img_v3_module, null);
            img_little = (ImageView) view.findViewById(R.id.img_little);
            img_little.setScaleType(ImageView.ScaleType.CENTER_CROP);
            HomeMen.DataBean.ModuleBean.DataBean2 dataBean2 = dataList.get(i);
            Glide.with(context)
                    .load(dataBean2.getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_little);

            linearLayout.addView(view);

        }

    }


}
