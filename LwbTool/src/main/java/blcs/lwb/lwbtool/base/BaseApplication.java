package blcs.lwb.lwbtool.base;

import android.app.Application;

import blcs.lwb.lwbtool.BuildConfig;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.crash.CrashHandler;

public abstract class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //全局异常处理
        CrashHandler.getInstance(getApplicationContext());
    }
}
