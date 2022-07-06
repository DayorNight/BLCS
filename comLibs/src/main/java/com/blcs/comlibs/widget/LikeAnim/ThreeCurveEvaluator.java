package com.blcs.comlibs.widget.LikeAnim;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * @Description: 三阶贝赛尔曲线
 */
public class ThreeCurveEvaluator implements TypeEvaluator<PointF> {

    private final PointF mControlP1;
    private final PointF mControlP2;

    public ThreeCurveEvaluator(PointF pointF1, PointF pointF2) {
        this.mControlP1 = pointF1;
        this.mControlP2 = pointF2;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        PointF pointCur = new PointF();
        float leftTime = 1.0f - fraction;
        // 三阶贝赛尔曲线
        pointCur.x = (float) Math.pow(leftTime, 3) * startValue.x
                + 3 * (float) Math.pow(leftTime, 2) * fraction * mControlP1.x
                + 3 * leftTime * (float) Math.pow(fraction, 2) * mControlP2.x
                + (float) Math.pow(fraction, 3) * endValue.x;

        pointCur.y = (float) Math.pow(leftTime, 3) * startValue.y
                + 3 * (float) Math.pow(leftTime, 2) * fraction * mControlP1.y
                + 3 * leftTime * fraction * fraction * mControlP2.y
                + (float) Math.pow(fraction, 3) * endValue.y;
        return pointCur;
    }
}