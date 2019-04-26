package blcs.lwb.lwbtool.View.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 推荐文章：https://www.jianshu.com/p/146e5cec4863
 */
public class LinView extends View {

    private Paint paint;

    // 如果View是在Java代码里面new的，则调用第一个构造函数
    public LinView(Context context) {
        super(context);
    }

    /**
     *  如果View是在.xml里声明的，则调用第二个构造函数
     *  自定义属性是从AttributeSet参数传进来的
     */
    public LinView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.custom_view);
//        int color = typedArray.getColor(R.styleable.custom_view_color_text, Color.BLACK);
//        String content = typedArray.getString(R.styleable.custom_view_content);
//        typedArray.recycle();
        initPain();
    }



    /**
     * 不会自动调用
     * 一般是在第二个构造函数里主动调用
     * 如View有style属性时.
     */
    public LinView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //API21之后才使用
    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
//    public LinView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


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
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    private void initPain() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(48);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipRect(0,0,getWidth(),500);
        canvas.drawColor(Color.GRAY);
//        直线
        canvas.drawLine(0,200,getWidth(),200,paint);
//        矩形
        canvas.drawRect(50,50,150,150,paint);
//        画圆
        canvas.drawCircle(300,100,50,paint);
//        椭圆
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawOval(400,50,600,150,paint);
        }
//        画圆角矩形
        RectF rectF = new RectF(700,50,800,150);
        canvas.drawRoundRect(rectF, 30, 20, paint);

//      画圆弧
        RectF rectArc = new RectF(850,50,950,150);
        canvas.drawArc(rectArc,0,90,true,paint);
//      画圆弧
        RectF rectArc1 = new RectF(850,50,950,150);
        canvas.drawArc(rectArc1,180,90,false,paint);
////      画三角形
        Path path = new Path();
        path.moveTo(50, 250);
        path.lineTo(150, 250);
        path.lineTo(50, 350);
        path.close();
        canvas.drawPath(path, paint);
        canvas.save();

//        文本
        canvas.drawText("文本",200,300,paint);
        canvas.translate(1000,300);

    }
}
