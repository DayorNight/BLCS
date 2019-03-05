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

import blcs.lwb.lwbtool.retrofit.RetrofitUtils;
import blcs.lwb.lwbtool.retrofit.use.ApiUrl;
import blcs.lwb.lwbtool.utils.FileUtils;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RxBus;

/**
 * TODO 获取APP应用  缓存大小 数据大小 应用大小
 */

public class AppSizeUtils {

    private static AppSizeUtils mApiUrl;


    private AppSizeUtils() {
    }

    public static AppSizeUtils getInstance() {
        if (mApiUrl == null) {
            mApiUrl = new AppSizeUtils();
            synchronized (AppSizeUtils.class) {
                if (mApiUrl == null) {

                }
            }
        }
        return mApiUrl;
    }

    /**
     * TODO 获取应用总大小
     */
    public void init(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //检查权限
            if (checkUsageStats(context)) {
                getAppSizeO(context, context.getPackageName());
            } else {//手动开启权限
                openPermission(context);
            }
        } else {
            getAppsize(context);
        }
    }

    /**
     * 判断是否有#PACKAGE_USAGE_STATS#的权限
     */
    @RequiresApi(Build.VERSION_CODES.M)
    public Boolean checkUsageStats(Context context) {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), context.getPackageName());
        if (mode == AppOpsManager.MODE_DEFAULT) {
            return context.checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED;
        } else {
            return mode == AppOpsManager.MODE_ALLOWED;
        }
    }

    /**
     * 转到设置界面用户必须手动开启
     */
    public void openPermission(Context context) {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    /**
     * 获取应用的大小
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getAppSizeO(Context context, String pkgName) {
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
            int uid = getUid(context, pkgName);
            //通过包名获取uid
            StorageStats storageStats = null;
            try {
                storageStats = storageStatsManager.queryStatsForUid(uuid, uid);
            } catch (IOException e) {
                e.printStackTrace();
            }
            onBackListent.backData(storageStats.getCacheBytes(), storageStats.getDataBytes(), storageStats.getAppBytes());
        }
    }

    /**
     * 根据应用包名获取对应uid
     */
    public int getUid(Context context, String pakName) {
        try {
            return context.getPackageManager().getApplicationInfo(pakName, PackageManager.GET_META_DATA).uid;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取应用大小 File : getDataDirectory()
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getAppTotalsizeO(Activity activity, File file) {
        try {
            StorageStatsManager manager = (StorageStatsManager) activity.getSystemService(Context.STORAGE_STATS_SERVICE);
            StorageManager storageManager = (StorageManager) activity.getSystemService(Context.STORAGE_SERVICE);
            UUID uuid = storageManager.getUuidForPath(file);
            //通过包名获取uid
            int uid = getUid(activity, activity.getPackageName());
            StorageStats storageStats = manager.queryStatsForUid(uuid, uid);
            //获取到App的总大小
//          Size = storageStats.getAppBytes() + storageStats.getCacheBytes() + storageStats.getDataBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取应用大小8.0以下 permission.GET_PACKAGE_SIZE
     */
    public void getAppsize(Context context) {
        try {
            Method method = PackageManager.class.getMethod("getPackageSizeInfo", new Class[]{String.class, IPackageStatsObserver.class});
            // 调用 getPackageSizeInfo 方法，需要两个参数：1、需要检测的应用包名；2、回调
            method.invoke(context.getPackageManager(), context.getPackageName(), new IPackageStatsObserver.Stub() {
                @Override
                public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) {
                    onBackListent.backData(pStats.cacheSize, pStats.dataSize, pStats.codeSize);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OnBackListent onBackListent;

    public interface OnBackListent {
        void backData(long cacheSize, long dataSize, long codeSize);
    }

    public AppSizeUtils setDatasListent(OnBackListent listent) {
        onBackListent = listent;
        return this;
    }
}
