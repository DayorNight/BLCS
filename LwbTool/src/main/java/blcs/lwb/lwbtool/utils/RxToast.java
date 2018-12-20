package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import blcs.lwb.lwbtool.R;

import static android.support.v4.view.ViewCompat.setBackground;

/**
 * Toast封装类
 */
public class RxToast {
    @ColorInt
    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");

    @ColorInt
    private static final int ERROR_COLOR = Color.parseColor("#FD4C5B");

    @ColorInt
    private static final int INFO_COLOR = Color.parseColor("#3F51B5");

    @ColorInt
    private static final int SUCCESS_COLOR = Color.parseColor("#388E3C");

    @ColorInt
    private static final int WARNING_COLOR = Color.parseColor("#FFA900");

    private static final String TOAST_TYPEFACE = "sans-serif-condensed";
    private static Toast currentToast;
    // 参数： 显示消息
    public static void normal(@NonNull Activity context, @NonNull String message) {
        normal(context, message, Toast.LENGTH_SHORT, null, false).show();
    }
    // 参数： 显示消息 图片
    public static void normal(@NonNull Activity context, @NonNull String message, Drawable icon) {
        normal(context, message, Toast.LENGTH_SHORT, icon, true).show();
    }
    // 参数： 显示消息 显示时长
    public static void normal(@NonNull Activity context,@NonNull String message, int duration) {
        normal(context, message, duration, null, false).show();
    }
    // 参数： 显示消息 显示时长 图片
    public static void normal(@NonNull Activity context,@NonNull String message, int duration, Drawable icon) {
        normal(context, message, duration, icon, true).show();
    }
    // 参数： 显示消息 显示时长 图片 是否显示图片
    public static Toast normal(@NonNull Activity context,@NonNull String message, int duration, Drawable icon, boolean withIcon) {
        return custom(context, message, icon,DEFAULT_TEXT_COLOR , duration, withIcon);
    }

    // 参数： 显示消息
    public static void warning(@NonNull Activity context,@NonNull String message) {
        warning(context, message, Toast.LENGTH_SHORT, true).show();
    }
    // 参数： 显示消息 显示时长
    public static void warning(@NonNull Activity context,@NonNull String message, int duration) {
        warning(context, message, duration, true).show();
    }
    // 参数： 显示消息 显示时长 是否显示图片
    public static Toast warning(@NonNull Activity context,@NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.mipmap.ic_error_outline_white_48dp), DEFAULT_TEXT_COLOR, WARNING_COLOR, duration, withIcon, true);
    }
    // 参数： 显示消息
    public static void info(@NonNull Activity context,@NonNull String message) {
        info(context, message, Toast.LENGTH_SHORT, true).show();
    }
    // 参数： 显示消息 显示时长
    public static void info(@NonNull Activity context,@NonNull String message, int duration) {
        info(context, message, duration, true).show();
    }
    // 参数： 显示消息 显示时长 是否显示图片
    public static Toast info(@NonNull Activity context,@NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.mipmap.ic_info_outline_white_48dp), DEFAULT_TEXT_COLOR, INFO_COLOR, duration, withIcon, true);
    }
    // 参数： 显示消息
    public static void success(@NonNull Activity context,@NonNull String message) {
        success(context, message, Toast.LENGTH_SHORT, true).show();
    }
    // 参数： 显示消息 显示时长
    public static void success(@NonNull Activity context,@NonNull String message, int duration) {
        success(context, message, duration, true).show();
    }
    // 参数： 显示消息 显示时长 是否显示图片
    public static Toast success(@NonNull Activity context,@NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.mipmap.ic_check_white_48dp), DEFAULT_TEXT_COLOR, SUCCESS_COLOR, duration, withIcon, true);
    }
    // 参数： 显示消息
    public static void error(@NonNull Activity context,@NonNull String message) {
        error(context, message, Toast.LENGTH_SHORT, true).show();
    }
    // 参数： 显示消息 显示时长
    public static void error(@NonNull Activity context,@NonNull String message, int duration) {
        error(context, message, duration, true).show();
    }
    // 参数： 显示消息 显示时长 是否显示图片
    public static Toast error(@NonNull Activity context,@NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.mipmap.ic_clear_white_48dp), DEFAULT_TEXT_COLOR, ERROR_COLOR, duration, withIcon, true);
    }

    // 参数： 显示消息 图片 字体颜色 显示时长  是否显示图片
    public static Toast custom(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, int duration, boolean withIcon) {
        return custom(context, message, icon, textColor, -1, duration, withIcon, false);
    }
    // 参数： 显示消息 图片id 字体颜色 提示颜色 显示时长  是否显示图片 是否显示图片颜色
    public static Toast custom(@NonNull Context context, @NonNull String message, @DrawableRes int iconRes, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        return custom(context, message, getDrawable(context, iconRes), textColor, tintColor, duration, withIcon, shouldTint);
    }
    // 参数： 显示消息 图片id 字体颜色 提示颜色 显示时长  是否显示图片 是否显示图片颜色
    public static Toast custom(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        if (currentToast == null) {
            currentToast = new Toast(context);
        }
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast_layout, null);
        final ImageView toastIcon =  toastLayout.findViewById(R.id.toast_icon);
        final TextView toastTextView =  toastLayout.findViewById(R.id.toast_text);
        Drawable drawableFrame;
        if (shouldTint) {
            drawableFrame = tint9PatchDrawableFrame(context, tintColor);
        } else {
            drawableFrame = getDrawable(context, R.mipmap.toast_frame);
        }
        setBackground(toastLayout, drawableFrame);
        if (withIcon) {
            if (icon == null) {
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            }
            setBackground(toastIcon, icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        toastTextView.setTextColor(textColor);
        toastTextView.setText(message);
        toastTextView.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);
        return currentToast;
    }

    public static final Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    /**
     * 获取.9位图
     */
    public static final Drawable tint9PatchDrawableFrame(@NonNull Context context, @ColorInt int tintColor) {
        final NinePatchDrawable toastDrawable = (NinePatchDrawable) getDrawable(context, R.mipmap.toast_frame);
        toastDrawable.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
        return toastDrawable;
    }


    /**
     * 参数：消息 显示时长
     */
    public static void showToast(Context context, String str, boolean isLong) {
        if (isLong) {
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 参数：消息
     */
    public static void showToastShort(Activity context,String str) {
        showToast(context,str,false);
    }
    /**
     * 参数：消息id
     */
    public static void showToastShort(Activity context,int resId) {
        showToast(context,context.getString(resId),false);
    }
}
