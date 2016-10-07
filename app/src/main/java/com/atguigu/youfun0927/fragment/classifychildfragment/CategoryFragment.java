package com.atguigu.youfun0927.fragment.classifychildfragment;

import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.bean.CategoryClassify;
import com.atguigu.youfun0927.utils.Constants;
import com.atguigu.youfun0927.utils.LogUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/5.
 */
public class CategoryFragment extends Basefragment {
    @Bind(R.id.expandable_listview)
    ExpandableListView expandableListview;

    @Bind(R.id.refreshlayout)
    MaterialRefreshLayout refreshlayout;
    private List<CategoryClassify.DataBean> data;

    @Override
    public View initView() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.category_fragment, null);
        ButterKnife.bind(this, view);

        initRefresh();

        return view;

    }

    private void initRefresh() {

        refreshlayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                getDataFromNet();
            }
        });

    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "品类数据被初始化了");

        getDataFromNet();

    }

    private void getDataFromNet() {

        OkHttpUtils.get()
                .url(Constants.KIND_PINLEI_MEN)
                .id(100)
                .build()
                .execute(new MyStringCallBack());


    }

    class MyStringCallBack extends StringCallback{


        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("分类链接失败");
        }

        @Override
        public void onResponse(String response, int id) {
            refreshlayout.finishRefresh();
            processData(response);
        }
    }

    private void processData(String response) {
        Gson gson = new Gson();
        CategoryClassify categoryClassify = gson.fromJson(response, CategoryClassify.class);

        data = categoryClassify.getData();

        expandableListview.setAdapter(new MyExpandableListAdapter());


    }


    class MyExpandableListAdapter implements ExpandableListAdapter {


        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getGroupCount() {
            return data.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            List<CategoryClassify.DataBean.SubsBean> subs = data.get(groupPosition).getSubs();
            return subs.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return data.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return data.get(groupPosition).getSubs().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            GrouHolder grouHolder;
            if(convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_category_group, parent, false);
                grouHolder = new GrouHolder(convertView);
                convertView.setTag(grouHolder);
            }else{

                grouHolder = (GrouHolder) convertView.getTag();

            }

            CategoryClassify.DataBean dataBean = data.get(groupPosition);

            grouHolder.setData(dataBean);



            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder childHolder;
            if(convertView == null) {
               convertView =  LayoutInflater.from(mContext).inflate(R.layout.item_search_category_sub,null);
                childHolder = new ChildHolder(convertView);
                convertView.setTag(childHolder);

            }else{

                childHolder = (ChildHolder) convertView.getTag();
            }
            CategoryClassify.DataBean.SubsBean subsBean = data.get(groupPosition).getSubs().get(childPosition);

            childHolder.setData(subsBean);





            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void onGroupExpanded(int groupPosition) {

        }

        @Override
        public void onGroupCollapsed(int groupPosition) {

        }

        @Override
        public long getCombinedChildId(long groupId, long childId) {
            return 0;
        }

        @Override
        public long getCombinedGroupId(long groupId) {
            return 0;
        }
    }

    class GrouHolder{


        private final ImageView img_group;

        public GrouHolder(View convertView) {

            img_group = (ImageView) convertView.findViewById(R.id.img_group);

        }


        public void setData(CategoryClassify.DataBean dataBean) {

            Glide.with(mContext)
                    .load(dataBean.getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_group);

        }
    }

    class ChildHolder{


        private final TextView tv_sub;

        public ChildHolder(View convertView) {

            tv_sub = (TextView) convertView.findViewById(R.id.tv_sub);

        }

        public void setData(CategoryClassify.DataBean.SubsBean subsBean) {

            tv_sub.setText(subsBean.getCate_name());
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
