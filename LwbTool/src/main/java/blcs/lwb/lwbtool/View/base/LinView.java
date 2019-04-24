package blcs.lwb.lwbtool.View.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import blcs.lwb.lwbtool.R;



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
