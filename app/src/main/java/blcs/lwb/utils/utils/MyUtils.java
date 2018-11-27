package blcs.lwb.utils.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import blcs.lwb.utils.adapter.BaseDemoAdapter.MultipleItem;
import blcs.lwb.utils.adapter.BaseDemoAdapter.NormalMultipleEntity;

public class MyUtils {
    private static final String CYM_CHAD = "CymChad";
    private static final String CHAY_CHAN = "ChayChan";
    /**
     * 获取数组
     * @param activity
     * @param a
     * @return
     */
    public static List<String> getArray(Activity activity, int a){
        return Arrays.asList(activity.getResources().getStringArray(a));
    }

    /**
     * recycyler 数据
     * @return
     */
    public static List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(new MultipleItem(MultipleItem.IMG, MultipleItem.IMG_SPAN_SIZE));
            list.add(new MultipleItem(MultipleItem.TEXT, MultipleItem.TEXT_SPAN_SIZE, CYM_CHAD));
            list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE));
            list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN));
            list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN));
        }

        return list;
    }
    /**
     * recycyler 复杂数据
     * @return
     */
    public static List<NormalMultipleEntity> getNormalMultipleEntities() {
        List<NormalMultipleEntity> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(new NormalMultipleEntity(NormalMultipleEntity.SINGLE_IMG));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.SINGLE_TEXT,CHAY_CHAN));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.TEXT_IMG,CHAY_CHAN));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.TEXT_IMG,CYM_CHAD));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.TEXT_IMG,CHAY_CHAN));
        }
        return list;
    }
}
