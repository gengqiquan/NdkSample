package com.common.crypt;
import android.content.Context;


/**
 * .so文件加密类。
 *
 * @author gengqiquan
 * @date 2017/6/22 上午9:33
 */
public class Crypt {
    static {
        System.loadLibrary("crypt");
    }

    public static  native String getEncryptText(Context context, String text);

    public static native String getSigtureText(Context context);



}