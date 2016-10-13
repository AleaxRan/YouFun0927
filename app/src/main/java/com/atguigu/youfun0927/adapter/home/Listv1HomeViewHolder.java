package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.activity.JumpWebviewHomeActivity;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.HomeMen;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class Listv1HomeViewHolder extends BaseViewHolder {


    private final TextView c_title;
    private final TextView e_title;
    private final ImageView img_big;
    private final LinearLayout horizontal_scrollview;

    public Listv1HomeViewHolder(Context context, View itemView) {
        super(context, itemView);

        c_title = (TextView) itemView.findViewById(R.id.c_title);
        e_title = (TextView) itemView.findViewById(R.id.e_title);
        img_big = (ImageView) itemView.findViewById(R.id.img_big);
        horizontal_scrollview = (LinearLayout) itemView.findViewById(R.id.horizontal_scrollview);
    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        List<HomeMen.DataBean.ModuleBean.DataBean2> datalist = modulelist.getData();

        c_title.setText(modulelist.getC_title());
        e_title.setText(modulelist.getE_title());

        final HomeMen.DataBean.ModuleBean.DataBean2 dataBean2 = datalist.get(0);

        Glide.with(context)
                .load(dataBean2.getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_big);

        img_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,JumpWebviewHomeActivity.class);

                intent.putExtra("jumpurl",dataBean2.getJump().getUrl());
                intent.putExtra("title",dataBean2.getTitle());
                intent.putExtra("jumpname",dataBean2.getJump().getName());
                context.startActivity(intent);
            }
        });

        horizontal_scrollview.removeAllViews();
        //上一个是0这个从1开始
        for(int i = 1;i<datalist.size();i++){
            ImageView img_little;

            View view = LayoutInflater.from(context).inflate(R.layout.item_home_listv1_module, null);
            img_little = (ImageView) view.findViewById(R.id.img_little);

            final HomeMen.DataBean.ModuleBean.DataBean2 dataBean2b = datalist.get(i);

            Glide.with(context)
                    .load(dataBean2b.getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_little);
            horizontal_scrollview.addView(view);

            img_little.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,JumpWebviewHomeActivity.class);

                    intent.putExtra("jumpurl",dataBean2b.getJump().getUrl());
                    intent.putExtra("title",dataBean2b.getTitle());
                    intent.putExtra("jumpname",dataBean2b.getJump().getName());

                    context.startActivity(intent);

                }
            });

        }


    }
}
