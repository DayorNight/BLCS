package blcs.lwb.lwbtool;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * 渠道工具包
 */
public class ChannelUtils {

    /**
     * 获取渠道名
     * @param context
     * @return
     */
    public static  String getChannel(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return "";
    }

//    productFlavors{
//        wandoujia{
//
//        }
//        xiaomi{
//
//        }
//    }
//    productFlavors.all { flavor ->
//            flavor.manifestPlaceholders = [UMENG_CHANNEL_VALUE: name]
//    }
//    applicationVariants.all { variant ->
//            variant.outputs.each { output ->
//            def outputFile = output.outputFile
//        if (outputFile != null && outputFile.name.endsWith('.apk')) {
//            def fileName = outputFile.name.replace(".apk", "-${defaultConfig.versionName}.apk")
//            output.outputFile = new File(outputFile.parent, fileName)
//        }
//    }
//    }


//    <meta-data
//    android:name="UMENG_CHANNEL"
//    android:value="${UMENG_CHANNEL_VALUE}" />
}
