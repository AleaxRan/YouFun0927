package com.atguigu.youfun0927.adapter.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.Brand;

import java.util.List;

/**
 * Created by Administrator on 2016/10/6.
 */
public class BrandRecyclerView extends RecyclerView.Adapter<BaseViewHolder> {

    List<Brand> brandDatalist;
    Context mContext;

    public BrandRecyclerView(List<Brand> brandDatalist, Context mContext) {

        this.brandDatalist = brandDatalist;
        this.mContext = mContext;

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_brand, parent, false);

        BradViewHolder bradViewHolder = new BradViewHolder(mContext, view);

        return bradViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        ((BradViewHolder)holder).setBrandData(brandDatalist.get(position));

    }

    @Override
    public int getItemCount() {
        return brandDatalist.size();
    }
}
