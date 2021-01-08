package com.blcs.comlibs.common

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat


/**
 * TODO 获取网络状态
 *
 * 1、是否连接wifi
 * 2、是否连接移动网络
 * 3、是否有网络连接，不管是wifi还是数据流量
 * 4、打开网络设置界面
 * 5、获取网络状态，wifi,wap,2g,3g.
 * 6、通过TelephonyManager判断移动网络的类型
 */
object LinNetStatus {
    /** 没有网络  */
    const val NETWORKTYPE_INVALID = 0

    /** wap网络  */
    const val NETWORKTYPE_WAP = 1

    /** 2G网络  */
    const val NETWORKTYPE_2G = 2

    /** 3G和3G以上网络，或统称为快速网络  */
    const val NETWORKTYPE_3G = 3

    /** wifi网络  */
    const val NETWORKTYPE_WIFI = 4
    private var mNetWorkType = 0

    /**
     * 1、是否连接wifi
     */
    fun isWiFiConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            connectivityManager.activeNetworkInfo!!.type == ConnectivityManager.TYPE_WIFI
        }
    }

    /**
     * 2、是否连接移动网络
     */
    fun isMobileConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            connectivityManager.activeNetworkInfo!!.type == ConnectivityManager.TYPE_MOBILE
        }
    }

    /**
     * 3、是否有网络连接，不管是wifi还是数据流量
     */
    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return networkCapabilities != null
        } else {
            val info = connectivityManager.activeNetworkInfo ?: return false
            return info.isAvailable
        }
    }

    /**
     * 4、打开网络设置界面
     */
    fun openSetting(activity: Activity) {
        activity.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
    }

    /**
     * 5、获取网络状态，wifi,wap,2g,3g
     */
    fun getNetWorkType(context: Context): String {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = manager.getNetworkCapabilities(manager.activeNetwork)
            if (networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return "WIFI"
            } else if (networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return getMobileNetwork(context)
            }
        } else {
            val networkInfo = manager.activeNetworkInfo
            if (networkInfo!!.isAvailable) {
                val type = networkInfo.type
                if (type==ConnectivityManager.TYPE_WIFI) {
                    return "WIFI"
                } else if (type==ConnectivityManager.TYPE_MOBILE) {
                    return getMobileNetwork(context)
                }
            }
        }
        return "NO NETWORK"
    }

    /**
     * 6、通过TelephonyManager判断移动网络的类型
     * @param context
     * @return
     */
    private fun getMobileNetwork(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "WAP"
        }
        TelephonyManager.NETWORK_TYPE_NR
        var type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) telephonyManager.dataNetworkType else telephonyManager.networkType
        return when (type) {
            TelephonyManager.NETWORK_TYPE_GSM,
            TelephonyManager.NETWORK_TYPE_GPRS,
            TelephonyManager.NETWORK_TYPE_EDGE,
            TelephonyManager.NETWORK_TYPE_CDMA,
            TelephonyManager.NETWORK_TYPE_1xRTT -> "2G网络"
            TelephonyManager.NETWORK_TYPE_EVDO_0,
            TelephonyManager.NETWORK_TYPE_EVDO_A,
            TelephonyManager.NETWORK_TYPE_EVDO_B,
            TelephonyManager.NETWORK_TYPE_EHRPD,
            TelephonyManager.NETWORK_TYPE_HSUPA,
            TelephonyManager.NETWORK_TYPE_HSDPA,
            TelephonyManager.NETWORK_TYPE_HSPA,
            TelephonyManager.NETWORK_TYPE_HSPAP,
            TelephonyManager.NETWORK_TYPE_UMTS,
            TelephonyManager.NETWORK_TYPE_TD_SCDMA -> "3G网络"
            TelephonyManager.NETWORK_TYPE_LTE,
            TelephonyManager.NETWORK_TYPE_IWLAN -> "4G网络"
            TelephonyManager.NETWORK_TYPE_NR -> "5G网络"
            else -> "4G网络"
        }
    }
}