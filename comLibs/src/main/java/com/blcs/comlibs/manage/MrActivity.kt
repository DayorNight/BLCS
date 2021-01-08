package com.blcs.comlibs.manage

import android.app.Activity
import android.content.Intent
import android.os.Process
import android.util.Log
import java.util.*

/**
 * Created by lwb on 2018/1/3.
 * Activity管理类：用于管理Activity和退出程序
 */
class MrActivity private constructor() {

    companion object {
        // Activity栈
        private var activityStack: Stack<Activity?>? = null

        /**
         * 单一实例
         */
        @JvmStatic
        val instance: MrActivity by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MrActivity()
        }
        /**
         * 重启应用
         * @param activity
         */
        @JvmStatic
        fun restartApp(activity: Activity) {
            val intent1 = Intent(activity, getRestartActivityClass(activity))
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
            if (intent1.component != null) {
                intent1.action = Intent.ACTION_MAIN
                intent1.addCategory(Intent.CATEGORY_LAUNCHER)
            }
            activity.finish()
            activity.startActivity(intent1)
            killCurrentProcess()
        }

        /**
         * 获取APP初始页面
         * @param context
         * @return
         */
        private fun getRestartActivityClass(context: Activity): Class<out Activity>? {
            val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
            if (intent != null && intent.component != null) {
                try {
                    return Class.forName(intent.component!!.className) as Class<out Activity>
                } catch (e: ClassNotFoundException) {
                    //Should not happen, print it to the log!
                    Log.e("getLauncherActivity", "Failed when resolving the restart activity class via getLaunchIntentForPackage, stack trace follows!", e)
                }
            }
            return null
        }

        private fun killCurrentProcess() {
            Process.killProcess(Process.myPid())
            System.exit(10)
        }
    }
    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity?) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return activityStack!!.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack!!.remove(activity)
            activity.finish()
            activity = null
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity!!.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        for (i in activityStack!!.indices) {
            if (null != activityStack!![i]) {
                activityStack!![i]!!.finish()
            }
        }
        activityStack!!.clear()
    }

    /**
     * 退出应用程序
     */
    fun AppExit() {
        finishAllActivity()
        //退出程序
        Process.killProcess(Process.myPid())
        System.exit(1)
    }


}