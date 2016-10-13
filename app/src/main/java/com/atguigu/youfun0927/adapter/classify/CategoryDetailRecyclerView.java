package com.atguigu.youfun0927.adapter.classify;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.activity.GoodsDetailActivity;
import com.atguigu.youfun0927.bean.BrandDeatil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
public class CategoryDetailRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;

    List<BrandDeatil.ResultsBean> results;

    public CategoryDetailRecyclerView(Context mContext, List<BrandDeatil.ResultsBean> results) {
        this.mContext = mContext;

        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_brand_detail, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder) holder).setData(results.get(position));

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img_goods;
        private final TextView tv_brand;
        private final TextView tv_name;
        private final TextView tv_sale_price;
        private final TextView tv_price;

        public MyViewHolder(View itemView) {
            super(itemView);

            img_goods = (ImageView) itemView.findViewById(R.id.img_goods);
            tv_brand = (TextView) itemView.findViewById(R.id.tv_brand);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_sale_price = (TextView) itemView.findViewById(R.id.tv_sale_price);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);

        }


        public void setData(final BrandDeatil.ResultsBean resultsBean) {

            BrandDeatil.ResultsBean.ClsInfoBean clsInfo = resultsBean.getClsInfo();
            Glide.with(mContext)
                    .load(clsInfo.getMainImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_goods);

            tv_brand.setText(clsInfo.getBrand());
            tv_name.setText(clsInfo.getName());
            tv_sale_price.setText("￥"+clsInfo.getSale_price());
            tv_price.setText("￥"+clsInfo.getPrice());
            tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            img_goods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String code = resultsBean.getClsInfo().getCode();

                    Intent intent = new Intent(mContext,GoodsDetailActivity.class);

                    intent.putExtra("goods_code",code);

                    mContext.startActivity(intent);
                }
            });

        }
    }


}
