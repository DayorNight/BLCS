package blcs.lwb.lwbtool.manager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ls on 2017/11/25.
 *  层叠卡片
 */

public class SwipeCardLayoutManager extends RecyclerView.LayoutManager {
    Context context;
    int TRANS_Y_GAP;
    public SwipeCardLayoutManager(Context context){
        TRANS_Y_GAP= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,
                context.getResources().getDisplayMetrics());
    }
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        //1.如何实现层叠效果--cardView.layout(l,t,r,b)
        //2.如何让8个条目中的4个展示在RecylerView里面
        //1在布局layout之前，将所有的子View先全部detach掉，然后放到Scrap集合里面缓存。
        detachAndScrapAttachedViews(recycler);
        //2)只将最上面4个view添加到RecylerView容器里面
        int itemCount=getItemCount();//8个
        int bottomPosition;
        if(itemCount<3){
            bottomPosition=0;
        }else{
            bottomPosition=itemCount-3;
        }
        for(int i=bottomPosition;i<itemCount;i++){
            View view=recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view,0,0);
            int widthSpace=getWidth()-getDecoratedMeasuredWidth(view);
            int heightSpace=getWidth()-getDecoratedMeasuredHeight(view);
            //摆放cardView
            layoutDecorated(view,
                    widthSpace/2,
                    heightSpace/2,
                    widthSpace/2+getDecoratedMeasuredWidth(view),
                    heightSpace/2+getDecoratedMeasuredHeight(view));
            //层叠效果--Scale+TranslationY
            //层级的位置关系1/2/3/4
            int level=itemCount-i-1;
            if(level>0){
                if(level< MAX_SHOW_COUNT){
                    view.setTranslationY(TRANS_Y_GAP*level);
                    view.setTranslationX(TRANS_Y_GAP*level);
                    view.setScaleX(1-SCALE_GAP*level);
                    view.setScaleY(1-SCALE_GAP*level);
                }
            }else {
                view.setTranslationY(TRANS_Y_GAP*(level-1));
                view.setTranslationX(TRANS_Y_GAP*(level-1));
                view.setScaleX(1-SCALE_GAP*(level-1));
                view.setScaleY(1-SCALE_GAP*(level-1));
            }
        }
    }

    //屏幕最对同时显示几个item
    public static int  MAX_SHOW_COUNT=3;
    //没一级Scale相差0.05f，translation相差7dp左右
    public static  float SCALE_GAP;
    public static int TRANS_V_GAP;

    public static void initConfig(Context context){
        MAX_SHOW_COUNT=3;
        SCALE_GAP=0.05f;
        TRANS_V_GAP=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,context.getResources().getDisplayMetrics());
    }
}
