package blcs.lwb.utils;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;

import blcs.lwb.lwbtool.utils.LogUtils;

import static java.lang.Thread.sleep;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static Context context;
    private static String packageName;
    @Test
    public void useAppContext() throws Exception{
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        Context context = InstrumentationRegistry.getContext();
//
//        String packageName = appContext.getPackageName();
//        assertEquals("blcs.lwb.utils", packageName);
//
//        //初始化一个UiDevice对象
//        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        // 点击home键，回到home界面
//        mDevice.pressHome();
//        String launcherPackage = mDevice.getLauncherPackageName();
//        assertThat(launcherPackage, notNullValue());
//        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 3);
//
//        // 启动espressotests App
//        Intent intent = context.getPackageManager().getLaunchIntentForPackage( packageName);
//        // 清除以前的实例
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        context.startActivity(intent);
//
//        // 等待应用程序启动
//        mDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), 3);
//
//        //通过id找到输入框一
//        UiObject edt1 = mDevice.findObject(new UiSelector().resourceId("me.shihao.espressotests:id/editText").className(EditText.class));
//        UiObject edt2 = mDevice.findObject(new UiSelector().resourceId("me.shihao.espressotests:id/editText2").className(EditText.class));
//        //通过文本"计算"找到按钮
//        UiObject btn = mDevice.findObject(new UiSelector().text("计算").className(Button.class));
//        UiObject tvResult = mDevice.findObject(new UiSelector().resourceId("me.shihao.espressotests:id/textView").className(TextView.class));
//        UiObject btnRecycleView = mDevice.findObject(new UiSelector().text("RecycleView").className(Button.class));
////        UiScrollable recycleview = new UiScrollable(new UiSelector().className(RecyclerView.class).resourceId("me.shihao.espressotests:id/recycleview"));
////        UiObject item5 = recycleview.getChild(new UiSelector().text("Item 5"));
//        UiObject btnConfirm = mDevice.findObject(new UiSelector().text("确定").className(Button.class));
////        UiObject item = mDevice.findObject(new UiSelector().className(RecyclerView.class).resourceId("me.shihao.espressotests:id/recycleview")
////                .childSelector(new UiSelector().text("Item 2")));
//        //往里面输入字符2
//        edt1.setText("2");
//        //通过id找到输入框二
//        //往里面输入5
//        edt2.setText("5");
//        //执行点击事件，计算结果
//        btn.click();
//        //执行点击事件跳转到另一个界面
//        btnRecycleView.click();
////        //滑动到底部
////        recycleview.flingForward();
////        //滑动到顶部
////        recycleview.flingBackward();
////        //点击Item 5，然后会弹出一个对话框
////        item5.click();
////        //点击确定关闭对话框
////        btnConfirm.click();
////        //点击弹出对话框
////        item.click();
//        //点击返回关闭对话框
//        mDevice.pressBack();

        Context appContext = InstrumentationRegistry.getTargetContext();
        Context context = InstrumentationRegistry.getContext();
//        int x = UiDevice.getInstance().getDisplayWidth();
//        int y = UiDevice.getInstance().getDisplayHeight();

//        UiDevice.getInstance().click(x*4/5,y/2);


//        //初始化一个UiDevice对象
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        int width = mDevice.getDisplayWidth();
        int height = mDevice.getDisplayHeight();
//        // 点击home键，回到home界面
//        mDevice.pressHome();
//        // 启动espressotests App
//        Intent intent = context.getPackageManager().getLaunchIntentForPackage( packageName);
//        // 清除以前的实例
////        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        context.startActivity(intent);
//
////        // 等待应用程序启动
//        mDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), 3);

//        sleep(3000);
//        LogUtils.e("width/2 "+width*2/3);
//        LogUtils.e("height/2 "+height/2);
        //决斗之城
//        mDevice.click(width*3/4,height/2);
//        sleep(1000);

        for (int i = 0;i<100000000;i++){
            LogUtils.e("pos====="+i);
//          匹配
//            mDevice.click(width*6/10,height*8/10);
            mDevice.click(1000,780);
            sleep(2000);
//          自动
//            mDevice.click(50,height*2/10);
            mDevice.click(55,300);
            sleep(10000);
////          返回
//            mDevice.click(width*1/3,height*9/10);
            mDevice.click(777,1000);
            sleep(2000);
        }

    }

}
