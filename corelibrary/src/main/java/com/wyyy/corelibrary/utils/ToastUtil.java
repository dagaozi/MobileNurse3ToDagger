package com.wyyy.corelibrary.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wyyy.corelibrary.R;
import com.wyyy.corelibrary.base.BaseActivity;
import com.wyyy.corelibrary.base.BaseApp;

/**
 * Created by 海滨 .（dagaozi@163.com）
 * 创建时间：2016/4/27 8:57
 * 类描述：Toast统一管理类
 */
public class ToastUtil {
    private static Toast toast = null;
    private static int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static int LENGTH_LONG = Toast.LENGTH_LONG;

    public static void showToast(Context context,String text)
    {
        Toast.makeText(context,text,LENGTH_SHORT).show();
    }


    /**
     * 带图片的提醒
     * @param msg
     * @param isSuccess
     */
    public static void showImageToast(String msg, boolean isSuccess) {
        if (BaseActivity.currentActivity == null) {
            return;
        }
        Toast mToast = Toast.makeText(BaseActivity.currentActivity, "\r\r"
                + msg, Toast.LENGTH_SHORT);
        // 创建LinearLayout布局
        LinearLayout toastView = (LinearLayout) mToast.getView();
        toastView.setGravity(Gravity.CENTER);
        // 设置LinearLayout的布局取向
        toastView.setOrientation(LinearLayout.HORIZONTAL);
        // 创建ImageView
        ImageView toastIcon = new ImageView(BaseApp.getAppContext());
        if (isSuccess) {
            toastIcon.setImageResource(R.drawable.ico_success);
        } else {
            toastIcon.setImageResource(R.drawable.ico_fail);
        }
        // 给toastView添加View布局
        toastView.addView(toastIcon, 0);
        // 显示Toast
        mToast.show();
    }
}
