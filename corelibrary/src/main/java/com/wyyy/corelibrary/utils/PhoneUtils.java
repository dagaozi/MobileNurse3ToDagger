package com.wyyy.corelibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.wyyy.corelibrary.base.BaseActivity;

/**
 * Created by haohaibin .（dagaozi@163.com）
 * 创建时间：2016/7/26 15:40
 * 类描述：手机相关方法
 */
public class PhoneUtils {
    /**
     * 获取设备号
     *
     * @author LinHao 439224@qq.com
     * @version 创建时间： 2013-8-6 上午11:46:10
     */
    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return androidId;
    }

    /**
     * 获取SIM IMSI码
     *
     * @author LinHao 439224@qq.com
     * @version 创建时间： 2013-8-6 上午11:46:28
     */
    public static String getSIMDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        return deviceId;
    }
  /**
   *
   *Created by 郝海滨（dagaozi@163.com）
   *创建时间 2016/7/26 15:44
   *描述：拨打电话，isCall = true时直接拨打
   */
    public static void callPhone(String number, boolean isCall) {
        Intent intent;
        Uri uri = Uri.parse("tel:" + number);
        if (isCall) {
            intent = new Intent(Intent.ACTION_CALL, uri);
        } else {
            intent = new Intent(Intent.ACTION_DIAL, uri);
        }
        BaseActivity.currentActivity.startActivity(intent);
    }

    /**
     * 跳转到短信界面
     *
     * @author LinHao 439224@qq.com
     * @version 创建时间： 2013-8-23 下午4:26:25
     */
    public static void callNote(String number) {
        Intent intent;
        Uri uri = Uri.parse("smsto:" + number);
        intent = new Intent(Intent.ACTION_SENDTO, uri);
        BaseActivity.currentActivity.startActivity(intent);

    }
}
