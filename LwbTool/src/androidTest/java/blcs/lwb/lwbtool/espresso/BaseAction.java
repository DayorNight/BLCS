package blcs.lwb.lwbtool.espresso;

import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.MotionEvents;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * 1.根据 ID 与文本 单击操作
 * 2.当需要点击的控件在屏幕之外时，调用此方法 isScroll = true
 * 3.根据 ID 与文本 长按操作
 * 4.根据 ID 与输入文本操作
 * 5.根据 ID 输入文本之后点击键盘的回车键
 * 6.touch事件，按下和抬起 ：比如发送语音的时候
 * 7.不带坐标的 touch事件，默认为 （0,0）点
 * 8.带坐标的touch事件
 * 9.滑动操作，比如viewpager的左右滑动
 * 10.spinner 点击,根据item的文本点击
 * 11.spinner 点击，根据item的index点击
 * 12.点击 list item
 * 13.点击list item中的某个控件
 * 14.list item 长按操作
 * 15.查找含有特定规则的list item
 * 16.recycleview item的点击事件
 * 17.击recycleview的item布局中的某个控件
 * 18.recycle item的长按事件
 * 19.根据匹配规则单击某一项
 * 20.休眠
 */
public class BaseAction {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int UP = 4;
    public static final int INPUTTEXT = 1;
    public static final int TYPETEXT = 2;
    private static MotionEvent downEvent;

    /**
     * 1.根据 ID 与文本 单击操作
     */
    public static void clickView(Object object) {
        clickView(object, false);
    }

    /**
     * 2.当需要点击的控件在屏幕之外时，调用此方法 isScroll = true
     */
    public static void clickView(Object object, boolean isScroll) {
        doClick(BaseView.getView(object), isScroll);
    }

    /**
     * 3.根据 ID 与文本 长按操作
     */
    public static void longClickView(Object object) {
        doLongClick(BaseView.getView(object));
    }

    /**
     * 4.根据 ID 与输入文本操作
     */
    public static void inputText(Object id, String text) {
        doInputText(BaseView.getView(id), text);
    }

    /**
     * 5.根据 ID 输入文本之后点击键盘的回车键
     */
    public static void inputTextIme(Object id, String text){
        BaseView.getView(id).perform(click(),clearText(), replaceText(text), pressImeActionButton(),closeSoftKeyboard());
    }

    /**
     * 6.touch事件，按下和抬起 ：比如发送语音的时候
     */
    public static void touchViewDU(Object id,int x,int y,int duration){
        BaseView.getView(id).perform(touchDownAndUp(x,y,duration));
    }

    /**
     * 7.不带坐标的 touch事件，默认为 （0,0）点
     * @param id 被触摸的控件ID或则文本
     * @param action 触摸事件：MotionEvent.ACTION_DOWN,MotionEvent.ACTION_UP,MotionEvent.ACTION_MOVE,MotionEvent.ACTION_CANCEL
     */
    public static void touchView(Object id,int action){
        touchView(id,0,0,action);
    }

    /**
     * 8.带坐标的touch事件
     */
    public static void touchView(Object id,float x,float y,int action){
        BaseView.getView(id).perform(touch(x, y, action));
    }
    public static ViewAction touchDownAndUp(final float x, final float y, final int duration) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return BaseCheck.anyView();
            }

            @Override
            public String getDescription() {
                return "Send touch events.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                // Get view absolute position
                int[] location = new int[2];
                view.getLocationOnScreen(location);

                // Offset coordinates by view position
                float[] coordinates = new float[]{x + location[0], y + location[1]};
                float[] precision = new float[]{1f, 1f};

                // Send down event, pause, and send up
                MotionEvent down = MotionEvents.sendDown(uiController, coordinates, precision).down;
                uiController.loopMainThreadForAtLeast(duration);
                MotionEvents.sendUp(uiController, down, coordinates);
            }
        };
    }
    public static ViewAction touch(final float x, final float y, final int action) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return BaseCheck.anyView();
            }

            @Override
            public String getDescription() {
                return "Send touch events.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                // Get view absolute position
                int[] location = new int[2];
                view.getLocationOnScreen(location);

                // Offset coordinates by view position
                float[] coordinates = new float[]{x + location[0], y + location[1]};
                float[] precision = new float[]{1f, 1f};

                // Send down event, pause, and send up
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        downEvent = MotionEvents.sendDown(uiController, coordinates, precision).down;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        MotionEvents.sendMovement(uiController,downEvent,coordinates);
                        break;
                    case MotionEvent.ACTION_UP:
                        MotionEvents.sendUp(uiController,downEvent,coordinates);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        MotionEvents.sendCancel(uiController,downEvent);
                        break;
                }
            }
        };
    }

    /**
     * 9.滑动操作，比如viewpager的左右滑动
     * @param id 需要滑动的控件
     * @param dir 滑动方向 left = 1,right = 2,down = 3,up = 4
     */
    public static void swipe(Object id, int dir) {
        ViewAction viewAction = null;
        switch (dir) {
            case LEFT:
                viewAction = swipeLeft();
                break;
            case RIGHT:
                viewAction = swipeRight();
                break;
            case UP:
                viewAction = swipeUp();
                break;
            case DOWN:
                viewAction = swipeDown();
                break;
        }
        BaseView.getView(id).perform(viewAction);
    }

    /**
     * 10.spinner 点击,根据item的文本点击
     * @param str
     */
    public static void clickSpinnerItem(String str) {
        onData(allOf(is(instanceOf(String.class)), is(str))).perform(click());
    }

    /**
     * 11.spinner 点击，根据item的index点击
     * @param index
     */
    public static void clickSpinnerItem(int index) {
        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(android.R.id.text1),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                        withParent(withClassName(is("android.widget.FrameLayout")))),
                                index),
                        isDisplayed()));
        appCompatCheckedTextView.perform(click());
    }

    /**
     * 12.点击 list item
     * @param id list的ID
     * @param itemPosition 所要点击的item的index
     */
    public static void clickListItem(@IdRes int id, int itemPosition) {
        listItemAction(id,itemPosition,click());
    }

    /**
     * 13.点击list item中的某个控件
     * @param id list的ID
     * @param child item中需要点击的控件的ID
     * @param itemPosition 点击的第几个item
     */
    public static void clickListItemChild(@IdRes int id, @IdRes int child, int itemPosition) {
        BaseView.getListItemView(id, itemPosition).onChildView(withId(child)).perform(click());
    }

    public static void inputListItemChild(@IdRes int id, @IdRes int child, int itemPosition, final int flag, String content) {
        BaseView.getListItemView(id, itemPosition).onChildView(withId(child)).perform(clearText(), new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return BaseCheck.anyView();
            }

            @Override
            public String getDescription() {
                return "";
            }

            @Override
            public void perform(UiController uiController, View view) {
                if(view instanceof EditText){
                    if (flag == TYPETEXT) {
                        ((EditText) view).setInputType(InputType.TYPE_CLASS_NUMBER);
                    }

                }
            }
        }, flag == TYPETEXT ? typeText(content) : replaceText(content), closeSoftKeyboard());
    }

    /**
     * 14.list item 长按操作
     * @param id list ID
     * @param itemPosition item所在的index
     */
    public static void longClickListItem(@IdRes int id, int itemPosition) {
        listItemAction(id,itemPosition,longClick());
    }

    private static void listItemAction(@IdRes int id, int itemPosition,ViewAction viewAction){
        BaseView.getListItemView(id, itemPosition).perform(viewAction);
    }

    /**
     * 15.查找含有特定规则的list item
     * @param var1 adapterBean的class类
     * @param match 匹配规则，返回true则找到
     * @param <T> class类
     */
    public static <T> void clickListItemMatch(Class<? extends T> var1, MatchBean<T> match){
        listItemMatch(var1,match,click());
    }

    public static <T> void longClickListItemMatch(Class<? extends T> var1, MatchBean<T> match){
        listItemMatch(var1,match,longClick());
    }

    private static <T> void listItemMatch(Class<? extends T> var1, MatchBean<T> match, ViewAction viewAction){
        onData(withContainTextAdapter(var1,match)).inAdapterView(allOf(isAssignableFrom(AdapterView.class),hasFocus())).perform(viewAction);
    }

    private static <S> Matcher<Object> withContainTextAdapter(Class<? extends S> var1, final MatchBean<S> match){
        return new BoundedMatcher<Object,S>(var1){

            @Override
            public void describeTo(Description description) {
                description.appendText("with text item");
            }

            @Override
            protected boolean matchesSafely(S s) {
                return match.matches(s);
            }
        };
    }
    public  interface MatchBean<T>{
        boolean matches(T t);
    }

    /*recycler 操作*/

    /**
     * 16.recycleview item的点击事件
     * @param id
     * @param itemPosition
     */
    public static void clickRecyclerItem(@IdRes int id, int itemPosition) {
        recyclerItemAction(id,itemPosition,click());
    }

    /**
     * 17.击recycleview的item布局中的某个控件
     * @param id recycle 的ID
     * @param child item布局中需要点击的控件的ID
     * @param itemPosition 点击的哪个item
     */
    public static void clickRecyclerItemChild(@IdRes int id,@IdRes int child ,int itemPosition){
        recyclerItemAction(id,itemPosition,clickChildViewWithId(child));
    }

    /**
     * 18.recycle item的长按事件
     * @param id
     * @param itemPosition
     */
    public static void longclickRecyclerItem(@IdRes int id, int itemPosition) {
        recyclerItemAction(id,itemPosition,longClick());
    }

    public static void recyclerItemAction(@IdRes int id, int itemPosition,ViewAction viewAction){
        BaseView.getView(id).perform(RecyclerViewActions.actionOnItemAtPosition(itemPosition, viewAction));
    }

    private static ViewAction clickChildViewWithId(final int id){
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return BaseCheck.anyView();
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View view1=view.findViewById(id);
                view1.performClick();
            }
        };
    }

    /**
     * 19.根据匹配规则单击某一项
     * @param id recycler的id
     * @param var1 viewholder的class
     * @param match 自定义匹配规则，会返回viewholder，来匹配
     * @param <T>
     */
    public static <T extends RecyclerView.ViewHolder> void clickRecyclerMatch(@IdRes int id, Class<? extends T> var1, final MatchViewHolder<T> match){
        actionRecyclerWithText(id,var1,match,click());
    }

    public static <T extends RecyclerView.ViewHolder> void longClickRecyclerMatch(@IdRes int id, Class<? extends T> var1, final MatchViewHolder<T> match){
        actionRecyclerWithText(id,var1,match,longClick());
    }

    public static <T extends RecyclerView.ViewHolder> void actionRecyclerWithText(@IdRes int id, Class<? extends T> var1, final MatchViewHolder<T> match, ViewAction viewAction){
        BaseView.getView(id).perform(RecyclerViewActions.actionOnHolderItem(new BoundedMatcher<RecyclerView.ViewHolder,T>(var1) {
            @Override
            public void describeTo(Description description) {
                description.appendText("find the view holder ");
            }

            @Override
            protected boolean matchesSafely(T t) {
                return match.match(t);
            }
        }, viewAction));
    }

    public interface MatchViewHolder<T>{
        boolean match(T viewHolder);
    }

    /**
     * 20.休眠
     * @param ms 毫秒
     */
    public static void sleep(long ms) {
        SystemClock.sleep(ms);
    }

    private static void doClick(ViewInteraction view, boolean isScroll) {
        if (isScroll) {
            doScroll(view);
        }
        view.perform(click());
    }

    private static void doScroll(ViewInteraction view) {
        view.perform(scrollTo());
    }

    private static void doLongClick(ViewInteraction view) {
        view.perform(longClick());
    }

    private static void doInputText(ViewInteraction view, String text) {
        view.perform(clearText(), replaceText(text), closeSoftKeyboard());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}