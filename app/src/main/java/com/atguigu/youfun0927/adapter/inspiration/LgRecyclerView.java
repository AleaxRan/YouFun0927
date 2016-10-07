package com.atguigu.youfun0927.adapter.inspiration;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.LGbean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/7.
 */
public class LgRecyclerView extends RecyclerView.Adapter<BaseViewHolder> {

    Context mContext;

    List<LGbean.DataBean.ListBean> list;

    public LgRecyclerView(Context mContext, List<LGbean.DataBean.ListBean> list) {

        this.mContext = mContext;
        this.list = list;

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.lgchild_item, null);

        LgChildViewHolder lgChildViewHolder = new LgChildViewHolder(mContext, view);

        return lgChildViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        ((LgChildViewHolder)holder).setLgData(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
