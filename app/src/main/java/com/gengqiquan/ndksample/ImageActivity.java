package com.gengqiquan.ndksample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gengqiquan.qqresult.Func1;
import com.gengqiquan.qqresult.QQResult;
import com.xhe.photoalbum.PhotoAlbum;

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
                        .getAlbumIntent()).result(new Func1() {
                    @Override
                    public void result(Intent intent) {
                        String path = PhotoAlbum.parseResult(intent).get(0);
                        Glide.with(mContext).load(path).asBitmap().into(iv_img);
                        path = Compress.compress(mContext, path);
                        tv.setText(path);
                    }
                });
            }
        });
    }

}
