package blcs.lwb.lwbtool.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;

import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.utils.DensityUtils;

/**
 * 红色圆点 TextView
 * @Author BLCS
 * @Time 2020/3/25 12:33
 */
public class RedCircleTextView extends AppCompatTextView {

    private Paint mPaint;
    private int mNormalSize;
    private Context context;

    public RedCircleTextView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public RedCircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public RedCircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        mNormalSize = DensityUtils.px2dip(context,18.4f);
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        setTextColor(Color.WHITE);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 13.6f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (getText().length() == 0) {
            // 没有字符，就在本View中心画一个小圆点
            int l = (getMeasuredWidth() -  DensityUtils.px2dip(context,10)) / 2;
            int t = l;
            int r = getMeasuredWidth() - l;
            int b = r;
            canvas.drawOval(new RectF(l, t, r, b), mPaint);
        } else if (getText().length() == 1) {
            canvas.drawOval(new RectF(0, 0, mNormalSize, mNormalSize), mPaint);
        } else if (getText().length() > 1) {
            canvas.drawRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), getMeasuredHeight() / 2, getMeasuredHeight() / 2, mPaint);
        }
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = mNormalSize;
        int height = mNormalSize;
        if (getText().length() > 1) {
            width = mNormalSize +  DensityUtils.px2dip(context,(getText().length() - 1) * 10);
        }
        setMeasuredDimension(width, height);
    }
}
