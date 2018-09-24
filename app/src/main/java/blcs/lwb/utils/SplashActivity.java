package blcs.lwb.utils;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import blcs.lwb.lwbtool.LogUtils;


public class SplashActivity extends Activity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //封装前
        Log.e(TAG, "onCreate: 封装前" );
        //封装后
        LogUtils.e("封装后");
    }
}
