package blcs.lwb.lwbtool.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import blcs.lwb.lwbtool.R;

public class LoupeView extends android.support.v7.widget.AppCompatImageView {
    //半径
    private float radius;
    //缩放大小
    private int SCALE ;
    private ShapeDrawable shapeDrawable;
    private Matrix matrix;

    private boolean SHOW = false;

    private Bitmap bitmap;
    private float widthScale = 0;
    private float hightScale = 0 ;

    public LoupeView(Context context) {
        super(context);
    }

    public LoupeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //禁用硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //设置属性
        initAttr(context,attrs);
        //设置画笔
        initPaint();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoupeView);
        radius = typedArray.getInt(R.styleable.LoupeView_radius, 100);
        SCALE = typedArray.getInt(R.styleable.LoupeView_scale, 3);
        typedArray.recycle();
    }

    private void initPaint() {
        matrix = new Matrix();
        BitmapDrawable drawable = (BitmapDrawable) getDrawable();
        bitmap = drawable.getBitmap();

        shapeDrawable = new ShapeDrawable(new OvalShape());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (wMode){
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                widthScale = wSize / (float)bitmap.getWidth();
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (hMode){
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                hightScale = hSize / (float)bitmap.getHeight();
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        Bitmap scaledBitmap =null;
        if(widthScale!=0){//适配match
            float width = bitmap.getWidth() * SCALE * widthScale;
            float height = bitmap.getHeight() * SCALE * hightScale;
            scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int)width, (int)height, true);
        }else{
            scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * SCALE, bitmap.getHeight() * SCALE, true);
        }
        BitmapShader bitmapShader = new BitmapShader(scaledBitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        shapeDrawable.getPaint().setShader(bitmapShader);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制放大镜
        if(SHOW) shapeDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float left = event.getX() - radius;
        float top = event.getY()-radius;
        float right = event.getX()+radius;
        float bottom = event.getY()+radius;
        matrix.setTranslate(radius - event.getX() * SCALE, radius - event.getY() * SCALE);
        shapeDrawable.getPaint().getShader().setLocalMatrix(matrix);
        shapeDrawable.setBounds((int)left,(int)top,(int)right,(int)bottom);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                SHOW = true;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                SHOW = false;
                break;
        }
        invalidate();
        return true;
    }
}
