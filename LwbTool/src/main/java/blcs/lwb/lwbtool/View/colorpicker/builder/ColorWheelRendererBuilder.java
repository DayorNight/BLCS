package blcs.lwb.lwbtool.View.colorpicker.builder;


import blcs.lwb.lwbtool.View.colorpicker.renderer.ColorWheelRenderer;
import blcs.lwb.lwbtool.View.colorpicker.renderer.FlowerColorWheelRenderer;
import blcs.lwb.lwbtool.View.colorpicker.renderer.SimpleColorWheelRenderer;
import blcs.lwb.lwbtool.View.colorpicker.spider.ColorPickerView;

/**
 * @author vondear
 * @date 2018/6/11 11:36:40 整合修改
 */
public class ColorWheelRendererBuilder {
    public static ColorWheelRenderer getRenderer(ColorPickerView.WHEEL_TYPE wheelType) {
        switch (wheelType) {
            case CIRCLE:
                return new SimpleColorWheelRenderer();
            case FLOWER:
                return new FlowerColorWheelRenderer();
                default:
                    break;
        }
        throw new IllegalArgumentException("wrong WHEEL_TYPE");
    }
}