package com.atguigu.youfun0927.utils;

import android.content.Context;

/**
 * 作者：尚硅谷-杨光福 on 2016/9/12 15:29
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：dp和px相互转换工具
 */
public class DensityUtil {
    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
