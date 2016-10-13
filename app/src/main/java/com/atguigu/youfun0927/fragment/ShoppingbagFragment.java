package com.atguigu.youfun0927.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.Goods;
import com.atguigu.youfun0927.bean.GoodsSelected;
import com.atguigu.youfun0927.dao.GoodsDao;
import com.atguigu.youfun0927.db.Model;
import com.atguigu.youfun0927.utils.Constants;
import com.atguigu.youfun0927.utils.LogUtil;
import com.atguigu.youfun0927.view.NumberAddSubView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/27.
 */
public class ShoppingbagFragment extends Basefragment {

    //编辑
    private static final int EDIT = 0;
    //显示
    private static final int SHOW = 1;

    int state = SHOW;

    @Bind(R.id.tv_men_women)
    TextView tvMenWomen;
    @Bind(R.id.top_search_main)
    TextView topSearchMain;
    @Bind(R.id.rla_title)
    RelativeLayout rlaTitle;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.checkbox_all)
    CheckBox checkboxAll;
    @Bind(R.id.btn_order)
    Button btnOrder;
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.lin_totalprice)
    LinearLayout linTotalprice;
    @Bind(R.id.btn_delete)
    Button btnDelete;
    @Bind(R.id.btn_collect)
    Button btnCollect;

    List<Goods> goodsListSelect = new ArrayList<>();
    private GoodsDao goodsDao;
    private List<Goods> goodsList;

    HashMap<String, GoodsSelected.ResultsBean> goodsDetailSelectMap = new HashMap<>();
    private MyRecycleViewAdapter myRecycleViewAdapter;

    @Override
    public View initView() {
        Log.e("TAG", "购物袋被初始化了");
        View view = LayoutInflater.from(mContext).inflate(R.layout.shoppingbag_fragment, null);
        ButterKnife.bind(this, view);

        initlistener();
        return view;

    }

    private void initlistener() {

        //编辑按钮
        topSearchMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == SHOW) {
                    topSearchMain.setText("完成");
                    state = EDIT;
                } else {
                    topSearchMain.setText("编辑");
                    state = SHOW;
                }

                changeView();
            }
        });

        //全选按钮
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checked = checkboxAll.isChecked();

                if (checked) {

                    goodsListSelect.clear();
                    goodsListSelect.addAll(goodsList);
                    //更新适配器
                    myRecycleViewAdapter.notifyDataSetChanged();
                    //更新价格
                    updateMony();

                }else{

                    goodsListSelect.clear();
                    myRecycleViewAdapter.notifyDataSetChanged();
                    updateMony();
                }


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsListSelect == null || goodsListSelect.size() <= 0) {
                    Toast.makeText(mContext, "还没有选中商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < goodsListSelect.size(); i++) {
                    Goods goods = goodsListSelect.get(i);
                    goodsDao.deleteById(goods.getId());
                    goodsList.remove(goods);
                    goodsDetailSelectMap.remove(goods.getId());
                }
                goodsListSelect.clear();
                myRecycleViewAdapter.notifyDataSetChanged();
                Toast.makeText(mContext, "已经删除选中商品", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void changeView() {

        if(state == EDIT ) {
            //去结算隐藏，合算隐藏
            btnOrder.setVisibility(View.INVISIBLE);
            linTotalprice.setVisibility(View.INVISIBLE);
            //删除显示，收藏显示
            btnDelete.setVisibility(View.VISIBLE);
            btnCollect.setVisibility(View.VISIBLE);
        }else{//反之

            btnOrder.setVisibility(View.VISIBLE);
            linTotalprice.setVisibility(View.VISIBLE);

            btnDelete.setVisibility(View.INVISIBLE);
            btnCollect.setVisibility(View.INVISIBLE);

        }


    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "购物袋被初始化了");

        goodsDao = Model.getInstance().getDbManager().getGoodsDao();

        goodsList = goodsDao.getGoodsList();

        if (goodsList == null || goodsList.size() <= 0) {
            return;
        }

        getAllGoodsSelectedDataFromNet();


    }

    public void getAllGoodsSelectedDataFromNet() {

        for (int i = 0; i < goodsList.size(); i++) {
            Goods goods = goodsList.get(i);
            String url = Constants.GOODS_DETAIL_SELECT_PRE + goods.getProdClsNum() + Constants.GOODS_DETAIL_TAIL;
            getDataFromNet(url, goods);
        }


    }

    public void getDataFromNet(String url, Goods goods) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new MyString(goods));


    }


    class MyString extends StringCallback{

        Goods goods;
        public MyString(Goods goods) {

            this.goods = goods;
        }

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            processData(response, goods);

        }
    }

    private void processData(String response, Goods goods) {

        Gson gson = new Gson();
        GoodsSelected goodsSelected = gson.fromJson(response, GoodsSelected.class);
        List<GoodsSelected.ResultsBean> results = goodsSelected.getResults();
        if (results == null || results.size() <= 0) {
            return;
        }
        GoodsSelected.ResultsBean resultsBean = null;
        for (int i = 0; i < results.size(); i++) {

            if (results.get(i).getLM_PROD_CLS_ID().equals(goods.getLmProdClsId())) {

                 resultsBean = results.get(i);
            }

        }

        if (results != null) {
            //缓存数据
            goodsDetailSelectMap.put(goods.getId(), resultsBean);
            //设置数据
            if (goodsDetailSelectMap.size() >= goodsList.size()) {

                initrecyclerView();
            }
        }
    }

    private void initrecyclerView() {

        myRecycleViewAdapter = new MyRecycleViewAdapter();

        recyclerview.setAdapter(myRecycleViewAdapter);

        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));


    }


    private class MyRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.item_shoppingbag, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            ((MyViewHolder) holder).setData(goodsList.get(position));

        }

        @Override
        public int getItemCount() {
            return goodsList.size();
        }


        class MyViewHolder extends  RecyclerView.ViewHolder{
            CheckBox checkbox;
            ImageView img_purchase;
            TextView tv_brand;
            TextView tv_name;
            TextView tv_color;
            TextView tv_size;
            TextView tv_price;
            TextView tv_count;
            Goods goods;
            LinearLayout lin_item;
            RelativeLayout rel_price;

            NumberAddSubView auto_sub_add;

            public MyViewHolder(View itemView) {
                super(itemView);

                checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
                auto_sub_add = (NumberAddSubView) itemView.findViewById(R.id.auto_sub_add);
                img_purchase = (ImageView) itemView.findViewById(R.id.img_purchase);
                tv_brand = (TextView) itemView.findViewById(R.id.tv_brand);
                rel_price = (RelativeLayout) itemView.findViewById(R.id.rel_price);
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
                tv_color = (TextView) itemView.findViewById(R.id.tv_color);
                tv_size = (TextView) itemView.findViewById(R.id.tv_size);
                tv_price = (TextView) itemView.findViewById(R.id.tv_price);
                tv_count = (TextView) itemView.findViewById(R.id.tv_count);
                lin_item = (LinearLayout) itemView.findViewById(R.id.lin_item);

            }


            public void setData(Goods goods) {
                //根据状态设置要显示的view
                if (state == EDIT) {
                    tv_brand.setVisibility(View.INVISIBLE);
                    tv_name.setVisibility(View.INVISIBLE);
                    rel_price.setVisibility(View.INVISIBLE);
                    auto_sub_add.setVisibility(View.VISIBLE);
                } else {
                    tv_brand.setVisibility(View.VISIBLE);
                    tv_name.setVisibility(View.VISIBLE);
                    rel_price.setVisibility(View.VISIBLE);
                    auto_sub_add.setVisibility(View.INVISIBLE);
                }

                this.goods = goods;

                if (goodsDetailSelectMap.get(goods.getId()) == null) {
                    //没有缓存
                    LogUtil.e("goods select have no data in loacal !in purchaseFragment ");
                } else {
                    setGoodsData(goodsDetailSelectMap.get(goods.getId()));
                }

            }

            private void setGoodsData(GoodsSelected.ResultsBean resultsBean) {
                Glide.with(mContext)
                        .load(resultsBean.getColoR_FILE_PATH())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.default100)
                        .into(img_purchase);
                tv_brand.setText(resultsBean.getBrand_name());
                tv_name.setText(resultsBean.getProD_NAME());
                tv_color.setText("颜色:" + resultsBean.getColoR_NAME());
                tv_size.setText("尺寸:" + resultsBean.getSpeC_NAME());
                tv_price.setText("￥" + resultsBean.getSalE_PRICE());
                tv_count.setText("X" + goods.getCount());
                auto_sub_add.setValue(goods.getCount());
                auto_sub_add.setMaxValue(resultsBean.getLisT_QTY());

                auto_sub_add.setOnNumberClickListener(new NumberAddSubView.OnNumberClickListener() {
                    @Override
                    public void onButtonSub(View view, int value) {
                        //更新数目
                        tv_count.setText("X" + value);
                        goods.setCount(value);
                        //更新goodsList
                        goodsList.get(goodsList.indexOf(goods)).setCount(value);
                        //更新goodsListSelect
                        if (goodsListSelect.contains(goods)) {
                            goodsListSelect.get(goodsListSelect.indexOf(goods)).setCount(value);
                            updateMony();
                        }
                        //更新数据库
                        goodsDao.updateGoods(goods);

                    }

                    @Override
                    public void onButtonAdd(View view, int value) {
                        if (value >= auto_sub_add.getMaxValue()) {
                            Toast.makeText(mContext, "被你购完了", Toast.LENGTH_SHORT).show();
                        }
                        //更新数目
                        tv_count.setText("X" + value);
                        goods.setCount(value);
                        //更新goodsList
                        goodsList.get(goodsList.indexOf(goods)).setCount(value);
                        //更新goodsListSelect
                        if (goodsListSelect.contains(goods)) {
                            goodsListSelect.get(goodsListSelect.indexOf(goods)).setCount(value);
                            updateMony();
                        }
                        //更新数据库
                        goodsDao.updateGoods(goods);


                    }
                });
                checkbox.setChecked(goodsListSelect.contains(goods));
                checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            if (!goodsListSelect.contains(goods)) {
                                goodsListSelect.add(goods);
                            }
                        } else {
                            goodsListSelect.remove(goods);
                        }
                        updateCheckBoxViewState();
                        updateMony();
                    }
                });


            }

        }




    }

    private void updateCheckBoxViewState() {
        if (goodsListSelect.size() >= goodsList.size()) {
            checkboxAll.setChecked(true);
        } else {
            checkboxAll.setChecked(false);
        }


    }

    private void updateMony() {
        int count = 0;
        for (int i = 0; i < goodsListSelect.size(); i++) {
            Goods goods = goodsListSelect.get(i);

            int tempMony = goods.getCount() * goodsDetailSelectMap.get(goods.getId()).getSalE_PRICE();
            count += tempMony;
        }
        tvTotalPrice.setText("合计￥" + count);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
