package blcs.lwb.lwbtool.View.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import blcs.lwb.lwbtool.R;
public class WeChatFlowPieView extends View {
    private Paint paint;
    private Paint textPaint;
    private Paint text1Paint;
    private int currentAngle;
    private int otherAngle;
    private int otherCatchAngle;

    public WeChatFlowPieView(Context context) {
        super(context);
    }

    public WeChatFlowPieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
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
        int mHeight = 1200;
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

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(getResources().getColor(R.color.cyan));
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setColor(getResources().getColor(R.color.gray));
        textPaint.setTextSize(40);
        text1Paint = new Paint();
        text1Paint.setStyle(Paint.Style.FILL_AND_STROKE);
        text1Paint.setColor(getResources().getColor(R.color.gray));
        text1Paint.setTextSize(50);
        text1Paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    private int x = 200 ;
    //定义聊天数据大圆的半径
    private int radius = 210 ;
    //定义其他大圆的半径
    private int other_radius = 200 ;
    private int leftText = x + 70 ;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int y = getHeight() / 2;


        RectF rectF1 = new RectF(getWidth()/2-x,100,getWidth()/2+x,300+x);
        canvas.drawArc(rectF1,270 + currentAngle + otherAngle,otherCatchAngle,true,paint);

        //其他
        canvas.drawRect(x,y-50+210,x+50,y+210,paint);
        canvas.drawText("其他（缓存）",leftText,y+200,textPaint);
        canvas.drawText(""+otherCatchAngle,getWidth()-x,y+200,text1Paint);

        //当前账号
        paint.setColor(getResources().getColor(R.color.green));
        RectF rectF = new RectF(getWidth()/2-210,300 -210,getWidth()/2+210,300+210);
        canvas.drawArc(rectF,270,currentAngle,true,paint);
        canvas.drawRect(x,y-50+70,x+50,y+70,paint);
        canvas.drawText("当前账号聊天数据",leftText,y+60,textPaint);
        canvas.drawText(""+currentAngle,getWidth()-x,y+60,text1Paint);

        //其他账号
        paint.setColor(getResources().getColor(R.color.e4));
        canvas.drawArc(rectF,270 + currentAngle,otherAngle,true,paint);
        canvas.drawRect(x,y-50+140,x+50,y+140,paint);
        canvas.drawText("其他账号聊天数据",leftText,y+130,textPaint);
        canvas.drawText(""+otherAngle,getWidth()-x,y+130,text1Paint);
        canvas.save();
    }


    public void setData(long currentData,long otherData,long otherCatch){

        long allData = currentData + otherData + otherCatch;
        this.currentAngle = (int)(currentData % allData * 36) ;
        this.otherAngle = (int)(otherData % allData*36);
        this.otherCatchAngle = 360 - currentAngle - otherAngle;
        invalidate();
    }

}
