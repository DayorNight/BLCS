package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import blcs.lwb.lwbtool.R;

/**
 * Created by lwb on 2017/12/26.
 * TODO 创建删除快捷图标
 * 1、检测是否存在快捷键
 * 2、为程序创建桌面快捷方式
 * 3、删除程序的快捷方式
 *
 */

public class ShortCutUtil {
    private ShortCutUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 1、检测是否存在快捷键
     * @param activity Activity
     * @return 是否存在桌面图标
     */
    public static boolean hasShortcut(Activity activity) {
        boolean isInstallShortcut = false;
        ContentResolver cr = activity.getContentResolver();
        String AUTHORITY = "com.android.launcher.settings";
        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI,
                new String[]{"title", "iconResource"}, "title=?",
                new String[]{activity.getString(R.string.app_name).trim()},
                null);
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
        }

        return isInstallShortcut;
    }

    /**
     * 2、为程序创建桌面快捷方式
     * @param activity Activity
     * @param res     res
     */
    public static void addShortcut(Activity activity, int res) {
        Intent shortcut = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        //快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                activity.getString(R.string.app_name));
        //不允许重复创建
        shortcut.putExtra("duplicate", false);
        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
        shortcutIntent.setClassName(activity, activity.getClass().getName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        //快捷方式的图标
        Intent.ShortcutIconResource iconRes =
                Intent.ShortcutIconResource.fromContext(activity, res);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        activity.sendBroadcast(shortcut);
    }

    /**
     * 3、删除程序的快捷方式
     * @param activity Activity
     */
    public static void delShortcut(Activity activity) {
        Intent shortcut = new Intent(
                "com.android.launcher.action.UNINSTALL_SHORTCUT");
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                activity.getString(R.string.app_name));
        String appClass = activity.getPackageName() + "."
                + activity.getLocalClassName();
        ComponentName cn = new ComponentName(activity.getPackageName(), appClass);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(
                Intent.ACTION_MAIN).setComponent(cn));
        activity.sendBroadcast(shortcut);
    }

}
