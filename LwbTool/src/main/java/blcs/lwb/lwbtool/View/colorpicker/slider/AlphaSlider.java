package blcs.lwb.lwbtool.View.colorpicker.slider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;

import blcs.lwb.lwbtool.View.colorpicker.builder.PaintBuilder;
import blcs.lwb.lwbtool.View.colorpicker.spider.ColorPickerView;
import blcs.lwb.lwbtool.utils.ColorsUtil;
import blcs.lwb.lwbtool.utils.MyUtils;

/**
 * @author vondear
 * @date 2018/6/11 11:36:40 整合修改
 */
public class AlphaSlider extends AbsCustomSlider {
    public int color;
    private final Paint alphaPatternPaint = PaintBuilder.newPaint().build();
    private final Paint barPaint = PaintBuilder.newPaint().build();
    private final Paint solid = PaintBuilder.newPaint().build();
    private final Paint clearingStroke = PaintBuilder.newPaint().color(0xffffffff).xPerMode(PorterDuff.Mode.CLEAR).build();

    private ColorPickerView colorPicker;

    public AlphaSlider(Context context) {
        super(context);
    }

    public AlphaSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlphaSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createBitmaps() {
        super.createBitmaps();
        alphaPatternPaint.setShader(PaintBuilder.createAlphaPatternShader(barHeight / 2));
    }

    @Override
    protected void drawBar(Canvas barCanvas) {
        int width = barCanvas.getWidth();
        int height = barCanvas.getHeight();

        barCanvas.drawRect(0, 0, width, height, alphaPatternPaint);
        int l = Math.max(2, width / 256);
        for (int x = 0; x <= width; x += l) {
            float alpha = (float) x / (width - 1);
            barPaint.setColor(color);
            barPaint.setAlpha(Math.round(alpha * 255));
            barCanvas.drawRect(x, 0, x + l, height, barPaint);
        }
    }

    @Override
    protected void onValueChanged(float value) {
        if (colorPicker != null) {
            colorPicker.setAlphaValue(value);
        }
    }

    @Override
    protected void drawHandle(Canvas canvas, float x, float y) {
        solid.setColor(color);
        solid.setAlpha(Math.round(value * 255));
        canvas.drawCircle(x, y, handleRadius, clearingStroke);
        if (value < 1) {
            canvas.drawCircle(x, y, handleRadius * 0.75f, alphaPatternPaint);
        }
        canvas.drawCircle(x, y, handleRadius * 0.75f, solid);
    }

    public void setColorPicker(ColorPickerView colorPicker) {
        this.colorPicker = colorPicker;
    }

    public void setColor(int color) {
        this.color = color;
        this.value = ColorsUtil.getAlphaPercent(color);
        if (bar != null) {
            updateBar();
            invalidate();
        }
    }
}