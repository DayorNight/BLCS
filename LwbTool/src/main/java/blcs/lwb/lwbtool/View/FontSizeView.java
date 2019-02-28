package blcs.lwb.lwbtool.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.utils.DensityUtils;

/**
 * TODO 仿微信字体大小调整
 * 自定义属性：
 * lineWidth        线条粗细
 * lineColor        线条颜色
 * totalCount       线条格数
 * circleColor      球型颜色
 * circleRadius     球型颜色半径
 * textFontColor    文字颜色
 * smallSize        小“A” 字体大小
 * standerSize      “标准” 字体大小
 * bigSize          大“A” 字体大小
 * defaultPosition  默认位置
 */
public class FontSizeView extends View {

    private int defaultLineColor = Color.rgb(33, 33, 33);
    private int defaultLineWidth;
    private int defaultMax = 5;
    private int defaultCircleColor = Color.WHITE;
    private int defaultCircleRadius;
    // 当前所在位置
    private int currentProgress;

    // 默认位置
    private int defaultPosition = 1;

    // 一共有多少格
    private int max = 7;
    // 线条颜色
    private int lineColor = Color.BLACK;
    // 线条粗细
    private int lineWidth;

    //字体颜色
    private int textColor = Color.BLACK;
    //字体大小
    private int smallSize = 14;
    private int standerSize = 16;
    private int bigSize = 28;

    // 圆半径
    private int circleRadius;
    private int circleColor = Color.WHITE;
    // 一段的宽度，根据总宽度和总格数计算得来
    private int itemWidth;
    // 控件的宽高
    private int height;
    private int width;
    // 画笔
    private Paint mLinePaint;
    private Paint mTextPaint;
    private Paint mText1Paint;
    private Paint mText2Paint;
    private Paint mCirclePaint;
    // 滑动过程中x坐标
    private float currentX = 0;
    // 有效数据点
    private List<Point> points = new ArrayList<>();

    private float circleY;
    private float textScaleX;
    private float text1ScaleX;
    private float text2ScaleX;

    public FontSizeView(Context context) {
        this(context, null);
    }

    public FontSizeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        // initDefault
        defaultLineWidth = DensityUtils.dp2px(context, 2);
        defaultCircleRadius = DensityUtils.dp2px(context, 35);
        lineWidth = DensityUtils.dp2px(context, 1);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FontSizeView);
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initCustomAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
        // 初始化画笔
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(lineColor);
        mLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mLinePaint.setStrokeWidth(lineWidth);

        //文字画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(textColor);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mTextPaint.setStrokeWidth(DensityUtils.dp2px(context, 1));
        mTextPaint.setTextSize(DensityUtils.sp2px(context, smallSize));
        textScaleX = mTextPaint.measureText("A");
        //文字画笔
        mText1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mText1Paint.setColor(textColor);
        mText1Paint.setStyle(Paint.Style.FILL_AND_STROKE);
        mText1Paint.setTextSize(DensityUtils.sp2px(context, bigSize));
        text1ScaleX = mText1Paint.measureText("A");

        //文字画笔
        mText2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mText2Paint.setColor(textColor);
        mText2Paint.setStyle(Paint.Style.FILL_AND_STROKE);
        mText2Paint.setTextSize(DensityUtils.sp2px(context, standerSize));
        text2ScaleX = mText2Paint.measureText("标准");

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(circleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        // 设置阴影效果
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mCirclePaint.setShadowLayer(2, 0, 0, Color.rgb(33, 33, 33));
    }

    private void initCustomAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.FontSizeView_lineColor) {
            lineColor = typedArray.getColor(attr, defaultLineColor);
        } else if (attr == R.styleable.FontSizeView_circleColor) {
            circleColor = typedArray.getColor(attr, defaultCircleColor);
        } else if (attr == R.styleable.FontSizeView_lineWidth) {
            lineWidth = typedArray.getDimensionPixelSize(attr, defaultLineWidth);
        } else if (attr == R.styleable.FontSizeView_circleRadius) {
            circleRadius = typedArray.getDimensionPixelSize(attr, defaultCircleRadius);
        } else if (attr == R.styleable.FontSizeView_totalCount) {
            max = typedArray.getInteger(attr, defaultMax);
        } else if (attr == R.styleable.FontSizeView_textFontColor) {
            textColor = typedArray.getColor(attr, textColor);
        } else if (attr == R.styleable.FontSizeView_smallSize) {
            smallSize = typedArray.getInteger(attr, smallSize);
        } else if (attr == R.styleable.FontSizeView_standerSize) {
            standerSize = typedArray.getInteger(attr, standerSize);
        } else if (attr == R.styleable.FontSizeView_bigSize) {
            bigSize = typedArray.getInteger(attr, bigSize);
        }else if (attr == R.styleable.FontSizeView_defaultPosition) {
            defaultPosition = typedArray.getInteger(attr, defaultPosition);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
        circleY = height / 2;
        // 横线宽度是总宽度-2个圆的半径
        itemWidth = (w - 2 * circleRadius) / max;
        // 把可点击点保存起来
        for (int i = 0; i <= max; i++) {
            points.add(new Point(circleRadius + i * itemWidth, height / 2));
        }
        //初始刻度
        currentX = points.get(defaultPosition).x;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画字
        canvas.drawText("A", points.get(0).x - textScaleX / 2, height / 2 - 50, mTextPaint);

        //画字
        canvas.drawText("标准", points.get(1).x - text2ScaleX / 2, height / 2 - 50, mText2Paint);

        //画字
        canvas.drawText("A", points.get(points.size() - 1).x - text1ScaleX / 2, height / 2 - 50, mText1Paint);

        // 先画中间的横线
        canvas.drawLine(points.get(0).x, height / 2, points.get(points.size() - 1).x, height / 2, mLinePaint);
        // 绘制刻度
        for (Point point : points) {
            canvas.drawLine(point.x + 1, height / 2 - 20, point.x + 1, height / 2 + 20, mLinePaint);
        }

        // 画圆
        if (currentX < circleRadius) {
            currentX = circleRadius;
        }
        if (currentX > width - circleRadius) {
            currentX = width - circleRadius;
        }

        // 实体圆
        canvas.drawCircle(currentX + 1, circleY, circleRadius, mCirclePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //回到最近的一个刻度点
                Point targetPoint = getNearestPoint(currentX);
                if (targetPoint != null) {
                    // 最终
                    currentX = points.get(currentProgress).x;
                    invalidate();
                }
                if (onChangeCallbackListener != null) {
                    onChangeCallbackListener.onChangeListener(currentProgress);
                }
                break;
        }
        return true;
    }

    /**
     * 获取最近的刻度
     */
    private Point getNearestPoint(float x) {
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            if (Math.abs(point.x - x) < itemWidth / 2) {
                currentProgress = i;
                return point;
            }
        }
        return null;
    }

    public void setChangeCallbackListener(OnChangeCallbackListener listener) {
        this.onChangeCallbackListener = listener;
    }

    private OnChangeCallbackListener onChangeCallbackListener;

    public interface OnChangeCallbackListener {
        void onChangeListener(int position);
    }

    public void setDefaultPosition(int position){
        defaultPosition=position;
        if (onChangeCallbackListener != null) {
            onChangeCallbackListener.onChangeListener(defaultPosition);
        }
        invalidate();
    }
}
