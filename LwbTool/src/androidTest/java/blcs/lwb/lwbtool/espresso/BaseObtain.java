package blcs.lwb.lwbtool.espresso;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.core.internal.deps.guava.collect.Iterables;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.contrarywind.view.WheelView;

import org.hamcrest.Matcher;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * 1.根据 ID 获取文本
 * 2.获取 WheelView 文本
 * 3.获取 ListChild 文本
 * 4.根据 ID 判断获取View的显示状态
 * 5.根据 ID 判断获取ListView的item数量
 * 6.根据 ID 获取ListView的数据
 * 7.根据 ID 获取RecyclerView的数据
 * 8.根据 ID 与position 获取RecyclerView.ViewHolder布局
 * 9.根据 ID 获取View
 * 10.获取当前 Activity
 */
public class BaseObtain {
    public final static int NOEXISTS = -2;
    public final static int YEAR = 1;
    public final static int MONTH = 2;
    public final static int DAY = 3;

   //1.根据ID获取文本
    public static String getText(Object id) {
        return ((TextView)getRealView(id)).getText().toString();
    }
    //2.获取WheelView文本
    public static String getTimeWheelViewText(Object id, int type) {
        if (type == YEAR) {
            return (((WheelView)getRealView(id)).getCurrentItem() + 1900) + "";
        } else if (type == MONTH || type == DAY) {
            if((((WheelView)getRealView(id)).getCurrentItem() + 1) < 10) {
                return  "0" + (((WheelView)getRealView(id)).getCurrentItem() + 1);
            } else {
                return (((WheelView)getRealView(id)).getCurrentItem() + 1) + "";
            }
        }
        return "";
    }
    //3.获取ListChild文本
    public static String getListChildText(@IdRes int id, @IdRes int child, int itemPosition) {
        final String[] str = new String[1];
        BaseView.getListItemView(id, itemPosition).onChildView(withId(child)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return BaseCheck.anyView();
            }

            @Override
            public String getDescription() {
                return "get view";
            }

            @Override
            public void perform(UiController uiController, View view) {
                str[0] = ((TextView)view).getText().toString();
            }
        });
        if (str[0] == null) {
            return "";
        }
        return str[0];
    }
    //4.根据ID 判断获取View的显示状态
    public static int getViewVisibility(Object id) {
        int visibility = NOEXISTS;
        View view=getRealView(id);
        if(view!=null){
            visibility=view.getVisibility();
        }
        return visibility;
    }
    //5.根据ID 判断获取ListView的item数量
    public static int getListItemCount(Object id) {
        View view = getRealView(id);
        int count = -1;
        if (view instanceof ListView) {
            count = ((ListView) view).getAdapter().getCount();
        } else if (view instanceof RecyclerView) {
            count = ((RecyclerView) view).getAdapter().getItemCount();
        }
        return count;
    }
    //6.根据ID 获取ListView的数据
    public static Adapter getListData(Object id) {
        return ((ListView) getRealView(id)).getAdapter();
    }
    //7.根据ID 获取RecyclerView的数据
    public static RecyclerView getRecylerData(Object id) {
        return (RecyclerView) getRealView(id);
    }
    //8.根据ID 与position 获取RecyclerView.ViewHolder布局
    public static RecyclerView.ViewHolder getRecylerItemViewHolder(final Object id, int index){
        final View[] view = {null};
        BaseView.getView(id).perform(RecyclerViewActions.actionOnItemAtPosition(index, new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return BaseCheck.anyView();
            }

            @Override
            public String getDescription() {
                return "";
            }

            @Override
            public void perform(UiController uiController, View view1) {
                view[0] =view1;
            }
        }));
        return getRecylerData(id).getChildViewHolder(view[0]);
    }
    //9.根据ID  获取View
    public static View getRealView(Object id) {
        final View[] view = {null};
        BaseView.getView(id).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return BaseCheck.anyView();
            }

            @Override
            public String getDescription() {
                return "get view";
            }

            @Override
            public void perform(UiController uiController, View view1) {
                view[0] = view1;
            }
        });
        return view[0];
    }
    //10.获取当前Activity
    public static Activity getCurrentActivity() {
        getInstrumentation().waitForIdleSync();
        final Activity[] activity = new Activity[1];
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                java.util.Collection<Activity> activities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                activity[0] = Iterables.getOnlyElement(activities);
            }
        });
        return activity[0];
    }
}