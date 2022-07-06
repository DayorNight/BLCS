package com.blcs.comlibs.widget.LikeAnim;

import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.util.SparseArray;

import java.util.Random;

/**
 * @Description: 贝塞尔曲线缓存类
 */
public class CurveEvaluatorRecord {

    private static final int MAX_PATH_COUNTS = 100;

    private final Random mRandom = new Random();

    private int mCurrentPathCounts;

    private SparseArray<TypeEvaluator<PointF>> mPathArray;

    public CurveEvaluatorRecord() {
        this.mPathArray = new SparseArray<>();
    }

    /**
     * 生成曲线路线
     */
    public TypeEvaluator<PointF> getCurrentPath(PointF pointF1, PointF pointF2) {
        TypeEvaluator<PointF> evaluator;
        // 记录已生成路径
        ++mCurrentPathCounts;
        // 如果已经生成的路径数目超过最大设定，就从路径缓存中随机取一个路径用于绘制，否则新生成一个
        if (mCurrentPathCounts > MAX_PATH_COUNTS) {
            // 读取缓存
            evaluator = mPathArray.get(Math.abs(mRandom.nextInt() % MAX_PATH_COUNTS) + 1);
        } else {
            // 创建路径
            evaluator = createPath(pointF1, pointF2);
            // 缓存
            this.mPathArray.put(mCurrentPathCounts, evaluator);
        }
        return evaluator;
    }

    /**
     * 生成 贝塞尔路径
     */
    private TypeEvaluator<PointF> createPath(PointF pointF1, PointF pointF2) {
        return new ThreeCurveEvaluator(pointF1, pointF2);
    }

    /**
     * destroy
     */
    public void destroy() {
        if (mPathArray != null) {
            this.mPathArray.clear();
            this.mPathArray = null;
        }
    }
}