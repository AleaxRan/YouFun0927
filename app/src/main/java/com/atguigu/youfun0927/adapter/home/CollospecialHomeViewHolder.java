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
public class CollospecialHomeViewHolder extends BaseViewHolder {


    private final TextView c_title;
    private final TextView e_title;
    private final LinearLayout horizontal_scrollview;

    public CollospecialHomeViewHolder(Context context, View itemView) {
        super(context, itemView);

        c_title = (TextView) itemView.findViewById(R.id.c_title);
        e_title = (TextView) itemView.findViewById(R.id.e_title);
        horizontal_scrollview = (LinearLayout) itemView.findViewById(R.id.horizontal_scrollview);

    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        List<HomeMen.DataBean.ModuleBean.DataBean2> datalist = modulelist.getData();

        c_title.setText(modulelist.getC_title());
        e_title.setText(modulelist.getE_title());

        horizontal_scrollview.removeAllViews();

        for(int i = 0;i<datalist.size();i++){
            ImageView img_collospecial;

            View view = LayoutInflater.from(context).inflate(R.layout.item_home_collospecial_module, null);
            img_collospecial = (ImageView) view.findViewById(R.id.img_collospecial);

            final HomeMen.DataBean.ModuleBean.DataBean2 dataBean2 = datalist.get(i);
            Glide.with(context)
                    .load(dataBean2.getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_collospecial);
            horizontal_scrollview.addView(view);

            img_collospecial.setOnClickListener(new View.OnClickListener() {
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


}
