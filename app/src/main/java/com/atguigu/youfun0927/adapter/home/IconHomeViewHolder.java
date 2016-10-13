package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.content.Intent;
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

/**
 * Created by Administrator on 2016/9/29.
 */
public class IconHomeViewHolder extends BaseViewHolder {


    private final ImageView img1;
    private final TextView tv1;
    private final ImageView img2;
    private final TextView tv2;
    private final ImageView img3;
    private final TextView tv3;
    private final ImageView img4;
    private final TextView tv4;
    private final ImageView img5;
    private final TextView tv5;
    private final ImageView img6;
    private final TextView tv6;
    private final ImageView img7;
    private final TextView tv7;
    private final ImageView img8;
    private final TextView tv8;
    private final List<ImageView> imageViews;
    private final List<TextView> textViews;

    public IconHomeViewHolder(Context context, View itemView) {
        super(context, itemView);

        img1 = (ImageView) itemView.findViewById(R.id.img1);
        tv1 = (TextView) itemView.findViewById(R.id.tv1);
        img2 = (ImageView) itemView.findViewById(R.id.img2);
        tv2 = (TextView) itemView.findViewById(R.id.tv2);
        img3 = (ImageView) itemView.findViewById(R.id.img3);
        tv3 = (TextView) itemView.findViewById(R.id.tv3);
        img4 = (ImageView) itemView.findViewById(R.id.img4);
        tv4 = (TextView) itemView.findViewById(R.id.tv4);
        img5 = (ImageView) itemView.findViewById(R.id.img5);
        tv5 = (TextView) itemView.findViewById(R.id.tv5);
        img6 = (ImageView) itemView.findViewById(R.id.img6);
        tv6 = (TextView) itemView.findViewById(R.id.tv6);
        img7 = (ImageView) itemView.findViewById(R.id.img7);
        tv7 = (TextView) itemView.findViewById(R.id.tv7);
        img8 = (ImageView) itemView.findViewById(R.id.img8);
        tv8 = (TextView) itemView.findViewById(R.id.tv8);

        imageViews = new ArrayList<>();
         imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);
        imageViews.add(img4);
        imageViews.add(img5);
        imageViews.add(img6);
        imageViews.add(img7);
        imageViews.add(img8);
        textViews = new ArrayList<>();
        textViews.add(tv1);
        textViews.add(tv2);
        textViews.add(tv3);
        textViews.add(tv3);
        textViews.add(tv4);
        textViews.add(tv5);
        textViews.add(tv6);
        textViews.add(tv7);
        textViews.add(tv8);

    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        final List<HomeMen.DataBean.ModuleBean.DataBean2> data = modulelist.getData();

        for(int i = 0; i < data.size(); i++) {

            final HomeMen.DataBean.ModuleBean.DataBean2 dataBean2 = data.get(i);

            Glide.with(context)
                    .load(dataBean2.getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_launcher)//真正加载的默认图片
                    .error(R.drawable.ic_launcher)//失败的默认图片
                    .into(imageViews.get(i));

            textViews.get(i).setText(dataBean2.getTitle());


            imageViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = dataBean2.getJump().getUrl();
                    Intent intent = new Intent(context, JumpWebviewHomeActivity.class);

                    intent.putExtra("jumpurl",dataBean2.getJump().getUrl());
                    intent.putExtra("title",dataBean2.getTitle());
                    intent.putExtra("jumpname",dataBean2.getJump().getName());

                    context.startActivity(intent);

                }
            });

        }

    }
}
