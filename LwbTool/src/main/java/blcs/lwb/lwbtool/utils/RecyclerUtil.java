package blcs.lwb.lwbtool.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import blcs.lwb.lwbtool.View.DividerItem;

/**
 * TODO RecycylerView 工具类
 */

public class RecyclerUtil {

    /**
     * 初始化配置
     */
    public static void init(Context context, int orientation, RecyclerView.Adapter mAdapter,RecyclerView mRecycyler){
        mRecycyler.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.VERTICAL,false));
        mRecycyler.setAdapter(mAdapter);
        mRecycyler.addItemDecoration(new DividerItem(context,OrientationHelper.VERTICAL));
    }
}
