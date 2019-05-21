package blcs.lwb.utils.testApp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.Configurator;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;

//BeforeClass-->Before-->Test-->After-->Before-->Test1-->After-->AfterClass
@RunWith(AndroidJUnit4.class)
public class ToolTest {
    private static Context context;
    private static String packageName;

    @BeforeClass
    public static  void setBeforeClass() throws Exception{
        context = InstrumentationRegistry.getTargetContext();
        packageName = context.getPackageName();
    }
    @AfterClass
    public static void setAfterClass() throws Exception{

    }

    @Test
    public void name() throws Exception {
        //初始化一个UiDevice对象
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage( context.getPackageName());
        // 清除以前的实例
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        UiObject view = mDevice.findObject(new UiSelector().text("工具"));
        view.click();

        UiObject linView = mDevice.findObject(new UiSelector().textContains("LinView"));
        linView.click();
        sleep(10000);
    }
}
