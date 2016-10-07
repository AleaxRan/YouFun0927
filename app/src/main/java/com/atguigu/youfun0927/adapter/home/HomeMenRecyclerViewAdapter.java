package com.atguigu.youfun0927.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.BaseViewHolder;
import com.atguigu.youfun0927.bean.HomeMen;
import com.atguigu.youfun0927.utils.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public class HomeMenRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

   List<HomeMen.DataBean.ModuleBean> modulelist ;

    Context context ;
    private View view;
    private BaseViewHolder baseViewHolder;


    public HomeMenRecyclerViewAdapter(List<HomeMen.DataBean.ModuleBean> module, Context mContext) {

        this.modulelist = module;

        this.context = mContext;
    }

    //Module里的的一个 按照module_key的内容来
    private static final int TOPIMAGE = 0 ;

    private static final int ICON = 1;

    private static final int BANNER = 2;

    private static final int NEW = 3;

    private static final int HOTCATE = 4;

    private static final int HOTBRAND = 5;

    private static final int COLLOSPECIAL = 6;

    private static final int IMG = 7 ;

    private static final int LISTV1 = 8;

    private static final int LISTV3 = 9;

    private static final int LISTV4 = 10;

    private static final int LIKE = 11;

    private static final int NOTICE = 12;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.deafult_home_module,parent,false);

        baseViewHolder = new DeafultHomeViewHolder(context, view);

        switch (viewType){
            
            case TOPIMAGE:
                view = LayoutInflater.from(context).inflate(R.layout.topimg_home,parent,false);

                baseViewHolder =  new TopImgHomeViewHolder(context, view);

                break;

            case ICON:
                view = LayoutInflater.from(context).inflate(R.layout.icon_home,parent,false);

                baseViewHolder = new IconHomeViewHolder(context, view);
                break;

            case BANNER:
                view = LayoutInflater.from(context).inflate(R.layout.banner_home,parent,false);

                baseViewHolder = new BannerHomeViewHolder(context, view);
                break;
            case NEW:
                view = LayoutInflater.from(context).inflate(R.layout.new_home_module,parent,false);

                baseViewHolder = new  NewModuleViewHoleder(context,view);
                break;

            case HOTCATE:
                view = LayoutInflater.from(context).inflate(R.layout.hotcate_home_module,parent,false);

                baseViewHolder = new HotcateHomeViewHolder(context, view);
                break;

            case HOTBRAND:
                view = LayoutInflater.from(context).inflate(R.layout.hotbrand_home_module,parent,false);

                baseViewHolder = new HotbrandHomeViewHolder(context,view);
                break;

            case COLLOSPECIAL:
                view = LayoutInflater.from(context).inflate(R.layout.collospecial_home_module,parent,false);

                baseViewHolder = new CollospecialHomeViewHolder(context, view);
                break;
            case IMG:
                view = LayoutInflater.from(context).inflate(R.layout.img_home_module,parent,false);

                baseViewHolder = new ImgHomeViewHolder(context,view);
                break;

            case LISTV1:

                view = LayoutInflater.from(context).inflate(R.layout.listv1_home_module,parent,false);

                baseViewHolder = new Listv1HomeViewHolder(context,view);
                break;

            case LISTV3:

                view = LayoutInflater.from(context).inflate(R.layout.listv3_home_module,parent,false);

                baseViewHolder = new Listv3HomeViewHolder(context,view);
                break;
            case LISTV4:
                view = LayoutInflater.from(context).inflate(R.layout.listv4_home_module,parent,false);

                baseViewHolder = new Listv4HomeViewHolder(context,view);
                break;

            case LIKE:
                view = LayoutInflater.from(context).inflate(R.layout.like_home_module,parent,false);

               baseViewHolder =  new LikeHomeViewHolder(context,view);
                break;

            case NOTICE:

                view = LayoutInflater.from(context).inflate(R.layout.notic_home_module,parent,false);

                baseViewHolder = new NoticHomeViewHolder(context, view);
                break;

        }
        

        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder , int position) {

        if(modulelist==null) {
            LogUtil.e("moulelist是空");
            return;
        }

        baseViewHolder.setData(modulelist.get(position));

    }

    @Override
    public int getItemCount() {
        return modulelist == null ? 0 : modulelist.size() ;
    }


    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;

        HomeMen.DataBean.ModuleBean moduleBean = modulelist.get(position);

        String type = moduleBean.getModule_key();//得到类型

        if("topImgModule".equals(type)) {
            itemViewType = TOPIMAGE;
        }else if("iconModule".equals(type)) {
            itemViewType = ICON;
        }else if("bannerModule".equals(type)) {
            itemViewType = BANNER;
        }else if("newModule".equals(type)) {
            itemViewType = NEW;
        }else if("hotCateModule".equals(type)) {
            itemViewType = HOTCATE;
        }else if("hotBrandModule".equals(type)) {
            itemViewType = HOTBRAND;
        }else if("colloSpecialModule".equals(type)) {
            itemViewType = COLLOSPECIAL;
        }else if("imgModule".equals(type)) {
            itemViewType = IMG;
        }else if("imgListV1Module".equals(type)) {
            itemViewType = LISTV1;
        }else if("imgListV3Module".equals(type)) {
            itemViewType = LISTV3;
        }else if("imgListV4Module".equals(type)) {
            itemViewType = LISTV4;
        }else if("likeModule".equals(type)){
            itemViewType = LIKE;
        }else{
            itemViewType = NOTICE;
        }




        return itemViewType;
    }


}
