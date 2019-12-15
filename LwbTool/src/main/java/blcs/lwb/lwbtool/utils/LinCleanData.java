package blcs.lwb.lwbtool.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

/**
 * 清除缓存/数据
 * 1.获取所有的缓存数据
 * 3.清除所有缓存
 * 5.单位格式化
 * 6.清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
 * 7.清除本应用全部数据库(/data/data/com.xxx.xxx/databases)
 * 8.清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
 * 9.按名字清除本应用数据库
 * 10.清除/data/data/com.xxx.xxx/files下的内容
 * 11.清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
 * 12.清除自己定义路径下的文件。使用需小心。请不要误删。并且仅仅支持文件夹下的文件删除
 * 13.清除本应用全部的数据
 */
public class LinCleanData {
    /**
     * 1.获取所有的缓存数据
     */
    public static String getAllCacheSize(Context context) {
        long cacheSize = FileUtils.getFileLen(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += FileUtils.getFileLen(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 3.清除所有缓存
     */
    public static void clearAllCache(Context context) {
        FileUtils.delFilesFromPath(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            FileUtils.delFilesFromPath(context.getExternalCacheDir());
        }
    }

    /**
     * 5.单位格式化
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     *  6.清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
     */
    public static void cleanInternalCache(Context context) {
        FileUtils.deleteFilesByDirectory(context.getCacheDir());
    }

    /**
     *  7.清除本应用全部数据库(/data/data/com.xxx.xxx/databases)
     */
    public static void cleanDatabases(Context context) {
        FileUtils.deleteFilesByDirectory(new File(context.getFilesDir().getPath()
                + context.getPackageName() + "/databases"));
    }

    /**
     * 8.清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     */
    public static void cleanSharedPreference(Context context) {
        FileUtils.deleteFilesByDirectory(new File(context.getFilesDir().getPath()
                + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * 9.按名字清除本应用数据库
     */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     *  10.清除/data/data/com.xxx.xxx/files下的内容
     */
    public static void cleanFiles(Context context) {
        FileUtils.deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * 11.清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            FileUtils.deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * 12.清除自己定义路径下的文件。使用需小心。请不要误删。并且仅仅支持文件夹下的文件删除
     */
    public static void cleanCustomCache(String filePath) {
        FileUtils.deleteFilesByDirectory(new File(filePath));
    }

    /**
     * 13.清除本应用全部的数据
     */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }

}
