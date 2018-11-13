package blcs.lwb.lwbtool;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewUtils {

    private static DividerItemDecoration decoration;

    /**
     * 切换横竖布局
     * @param view
     * @param manager
     * @param orientation
     */
    public static void changeLayouot(Context context, RecyclerView view, LinearLayoutManager manager, int orientation){
        //设置为垂直布局，这也是默认的
        manager.setOrientation(orientation);
        //设置布局管理器
        view.setLayoutManager(manager);
        if(decoration==null){
            decoration = new DividerItemDecoration(context,orientation);
        }else{
            decoration.setOrientation(orientation);
        }
        //设置分隔线
        view.addItemDecoration(decoration);
    }
}
