package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
 */
public class AppUtils
{

    /**
     * 获取包名
     * @param context
     * @return
     */
    public static String getAppPackageName(Context context) {
        return context.getApplicationContext().getPackageName();
    }

    /**
     * 1获取应用程序名称
     */
    public static String getAppName(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 2[获取应用程序版本名称信息]
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 3获取版本号
     * int
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int versionCode = info.versionCode;
            return versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    /**
     * 4获取所有安装的应用程序,不包含系统应用
     * @param context
     * @return
     */
    public static List<PackageInfo> getInstalledPackages(Context context){
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        List<PackageInfo> packageInfoList  = new ArrayList<PackageInfo>();
        for(int i=0; i < packageInfos.size();i++){
            if ((packageInfos.get(i).applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                packageInfoList.add(packageInfos.get(i));
            }
        }
        return packageInfoList;
    }
    /**
     * 5、获取应用程序的icon图标
     * @param context
     * @return
     * 当包名错误时，返回null
     */
    public static Drawable getApplicationIcon(Context context){
        PackageManager packageManager = context.getPackageManager( );
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName( ), 0);
            return packageInfo.applicationInfo.loadIcon(packageManager);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 6、启动安装应用程序(兼容Android7.0) https://www.jianshu.com/p/3c554d3983d8
     * @param apkPath  应用程序路径
     */
    public static boolean  installApk(Activity context, String apkPath){
        try {
            if(TextUtils.isEmpty(apkPath)){
                Toast.makeText(context,"Apk路径为空",Toast.LENGTH_SHORT).show();
                return false;
            }
            File file = new File(apkPath);
            if(!file.exists()){
                Toast.makeText(context,"文件不存在",Toast.LENGTH_SHORT).show();
                return false;
            }
            LogUtils.e(apkPath);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//增加读写权限
            }
            intent.setDataAndType(getPathUri(context, apkPath), "application/vnd.android.package-archive");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "安装失败，请重新下载", Toast.LENGTH_LONG).show();
            return false;
        } catch (Error error) {
            error.printStackTrace();
            Toast.makeText(context, "安装失败，请重新下载", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public static Uri getPathUri(Context context, String filePath) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String packageName = context.getPackageName();
            uri = FileProvider.getUriForFile(context, packageName + ".fileprovider", new File(filePath));
        } else {
            uri = Uri.fromFile(new File(filePath));
        }
        return uri;
    }

    /**
     * 7、获取渠道名
     * @param context
     * @return
     */
    public static String getChannel(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return "";
    }


    /**
     * 8、双击退出
     */
    private  static long firstTime=0;
    public static void againstClick(Activity context){
        long secondTime = System.currentTimeMillis();
        if(secondTime-firstTime>2000){
            Toast.makeText(context,"再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime=secondTime;
        }else{
            context.finish();
        }
    }
    /**
     * 9.获取进程号对应的进程名
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 10、判断是否在主进程
     */
    public static boolean isMainProcess(Context context) {
        ActivityManager am = (ActivityManager)context.getSystemService("activity");
        String mainProcessName = context.getPackageName();
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        if (processInfos == null) {
            List<ActivityManager.RunningServiceInfo> processList = am.getRunningServices(2147483647);
            if (processList == null) {
                return false;
            } else {
                Iterator var9 = processList.iterator();

                ActivityManager.RunningServiceInfo rsi;
                do {
                    if (!var9.hasNext()) {
                        return false;
                    }

                    rsi = (ActivityManager.RunningServiceInfo)var9.next();
                } while(rsi.pid != myPid || !mainProcessName.equals(rsi.service.getPackageName()));

                return true;
            }
        } else {
            Iterator var5 = processInfos.iterator();

            ActivityManager.RunningAppProcessInfo info;
            do {
                if (!var5.hasNext()) {
                    return false;
                }

                info = (ActivityManager.RunningAppProcessInfo)var5.next();
            } while(info.pid != myPid || !mainProcessName.equals(info.processName));

            return true;
        }
    }
}
