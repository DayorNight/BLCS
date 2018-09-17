package blcs.lwb.lwbtool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class IntentUtils {
    /**
     * 打开新的Activity
     */
    public static void toActivity(Activity activity,Class<?> cls) {
        Intent intent = new Intent(activity,cls);
        toActivity(activity,intent, true);
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
    public static void toActivity(Activity activity,final Intent intent, final int requestCode) {
        toActivity(activity,intent, requestCode, true);
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
                    activity.overridePendingTransition(R.anim.right_push_in, R.anim.hold);
                } else {
                    activity.overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
                }
            }
        });
    }
}
