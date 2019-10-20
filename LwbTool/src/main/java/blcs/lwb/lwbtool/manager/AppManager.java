package blcs.lwb.lwbtool.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Stack;

/**
 * Created by lwb on 2018/1/3.
 * Activity管理类：用于管理Activity和退出程序
 */

public class AppManager {
    // Activity栈
    private static Stack<Activity> activityStack;
    // 单例模式
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } catch (Exception e) {
        }
    }
    /**
     * 重启应用
     * @param activity
     */
    public static void restartApp(@NonNull Activity activity) {
        Intent intent1 = new Intent(activity, getRestartActivityClass(activity));
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        if (intent1.getComponent() != null) {
            intent1.setAction(Intent.ACTION_MAIN);
            intent1.addCategory(Intent.CATEGORY_LAUNCHER);
        }
        activity.finish();
        activity.startActivity(intent1);
        killCurrentProcess();
    }

    /**
     * 获取APP初始页面
     * @param context
     * @return
     */
    @Nullable
    private static Class<? extends Activity> getRestartActivityClass(@NonNull Activity context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (intent != null && intent.getComponent() != null) {
            try {
                return (Class<? extends Activity>) Class.forName(intent.getComponent().getClassName());
            } catch (ClassNotFoundException e) {
                //Should not happen, print it to the log!
                Log.e("getLauncherActivity", "Failed when resolving the restart activity class via getLaunchIntentForPackage, stack trace follows!", e);
            }
        }
        return null;
    }

    private static void killCurrentProcess() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}
