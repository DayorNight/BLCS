package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 *  TODO 手机组件调用工具类
 * 1、调用系统发短信界面
 * 2、判断是否为连击
 * 3、获取手机型号
 * 4、获取手机品牌
 * 5、拍照打开照相机！
 * 6、打开相册
 *
 */

public class PhoneUtil {
    private static long lastClickTime;

    private PhoneUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 1、调用系统发短信界面
     * @param activity    Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessage(Context activity, String phoneNumber, String smsContent) {
        if (smsContent == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", smsContent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    /**
     * 2、判断是否为连击
     * @return  boolean
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     *  3、获取手机型号
     * @param context  上下文
     * @return   String
     */
    public static String getMobileModel(Context context) {
        try {
            String model = android.os.Build.MODEL;
            return model;
        } catch (Exception e) {
            return "未知！";
        }
    }

    /**
     *  4、获取手机品牌
     * @param context  上下文
     * @return  String
     */
    public static String getMobileBrand(Context context) {
        try {
            // android系统版本号
            String brand = android.os.Build.BRAND;
            return brand;
        } catch (Exception e) {
            return "未知";
        }
    }

    /**
     *  5、拍照打开照相机！
     * @param requestcode   返回值
     * @param activity   上下文
     * @param fileName    生成的图片文件的路径
     */
    public static void toTakePhoto(int requestcode, Activity activity, String fileName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("camerasensortype", 2);// 调用前置摄像头
        intent.putExtra("autofocus", true);// 自动对焦
        intent.putExtra("fullScreen", false);// 全屏
        intent.putExtra("showActionIcons", false);
        try {
            //创建一个当前任务id的文件，然后里面存放任务的照片和路径！这主文件的名字是用uuid到时候再用任务id去查路径！
            File file = new File(fileName);
            //如果这个文件不存在就创建一个文件夹！
            if (!file.exists()) {
                file.mkdirs();
            }
            Uri uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            activity.startActivityForResult(intent, requestcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  6、打开相册
     * @param requestcode  响应码
     * @param activity  上下文
     */
    public static void toTakePicture(int requestcode, Activity activity){
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, requestcode);
    }

}
