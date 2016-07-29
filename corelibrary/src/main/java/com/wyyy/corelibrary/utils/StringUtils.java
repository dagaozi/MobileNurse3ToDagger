package com.wyyy.corelibrary.utils;

import com.wyyy.corelibrary.base.BaseApp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by haohaibin .（dagaozi@163.com）
 * 创建时间：2016/7/26 15:37
 * 类描述：字符串工具
 */
public class StringUtils {
    /**
     *
     *Created by 郝海滨（dagaozi@163.com）
     *创建时间 2016/7/26 15:38
     *描述：判断字符串是否为空
     */
    public static boolean isStrEmpty(String value) {
        if (null == value || "".equals(value.trim())) {
            return true;
        } else {
            // 判断是否全是全角空格
            value = value.replaceAll(" ", "").trim();
            if (null == value || "".equals(value.trim())) {
                return true;
            }
        }
        return false;
    }
    /**
     *
     *Created by 郝海滨（dagaozi@163.com）
     *创建时间 2016/7/26 15:38
     *描述：判断对象是否为空
     */
    public static boolean isNotEmpty(Object object) {
        return null != object;
    }
  /**
   *
   *Created by 郝海滨（dagaozi@163.com）
   *创建时间 2016/7/26 15:38
   *描述：查找字符村是否存在某字符
   */
    public static boolean isStringExist(String value, String subValue) {
        // 空判断
        if(isStrEmpty(value) || isStrEmpty(subValue)){
            return false;
        }
        if (value.indexOf(subValue) > -1) {
            return true;
        }
        return false;
    }

    /**
     * 取中间字符串
     * 原：'《你好》' leftStr：'《 ' rightStr：'》' --> '你好'
     * @param content
     * @param leftStr
     * @param rightStr
     * @return
     */
    public static String getContentStr(String content, String leftStr, String rightStr) {
        String str = "";
        int start = content.indexOf(leftStr);
        if (start > -1) {
            start += leftStr.length();
        } else {
            return "";
        }
        int end = content.indexOf(rightStr);
        if (end >= start) {
            str = content.substring(start, end);
        } else {
            return "";
        }
        return str;
    }

    /**
     * 用MD5加密
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString().toUpperCase();
    }
    /**
     * 获取字符串（数据为空时，取默认值）
     * @param value
     * @param defaultStr
     * @return
     */
    public static String getStr(String value, String defaultStr) {
        if (isStrEmpty(value)) {
            return defaultStr;
        } else {
            return value;
        }
    }

    /**
     * 根据资源ID获得字符串
     * @param resId 资源ID
     * @return
     */
    public static String getResStr(int resId) {
        return BaseApp.getAppContext().getString(resId);
    }

    /**
     * 根据资源ID获得颜色
     * @param resId 资源ID
     * @return
     */
    public static int getResColor(int resId) {
        return BaseApp.getAppContext().getResources().getColor(resId);
    }

    /**
     * 生成UUID
     * @return
     */
    public static String getNewUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid + "";
    }

    /**
     * 从路径中获取文件名
     * @param filePath
     * @return
     */
    public static String getFileNameByFilePath(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }



    /**
     * 字符串不为空
     * @param value
     * @return
     */
    public static String getStrNoNull(String value) {
        return getStr(value, "");
    }

    /**
     * 连接字符串
     *
     * @param values
     * @return
     */
    public static String concat(String... values) {
        String result = "";
        for (String value:values) {
            result = result.concat(getStr(value,""));
        }
        return result;
    }

}
