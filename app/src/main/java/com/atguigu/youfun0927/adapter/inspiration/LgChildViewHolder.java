package com.atguigu.youfun0927.adapter.inspiration;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.HomeMen;
import com.atguigu.youfun0927.bean.LGbean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Administrator on 2016/10/7.
 */
public class LgChildViewHolder extends BaseViewHolder {

    private final ImageView img_item_lgchild;
    private final TextView tv_title;
    private final RelativeLayout tit_item_lg;

    public LgChildViewHolder(Context context, View itemView) {
        super(context, itemView);

        img_item_lgchild = (ImageView) itemView.findViewById(R.id.img_item_lgchild);
        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        tit_item_lg = (RelativeLayout) itemView.findViewById(R.id.tit_item_lg);

    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

    }

    public void setLgData(LGbean.DataBean.ListBean listBean){

        Glide.with(context)
                .load(listBean.getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_item_lgchild);

        tv_title.setText(listBean.getTitle());

    }
}
