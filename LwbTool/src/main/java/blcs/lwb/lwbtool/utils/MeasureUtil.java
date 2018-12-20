package blcs.lwb.lwbtool.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListView;

/**
 * TODO MeasureUtil工具类
 * 1获取控件的测量高度
 * 2控件的高度
 * 3获取控件的测量宽度
 * 4获取控件的宽度
 * 5设置高度
 * 6设置View的宽度
 * 7设置ListView的实际高度
 * 8设置GridView的高度
 *
 */

public class MeasureUtil {
    private MeasureUtil(){}

    /**
     * 1获取控件的测量高度
     * @param view  控件
     * @return  返回测量高度（MeasuredHeight）
     */
    public static int getMeasuredHeight(View view) {
        if (view == null) {
            throw new IllegalArgumentException("view is null");
        }
        view.measure(0, 0);
        return view.getMeasuredHeight();
    }

    /**
     * 2控件的高度
     * @param view  控件View
     * @return  返回控件的高度
     */
    public static int getHeight(View view){
        if(view == null){
            throw new IllegalArgumentException("view is null");
        }

        view.measure(0, 0);
        return view.getHeight();
    }

    /**
     * 获取控件的测量宽度
     * @param view  控件
     * @return  返回控件的测量宽度
     */
    public static int getMeasuredWidth(View view){
        if(view == null){
            throw new IllegalArgumentException("view is null");
        }

        view.measure(0, 0);
        return view.getMeasuredWidth();
    }

    /**
     * 4获取控件的宽度
     * @param view  控件
     * @return  返回控件的宽度
     */
    public static int getWidth(View view){
        if(view == null){
            throw new IllegalArgumentException("view is null");
        }

        view.measure(0, 0);
        return view.getWidth();
    }

    /**
     * 5设置高度
     * @param view  控件
     * @param height    高度
     */
    public static void setHeight(View view, int height) {
        if (view == null || view.getLayoutParams() == null) {
            throw new IllegalArgumentException("View LayoutParams is null");
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }

    /**
     * 6设置View的宽度
     * @param view  view
     * @param width 宽度
     */
    public static void setWidth(View view, int width){
        if(view == null || view.getLayoutParams() == null){
            throw new IllegalArgumentException("View LayoutParams is null");
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }

    /**
     * 7设置ListView的实际高度
     * @param listView  ListView控件
     */
    public static void setListHeight(ListView listView) {
        if (listView == null) {
            throw new IllegalArgumentException("ListView is null");
        }
        Adapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        int size = adapter.getCount();
        for (int i = 0; i < size; i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (size - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 8设置GridView的高度，
     * @param context   应用程序上下文
     * @param gv        GridView控件
     * @param n         行数
     * @param m         列数
     */
    public static void setGridViewHeight(Context context, GridView gv, int n, int m) {
        if(gv == null){
            throw new IllegalArgumentException("GridView is null");
        }
        Adapter adapter = gv.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        int size = adapter.getCount();
        for (int i = 0; i < size; i++) {
            View listItem = adapter.getView(i, null, gv);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight() + DensityUtils.dp2px(context, m);
        }
        ViewGroup.LayoutParams params = gv.getLayoutParams();
        params.height = totalHeight + gv.getPaddingTop() + gv.getPaddingBottom() + 2;
        gv.setLayoutParams(params);
    }
}
