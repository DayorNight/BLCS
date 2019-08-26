package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * TODO 获取网络状态
 * 
 * 1、是否连接wifi
 * 2、是否连接移动网络
 * 3、是否有网络连接，不管是wifi还是数据流量
 * 4、打开网络设置界面
 * 5、获取网络状态，wifi,wap,2g,3g.
 * 6、跳转到网络设置页面
 */
public class LinNetStatus
{
	/** 没有网络 */
	public static final int NETWORKTYPE_INVALID = 0;
	/** wap网络 */
	public static final int NETWORKTYPE_WAP = 1;
	/** 2G网络 */
	public static final int NETWORKTYPE_2G = 2;
	/** 3G和3G以上网络，或统称为快速网络 */
	public static final int NETWORKTYPE_3G = 3;
	/** wifi网络 */
	public static final int NETWORKTYPE_WIFI = 4;
	private static int mNetWorkType;
	/**
	 * 是否连接wifi
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWIFI(Context context)
	{
		if (isConnected(context))
		{
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			int type = info.getType();
			if (ConnectivityManager.TYPE_WIFI == type)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否连接移动网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobile(Context context)
	{
		if (isConnected(context))
		{
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			int type = info.getType();
			if (ConnectivityManager.TYPE_MOBILE == type)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否有网络连接，不管是wifi还是数据流量
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context)
	{
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info == null)
		{
			return false;
		}
		boolean available = info.isAvailable();
		return available;
	}

	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Activity activity)
	{
		Intent intent = null;
		if(android.os.Build.VERSION.SDK_INT > 10){  // 3.0以上
			intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
		}else{
			intent = new Intent();
			intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
		}
		IntentUtils.toActivity(activity, intent);
	}

	/**
	 * 获取网络状态，wifi,wap,2g,3g.
	 * @param context 上下文
	 * @return int 网络状态 {@link #NETWORKTYPE_2G},{@link #NETWORKTYPE_3G},
	 * {@link #NETWORKTYPE_INVALID},{@link #NETWORKTYPE_WAP}* <p>{@link #NETWORKTYPE_WIFI}
	 */
	public static int getNetWorkType(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			String type = networkInfo.getTypeName();
			if (type.equalsIgnoreCase("WIFI")) {
				mNetWorkType = NETWORKTYPE_WIFI;
			} else if (type.equalsIgnoreCase("MOBILE")) {
				String proxyHost = android.net.Proxy.getDefaultHost();
				mNetWorkType = TextUtils.isEmpty(proxyHost)
						? (isFastMobileNetwork(context) ? NETWORKTYPE_3G : NETWORKTYPE_2G)
						: NETWORKTYPE_WAP;
			}
		} else {
			mNetWorkType = NETWORKTYPE_INVALID;
		}
		LogUtils.e("mNetWorkType:" + mNetWorkType);
		return mNetWorkType;
	}

	/**
	 * 通过TelephonyManager判断移动网络的类型
	 * @param context
	 * @return
	 */
	private static boolean isFastMobileNetwork(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		switch (telephonyManager.getNetworkType()) {
			case TelephonyManager.NETWORK_TYPE_1xRTT:
				return false; // ~ 50-100 kbps
			case TelephonyManager.NETWORK_TYPE_CDMA:
				return false; // ~ 14-64 kbps
			case TelephonyManager.NETWORK_TYPE_EDGE:
				return false; // ~ 50-100 kbps
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
				return true; // ~ 400-1000 kbps
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
				return true; // ~ 600-1400 kbps
			case TelephonyManager.NETWORK_TYPE_GPRS:
				return false; // ~ 100 kbps
			case TelephonyManager.NETWORK_TYPE_HSDPA:
				return true; // ~ 2-14 Mbps
			case TelephonyManager.NETWORK_TYPE_HSPA:
				return true; // ~ 700-1700 kbps
			case TelephonyManager.NETWORK_TYPE_HSUPA:
				return true; // ~ 1-23 Mbps
			case TelephonyManager.NETWORK_TYPE_UMTS:
				return true; // ~ 400-7000 kbps
			case TelephonyManager.NETWORK_TYPE_EHRPD:
				return true; // ~ 1-2 Mbps
			case TelephonyManager.NETWORK_TYPE_EVDO_B:
				return true; // ~ 5 Mbps
			case TelephonyManager.NETWORK_TYPE_HSPAP:
				return true; // ~ 10-20 Mbps
			case TelephonyManager.NETWORK_TYPE_IDEN:
				return false; // ~25 kbps
			case TelephonyManager.NETWORK_TYPE_LTE:
				return true; // ~ 10+ Mbps
			case TelephonyManager.NETWORK_TYPE_UNKNOWN:
				return false;
			default:
				return false;
		}
	}

}
