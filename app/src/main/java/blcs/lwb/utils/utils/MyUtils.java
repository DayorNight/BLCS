package blcs.lwb.utils.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import blcs.lwb.utils.R;

public class MyUtils {

    /**
     * 获取数组
     * @param activity
     * @param a
     * @return
     */
    public static List<String> getArray(Activity activity, int a){
        return Arrays.asList(activity.getResources().getStringArray(a));
    }
}
