package blcs.lwb.lwbtool.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.PopupWindow;

import blcs.lwb.lwbtool.R;

public class LinPopup {

    private static PopupWindow popupWindow;

    public static PopupWindow getInstance(Context context,View contain){
        if(popupWindow ==null){
            popupWindow = new PopupWindow();
            init(context,contain);
        }
        return popupWindow;
    }
    public static PopupWindow get(){
        if (popupWindow ==null) throw new RuntimeException("PopupWindow is null");
        return popupWindow;
    }

    public static void init(Context context, View contain){
        popupWindow.setContentView(contain);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.color.transparent));
        popupWindow.setOutsideTouchable(true);//外部是否可点击 点击外部消失  //必须设置背景图
        popupWindow.setTouchable(true);
//        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(AnimationSet.RELATIVE_TO_PARENT);
    }
}
