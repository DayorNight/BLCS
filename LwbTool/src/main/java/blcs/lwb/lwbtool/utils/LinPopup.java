package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.PopupWindow;

import blcs.lwb.lwbtool.R;

public class LinPopup {

    private static PopupWindow popupWindow;
    public static PopupWindow getInstance(Context context, View view) {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            init(context, view);
        }
        return popupWindow;
    }

    public static PopupWindow get() {
        if (popupWindow == null) throw new RuntimeException("PopupWindow is null");
        return popupWindow;
    }

    public static void init(Context context, View view) {
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.color.transparent));
        popupWindow.setOutsideTouchable(true);//外部是否可点击 点击外部消失  //必须设置背景图
        popupWindow.setTouchable(true);
//        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(AnimationSet.RELATIVE_TO_PARENT);
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
    }
    //上左
    public static void showUpLeft(View view) {
        popupWindow.showAsDropDown(view, 0, -view.getHeight() - popupWindow.getContentView().getMeasuredHeight());
    }
    //上右
    public static void showUpRight(View view) {
        popupWindow.showAsDropDown(view, view.getWidth()-popupWindow.getContentView().getMeasuredWidth(), -view.getHeight() - popupWindow.getContentView().getMeasuredHeight());
    }
    //下左
    public static void showDownLeft(View view) {
        popupWindow.showAsDropDown(view);
    }
    //下右
    public static void showDownRight(View view) {
        popupWindow.showAsDropDown(view,view.getWidth()-popupWindow.getContentView().getMeasuredWidth(),0);
    }
    //左上
    public static void showLeftUp(View view) {
        popupWindow.showAsDropDown(view, -popupWindow.getContentView().getMeasuredWidth(), -view.getHeight());
    }
    //左下
    public static void showLeftDown(View view) {
        popupWindow.showAsDropDown(view, -popupWindow.getContentView().getMeasuredWidth(), -popupWindow.getContentView().getMeasuredHeight());
    }
    //右上
    public static void showRightUp(View view) {
        popupWindow.showAsDropDown(view, view.getWidth(), -view.getHeight());
    }
    //右下
    public static void showRightDown(View view) {
        popupWindow.showAsDropDown(view, view.getWidth(), -popupWindow.getContentView().getMeasuredHeight());
    }
    //上中
    public static void showUpCenter(View view) {
        int width = popupWindow.getContentView().getMeasuredWidth() - view.getWidth();
        popupWindow.showAsDropDown(view, -width/2, -view.getHeight() - popupWindow.getContentView().getMeasuredHeight());
    }
    //下中
    public static void showDownCenter(View view) {
        int width = popupWindow.getContentView().getMeasuredWidth() - view.getWidth();
        popupWindow.showAsDropDown(view, -width/2, 0);
    }
    //左中
    public static void showLeftCenter(View view) {
        int height = view.getHeight()-popupWindow.getContentView().getMeasuredHeight();
        int height1 = popupWindow.getContentView().getMeasuredHeight() + height / 2;
        popupWindow.showAsDropDown(view,  -popupWindow.getContentView().getMeasuredWidth(), -height1);
    }
    //右中
    public static void showRightCenter(View view) {
        int height = view.getHeight()-popupWindow.getContentView().getMeasuredHeight();
        int height1 = popupWindow.getContentView().getMeasuredHeight() + height / 2;
        popupWindow.showAsDropDown(view, view.getWidth(), -height1);
    }

    public static void showUp(Activity activity) {
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.TOP,0,0);
    }
    public static void showDown(Activity activity) {
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM,0,0);
    }
    public static void showLeft(Activity activity) {
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.LEFT,0,0);
    }
    public static void showRight(Activity activity) {
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.RIGHT,0,0);
    }
}
