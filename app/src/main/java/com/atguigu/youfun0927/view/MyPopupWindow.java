package com.atguigu.youfun0927.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.youfun0927.R;

/**
 * Created by Administrator on 2016/10/10.
 */
public class MyPopupWindow extends PopupWindow{

    private Context mContext;

    private View view;

    private Button btn_cancel,btn_sure;


    public MyPopupWindow(Context mContext) {

        this.view = LayoutInflater.from(mContext).inflate(R.layout.popuplayout, null);

//        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
//        btn_sure= (Button) view.findViewById(R.id.btn_sure);
        // 取消按钮
//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                // 销毁弹出框
//                dismiss();
//            }
//        });
//        btn_sure.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                // 销毁弹出框
//                dismiss();
//            }
//        });
//        // 设置按钮监听

        TextView tv_man = (TextView) view.findViewById(R.id.tv_man);
        TextView tv_women = (TextView) view.findViewById(R.id.tv_women);
        TextView tv_life = (TextView) view.findViewById(R.id.tv_life);

        tv_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onPopupClickListener.onClick(v,0);
            }
        });

        tv_women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onPopupClickListener.onClick(v,1);

            }
        });

        tv_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPopupClickListener.onClick(v,2);
            }
        });



        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.lin_pop).getBottom();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });


    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
//        this.setAnimationStyle(R.style.take_photo_anim);

    }
    public interface OnPopupClickListener {
        void onClick(View view,int index);
    }

  private OnPopupClickListener onPopupClickListener;

    public void setOnPopupClickListener(OnPopupClickListener onPopupClickListener) {
        this.onPopupClickListener = onPopupClickListener;
    }
}
