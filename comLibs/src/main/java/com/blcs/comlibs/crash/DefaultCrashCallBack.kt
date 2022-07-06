package com.blcs.comlibs.crash

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.blcs.comlibs.BuildConfig
import com.blcs.comlibs.R
import com.blcs.comlibs.common.AppUtils
import com.blcs.comlibs.manage.MrActivity
import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer

class DefaultCrashCallBack : ICrashCallBack {
    private val MAX_STACK_TRACE_SIZE = 131071 //128 KB - 1
    private val TAG = "DefaultCrashCallBack"
    lateinit var deviceInfo: StringBuilder
    private var exception :Throwable? = null
    /**
     * 异常捕获处理
     */
    override fun uncaughtException(ctx: Context, t: Thread,throwable: Throwable ) {
        if (throwable is Exception){
             exception = throwable as Exception
        }
        deviceInfo = StringBuilder()
        val writer: Writer = StringWriter()
        val printWriter = PrintWriter(writer)
        /*打印日志*/
        exception?.printStackTrace(printWriter)
        printWriter.close()
        var stackTraceString = writer.toString()
        if (stackTraceString.length > MAX_STACK_TRACE_SIZE) {
            val disclaimer = " [stack trace too large]"
            stackTraceString = stackTraceString.substring(0, MAX_STACK_TRACE_SIZE - disclaimer.length) + disclaimer
        }
        deviceInfo.append(" \n-----------BUG----------\n")
                .append(stackTraceString)
                .append("\n-----------END----------\n")
        if (BuildConfig.DEBUG) {
            exception?.printStackTrace()
            Log.e(TAG, deviceInfo.toString())
            /*弹窗*/
            try {
                val activity = MrActivity.instance.currentActivity()
                val dialog = AlertDialog.Builder(activity!!)
                        .setTitle("错误信息")
                        .setMessage(deviceInfo.toString())
                        .setPositiveButton("重启", { dialog, which -> MrActivity.restartApp(activity!!) })
                        .setNeutralButton("确定", { dialog, which -> }).show()
                val content: TextView? = dialog.findViewById(android.R.id.message)
                val title: TextView? = dialog.findViewById(R.id.alertTitle)
                title?.setTextColor(Color.RED)
                content?.setTextSize(TypedValue.COMPLEX_UNIT_PX, ctx.resources.getDimension(R.dimen.standard_weak))
                title?.setTextSize(TypedValue.COMPLEX_UNIT_PX, ctx.resources.getDimension(R.dimen.standard_work))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            val pi = ctx.packageManager.getPackageInfo(ctx.packageName, PackageManager.GET_ACTIVITIES)
            val versionName = if (pi.versionName == null) "null" else pi.versionName
            val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) pi.longVersionCode.toString() else pi.versionCode.toString()
            deviceInfo.append("应用名：").append(AppUtils.getAppName(ctx)).append("\n")
                    .append("应用版本：").append(versionName).append("\n")
                    .append("应用版本号：").append(versionCode).append("\n")
                    .append("型号：").append(Build.MODEL).append("\n")
                    .append("设备名：").append(Build.DEVICE).append("\n")
                    .append("安卓版本：").append(Build.VERSION.RELEASE).append("\n")
                    .append("生产厂商：").append(Build.MANUFACTURER).append("\n")
                    .append("Android SDK版本：").append(Build.VERSION.SDK_INT).append("\n")
                    .append("硬件名：").append(Build.HARDWARE).append("\n")
                    .append("-----------BUG----------").append("\n")
                    .append(stackTraceString).append("\n")
                    .append("-----------END----------").append("\n")
            upLoadService(ctx, deviceInfo.toString())
        }
    }


    /**
     * 错误汇报
     */
    private fun upLoadService(mContext: Any, toString: String) {
        TODO("Not yet implemented")
    }
}