package blcs.lwb.utils.utils;

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
     *  BottomNavigationView去除动画
     * @param view
     */
    public static void disableShiftMode(BottomNavigationView view) {
        //获取子View BottomNavigationMenuView的对象
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            //设置私有成员变量mShiftingMode可以修改
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //去除shift效果
//                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
                item.setShifting(false);
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }

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
