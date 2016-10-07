package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.HomeMen;
import com.atguigu.youfun0927.bean.HomeMenMore;
import com.atguigu.youfun0927.utils.Constants;
import com.atguigu.youfun0927.utils.LogUtil;
import com.atguigu.youfun0927.view.MyRecycleView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/5.
 */
public class LikeHomeViewHolder extends BaseViewHolder {


    private final TextView c_title;
    private final TextView e_title;
    private final MyRecycleView recyclerview_like_module;
    private List<HomeMenMore.DataBean.ListBean> listBeans;

    public LikeHomeViewHolder(Context context, View itemView) {
        super(context, itemView);
        c_title = (TextView) itemView.findViewById(R.id.c_title);
        e_title = (TextView) itemView.findViewById(R.id.e_title);

        recyclerview_like_module = (MyRecycleView) itemView.findViewById(R.id.recyclerview_like_module);

    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        c_title.setText(modulelist.getC_title());
        e_title.setText(modulelist.getE_title());
        getDataFromNet();


    }

    private void getDataFromNet() {

        OkHttpUtils.get()
                .url(Constants.HOMECASH_MAN_MORE)
                .id(100)
                .build()
                .execute(new MyStringCallBack());


    }

    private class MyStringCallBack extends StringCallback {


        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            processData(response);
        }
    }

    private void processData(String json) {

        Gson gson = new Gson();
        HomeMenMore homeMenMore = gson.fromJson(json, HomeMenMore.class);

        listBeans = homeMenMore.getData().getList();

        LikeModuleAdapter likeModuleAdapter = new LikeModuleAdapter();

        recyclerview_like_module.setAdapter(likeModuleAdapter);

        recyclerview_like_module.setLayoutManager(new GridLayoutManager(context,2));
    }

    class LikeModuleAdapter extends RecyclerView.Adapter<MyHolder> {


        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_like_module,parent,false);

            MyHolder myHolder = new MyHolder(view);

            return myHolder;
        }

        @Override
        public int getItemCount() {
            return listBeans.size();
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {

            holder.setData(listBeans.get(position));

        }


    }
    class MyHolder extends  RecyclerView.ViewHolder{


        TextView tv_brand_name;
        TextView product_name;
        TextView tv_price;
        ImageView img_like;
        ImageView img_tag;

        public MyHolder(View itemView) {
            super(itemView);

            img_like = (ImageView) itemView.findViewById(R.id.img_like);
            tv_brand_name = (TextView) itemView.findViewById(R.id.tv_brand_name);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            img_tag = (ImageView) itemView.findViewById(R.id.img_tag);
        }

        public void setData(HomeMenMore.DataBean.ListBean listBean) {

            if(listBean ==null) {
                LogUtil.e("listbean is null !!");
                return;
            }
            tv_brand_name.setText(listBean.getBrandName());
            product_name.setText(listBean.getProduct_name());

            String price = listBean.getPrice();
            tv_price.setText("￥"+ price.substring(0,price.lastIndexOf(".")));
            Glide.with(context)
                    .load(listBean.getProduct_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_like);
            if(listBean.getProdClsTag() == null||listBean.getProdClsTag().size()==0) {
                return;
            }
            Glide.with(context)
                    .load(listBean.getProdClsTag().get(0).getTagUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_tag);

        }

    }


}
