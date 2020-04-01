package blcs.lwb.lwbtool.View.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.utils.LogUtils;

/**
 * 年龄范围选择
 *
 * @Author BLCS
 * @Time 2020/3/31 11:08
 */
public class SelectedRangeView extends View {
    private Paint grayPaint, purplePaint, circlePaint;
    private float endX;
    private float centerY;
    private Paint shadowPaint;
    private float distance;
    private boolean checkA = true;
    private boolean checkB = true;

    //可自定义属性
    private float circleRadius ; //圆半径
    private int starValue ; //起始值
    private int AcircleValue; //第一个圆X轴位置
    private int BcircleValue; //第二个圆X轴位置
    private int valueRange;//设置进度条取值范围
    private float progressBarHeight ;//进度条高度
    private int progressColor ;//进度条颜色
    private int selectedColor ; //选中范围颜色
    private int circleCorlor ;//圆的颜色
    private int circleShadowCorlor ; //阴影的颜色
    private float ShadowWidth ; //阴影辐射范围


    //第一个圆X轴位置
    private float AcircleX ;
    //第二个圆X轴位置
    private float BcircleX;
    public SelectedRangeView(Context context) {
        super(context);
        init();
    }
    public SelectedRangeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 需禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        initAttrs(context, attrs);
        init();
    }

    /**
     * 定义属性
     */
    private void initAttrs(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.view_selected_range);
        circleRadius = typedArray.getDimension(R.styleable.view_selected_range_circleR, 60);
        AcircleValue = typedArray.getInteger(R.styleable.view_selected_range_AcircleValue, 0);
        BcircleValue = typedArray.getInteger(R.styleable.view_selected_range_BcircleValue, 0);
        valueRange = typedArray.getInteger(R.styleable.view_selected_range_valueRange, 100);
        progressBarHeight = typedArray.getDimension(R.styleable.view_selected_range_progressBarHeight, 10);
        progressColor = typedArray.getColor(R.styleable.view_selected_range_progressColor,getResources().getColor(R.color.gray));
        selectedColor = typedArray.getColor(R.styleable.view_selected_range_selectedColor,getResources().getColor(R.color.custom_progress_purple_header));
        circleCorlor = typedArray.getColor(R.styleable.view_selected_range_circleCorlor,getResources().getColor(R.color.white));
        circleShadowCorlor = typedArray.getColor(R.styleable.view_selected_range_circleShadowCorlor,getResources().getColor(R.color.black));
        ShadowWidth = typedArray.getDimension(R.styleable.view_selected_range_shadowWidth,30f);
        starValue = typedArray.getInteger(R.styleable.view_selected_range_starValue,0);
        typedArray.recycle();
        AcircleX = circleRadius;
    }

    private void init() {

        //进度条
        grayPaint = new Paint();
        grayPaint.setColor(progressColor);
        grayPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //选中区域
        purplePaint = new Paint();
        purplePaint.setColor(selectedColor);
        purplePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //圆
        circlePaint = new Paint();
        circlePaint.setColor(circleCorlor);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //阴影
        shadowPaint = new Paint();
        shadowPaint.setColor(circleShadowCorlor);
        shadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(ShadowWidth, BlurMaskFilter.Blur.OUTER);
        shadowPaint.setMaskFilter(blurMaskFilter);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        int mWidth = 400;
        int mHeight = 400;
        // 当布局参数设置为wrap_content时，设置默认值
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }
        // 一个进度距离
        distance = (getMeasuredWidth() - 4 * circleRadius) / (float) valueRange;
        //设置A圆初始值
        if (AcircleValue != 0) AcircleX = circleRadius + AcircleValue * distance;
        if (BcircleValue != 0) {//设置B圆 初始值
            BcircleX = circleRadius + BcircleValue * distance;
        }else {//默认最大值
            BcircleX = getMeasuredWidth()-circleRadius;
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.e("======"+circleRadius);
        endX = getWidth() - circleRadius;
        //Y轴显示位置
        centerY = getHeight() / 2;
        //灰色线
        RectF rectF = new RectF(circleRadius, centerY - progressBarHeight, endX, centerY + progressBarHeight);
        canvas.drawRoundRect(rectF, progressBarHeight, progressBarHeight, grayPaint);

        //紫色线
        RectF purpleRectF = new RectF(AcircleX, centerY - progressBarHeight, BcircleX, centerY + progressBarHeight);
        canvas.drawRoundRect(purpleRectF, progressBarHeight, progressBarHeight, purplePaint);

        //圆
        canvas.drawCircle(AcircleX, centerY, circleRadius, circlePaint);
        canvas.drawCircle(AcircleX, centerY, circleRadius, shadowPaint);

        //第二圆
        canvas.drawCircle(BcircleX, centerY, circleRadius, circlePaint);
        canvas.drawCircle(BcircleX, centerY, circleRadius, shadowPaint);

        //计算当前进度
        if (checkA && (AcircleX - circleRadius) >= 0) {
            float posA = (AcircleX - circleRadius) / distance;
            if (onChangeListener != null) onChangeListener.getValueA(Math.round(posA)+starValue);
        }
        if (checkB) {
            float posB = (BcircleX - 3 * circleRadius) / distance;
            if (onChangeListener != null) onChangeListener.getValueB(Math.round(posB)+starValue);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //判断选中哪个圆++
                if (AcircleX - circleRadius <= currentX && currentX <= AcircleX + circleRadius) { //点击第一个圆
                    checkA = true;
                    checkB = false;
                } else if (BcircleX -circleRadius < currentX && currentX < BcircleX + circleRadius) {//点击第二个圆
                    checkB = true;
                    checkA = false;
                } else {
                    checkA = checkB = false;
                }
            case MotionEvent.ACTION_MOVE:
                if (checkA && currentX <= BcircleX - circleRadius * 2 && circleRadius < currentX) {
                    AcircleX = currentX;
                } else if (checkB && AcircleX + circleRadius * 2 <= currentX && currentX < endX) {
                    BcircleX = currentX;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    public interface OnChangeListener {
        void getValueA(int value);

        void getValueB(int value);
    }

    public OnChangeListener onChangeListener;

    public void addOnChangeListener(OnChangeListener listener) {
        onChangeListener = listener;
    }

}
