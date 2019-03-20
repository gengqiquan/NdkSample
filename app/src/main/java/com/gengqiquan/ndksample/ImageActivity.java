package com.gengqiquan.ndksample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gengqiquan.qqresult.Func1;
import com.gengqiquan.qqresult.QQResult;
import com.xhe.photoalbum.PhotoAlbum;

import java.io.IOException;
import java.util.List;

import top.zibin.luban.Luban;

public class ImageActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mContext = this;
        // Example of a call to a native method
        final TextView tv = (TextView) findViewById(R.id.sample_text);
        final ImageView iv_img = (ImageView) findViewById(R.id.iv_img);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QQResult.startActivityWith(ImageActivity.this, new PhotoAlbum(ImageActivity.this)
                        .setLimitCount(9)
                        .getAlbumIntent()).result(new Func1() {
                    @Override
                    public void result(Intent intent) {
                        List<String> list = PhotoAlbum.parseResult(intent);
                        long start = System.currentTimeMillis();
                        for (String path : list) {
//                            try {
//                                Compress.compress(mContext, path);
                            Glide.with(mContext).load(  Compress.compress(mContext, path)).asBitmap().into(iv_img);
//                                Luban.with(mContext).get(path).getPath();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
                        }
                        long end = System.currentTimeMillis();

//                        path = Compress.compress(mContext, path);
                        Log.d("ImageActivity", "start: " + start);
                        Log.d("ImageActivity", "end: " + end);
                        Log.d("ImageActivity", "result: " + (end - start));
                    }
                });
            }
        });
    }

}
