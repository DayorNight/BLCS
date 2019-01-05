package blcs.lwb.lwbtool.View.colorpicker.renderer;


import java.util.List;

import blcs.lwb.lwbtool.View.colorpicker.ColorCircle;

/**
 * @author vondear
 * @date 2018/6/11 11:36:40 整合修改
 */
public interface ColorWheelRenderer {
    float GAP_PERCENTAGE = 0.025f;

    void draw();

    ColorWheelRenderOption getRenderOption();

    void initWith(ColorWheelRenderOption colorWheelRenderOption);

    List<ColorCircle> getColorCircleList();
}
