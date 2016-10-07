package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.view.View;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.HomeMen;
import com.atguigu.youfun0927.utils.LogUtil;
import com.atguigu.youfun0927.view.TextSwitcherView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class NoticHomeViewHolder extends BaseViewHolder {


    private final TextSwitcherView text_switcher;

    public NoticHomeViewHolder(Context context, View itemView) {
        super(context, itemView);

        text_switcher = (TextSwitcherView) itemView.findViewById(R.id.text_switcher);

    }

    @Override
    public void setData(HomeMen.DataBean.ModuleBean modulelist) {

        if(modulelist == null) {
            LogUtil.e("modulelist is null");
            return;
        }

        List<HomeMen.DataBean.ModuleBean.DataBean2> listdata = modulelist.getData();

        ArrayList<String> arrayList = new ArrayList<>();

        for(int i = 0; i < listdata.size(); i++) {

            String title = listdata.get(i).getTitle();

            arrayList.add(title);

        }

        text_switcher.setArrayList(arrayList);
    }
}
