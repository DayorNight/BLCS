package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import blcs.lwb.lwbtool.R;

public class IntentUtils {
    /**
     * 打开新的Activity
     */
    public static void toActivity(Activity activity,Class<?> cls,Boolean showAnimation) {
        Intent intent = new Intent(activity,cls);
        toActivity(activity,intent, showAnimation);
    }

    /**
     * 打开新的Activity
     * @param activity
     * @param cls 目标Activity
     * @param bundle 参数
     * @param requestCode 请求码
     * @param showAnimation 是否开启动画
     */
    public static void toActivity(Activity activity,Class<?> cls,Bundle bundle,int requestCode,Boolean showAnimation) {
        Intent intent = new Intent(activity,cls);
        intent.putExtra("Bundle",bundle);
        toActivity(activity,intent,requestCode, showAnimation);
    }
    /**
     * 打开新的Activity 动画默认打开
     */
    public static void toActivity(Activity activity,final Intent intent) {
        toActivity(activity,intent, true);
    }
    /**
     * 打开新的Activity
     */
    public static void toActivity(Activity activity,final Intent intent, final boolean showAnimation) {
        toActivity(activity,intent, -1, showAnimation);
    }
    /**
     * 打开新的Activity  带请求码
     */
    public static void toActivity(Activity activity,Class<?> cls, final int requestCode,Boolean showAnimation) {
        Intent intent = new Intent(activity,cls);
        toActivity(activity,intent, requestCode, showAnimation);
    }

    /**
     * 打开新的Activity
     */
    public static void toActivity(final Activity activity, final Intent intent, final int requestCode, final boolean showAnimation) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (requestCode < 0) {
                    activity.startActivity(intent);
                } else {
                    activity.startActivityForResult(intent, requestCode);
                }
                if (showAnimation) {
                    activity.overridePendingTransition(R.anim.right_push_in, R.anim.left_push_out);
                }
            }
        });
    }


    /**TODO 打开新的Activity，向左滑入效果
     * @param intent
     * @param requestCode
     */
    public static void toActivity(final Activity context, final Intent intent, final int requestCode) {
        toActivity(context, intent, requestCode, true);
    }

}
