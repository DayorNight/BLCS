package com.blcs.comlibs.common

import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Process
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.*

/**
 * TODO 跟App相关的辅助类
 *
 * 1、获取应用程序名称
 * 2、获取应用程序版本名称信息
 * 3、获取版本号
 * 4、获取所有安装的应用程序,不包含系统应用
 * 5、获取应用程序的icon图标
 * 6、启动安装应用程序
 * 7、获取渠道名
 * 8、双击退出
 * 9、获取进程号对应的进程名
 * 10、判断是否在主进程
 */
object AppUtils {
    /**
     * 获取包名
     * @param context
     * @return
     */
    fun getAppPackageName(context: Context): String {
        return context.applicationContext.packageName
    }

    /**
     * 获取应用程序名称
     */
    fun getAppName(context: Context): String {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
        val labelRes = packageInfo.applicationInfo.labelRes
        return context.resources.getString(labelRes)
    }

    /**
     * 获取应用程序版本名称信息
     * @param context
     * @return 当前应用的版本名称
     */
    fun getVersionName(context: Context): String {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    }

    /**
     * 3获取版本号
     * int
     * @return 当前应用的版本号
     */
    fun getVersionCode(context: Context): Int {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.versionCode
        } catch (e: Exception) {
            e.printStackTrace()
            1
        }
    }

    /**
     * 4获取所有安装的应用程序,不包含系统应用
     * @param context
     * @return
     */
    fun getInstalledPackages(context: Context): List<PackageInfo> {
        val packageManager = context.packageManager
        val packageInfos = packageManager.getInstalledPackages(0)
        val packageInfoList: MutableList<PackageInfo> = ArrayList()
        for (i in packageInfos.indices) {
            if (packageInfos[i].applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                packageInfoList.add(packageInfos[i])
            }
        }
        return packageInfoList
    }

    /**
     * 5、获取应用程序的icon图标
     * @param context
     * @return
     * 当包名错误时，返回null
     */
    fun getApplicationIcon(context: Context): Drawable? {
        val packageManager = context.packageManager
        try {
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.applicationInfo.loadIcon(packageManager)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 6、启动安装应用程序(兼容Android7.0) https://www.jianshu.com/p/3c554d3983d8
     * @param apkPath  应用程序路径
     */
    fun installApk(context: Activity, apkPath: String?): Boolean {
        try {
            if (TextUtils.isEmpty(apkPath)) {
                Toast.makeText(context, "Apk路径为空", Toast.LENGTH_SHORT).show()
                return false
            }
            val file = File(apkPath)
            if (!file.exists()) {
                Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show()
                return false
            }
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) //增加读写权限
            }
            intent.setDataAndType(getPathUri(context, apkPath), "application/vnd.android.package-archive")
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "安装失败，请重新下载", Toast.LENGTH_LONG).show()
            return false
        } catch (error: Error) {
            error.printStackTrace()
            Toast.makeText(context, "安装失败，请重新下载", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    fun getPathUri(context: Context, filePath: String?): Uri {
        val uri: Uri
        uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val packageName = context.packageName
            FileProvider.getUriForFile(context, "$packageName.fileprovider", File(filePath))
        } else {
            Uri.fromFile(File(filePath))
        }
        return uri
    }

    /**
     * 7、获取渠道名
     * @param context
     * @return
     */
    fun getChannel(context: Context): String? {
        try {
            val pm = context.packageManager
            val appInfo = pm.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            return appInfo.metaData.getString("UMENG_CHANNEL")
        } catch (ignored: PackageManager.NameNotFoundException) {
        }
        return ""
    }

    /**
     * 8、双击退出
     */
    private var firstTime: Long = 0
    fun againstClick(context: Activity) {
        val secondTime = System.currentTimeMillis()
        if (secondTime - firstTime > 2000) {
            Toast.makeText(context, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            firstTime = secondTime
        } else {
            context.finish()
        }
    }

    /**
     * 9.获取进程号对应的进程名
     * @param pid 进程号
     * @return 进程名
     */
    fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
        return null
    }

    /**
     * 10、判断是否在主进程
     */
    fun isMainProcess(context: Context): Boolean {
        val am = context.getSystemService("activity") as ActivityManager
        val mainProcessName = context.packageName
        val myPid = Process.myPid()
        val processInfos = am.runningAppProcesses
        return if (processInfos == null) {
            val processList = am.getRunningServices(2147483647)
            if (processList == null) {
                false
            } else {
                val var9: Iterator<*> = processList.iterator()
                var rsi: ActivityManager.RunningServiceInfo
                do {
                    if (!var9.hasNext()) {
                        return false
                    }
                    rsi = var9.next() as ActivityManager.RunningServiceInfo
                } while (rsi.pid != myPid || mainProcessName != rsi.service.packageName)
                true
            }
        } else {
            val var5: Iterator<*> = processInfos.iterator()
            var info: RunningAppProcessInfo
            do {
                if (!var5.hasNext()) {
                    return false
                }
                info = var5.next() as RunningAppProcessInfo
            } while (info.pid != myPid || mainProcessName != info.processName)
            true
        }
    }

    /**
     * 11、根据应用包名获取对应uid
     */
    fun getAppUid(context: Context, pakName: String): Int {
        return context.packageManager.getApplicationInfo(pakName, PackageManager.GET_META_DATA).uid
    }
}