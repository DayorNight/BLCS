package blcs.lwb.lwbtool.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.Locale;

/**
 * TODO 手机组件调用工具类
 */

public class LinPhone {
    private static final String TAG = "PhoneUtil";

    /**
     * 获取设备宽度（px）
     */
    public static int getDeviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备高度（px）
     */
    public static int getDeviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取设备的唯一标识， 需要 “android.permission.READ_Phone_STATE”权限
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "未添加Manifest.permission.READ_PHONE_STATE权限";
        }
        String deviceId = tm.getDeviceId();
        if (deviceId == null) {
            return "UnKnown";
        } else {
            return deviceId;
        }
    }

    /**
     * 获取厂商名
     * **/
    public static String getDeviceManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 获取产品名
     * **/
    public static String getDeviceProduct() {
        return android.os.Build.PRODUCT;
    }

    /**
     * 获取手机品牌
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机主板名
     */
    public static String getDeviceBoard() {
        return android.os.Build.BOARD;
    }

    /**
     * 设备名
     * **/
    public static String getDeviceDevice() {
        return android.os.Build.DEVICE;
    }

    /**
     * fingerprit 信息
     **/
    public static String getDeviceFubgerprint() {
        return android.os.Build.FINGERPRINT;
    }

    /**
     * 硬件名
     *
     * **/
    public static String getDeviceHardware() {
        return android.os.Build.HARDWARE;
    }

    /**
     * 主机
     *
     * **/
    public static String getDeviceHost() {
        return android.os.Build.HOST;
    }

    /**
     *
     * 显示ID
     * **/
    public static String getDeviceDisplay() {
        return android.os.Build.DISPLAY;
    }

    /**
     * ID
     *
     * **/
    public static String getDeviceId() {
        return android.os.Build.ID;
    }

    /**
     * 获取手机用户名
     *
     * **/
    public static String getDeviceUser() {
        return android.os.Build.USER;
    }

    /**
     * 获取手机 硬件序列号
     * **/
    public static String getDeviceSerial() {
        return android.os.Build.SERIAL;
    }

    /**
     * 获取手机Android 系统SDK
     *
     * @return
     */
    public static int getDeviceSDK() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本
     *
     * @return
     */
    public static String getDeviceAndroidVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取当前手机系统语言。
     */
    public static String getDeviceDefaultLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     */
    public static String getDeviceSupportLanguage() {
        Log.e("wangjie", "Local:" + Locale.GERMAN);
        Log.e("wangjie", "Local:" + Locale.ENGLISH);
        Log.e("wangjie", "Local:" + Locale.US);
        Log.e("wangjie", "Local:" + Locale.CHINESE);
        Log.e("wangjie", "Local:" + Locale.TAIWAN);
        Log.e("wangjie", "Local:" + Locale.FRANCE);
        Log.e("wangjie", "Local:" + Locale.FRENCH);
        Log.e("wangjie", "Local:" + Locale.GERMANY);
        Log.e("wangjie", "Local:" + Locale.ITALIAN);
        Log.e("wangjie", "Local:" + Locale.JAPAN);
        Log.e("wangjie", "Local:" + Locale.JAPANESE);
        return Locale.getAvailableLocales().toString();
    }

    public static String getDeviceAllInfo(Context context) {

        return "\n1. IMEI:\n\t\t" + getIMEI(context)

                + "\n2. 设备宽度:\n\t\t" + getDeviceWidth(context)

                + "\n3. 设备高度:\n\t\t" + getDeviceHeight(context)

                + "\n4. 系统默认语言:\n\t\t" + getDeviceDefaultLanguage()

                + "\n5. 硬件序列号(设备名):\n\t\t" + android.os.Build.SERIAL

                + "\n6. 手机型号:\n\t\t" + android.os.Build.MODEL

                + "\n7. 生产厂商:\n\t\t" + android.os.Build.MANUFACTURER

                + "\n8. 手机Fingerprint标识:\n\t\t" + android.os.Build.FINGERPRINT

                + "\n9. Android 版本:\n\t\t" + android.os.Build.VERSION.RELEASE

                + "\n11. Android SDK版本:\n\t\t" + android.os.Build.VERSION.SDK_INT

                + "\n12. 安全patch 时间:\n\t\t" + android.os.Build.VERSION.SECURITY_PATCH

                + "\n13. 发布时间:\n\t\t" + android.os.Build.TIME

                + "\n14. 版本类型:\n\t\t" + android.os.Build.TYPE

                + "\n15. 用户名:\n\t\t" + android.os.Build.USER

                + "\n16. 产品名:\n\t\t" + android.os.Build.PRODUCT

                + "\n17. ID:\n\t\t" + android.os.Build.ID

                + "\n18. 显示ID:\n\t\t" + android.os.Build.DISPLAY

                + "\n19. 硬件名:\n\t\t" + android.os.Build.HARDWARE

                + "\n20. 产品名:\n\t\t" + android.os.Build.DEVICE

                + "\n21. Bootloader:\n\t\t" + android.os.Build.BOOTLOADER

                + "\n22. 主板名:\n\t\t" + android.os.Build.BOARD

                + "\n23. CodeName:\n\t\t" + android.os.Build.VERSION.CODENAME;
    }
}
