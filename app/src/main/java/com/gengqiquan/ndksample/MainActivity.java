package com.gengqiquan.ndksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.common.crypt.Crypt;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        String m1 = MD5.md5("你好" + "DFABE927309A5035").substring(8, 24).toUpperCase();
        String m2 = MD5.md5(m1 + "DFABE927309A5035" + "ESCFSOUNAL").substring(8, 24).toUpperCase();
        tv.setText(Crypt.getEncryptText(this,"你好")+"\\n"+m2);

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
