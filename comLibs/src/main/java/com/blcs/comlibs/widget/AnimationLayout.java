package com.blcs.comlibs.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blcs.comlibs.widget.LikeAnim.CurveEvaluatorRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: AnimationLayout
 * @Author: KaiSenGao
 * @CreateDate: 2020/12/24 10:58
 * @Description:
 */
public abstract class AnimationLayout extends FrameLayout implements IAnimationLayout {

    protected final Random mRandom = new Random();

    protected int mViewWidth, mViewHeight;

    protected float mPicWidth, mPicHeight;

    protected List<AnimatorSet> mAnimatorSets;

    protected CurveEvaluatorRecord mEvaluatorRecord;

    public AnimationLayout(@NonNull Context context) {
        this(context, null);
    }

    public AnimationLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 初始化
        this.init();
    }

    /**
     * 初始化
     */
    protected void init() {
        // 动画集合
        this.mAnimatorSets = new ArrayList<>();
        // 贝塞尔曲线缓存类
        this.mEvaluatorRecord = new CurveEvaluatorRecord();
    }

    /**
     * 获取资源图片信息
     *
     * @param resId 资源Id
     */
    public void getPictureInfo(@DrawableRes int resId) {
        // 获取图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 只读取图片，不加载到内存中
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getContext().getResources(), resId, options);
        // 获取图片的宽高
        this.mPicWidth = options.outWidth;
        this.mPicHeight = options.outHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mViewWidth = getMeasuredWidth();
        this.mViewHeight = getMeasuredHeight();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        this.mViewWidth = getMeasuredWidth();
        this.mViewHeight = getMeasuredHeight();
    }

    /**
     * 贝塞尔曲线路径更新事件
     */
    protected static class CurveUpdateLister implements ValueAnimator.AnimatorUpdateListener {

        private final View mChild;

        protected CurveUpdateLister(View child) {
            this.mChild = child;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            PointF value = (PointF) animation.getAnimatedValue();
            this.mChild.setX(value.x);
            this.mChild.setY(value.y);
            this.mChild.setAlpha(1 - animation.getAnimatedFraction());
        }
    }

    /**
     * 动画结束监听器,用于释放无用的资源
     */
    protected class AnimationEndListener extends AnimatorListenerAdapter {
        private final View mChild;
        private final ViewGroup mParent;
        private final AnimatorSet mAnimatorSet;

        protected AnimationEndListener(View child, ViewGroup parent, AnimatorSet animatorSet) {
            this.mChild = child;
            this.mParent = parent;
            this.mAnimatorSet = animatorSet;
            // 缓存
            mAnimatorSets.add(mAnimatorSet);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            // 动画结束 移除View
            this.mParent.removeView(mChild);
            // 从集合中移除
            mAnimatorSets.remove(mAnimatorSet);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 销毁资源
        this.destroy();
    }

    /**
     * 销毁资源
     */
    public void destroy() {
        // 取消动画 释放资源
        for (AnimatorSet animatorSet : mAnimatorSets) {
            // 初始化回调方法
            animatorSet.getListeners().clear();
            // 取消动画
            animatorSet.cancel();
        }
        // 释放集合资源
        this.mAnimatorSets.clear();
        this.mEvaluatorRecord.destroy();
    }
}