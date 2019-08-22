package blcs.lwb.lwbtool.utils.systemSetting;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * TODO SD卡相关的辅助类
 * @author CPC
 * 1判断SDCard是否可用/检测Sdcard是否存在
 * 2获取SD卡路径
 * 3获取SD卡的剩余容量 单位byte
 * 4获取指定路径所在空间的剩余可用容量字节数，单位byte
 * 5获取系统存储路径
 * 6获取系统存储路径文件
 * 7获取应用程序的/data/data目录
 * 8/data/data/PackageName/cache的路径
 * 9获取SD卡大小
 * 10获取SD卡可用大小
 * 11获得手机内存总大小
 * 12获得手机可用内存
 */
public class SDCardUtils
{
    private SDCardUtils()
    {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 1判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable()
    {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }
    /**
     * TODO 检测Sdcard是否存在
     * @return
     */
    public static boolean isExitsSdcard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 2获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * 3获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize()
    {
        if (isSDCardEnable())
        {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 4获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath)
    {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath()))
        {
            filePath = getSDCardPath();
        } else
        {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 5获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath()
    {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 6获取系统存储路径文件
     * @return
     * SD卡存在返回正常路径；SD卡不存在返回null
     */
    public static File getSDCardRootFile(){
        if(isSDCardEnable()){
            return Environment.getRootDirectory();
        }else{
            return null;
        }
    }

    /**
     * 7获取应用程序的/data/data目录
     * @param context
     * @return
     */
    public static String getDataFilePath(Context context){
        return context.getFilesDir().getAbsolutePath() + File.separator;
    }

    /**
     * 8/data/data/PackageName/cache的路径
     * @param context
     * @return
     */
    public static String getDataCachePath(Context context){
        return context.getCacheDir().getAbsolutePath() + File.separator;
    }

    /**
     * 9获取SD卡大小
     * @return
     * SD卡存在返回大小；SD卡不存在返回-1
     */
    public static long getSDCardSize(){
        if (isSDCardEnable()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator);
            if (android.os.Build.VERSION.SDK_INT < 18) {
                int blockSize = statFs.getBlockSize();
                int blockCount = statFs.getBlockCount();
                return blockSize * blockCount;
            } else {
                long blockSize = statFs.getBlockSizeLong();
                long blockCount = statFs.getBlockCountLong();
                return blockSize * blockCount;
            }
        }
        return -1;
    }

    /**
     * 10获取SD卡可用大小
     * @return
     * SD卡存在返回大小；SD卡不存在返回-1
     */
    public static long getSDCardAvailableSize(){
        if (isSDCardEnable()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator);
            if (android.os.Build.VERSION.SDK_INT < 18) {
                int blockSize = statFs.getBlockSize();
                int blockCount = statFs.getAvailableBlocks();
                return blockSize * blockCount;
            } else {
                long blockSize = statFs.getBlockSizeLong();
                long blockCount = statFs.getAvailableBlocksLong();
                return blockSize * blockCount;
            }
        }
        return -1;
    }

    /**
     * 11获得手机内存总大小
     * @return
     */
    public static long getRomTotalSize() {
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        if (android.os.Build.VERSION.SDK_INT < 18) {
            int blockSize = statFs.getBlockSize();
            int blockCount = statFs.getBlockCount();
            return blockSize * blockCount;
        } else {
            long blockSize = statFs.getBlockSizeLong();
            long blockCount = statFs.getBlockCountLong();
            return blockSize * blockCount;
        }
    }

    /**
     * 12获得手机可用内存
     * @return
     */
    public static long getRomAvailableSize() {
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        if (android.os.Build.VERSION.SDK_INT < 18) {
            int blockSize = statFs.getBlockSize();
            int blockCount = statFs.getAvailableBlocks();
            return blockSize * blockCount;
        } else {
            long blockSize = statFs.getBlockSizeLong();
            long blockCount = statFs.getAvailableBlocksLong();
            return blockSize * blockCount;
        }
    }
}
