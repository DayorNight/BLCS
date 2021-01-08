package com.blcs.comlibs.crash;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

import me.weishu.reflection.Reflection;

/**
 * 应用闪退crash 处理
 */
public final class Cockroach {
    private static IActivityKiller sActivityKiller;
    private static final String TAG = "Cockroach";
    public static final int EXECUTE_TRANSACTION = 159;
    public static final int LAUNCH_ACTIVITY = 100;
    public static final int PAUSE_ACTIVITY = 101;
    public static final int PAUSE_ACTIVITY_FINISHING = 102;
    public static final int STOP_ACTIVITY_HIDE = 104;
    public static final int RESUME_ACTIVITY = 107;
    public static final int DESTROY_ACTIVITY = 109;
    public static final int NEW_INTENT = 112;
    public static final int RELAUNCH_ACTIVITY = 126;
    private static Cockroach cockroach;

    public static void init(Context ctx, ICrashCallBack crashHandler) {
        if (cockroach == null) {
            cockroach = new Cockroach(ctx, crashHandler);
        }
    }

    private Cockroach(Context ctx, ICrashCallBack callBack) {
        ICrashCallBack iCrashCallBack = callBack == null ? new DefaultCrashCallBack() : callBack;
        /*解除 android P 反射限制*/
        Reflection.unseal(ctx);
        initActivityKiller();
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            iCrashCallBack.uncaughtException(ctx, thread, throwable);
            //主线程闪退抛出
            if (thread == Looper.getMainLooper().getThread()) {
                while (true) {
                    try {
                        //异常处理
                        Looper.loop();
                    } catch (Exception exception) {
                        iCrashCallBack.uncaughtException(ctx, thread,exception);
                    }
                }
            }

        });
    }

    /**
     * 替换ActivityThread.mH.mCallback，实现拦截Activity生命周期，直接忽略生命周期的异常的话会导致黑屏，目前
     * 会调用ActivityManager的finishActivity结束掉生命周期抛出异常的Activity
     */
    private void initActivityKiller() {
        //各版本android的ActivityManager获取方式，finishActivity的参数，token(binder对象)的获取不一样
        if (Build.VERSION.SDK_INT >= 28) {
            sActivityKiller = new ActivityKillerV28();
        } else if (Build.VERSION.SDK_INT >= 26) {
            sActivityKiller = new ActivityKillerV26();
        } else if (Build.VERSION.SDK_INT == 25 || Build.VERSION.SDK_INT == 24) {
            sActivityKiller = new ActivityKillerV24_V25();
        } else if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 23) {
            sActivityKiller = new ActivityKillerV21_V23();
        } else if (Build.VERSION.SDK_INT >= 15 && Build.VERSION.SDK_INT <= 20) {
            sActivityKiller = new ActivityKillerV15_V20();
        } else if (Build.VERSION.SDK_INT < 15) {
            sActivityKiller = new ActivityKillerV15_V20();
        }
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getDeclaredMethod("currentActivityThread").invoke(null);
            Field mhField = activityThreadClass.getDeclaredField("mH");
            mhField.setAccessible(true);
            Handler mhHandler = (Handler) mhField.get(activityThread);
            Field callbackField = Handler.class.getDeclaredField("mCallback");
            callbackField.setAccessible(true);
            callbackField.set(mhHandler, getValue(mhHandler));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "initActivityKiller: " + e.getStackTrace());
        }
    }


    @NotNull
    private Handler.Callback getValue(Handler mhHandler) {
        return msg -> {
            Log.e(TAG, "handleMessage: " + msg);
            Log.e(TAG, "handleMessage: " + msg.what);
            try {
                mhHandler.handleMessage(msg);
            } catch (Throwable throwable) {
                switch (msg.what) {
                    case EXECUTE_TRANSACTION:
                    case LAUNCH_ACTIVITY:
                        sActivityKiller.finishLaunchActivity(msg);
                        break;
                    case RESUME_ACTIVITY:
                        sActivityKiller.finishResumeActivity(msg);
                        break;
                    case PAUSE_ACTIVITY_FINISHING:
                    case PAUSE_ACTIVITY:
                        sActivityKiller.finishPauseActivity(msg);
                        break;
                    case STOP_ACTIVITY_HIDE:
                        sActivityKiller.finishStopActivity(msg);
                        break;
                }
                throwable.printStackTrace();
                Log.e(TAG, "getValue: " + throwable.getStackTrace());
            }
            return true;
        };
    }

}
