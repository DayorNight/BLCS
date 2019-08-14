package blcs.lwb.lwbtool.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import blcs.lwb.lwbtool.View.DividerItem;

/**
 * TODO RecycylerView 工具类
 */
public class RecyclerUtil {
    /**
     * 初始化配置
     */
    public static void init(Context context, int orientation, RecyclerView.Adapter mAdapter,RecyclerView mRecycyler){
        init(context,orientation,mAdapter,mRecycyler,true);
    }

    /**
     * 初始化配置
     */
    public static void init(Context context, int orientation, RecyclerView.Adapter mAdapter,RecyclerView mRecycyler,boolean showDecoration){
        mRecycyler.setLayoutManager(new LinearLayoutManager(context, orientation,false));
        mRecycyler.setAdapter(mAdapter);
        if(showDecoration){
            mRecycyler.addItemDecoration(new DividerItem(context,orientation));
        }
    }

    /**
     * 从控件所在位置移动到控件的底部
     * @return
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

}
