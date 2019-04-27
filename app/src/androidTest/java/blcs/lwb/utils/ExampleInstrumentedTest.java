package blcs.lwb.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

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
    private static String packageName = "com.tencent.tmgp.tyjdzc";
//    private static String packageName = "blcs.lwb.utils";
    @Test
    public void useAppContext() throws Exception{
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
            sleep(5000);
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
