package com.gengqiquan.ndksample;

import android.content.Context;

public class Compress {
    static {
        System.loadLibrary("compress-lib");
    }

    public static  native String compress(Context context, String path);

}
