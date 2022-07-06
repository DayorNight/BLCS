package com.blcs.comlibs.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatImageView
import com.blcs.comlibs.R

/**
 * @Description: 飘心View
 */
class LikeAnimView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) : AnimationLayout(context!!, attrs) {
    private var mEnterDuration = 0
    private var mCurveDuration = 0
    private var mLikeRes: MutableList<Int>? = null

    /**
     * Init
     */
    override fun init() {
        super.init()
        mLikeRes = mutableListOf();
    }

    /**
     * Init TypedArray
     */
    private fun initTypedArray(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.KsgLikeView)
        // 进入动画时长
        mEnterDuration = typedArray.getInteger(R.styleable.KsgLikeView_ksg_enter_duration, 1500)
        // 路径动画时长
        mCurveDuration = typedArray.getInteger(R.styleable.KsgLikeView_ksg_curve_duration, 4500)
        // 回收
        typedArray.recycle()
    }

    /**
     * 添加 资源文件
     */
    override fun addLikeImage(resId: Int) {
        this.addLikeImages(resId)
    }

    /**
     * 添加 资源文件组
     */
    override fun addLikeImages(vararg resIds: Int?) {
//        this.addLikeImages(Arrays.asList(*resIds))
    }

    /**
     * 添加 资源文件列表
     *
     * @param resIds resIds
     */
    override fun addLikeImages(resIds: MutableList<Int>?) {
//        mLikeRes!!.addAll(resIds)
    }

    /**
     * 添加 发送
     */
    override fun addFavor() {
        // 非空验证
        if (mLikeRes!!.isEmpty()) {
            return
        }
        // 随机获取一个资源
        val favorRes = Math.abs(mLikeRes!![mRandom.nextInt(mLikeRes!!.size)])
        // 生成 配置参数
        val layoutParams = createLayoutParams(favorRes)
        // 创建一个资源View
        val favorView = AppCompatImageView(context)
        favorView.setImageResource(favorRes)
        // 开始执行动画
        start(favorView, this, layoutParams)
    }

    /**
     * 生成 配置参数
     */
    private fun createLayoutParams(crystalLeaf: Int): LayoutParams {
        // 获取图片信息
        getPictureInfo(crystalLeaf)
        // 初始化布局参数
        return LayoutParams(mPicWidth.toInt(), mPicHeight.toInt(), Gravity.BOTTOM or Gravity.CENTER)
    }

    /**
     * 开始执行动画
     *
     * @param child        child
     * @param parent       parent
     * @param layoutParams layoutParams
     */
    private fun start(child: View, parent: ViewGroup, layoutParams: LayoutParams) {
        // 设置进入动画
        val enterAnimator = generateEnterAnimation(child)
        // 设置路径动画
        val curveAnimator = generateCurveAnimation(child)
        // 执行动画集合
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(curveAnimator, enterAnimator)
        animatorSet.addListener(AnimationEndListener(child, parent, animatorSet))
        animatorSet.start()
        // add父布局
        parent.addView(child, layoutParams)
    }

    /**
     * 进入动画
     *
     * @return 动画集合
     */
    private fun generateEnterAnimation(child: View): AnimatorSet {
        val enterAnimation = AnimatorSet()
        enterAnimation.playTogether(
                ObjectAnimator.ofFloat(child, ALPHA, 0.2f, 1f),
                ObjectAnimator.ofFloat(child, SCALE_X, 0.2f, 1f),
                ObjectAnimator.ofFloat(child, SCALE_Y, 0.2f, 1f))
        // 加一些动画差值器
        enterAnimation.interpolator = LinearInterpolator()
        return enterAnimation.setDuration(mEnterDuration.toLong())
    }

    /**
     * 贝赛尔曲线动画
     *
     * @return 动画集合
     */
    private fun generateCurveAnimation(child: View): ValueAnimator {
        // 起点 坐标
        val pointStart = PointF((mViewWidth - mPicWidth) / 2, mViewHeight - mPicHeight)
        // 终点 坐标
        val pointEnd = PointF((mViewWidth - mPicWidth) / 2 + (if (mRandom.nextBoolean()) 1 else -1) * mRandom.nextInt(100), 0f)
        // 属性动画
        val pointF1 = getTogglePoint(1)
        val pointF2 = getTogglePoint(2)
        val curveAnimator = ValueAnimator.ofObject(mEvaluatorRecord.getCurrentPath(pointF1, pointF2), pointStart, pointEnd)
        curveAnimator.addUpdateListener(CurveUpdateLister(child))
        curveAnimator.interpolator = LinearInterpolator()
        return curveAnimator.setDuration(mCurveDuration.toLong())
    }

    private fun getTogglePoint(scale: Int): PointF {
        val pointf = PointF()
        // 减去100 是为了控制 x轴活动范围
        pointf.x = mRandom.nextInt(mViewWidth - 100).toFloat()
        // 再Y轴上 为了确保第二个控制点 在第一个点之上,我把Y分成了上下两半
        pointf.y = mRandom.nextInt(mViewHeight - 100).toFloat() / scale
        return pointf
    }

    init {
        // Init TypedArray
        initTypedArray(attrs)
    }
}