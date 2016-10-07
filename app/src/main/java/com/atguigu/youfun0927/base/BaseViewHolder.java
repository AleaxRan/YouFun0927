package com.atguigu.youfun0927.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.atguigu.youfun0927.bean.HomeMen;

/**
 * Created by Administrator on 2016/9/29.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {


    public Context context;

    public BaseViewHolder(Context context,View itemView) {
        super(itemView);

        this.context = context;
    }

    public abstract void setData(HomeMen.DataBean.ModuleBean modulelist);



}
