package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by BlueSky on 2018/6/5.
 */

public class MyUtils {
    private  static long firstTime=0;
    private static final int MIN_DELAY_TIME= 1000; // 两次点击间隔不能少于1000ms
    private static long lastClickTime = 0;

    /**
     * 仿多次点击
     */
    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    /**
     * 获取Assets文件夹下的JSON数据
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * Toast
     * @param content
     */
    public static void toast(Context context, String content){
        Toast.makeText(context,content, Toast.LENGTH_SHORT).show();
    }
    /**
     * 修改颜色透明度
     * @param color
     * @param alpha
     * @return
     */
    public static int changeColorAlpha(int color, int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
    public static String getHexString(int color, boolean showAlpha) {
        int base = showAlpha ? 0xFFFFFFFF : 0xFFFFFF;
        String format = showAlpha ? "#%08X" : "#%06X";
        return String.format(format, (base & color)).toUpperCase();
    }
    public static int colorAtLightness(int color, float lightness) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] = lightness;
        return Color.HSVToColor(hsv);
    }
    public static int adjustAlpha(float alpha, int color) {
        return alphaValueAsInt(alpha) << 24 | (0x00ffffff & color);
    }
    public static int alphaValueAsInt(float alpha) {
        return Math.round(alpha * 255);
    }

    public static float getAlphaPercent(int argb) {
        return Color.alpha(argb) / 255f;
    }

    public static float lightnessOfColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv[2];
    }
}
