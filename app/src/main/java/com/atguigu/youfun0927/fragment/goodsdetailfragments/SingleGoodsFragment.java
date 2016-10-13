package com.atguigu.youfun0927.fragment.goodsdetailfragments;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.activity.PhotoViewShow;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.GoodDetailBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public class SingleGoodsFragment extends Basefragment {

    GoodDetailBean goodDetailBean;
    private LinearLayout lin_image;


    public SingleGoodsFragment(GoodDetailBean goodDetailBean) {

        this.goodDetailBean = goodDetailBean;

    }

    @Override
    public View initView() {
        Log.e("TAG", "单品详情初始化了");

        View view = LayoutInflater.from(mContext).inflate(R.layout.single_goods_fragment, null);
        lin_image = (LinearLayout) view.findViewById(R.id.lin_image);

        return view;

    }

    @Override
    public void initData() {
        super.initData();

        Log.e("TAG", "单品详情被初始化了");

        getDataFromNet();

    }

    private void getDataFromNet() {

        List<GoodDetailBean.DataBean.ClsPicUrlBean> clsPicUrl = goodDetailBean.getData().getClsPicUrl();

        for(int i = 0; i < clsPicUrl.size(); i++) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.item_single_goods, null);

            ImageView img_goods = (ImageView) view.findViewById(R.id.img_goods);

            final String filE_path = clsPicUrl.get(i).getFilE_PATH();

            Glide.with(mContext)
                    .load(filE_path)
                    .placeholder(R.drawable.default100)
                    .into(img_goods);
            img_goods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PhotoViewShow.class);
                    intent.putExtra("url", filE_path);
                    mContext.startActivity(intent);
                }
            });
            lin_image.addView(view);

        }




    }


}
