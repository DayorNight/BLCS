package blcs.lwb.lwbtool.utils;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/***
 * BottomNavigationView 工具类
 */
public class BottomNavigationUtils {
    /**
     * BottomNavigationView去除动画
     * @param view
     */
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //去除动画
//                item.setShiftingMode(false); //api 28之前
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            LogUtils.e( "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            LogUtils.e( "Unable to change value of shift mode");
        }
    }

    /**
     * 指定初始Item
     * @param view
     * @param pos
     */
    public static void setItem(BottomNavigationView view,int pos){
        view.getMenu().getItem(pos).setChecked(true);
    }
    /**
     * 是否开启动画
     * @param view
     */
    public static void openAnimation(BottomNavigationView view,boolean isAnimation){
        if(isAnimation){
            view.setLabelVisibilityMode(0);
        }else{
            view.setLabelVisibilityMode(1);
        }
    }
    /**
     * 添加Item
     * @param view
     */
    public static void addItem(BottomNavigationView view,int groupID,int itemID,int orderID,String text){
        view.getMenu().add(groupID, itemID, orderID, text);
    }
    /**
     * 删除Item
     * @param view
     */
    public static void remove(BottomNavigationView view,int pos){
        view.getMenu().removeItem(pos);
    }

}
