package blcs.lwb.lwbtool.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.crash.CaocConfig;
import blcs.lwb.lwbtool.utils.crash.CustomActivityOnCrash;

public abstract class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initCrash();
//        initLeakCanary();
    }

    /**
     * 内存泄漏
     */
    private void initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
    /**
     * 全局异常捕获
     */
    private void initCrash() {
        CaocConfig.Builder.create()
//        当应用程序处于后台时崩溃，默默地关闭程序！//default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
//        .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT)
//        这阻止了对崩溃的拦截,false表示阻止。用它来禁用customactivityoncrash框架
//        .enabled(false)//default: true
//        设置是否显示“错误详细信息”按钮
//        .showErrorDetails(false)//default: true
//        设置是否显示重启按钮
//        .showRestartButton(false)//default: true
//        设置是否显示错误日志
//        .logErrorOnRestart(false)//default: true
//        错误页面中显示错误详细信息
//        .trackActivities(true)//default: false
//        定义应用程序崩溃之间的最短时间，以确定我们不在崩溃循环中。比如：在规定的时间内再次崩溃，框架将不处理，让系统处理！
//        .minTimeBetweenCrashesMs(2000)//default: 3000
//        崩溃页面显示的图标
//        .errorDrawable(R.mipmap.ic_error)//default: bug image
//        重新启动后的页面
//        .restartActivity(MainActivity.class)//default: null
//        程序崩溃后显示的页面
//        .errorActivity(CustomErrorActivity.class)//default: null
//        设置监听
//        .eventListener(new CustomEventListener())//default: null
          .apply();
    }


    private static class CustomEventListener implements CustomActivityOnCrash.EventListener {
        @Override
        public void onLaunchErrorActivity() {
            LogUtils.e("onLaunchErrorActivity");
        }

        @Override
        public void onRestartAppFromErrorActivity() {
            LogUtils.e("onRestartAppFromErrorActivity");
        }

        @Override
        public void onCloseAppFromErrorActivity() {
            LogUtils.e("onCloseAppFromErrorActivity");
        }
    }
}
