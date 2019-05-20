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

/**
 * 仿微信存储空间自定义View
 */
public class WeChatFlowPieView extends View {
    private Paint paint;
    private Paint textPaint;
    private Paint text1Paint;
    private long currentData;
    private long otherData;
    private long otherCatch;
    private int currentAngle;
    private int otherAngle;
    private int otherCatchAngle;
    //左边距
    private int RectX = 200 ;
    //定义聊天数据大圆的半径
    private int radius = 210 ;
    //定义其他大圆的半径
    private int other_radius = 200 ;
    //    原点纵坐标
    private int circleY =300;
    //    画圆起点
    private int startAngle =270;
    //    矩形大小
    private int RectSize =50;
    //    拼图与底部文字距离
    private int space =50;
    //    矩形Y坐标
    private int RectY =circleY+radius+space;

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
        int mHeight = 800;
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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth() / 2;
        //当前账号
        paint.setColor(getResources().getColor(R.color.green));
        RectF rectF = new RectF(x-radius,circleY - radius,x+radius,circleY+radius);
        canvas.drawArc(rectF,startAngle,currentAngle,true,paint);
        canvas.drawRect(RectX,RectY,RectX+RectSize,RectY+RectSize,paint);
        canvas.drawText("当前账号聊天数据",RectX+70,RectY+RectSize-10,textPaint);
        canvas.drawText(""+currentAngle,getWidth()-RectX,RectY+RectSize-10,text1Paint);

        //其他账号
        paint.setColor(getResources().getColor(R.color.e4));
        canvas.drawArc(rectF,startAngle + currentAngle,otherAngle,true,paint);
        canvas.drawRect(RectX,RectY+RectSize+20,RectX+RectSize,RectY+RectSize*2+20,paint);
        canvas.drawText("其他账号聊天数据",RectX+70,RectY+RectSize*2+10,textPaint);
        canvas.drawText(""+otherAngle,getWidth()-RectX,RectY+RectSize*2+10,text1Paint);

        //其他
        paint.setColor(getResources().getColor(R.color.cyan));
        RectF rectF1 = new RectF(x-other_radius,circleY - other_radius,x+other_radius,circleY+other_radius);
        canvas.drawArc(rectF1,startAngle + currentAngle + otherAngle,otherCatchAngle,true,paint);
        canvas.drawRect(RectX,RectY+RectSize*2+40,RectX+RectSize,RectY+RectSize*3+40,paint);
        canvas.drawText("其他（缓存）",RectX+70,RectY+RectSize*3+30,textPaint);
        canvas.drawText(""+otherCatchAngle,getWidth()-RectX,RectY+RectSize*3+30,text1Paint);
        canvas.save();
    }

    public void setData(long currentData,long otherData,long otherCatch){
        long allData = currentData + otherData + otherCatch;
        this.currentData=currentData;
        this.otherData=otherData;
        this.otherCatch=otherCatch;
        this.currentAngle = (int)(currentData % allData * 36) ;
        this.otherAngle = (int)(otherData % allData*36);
        this.otherCatchAngle = 360 - currentAngle - otherAngle;
        invalidate();
    }

}
