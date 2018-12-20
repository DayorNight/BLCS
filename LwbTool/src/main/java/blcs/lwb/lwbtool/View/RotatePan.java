package blcs.lwb.lwbtool.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.R;

/**
 * 描述：
 * 作者：Nipuream
 * 时间: 2016-08-16 10:18
 * 邮箱：nipuream@163.com
 */
public class RotatePan extends View {

    private Context context;

    private int panNum = 0;

    private Paint dPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint sPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int InitAngle = 0;
    private int radius = 0;
    private int verPanRadius;
    private int diffRadius;
    public static final int FLING_VELOCITY_DOWNSCALE = 4;
    private Integer[] images;
    private String[] strs;
    private List<Bitmap> bitmaps = new ArrayList<>();
    private GestureDetectorCompat mDetector;
    private ScrollerCompat scroller;
    private int screenWidth, screeHeight;

    //旋转一圈所需要的时间
    private static final long ONE_WHEEL_TIME = 500;

    public RotatePan(Context context) {
        this(context, null);
    }

    public RotatePan(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotatePan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        screeHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;

        mDetector = new GestureDetectorCompat(context, new RotatePanGestureListener());
        scroller = ScrollerCompat.create(context);

        checkPanState(context, attrs);
        InitAngle = 360 / panNum;
        verPanRadius = 360 / panNum;
        diffRadius = verPanRadius / 2;
        dPaint.setColor(Color.rgb(255, 133, 132));
        sPaint.setColor(Color.rgb(254, 104, 105));
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(dip2px(context, 16));
        setClickable(true);

        for (int i = 0; i < panNum; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), images[i]);
            bitmaps.add(bitmap);
        }
    }

    private void checkPanState(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.luckpan);
        panNum = typedArray.getInteger(R.styleable.luckpan_pannum, 0);
        if (360 % panNum != 0)
            throw new RuntimeException("can't split pan for all icon.");
        int nameArray = typedArray.getResourceId(R.styleable.luckpan_names, -1);
        if (nameArray == -1) throw new RuntimeException("Can't find pan name.");
        strs = context.getResources().getStringArray(nameArray);
        int iconArray = typedArray.getResourceId(R.styleable.luckpan_icons, -1);
        if (iconArray == -1) throw new RuntimeException("Can't find pan icon.");

        String[] iconStrs = context.getResources().getStringArray(iconArray);
        List<Integer> iconLists = new ArrayList<>();
        for (int i = 0; i < iconStrs.length; i++) {
            iconLists.add(context.getResources().getIdentifier(iconStrs[i], "mipmap", context.getPackageName()));
        }

        images = iconLists.toArray(new Integer[iconLists.size()]);
        typedArray.recycle();
        if (strs == null || images == null)
            throw new RuntimeException("Can't find string or icon resources.");
        if (strs.length != panNum || images.length != panNum)
            throw new RuntimeException("The string length or icon length  isn't equals panNum.");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int MinValue = Math.min(screenWidth, screeHeight);
        MinValue -= dip2px(context, 38) * 2;
        setMeasuredDimension(MinValue, MinValue);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;

        int MinValue = Math.min(width, height);

        radius = MinValue / 2;

        RectF rectF = new RectF(getPaddingLeft(), getPaddingTop(), width, height);

        int angle = (panNum % 4 == 0) ? InitAngle : InitAngle - diffRadius;

        for (int i = 0; i < panNum; i++) {
            if (i % 2 == 0) {
                canvas.drawArc(rectF, angle, verPanRadius, true, dPaint);
            } else {
                canvas.drawArc(rectF, angle, verPanRadius, true, sPaint);
            }
            angle += verPanRadius;
        }

        for (int i = 0; i < panNum; i++) {
            drawIcon(width / 2, height / 2, radius, (panNum % 4 == 0) ? InitAngle + diffRadius : InitAngle, i, canvas);
            InitAngle += verPanRadius;
        }

        for (int i = 0; i < panNum; i++) {
            drawText((panNum % 4 == 0) ? InitAngle + diffRadius + (diffRadius * 3 / 4) : InitAngle + diffRadius, strs[i], 2 * radius, textPaint, canvas, rectF);
            InitAngle += verPanRadius;
        }
    }

    private void drawText(float startAngle, String string, int mRadius, Paint mTextPaint, Canvas mCanvas, RectF mRange) {
        Path path = new Path();

        path.addArc(mRange, startAngle, verPanRadius);
        float textWidth = mTextPaint.measureText(string);
        double set = mRadius * Math.PI / panNum / 2;
        float hOffset;
        if(panNum==12){
            hOffset = (float) (set- textWidth / 3);
        }else if(panNum % 8 == 0||panNum  == 11){
            hOffset = (float) (set);
        }else if (panNum % 4 == 0) {
            hOffset = (float) (set+ textWidth / 2);
        } else {
            hOffset = (float) (set - textWidth / 2);
        }
        //圆弧的垂直偏移
        float vOffset = mRadius / 2 / 6;

        mCanvas.drawTextOnPath(string, path, hOffset, vOffset, mTextPaint);
    }

    private void drawIcon(int xx, int yy, int mRadius, float startAngle, int i, Canvas mCanvas) {

        int imgWidth = mRadius / 4;

        float angle = (float) Math.toRadians(verPanRadius + startAngle);

        //确定图片在圆弧中 中心点的位置
        float x = (float) (xx + (mRadius / 2 + mRadius / 12) * Math.cos(angle));
        float y = (float) (yy + (mRadius / 2 + mRadius / 12) * Math.sin(angle));

        // 确定绘制图片的位置
        RectF rect = new RectF(x - imgWidth * 2 / 4, y - imgWidth * 2 / 4, x + imgWidth
                * 2 / 4, y + imgWidth * 2 / 4);

        Bitmap bitmap = bitmaps.get(i);
        mCanvas.drawBitmap(bitmap, null, rect, null);
    }

    /**
     * 设置图片
     *
     * @param imgs
     */
    private void setImages(List<String> imgs) {
        List<Integer> iconLists = new ArrayList<>();
        List<Bitmap> bitmapS = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            iconLists.add(context.getResources().getIdentifier(imgs.get(i), "mipmap", context.getPackageName()));
        }
        Integer[] images = iconLists.toArray(new Integer[iconLists.size()]);
        for (int i = 0; i < imgs.size(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), images[i]);
            bitmapS.add(bitmap);
        }
        this.bitmaps = bitmapS;
//        this.invalidate();
    }

    /**
     * 设置项目名称
     *
     * @param item
     */
    public void setStr(List<String> item, List<String> imgs) {

        if(item.size()<1){
            setVisibility(GONE);
            Toast.makeText(context,"数量不能小于1",Toast.LENGTH_SHORT).show();
            return;
        }
        if (getVisibility()>5){
            setVisibility(VISIBLE);
        }
        setItem(item.size());
        setImages(imgs);
        String[] strings = new String[item.size()];
        for (int i = 0; i < item.size(); i++) {
            strings[i] = item.get(i);
        }
        this.strs = strings;
        this.invalidate();
    }

    /**
     * 设置项目数量
     * @param item
     */
    private void setItem(int item) {

        InitAngle = 360 / item;
        verPanRadius = 360 / item;
        diffRadius = verPanRadius / 2;
        this.panNum = item;
    }


    /**
     * 开始转动
     *
     * @param pos 如果 pos = -1 则随机，如果指定某个值，则转到某个指定区域
     */
    protected void startRotate(int pos) {

        //Rotate lap.
        int lap = (int) (Math.random() * 12) + 4;

        //Rotate angle.
        int angle = 0;
        if (pos < 0) {
            angle = (int) (Math.random() * 360);
        } else {
            int initPos = queryPosition();
            if (pos > initPos) {
                angle = (pos - initPos) * verPanRadius;
                lap -= 1;
                angle = 360 - angle;
            } else if (pos < initPos) {
                angle = (initPos - pos) * verPanRadius;
            } else {
                //nothing to do.
            }
        }

        //All of the rotate angle.
        int increaseDegree = lap * 360 + angle;
        long time = (lap + angle / 360) * ONE_WHEEL_TIME;
        int DesRotate = increaseDegree + InitAngle;

        //TODO 为了每次都能旋转到转盘的中间位置
        int offRotate = DesRotate % 360 % verPanRadius;
        DesRotate -= offRotate;
        DesRotate += diffRadius;

        ValueAnimator animtor = ValueAnimator.ofInt(InitAngle, DesRotate);
        animtor.setInterpolator(new AccelerateDecelerateInterpolator());
        animtor.setDuration(time);
        animtor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int updateValue = (int) animation.getAnimatedValue();
                InitAngle = (updateValue % 360 + 360) % 360;
                ViewCompat.postInvalidateOnAnimation(RotatePan.this);
            }
        });

        animtor.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (((LuckPanLayout) getParent()).getAnimationEndListener() != null) {
                    ((LuckPanLayout) getParent()).setStartBtnEnable(true);
                    ((LuckPanLayout) getParent()).setDelayTime(LuckPanLayout.DEFAULT_TIME_PERIOD);
                    ((LuckPanLayout) getParent()).getAnimationEndListener().endAnimation(queryPosition());
                }
            }
        });
        animtor.start();
    }


    private int queryPosition() {
        InitAngle = (InitAngle % 360 + 360) % 360;
        int pos = InitAngle / verPanRadius;
        if (panNum == 4) pos++;
        return calcumAngle(pos);
    }

    private int calcumAngle(int pos) {
        if (pos >= 0 && pos <= panNum / 2) {
            pos = panNum / 2 - pos;
        } else {
            pos = (panNum - pos) + panNum / 2;
        }
        return pos;
    }


    @Override
    protected void onDetachedFromWindow() {
        clearAnimation();
        if (getParent() instanceof LuckPanLayout) {
            ((LuckPanLayout) getParent()).getHandler().removeCallbacksAndMessages(null);
        }
        super.onDetachedFromWindow();
    }


    // TODO ==================================== 手势处理 ===============================================================

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean consume = mDetector.onTouchEvent(event);
        if (consume) {
            getParent().getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        }

        return super.onTouchEvent(event);
    }


    public void setRotate(int rotation) {
        rotation = (rotation % 360 + 360) % 360;
        InitAngle = rotation;
        ViewCompat.postInvalidateOnAnimation(this);
    }


    @Override
    public void computeScroll() {

        if (scroller.computeScrollOffset()) {
            setRotate(scroller.getCurrY());
        }

        super.computeScroll();
    }

    private class RotatePanGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float centerX = (RotatePan.this.getLeft() + RotatePan.this.getRight()) * 0.5f;
            float centerY = (RotatePan.this.getTop() + RotatePan.this.getBottom()) * 0.5f;

            float scrollTheta = vectorToScalarScroll(distanceX, distanceY, e2.getX() - centerX, e2.getY() -
                    centerY);
            int rotate = InitAngle -
                    (int) scrollTheta / FLING_VELOCITY_DOWNSCALE;

            setRotate(rotate);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float centerX = (RotatePan.this.getLeft() + RotatePan.this.getRight()) * 0.5f;
            float centerY = (RotatePan.this.getTop() + RotatePan.this.getBottom()) * 0.5f;

            float scrollTheta = vectorToScalarScroll(velocityX, velocityY, e2.getX() - centerX, e2.getY() -
                    centerY);

            scroller.abortAnimation();
            scroller.fling(0, InitAngle, 0, (int) scrollTheta / FLING_VELOCITY_DOWNSCALE,
                    0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            return true;
        }
    }

    //TODO 判断滑动的方向
    private float vectorToScalarScroll(float dx, float dy, float x, float y) {

        float l = (float) Math.sqrt(dx * dx + dy * dy);

        float crossX = -y;
        float crossY = x;

        float dot = (crossX * dx + crossY * dy);
        float sign = Math.signum(dot);

        return l * sign;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
