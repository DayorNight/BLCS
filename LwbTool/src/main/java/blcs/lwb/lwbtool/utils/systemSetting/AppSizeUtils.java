package blcs.lwb.lwbtool.utils.systemSetting;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

import blcs.lwb.lwbtool.utils.FileUtils;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RxBus;

/**
 * TODO 获取APP应用存储大小
 */

public class AppSizeUtils {

    /**
     * 获取应用总大小
     */
    public static void getAppAllSize(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //检查权限
            if(checkUsageStats(activity)){
                getAllAppTotalsizeO(activity, activity.getPackageName());
            }else{//手动开启权限
                openUsagePermissionSetting(activity);
            }
        } else {
            getAppsize(activity);
        }
    }

    /**
     * 判断是否有#PACKAGE_USAGE_STATS#的权限
     */
    @RequiresApi(Build.VERSION_CODES.M)
    public static Boolean checkUsageStats(Activity activity) {
        Boolean granted;
        AppOpsManager appOps = (AppOpsManager) activity.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), activity.getPackageName());
        if (mode == AppOpsManager.MODE_DEFAULT) {
            granted = activity.checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED;
        } else {
            granted = mode == AppOpsManager.MODE_ALLOWED;
        }
        return granted;
    }

    /**
     * 转到设置界面用户必须手动开启
     *
     * @param context
     */
    public static void openUsagePermissionSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    /**
     * 获取应用的大小
     *
     * @param context
     * @param pkgName
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void getAllAppTotalsizeO(Context context, String pkgName) {
        StorageStatsManager storageStatsManager = (StorageStatsManager) context.getSystemService(Context.STORAGE_STATS_SERVICE);
        StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        //获取所有应用的StorageVolume列表
        List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
        for (StorageVolume item : storageVolumes) {
            String uuidStr = item.getUuid();
            UUID uuid;
            if (uuidStr == null) {
                uuid = StorageManager.UUID_DEFAULT;
            } else {
                uuid = UUID.fromString(uuidStr);
            }
            //通过包名获取uid
            int uid = getUid(context, pkgName);
            StorageStats storageStats = null;
            try {
                storageStats = storageStatsManager.queryStatsForUid(uuid, uid);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String leng =String.valueOf(storageStats.getAppBytes() + storageStats.getDataBytes());
            LogUtils.e("leng "+ leng);
            RxBus.getDefault().post(new RxBus.Event<String>(1,leng));
        }
    }

    /**
     * 根据应用包名获取对应uid
     */
    public static int getUid(Context context, String pakName) {
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appInfo = pm.getApplicationInfo(pakName, PackageManager.GET_META_DATA);
            return appInfo.uid;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取应用大小8.0以下 permission.GET_PACKAGE_SIZE
     */
    public static void getAppsize(Context context) {
        try {
            Method method = PackageManager.class.getMethod("getPackageSizeInfo", new Class[]{String.class, IPackageStatsObserver.class});
            // 调用 getPackageSizeInfo 方法，需要两个参数：1、需要检测的应用包名；2、回调
            method.invoke(context.getPackageManager(), context.getPackageName(), new IPackageStatsObserver.Stub() {
                @Override
                public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) {
                    if (succeeded && pStats != null) {
                        synchronized (AppSizeUtils.class) {
                            long cacheSize = pStats.cacheSize;//缓存大小
                            long dataSize = pStats.dataSize;//数据大小
                            long codeSize = pStats.codeSize;//应用大小
                            String leng =String.valueOf(dataSize + codeSize);
                            RxBus.getDefault().post(new RxBus.Event<String>(1,leng));
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
