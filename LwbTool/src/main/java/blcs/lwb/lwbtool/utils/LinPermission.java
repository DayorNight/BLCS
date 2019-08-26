package blcs.lwb.lwbtool.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

import java.util.ArrayList;

/**
 * android6.0 动态权限申请封装
 * (不试用Fragment，如果要在Fragment 请直接使用requestPermissions)
 * 开源库（https://github.com/permissions-dispatcher/PermissionsDispatcher 注解方式）
 * 开源库（https://github.com/tbruyelle/RxPermissions ）
 */
public class LinPermission {
    private static final String TAG = "LinPermission";
    public static final int CODE_AUDIO = 0;//录音
    public static final int CODE_GET_ACCOUNTS = 1;//读取通讯录权限
    public static final int CODE_PHONE = 2;//通话管理拨打电话
    public static final int CODE_CALENDAR = 3;//日志/日历
    public static final int CODE_CAMERA = 4;//拍照或录像
    public static final int CODE_LOCATION = 5;//获取位置信息
    public static final int CODE_SENSORS = 6;//传感器
    //        public static final int CODE_READ_EXTERNAL_STORAGE = 7;//文件读取
    public static final int CODE_STORAGE = 7;//文件操作
    public static final int CODE_SMS = 8;//短信操作

    public static final int RequestCode_Permission = 100;

    public static final String[] requestPermissions = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BODY_SENSORS,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.SEND_SMS,
    };

    /**
     * 检查是否有权限
     */
    public static boolean checkPermission(Activity context, int code) {
        try {
            return PackageManager.PERMISSION_GRANTED == PermissionChecker.checkSelfPermission(context, requestPermissions[code]);
        } catch (Exception e) {
            Log.e(TAG, "checkPermission: " + e.getMessage());
        }
        return false;
    }

    /**
     * 检查是否有所有权限
     */
    public static boolean checkPermission(Activity context, int[] codes) {
        for (int code : codes){
            if(!checkPermission(context, code)){
                return false;
            }
        }
        return true;
    }
    /**
     * 检查是否有所有权限
     */
    public static boolean checkPermission(Activity context, Integer[] codes) {
        for (int code : codes){
            if(!checkPermission(context, code)){
                return false;
            }
        }
        return true;
    }

    /**
     * 申请单一权限（指定开启某个权限）
     */
    public static void requestPermission(Activity context, int code) {
        if (!checkPermission(context, code)) {
            ActivityCompat.requestPermissions(context, new String[]{requestPermissions[code]}, RequestCode_Permission);
        }
    }

    /**
     * 申请多个权限
     */
    public static void requestMultiplePermission(Activity context, int[] permissions) {
        ArrayList<String> unRequest = new ArrayList<>();
        for (int code : permissions) {//遍历所有权限
            if (!checkPermission(context, code)) {//筛选未开启的权限
                unRequest.add(requestPermissions[code]);
            }
        }
        //判断list是否为空 不为空则进行申请
        if (unRequest.size() > 0) {
            String[] permission = unRequest.toArray(new String[unRequest.size()]);
            ActivityCompat.requestPermissions(context, permission, RequestCode_Permission);
        }
    }

    /**
     * 防止 禁止询问时 不会有提示窗  一般用于 申请失败时调用  否则 直接使用时跳转设置界面
     * @param context
     */
    public static void requestPermission(Activity context,String permission){
        if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) { //有权限申请提示
            ActivityCompat.requestPermissions(context, new String[]{permission}, RequestCode_Permission);
        } else {//点击禁止后不再提示动态申请  手动开启
            //可以自定义弹窗提示 是否手动开启
            LinToPermission.init(context).jumpPermissionPage();
        }
    }

    /**
     * 申请结果回调
     * 在Activity的onRequestPermissionsResult中调用
     */
    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, PermissionsResultListener listener) {
        if (requestCode == RequestCode_Permission) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    listener.onRequestPermissionSuccess(i, permissions[i]);
                } else {
                    listener.onRequestPermissionFailure(i, permissions[i]);
                }
            }
        }
    }

    public interface PermissionsResultListener {
        void onRequestPermissionSuccess(int pos, String permission);
        void onRequestPermissionFailure(int pos, String permission);
    }

    public static PermissionsResultListener listener;
}
