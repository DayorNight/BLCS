package blcs.lwb.lwbtool.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;


import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.utils.LogUtils;

public class DragFloatButton extends FloatingActionButton {

    private float mLastRawX;
    private float mLastRawY;
    private final String TAG = "AttachButton";
    private boolean isDrag = false;
    private int mRootMeasuredWidth = 0;
    private int mRootMeasuredHeight = 0;
    private int mRootTopY = 0;
    private boolean customIsAttach;
    private boolean customIsDrag;

    public DragFloatButton(Context context) {
        this(context, null);
    }

    public DragFloatButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        initAttrs(context, attrs);
    }

    public DragFloatButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    /**
     * 初始化自定义属性
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray mTypedAttay = context.obtainStyledAttributes(attrs, R.styleable.AttachButton);
        customIsAttach = mTypedAttay.getBoolean(R.styleable.AttachButton_customIsAttach, true);
        customIsDrag = mTypedAttay.getBoolean(R.styleable.AttachButton_customIsDrag, true);
        mTypedAttay.recycle();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //判断是否需要滑动
        if (customIsDrag) {
            //当前手指的坐标
            float mRawX = ev.getRawX();
            float mRawY = ev.getRawY();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN://手指按下

                    isDrag = false;
                    //记录按下的位置
                    mLastRawX = mRawX;
                    mLastRawY = mRawY;
                    ViewGroup mViewGroup = (ViewGroup) getParent();
                    if (mViewGroup != null) {
                        int[] location = new int[2];
                        mViewGroup.getLocationInWindow(location);
                        //获取父布局的高度
                        mRootMeasuredHeight = mViewGroup.getMeasuredHeight();
                        mRootMeasuredWidth = mViewGroup.getMeasuredWidth();
                        //获取父布局顶点的坐标
                        mRootTopY = location[1];
                    }
                    break;
                case MotionEvent.ACTION_MOVE://手指滑动
                    if (mRawX >= 0 && mRawX <= mRootMeasuredWidth && mRawY >= mRootTopY && mRawY <= (mRootMeasuredHeight + mRootTopY)) {
                        //手指X轴滑动距离
                        float differenceValueX = mRawX - mLastRawX;
                        //手指Y轴滑动距离
                        float differenceValueY = mRawY - mLastRawY;
                        //判断是否为拖动操作
                        if (!isDrag) {
                            if (Math.sqrt(differenceValueX * differenceValueX + differenceValueY * differenceValueY) < 2) {
                                isDrag = false;
                            } else {
                                isDrag = true;
                            }
                        }
                        //获取手指按下的距离与控件本身X轴的距离
                        float ownX = getX();
                        //获取手指按下的距离与控件本身Y轴的距离
                        float ownY = getY();
                        //理论中X轴拖动的距离
                        float endX = ownX + differenceValueX;
                        //理论中Y轴拖动的距离
                        float endY = ownY + differenceValueY;
                        //X轴可以拖动的最大距离
                        float maxX = mRootMeasuredWidth - getWidth();
                        //Y轴可以拖动的最大距离
                        float maxY = mRootMeasuredHeight - getHeight();
                        //X轴边界限制
                        endX = endX < 0 ? 0 : endX > maxX ? maxX : endX;
                        //Y轴边界限制
                        endY = endY < 0 ? 0 : endY > maxY ? maxY : endY;
                        //开始移动
                        setX(endX);
                        setY(endY);
                        //记录位置
                        mLastRawX = mRawX;
                        mLastRawY = mRawY;
                    }

                    break;
                case MotionEvent.ACTION_UP://手指离开
                    //根据自定义属性判断是否需要贴边
                    if (customIsAttach) {
                        //判断是否为点击事件
                        if (isDrag) {
                            float center = mRootMeasuredWidth / 2;
                            //自动贴边
                            if (mLastRawX <= center) {
                                //向左贴边
                                animate().setInterpolator(new DecelerateInterpolator())
                                        .setDuration(500)
                                        .x(-getWidth()/2)
                                        .start();
                            } else {
                                //向右贴边
                                animate().setInterpolator(new DecelerateInterpolator())
                                        .setDuration(500)
                                        .x(mRootMeasuredWidth - getWidth()/2)
                                        .start();
                            }
                            if(getY()==0){
                                animate().yBy(-getHeight()/2);
                            }else if(getY()==(mRootMeasuredHeight - getHeight())){
                                animate().yBy(getHeight()/2);
                            }
                        }
                    }
                    isDrag =false;
                    checkAttach();
                    break;
            }
        }
        //是否拦截事件
        return isDrag ? isDrag : super.onTouchEvent(ev);
    }

    /**
     * 未操作时 贴边半隐藏
     */
    private void checkAttach() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isDrag==true) return;
                //如果贴边 则半隐藏
                if(getY()==0){
                    animate().yBy(-getHeight()/2);
                }else if(getY()==(mRootMeasuredHeight - getHeight())){
                    animate().yBy(getHeight()/2);
                }
                if(getX()==0){
                    animate().xBy(-getWidth()/2);
                }else if(getX()==(mRootMeasuredWidth-getWidth())){
                    animate().xBy(getWidth()/2);
                }

            }
        }, 3000);
    }

    /**
     * 设置是否可以吸附
     */
    public void setAttach(boolean isAttach){
        customIsAttach = isAttach;
    }
    /**
     * 设置是否可以拖动
     */
    public void setDrag(boolean isDrag){
        customIsDrag = isDrag;
    }
}
