package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.HomeMen;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Administrator on 2016/10/3.
 */
public class HotcateHomeViewHolder extends BaseViewHolder {


    private final TextView c_title;
    private final TextView e_title;
    private final GridView gridview;
    private List<HomeMen.DataBean.ModuleBean.DataBean2> datalist;

    public HotcateHomeViewHolder(Context context, View itemView) {
        super(context, itemView);
        c_title = (TextView) itemView.findViewById(R.id.c_title);
        e_title = (TextView) itemView.findViewById(R.id.e_title);
        gridview = (GridView) itemView.findViewById(R.id.gridview);

    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        datalist = modulelist.getData();

        c_title.setText(modulelist.getC_title());
        e_title.setText(modulelist.getE_title());

        gridview.setAdapter(new MyBaseAdapter());

    }

    class MyBaseAdapter extends BaseAdapter{




        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public Object getItem(int position) {
            return datalist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View view =  View.inflate(context, R.layout.item_home_hotcate_module, null);

            ImageView img_hotcate = (ImageView) view.findViewById(R.id.img_hotcate);
               // TextView tv_hotcatetitle = (TextView) convertView.findViewById(R.id.tv_hotcatetitle);

            Glide.with(context)
                    .load(datalist.get(position).getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_hotcate);

            return view;

        }
    }

}
