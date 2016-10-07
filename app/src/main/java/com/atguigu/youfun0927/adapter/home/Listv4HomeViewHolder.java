package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.HomeMen;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class Listv4HomeViewHolder extends BaseViewHolder {


    private final ImageView img_big;
    private final GridView gridview;
    private List<HomeMen.DataBean.ModuleBean.DataBean2> datalist;

    public Listv4HomeViewHolder(Context context, View itemView) {
        super(context, itemView);

        img_big = (ImageView) itemView.findViewById(R.id.img_big);
        gridview = (GridView) itemView.findViewById(R.id.gridview);

    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        datalist = modulelist.getData();

        img_big.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context)
                .load(datalist.get(0).getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_big);
        gridview.setAdapter(new MyBaseAdapter());


    }

    private class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return (datalist.size() <= 1) ? 0 : datalist.size() - 1;
        }

        @Override
        public Object getItem(int position) {
            return datalist.get(position + 1);
        }

        @Override
        public long getItemId(int position) {
            return position + 1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(context).inflate(R.layout.item_home_img_list_v4_module, parent, false);
            ImageView img_listv4 = (ImageView) view.findViewById(R.id.img_listv4);
            Glide.with(context)
                    .load(datalist.get(position + 1).getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_listv4);
            return view;
        }


    }
}
