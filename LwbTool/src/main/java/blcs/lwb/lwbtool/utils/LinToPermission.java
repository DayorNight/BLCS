package blcs.lwb.lwbtool.utils;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * TODO 跳转权限设置界面
 * 来源：https://blog.csdn.net/donkor_/article/details/79374442
 */
public class LinToPermission {
    private final String TAG = "PermissionPageManager";
    private Context mContext;
    //自己的项目包名
    private static LinToPermission linToPermission;

    private LinToPermission(Context context) {
        this.mContext = context;
    }

    public static LinToPermission init(Context context) {
        if (linToPermission == null) {
            synchronized (LinToPermission.class) {
                if (linToPermission == null) {
                    linToPermission = new LinToPermission(context);
                }
            }
        }
        return linToPermission;
    }

    public void jumpPermissionPage() {
        String name = Build.MANUFACTURER;
        LogUtils.e("jumpPermissionPage --- name : " + name);
        switch (name) {
            case "HUAWEI":
                goHuaWeiMainager();
                break;
            case "vivo":
                goVivoMainager();
                break;
            case "OPPO":
                goOppoMainager();
                break;
            case "Coolpad":
                goCoolpadMainager();
                break;
            case "Meizu":
                goMeizuMainager();
                break;
            case "Xiaomi":
                goXiaoMiMainager();
                break;
            case "samsung":
                goSangXinMainager();
                break;
            case "Sony":
                goSonyMainager();
                break;
            case "LG":
                goLGMainager();
                break;
            default:
                goIntentSetting();
                break;
        }
    }

    private void goLGMainager() {
        try {
            Intent intent = new Intent(mContext.getPackageName());
            ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity");
            intent.setComponent(comp);
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private void goSonyMainager() {
        try {
            Intent intent = new Intent(mContext.getPackageName());
            ComponentName comp = new ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity");
            intent.setComponent(comp);
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private void goHuaWeiMainager() {
        try {
            Intent intent = new Intent(mContext.getPackageName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
            intent.setComponent(comp);
            mContext.startActivity(intent);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private static String getMiuiVersion() {
        String propName = "ro.miui.ui.version.name";
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(
                    new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }

    private void goXiaoMiMainager() {
        String rom = getMiuiVersion();
        Intent intent = new Intent();
        if ("V6".equals(rom) || "V7".equals(rom)) {
            intent.setAction("miui.intent.action.APP_PERM_EDITOR");
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            intent.putExtra("extra_pkgname", mContext.getPackageName());
        } else if ("V8".equals(rom) || "V9".equals(rom)) {
            intent.setAction("miui.intent.action.APP_PERM_EDITOR");
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            intent.putExtra("extra_pkgname", mContext.getPackageName());
        } else {
            goIntentSetting();
        }
        mContext.startActivity(intent);
    }

    private void goMeizuMainager() {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra("packageName", mContext.getPackageName());
            mContext.startActivity(intent);
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            localActivityNotFoundException.printStackTrace();
            goIntentSetting();
        }
    }

    private void goSangXinMainager() {
        //三星4.3可以直接跳转
        goIntentSetting();
    }

    private void goIntentSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
        intent.setData(uri);
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goOppoMainager() {
        doStartApplicationWithPackageName("com.coloros.safecenter");
    }

    /**
     * doStartApplicationWithPackageName("com.yulong.android.security:remote")
     * 和Intent open = getPackageManager().getLaunchIntentForPackage("com.yulong.android.security:remote");
     * startActivity(open);
     * 本质上没有什么区别，通过Intent open...打开比调用doStartApplicationWithPackageName方法更快，也是android本身提供的方法
     */
    private void goCoolpadMainager() {
        doStartApplicationWithPackageName("com.yulong.android.security:remote");
      /*  Intent openQQ = getPackageManager().getLaunchIntentForPackage("com.yulong.android.security:remote");
        startActivity(openQQ);*/
    }

    private void goVivoMainager() {
        doStartApplicationWithPackageName("com.bairenkeji.icaller");
     /*   Intent openQQ = getPackageManager().getLaunchIntentForPackage("com.vivo.securedaemonservice");
        startActivity(openQQ);*/
    }

    /**
     * 此方法在手机各个机型设置中已经失效
     *
     * @return
     */
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", mContext.getPackageName());
        }
        return localIntent;
    }

    private void doStartApplicationWithPackageName(String packagename) {
        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = mContext.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }
        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);
        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = mContext.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        for (int i = 0; i < resolveinfoList.size(); i++) {
            LogUtils.e(resolveinfoList.get(i).activityInfo.packageName + resolveinfoList.get(i).activityInfo.name);
        }
        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packageName参数2 = 参数 packname
            String packageName = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packageName参数2.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            // 设置ComponentName参数1:packageName参数2:MainActivity路径
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            try {
                mContext.startActivity(intent);
            } catch (Exception e) {
                goIntentSetting();
                e.printStackTrace();
            }
        }
    }

}
