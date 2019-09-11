package blcs.lwb.lwbtool.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import blcs.lwb.lwbtool.R;

/**
 * 刮刮乐
 * 注：前景图太大可能会出现问题
 */
public class ScratchCardView extends android.support.v7.widget.AppCompatImageView {
    //橡皮檫大小
    private float size;
    //前景图
    private Bitmap preBitmap = null;
    private Paint paint;
    private Path path;
    private float preX;
    private float preY;
    private float widthScale;
    private float hightScale;

    public ScratchCardView(Context context) {
        super(context);
    }

    public ScratchCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //禁用硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //属性设置
        initAttrs(context, attrs);
        //画笔设置
        initPaint();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScratchCard);
        size = typedArray.getFloat(R.styleable.ScratchCard_size, 50f);
        Drawable drawable = typedArray.getDrawable(R.styleable.ScratchCard_preBackground);
        if (drawable != null) preBitmap = ((BitmapDrawable) drawable).getBitmap();
        typedArray.recycle();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(size);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (wMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                if(preBitmap!=null) widthScale = wSize / (float)preBitmap.getWidth();
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (hMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                if(preBitmap!=null) hightScale = hSize / (float)preBitmap.getHeight();
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        //前景图填充满宽高
        if(preBitmap!=null) {
            preBitmap = Bitmap.createScaledBitmap(preBitmap, (int)(preBitmap.getWidth() * widthScale), (int)(preBitmap.getHeight() * hightScale), true);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //新建图层
        int canvasId = canvas.saveLayer(0f, 0f, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);

        //绘制前景图
        if (preBitmap == null) {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);
        } else {
            canvas.drawBitmap(preBitmap, 0f, 0f, paint);
        }

        //设置混合模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        //绘制手势图
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

        //清空混合模式
        paint.setXfermode(null);
        canvas.restoreToCount(canvasId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = event.getX();
                preY = event.getY();
                path.moveTo(preX, preY);
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (preX + event.getX()) / 2;
                float endY = (preY + event.getY()) / 2;
                path.quadTo(preX, preY, endX, endY);
                preX = event.getX();
                preY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
