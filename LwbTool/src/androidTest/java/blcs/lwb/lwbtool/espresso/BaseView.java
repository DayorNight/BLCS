package blcs.lwb.lwbtool.espresso;

import android.support.annotation.IdRes;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;

/**
 *  来源：https://www.jianshu.com/p/6328acb01bf6
 * 1.获取list中的item布局
 * 2.根据object获得某一个view
 */
public class BaseView {
    /**
     * 获取list中的item布局
     * @param id list 的ID
     * @param itemPosition 第几个item
     * @return
     */
    public static DataInteraction getListItemView(@IdRes int id, int itemPosition) {
        return onData(anything())
                .inAdapterView(allOf(withId(id), isDisplayed()))
                .atPosition(itemPosition);
    }

    /**
     * 获得某一个view
     * @param object 这个view 的ID 或者 这个view上显示的文本
     * @return
     */
    public static ViewInteraction getView(Object object) {
        if(object instanceof String ){
            return onView(allOf(withText(containsString((String) object))));
        }else if(object instanceof Integer){
            return onView(allOf(withId((Integer) object)));
        }
        return null;
    }
}