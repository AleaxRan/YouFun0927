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

/**
 * Created by Administrator on 2016/10/5.
 */
public class Listv3HomeViewHolder extends BaseViewHolder {


    //private final LinearLayout linearLayout;
    private final ImageView img_little1;
    private final ImageView img_little2;
    private final List<ImageView> imageViews;

    public Listv3HomeViewHolder(Context context, View itemView) {
        super(context, itemView);

//        linearLayout = (LinearLayout) itemView;

        img_little1 = (ImageView) itemView.findViewById(R.id.img_little1);
        img_little2 = (ImageView) itemView.findViewById(R.id.img_little2);
        imageViews = new ArrayList<>();
        imageViews.add(img_little1);
        imageViews.add(img_little2);
    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        List<HomeMen.DataBean.ModuleBean.DataBean2> dataList = modulelist.getData();

        //linearLayout.removeAllViews();
        for(int i = 0;i<dataList.size();i++) {
//            ImageView img_little;

//            View view = LayoutInflater.from(context).inflate(R.layout.item_home_img_v3_module, null);
//            img_little = (ImageView) view.findViewById(R.id.img_little);
//            img_little.setScaleType(ImageView.ScaleType.CENTER_CROP);
            final HomeMen.DataBean.ModuleBean.DataBean2 dataBean2 = dataList.get(i);

            Glide.with(context)
                    .load(dataBean2.getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViews.get(i));

            imageViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,JumpWebviewHomeActivity.class);

                    intent.putExtra("jumpurl",dataBean2.getJump().getUrl());
                    intent.putExtra("title",dataBean2.getTitle());
                    intent.putExtra("jumpname",dataBean2.getJump().getName());
                    context.startActivity(intent);
                }
            });

//            linearLayout.addView(view);

        }

    }

}
