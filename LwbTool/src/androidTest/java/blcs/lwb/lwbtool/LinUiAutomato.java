package blcs.lwb.lwbtool;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.Configurator;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.view.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import blcs.lwb.lwbtool.utils.AppUtils;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("deprecation")
public class LinUiAutomato extends UiAutomatorTestCase {
    /**
     * 启动APP
     */
    public static void startApp(Context context){
        Intent intent = context.getPackageManager().getLaunchIntentForPackage( context.getPackageName());
        // 清除以前的实例
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    //打开APP
    public static void startClassApp() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("am start -n blcs.lwb.utils/.activity.main.MainActivity").waitFor();
    }
    //关闭APP
    public static void stopClassApp() throws InterruptedException, IOException {
        setShort();
        Runtime.getRuntime().exec("am force-stop com.gaotu100.superclass").waitFor();
        setShort();
    }
    //打开alertover
    public static void stopAlertover() throws InterruptedException, IOException {
        Runtime.getRuntime().exec("am force-stop com.alertover.app").waitFor();
        setShort();
    }
    //关闭alertover
    public static void startAlertover() throws IOException, InterruptedException {
        setShort();
        Runtime.getRuntime().exec("am start -n com.alertover.app/.activity.LoginActivity").waitFor();
    }
    //打开或者关闭wifi
    public static void closeOrOpenWifi() throws InterruptedException, IOException {
        Runtime.getRuntime().exec("am start -n run.wifibutton/.WifiButtonActivity").waitFor();
        setShort();
    }
    //打开微信
    public static void startWechat() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("am start -n com.tencent.mm/.ui.LauncherUI").waitFor();
    }
    //关闭微信
    public static void stopWechat() throws InterruptedException, IOException {
        setShort();
        Runtime.getRuntime().exec("am force-stop com.tencent.mm").waitFor();
        Thread.sleep(500);
    }
    //关闭支付宝
    public static void stopAlipay() throws InterruptedException, IOException {
        Runtime.getRuntime().exec("am force-stop com.eg.android.AlipayGphone").waitFor();
        Thread.sleep(500);
    }
    //验证跳转支付宝
    public static void verifySkipAlipay() {
        waitForUiObjectByText("添加银行卡付款");
        String packagename = UiDevice.getInstance().getCurrentPackageName();
        assertEquals("跳转支付宝失败！", "com.eg.android.AlipayGphone", packagename);
    }
    //验证跳转支付宝
    public static void verifySkipWechat() {
        waitForUiObjectByText("使用零钱支付");
        String packagename = UiDevice.getInstance().getCurrentPackageName();
        assertEquals("跳转微信失败！", "com.tencent.mm", packagename);
    }
    /**
     * 获取控件
     */
    //通过文本获取控件
    public static UiObject getUiObjectByText(String text) {
        return new UiObject(new UiSelector().text(text));
    }
    public static UiObject getUiObjectByTextContains(String text) {
        return new UiObject(new UiSelector().textContains(text));
    }
    //通过text开始文字查找控件
    public static UiObject getUiObjectByStartText(String text) {
        return new UiObject(new UiSelector().textStartsWith(text));
    }
    public static UiObject getUiObjectByStartDesc(String desc) {
        return new UiObject(new UiSelector().descriptionStartsWith(desc));
    }
    //通过文本和类名获取控件
    public static UiObject getUiObjectByTextClassName(String text,String classname) {
        return new UiObject(new UiSelector().text(text).className(classname));
    }
    //通过文本和id获取对象
    public static UiObject getUiObjectByTextResourceId(String text, String id) {
        return new UiObject(new UiSelector().text(text).resourceId(id));
    }
    public static UiObject getUiObjectByResourceIdClassName(String id, String type) {
        return new UiObject(new UiSelector().resourceId(id).className(type));
    }
    //通过资源ID获取控件
    public static UiObject getUiObjectByResourceId(String id) {
        return new UiObject(new UiSelector().resourceId(id));
    }
    //通过desc获取控件
    public static UiObject getUiObjectByDesc(String desc) {
        return new UiObject(new UiSelector().description(desc));
    }
    public static UiObject getUiObjectByStartDescContains(String desc) {
        return new UiObject(new UiSelector().descriptionContains(desc));
    }
    public static UiObject getUiObjectByDescContains(String desc) {
        return new UiObject(new UiSelector().descriptionContains(desc));
    }
    //通过classname获取控件
    public static UiObject getUiObjectByClassName(String type) {
        return new UiObject(new UiSelector().className(type));
    }
    //通过id和instance获取控件
    public static UiObject getUiObjectByResourceIdIntance(String id, int instance) {
        return new UiObject(new UiSelector().resourceId(id).instance(instance));
    }
    //获取滚动控件
    public static UiScrollable getUiScrollabe() {
        return new UiScrollable(new UiSelector().scrollable(true));
    }
    //获取滚动对象
    public static UiScrollable getUiScrollableByResourceId(String id) {
        return new UiScrollable(new UiSelector().scrollable(true).resourceId(id));
    }
    public static void getChildByTextOfUiScrollableByClassName(String type, String text) throws UiObjectNotFoundException {
        getScrollableByClassName(type).getChildByText(new UiSelector().text(text), text).clickAndWaitForNewWindow();
    }
    //通过ID和index获取控件
    public static UiObject getUiObjectByResourIdIndex(String id, int index) {
        return new UiObject(new UiSelector().resourceId(id).index(index));
    }
    //通过classname获取滚动控件
    public static UiScrollable getScrollableByClassName(String type) {
        return new UiScrollable(new UiSelector().scrollable(true).className(type));
    }
    /**
     * 点击长按事件
     */

    //长按控件
    public static void longclickUiObectByResourceId(String id) throws UiObjectNotFoundException {
        int x = getUiObjectByResourceId(id).getBounds().centerX();
        int y = getUiObjectByResourceId(id).getBounds().centerY();
        UiDevice.getInstance().swipe(x, y, x, y, 300);//最后一个参数单位是5ms
    }
    public static void longclickUiObectByDesc(String desc) throws UiObjectNotFoundException {
        int x = getUiObjectByDesc(desc).getBounds().centerX();
        int y = getUiObjectByDesc(desc).getBounds().centerY();
        UiDevice.getInstance().swipe(x, y, x, y, 300);//最后一个参数单位是5ms
    }
    public static void longclickUiObectByText(String text) throws UiObjectNotFoundException {
        int x = getUiObjectByText(text).getBounds().centerX();
        int y = getUiObjectByText(text).getBounds().centerY();
        UiDevice.getInstance().swipe(x, y, x, y, 300);//最后一个参数单位是5ms
    }
    //点击中心
    public static void clickCenter() {
        int x = UiDevice.getInstance().getDisplayWidth();
        int y = UiDevice.getInstance().getDisplayHeight();
        clickPiont(x/2, y/2);
    }
    //点击某一个点
    public static void clickPiont(int x, int y) {
        UiDevice.getInstance().click(x, y);
    }
    //对于一个按键按多次
    public static void pressTimes(int keyCode, int times) {
        for(int i=0;i<times;i++){
            setFast();
            UiDevice.getInstance().pressKeyCode(keyCode);
        }
    }
    //获取子控件点击
    public static void getScrollChildByText(String text) throws UiObjectNotFoundException {
        UiObject child = getUiScrollabe().getChildByText(new UiSelector().text(text), text);
        child.clickAndWaitForNewWindow();
    }
    //点击控件左半边
    public static void getUiObjectByResoureIdAndclickLeftHalf(String id) throws UiObjectNotFoundException {
        //获取控件大小
        Rect sss = getUiObjectByResourceId(id).getBounds();
        //计算中心偏移量
        clickPiont(sss.centerX()-sss.width()/4, sss.centerY());
    }

    //通过开始文字查找控件并点击
    public static void waitForStartTextAndClick(String text) throws UiObjectNotFoundException {
        getUiObjectByStartText(text).waitForExists(10000);
        getUiObjectByStartText(text).clickAndWaitForNewWindow();
    }
    public static void waitForTextContainsAndClick(String text) throws UiObjectNotFoundException {
        getUiObjectByTextContains(text).waitForExists(10000);
        getUiObjectByTextContains(text).clickAndWaitForNewWindow();
    }
    public static void waitForStartDescAndClick(String desc) throws UiObjectNotFoundException {
        getUiObjectByStartDesc(desc).waitForExists(10000);
        getUiObjectByStartDesc(desc).clickAndWaitForNewWindow();
    }
    public static void waitForDescContainsAndClick(String desc) throws UiObjectNotFoundException {
        getUiObjectByDescContains(desc).waitForExists(10000);
        getUiObjectByDescContains(desc).clickAndWaitForNewWindow();
    }
    //等待资源id并点击
    public static void waitForResourceIdAndClick(String id) throws UiObjectNotFoundException {
        waitForUiObjectByResourceId(id);
//		getUiObjectByResourceId(id).waitForExists(10000);
        getUiObjectByResourceId(id).clickAndWaitForNewWindow();
    }
    //等待desc并点击
    public static void waitForDescAndClick(String desc) throws UiObjectNotFoundException {
        waitForUiObjectByDesc(desc);
        getUiObjectByDesc(desc).clickAndWaitForNewWindow();
    }
    /**
     * 输出
     */
    public static void outputBegin() {//输出开始
        System.out.println(getNow()+"..-. ...- 开始！");
    }
    public static void outputNow() {//输出当前时间
        System.out.println(getNow());
    }
    public static void outputOver() {//输出结束
        System.out.println(getNow()+"..-. ...- 结束！");
    }

    //输出时间差
    public static void outputTimeDiffer(Date start, Date end) {
        long time = end.getTime() - start.getTime();
        double differ = (double)time/1000;
        output("总计用时"+differ+"秒！");
    }
    //明显输出
    public static void output(String text) {
        System.out.println(text);
    }
    //方法重载
    public static void output(String ...text) {
        for (int i = 0; i < text.length; i++) {
            System.out.println("第"+ (i+1) + "个："+ text[i]);
        }
    }
    public static void output(long num) {
        System.out.println(num);
    }
    public static void output(long ...num) {//方法重载
        for (int i = 0; i < num.length; i++) {
            System.out.println("第"+ (i+1) + "个："+ num[i]);
        }
    }
    public static void output(double num) {
        System.out.println(num);
    }
    public static void output(double ...num) {//方法重载
        for (int i = 0; i < num.length; i++) {
            System.out.println("第"+ (i+1) + "个："+ num[i]);
        }
    }
    public static void output(int num) {
        System.out.println(num);
    }
    public static void output(int ...num) {//方法重载
        for (int i = 0; i < num.length; i++) {
            System.out.println("第"+ (i+1) + "个："+ num[i]);
        }
    }
    public static void outpu(Object ...object) {
        for (int i = 0; i < object.length; i++) {
            System.out.println("第"+ (i+1) + "个："+ object[i]);
        }
    }
    public static void outpu(Object object) {
        System.out.println(object.toString());
    }

    /**
     * 滑动
     */
    public static void swipeLeft() {//左滑
        int y = UiDevice.getInstance().getDisplayHeight();
        int x = UiDevice.getInstance().getDisplayWidth();
        UiDevice.getInstance().swipe(x-100, y/2, 100, y/2, 20);
        setFast();
    }
    public static void swipeRight() {//右滑
        int y = UiDevice.getInstance().getDisplayHeight();
        int x = UiDevice.getInstance().getDisplayWidth();
        UiDevice.getInstance().swipe(100, y/2, x-100, y/2, 20);
        setFast();
    }
    public static void swipeDown() {//下滑
        int y = UiDevice.getInstance().getDisplayHeight();
        int x = UiDevice.getInstance().getDisplayWidth();
        UiDevice.getInstance().swipe(x/2, 200, x/2, y-200, 20);
        setFast();
    }
    public static void swipeUp() {//上滑
        int y = UiDevice.getInstance().getDisplayHeight();
        int x = UiDevice.getInstance().getDisplayWidth();
        UiDevice.getInstance().swipe(x/2, y-200, x/2, 200, 20);
        setFast();
    }
    public static void swipUpLittle() {//上滑一点点
        int x = UiDevice.getInstance().getDisplayWidth()/2;
        int y = UiDevice.getInstance().getDisplayHeight()/2;
        UiDevice.getInstance().swipe(x, y+150, x, y-150, 20);
        setFast();
    }
    public static void swipDownLittle() {//下拉一点点
        int x = UiDevice.getInstance().getDisplayWidth()/2;
        int y = UiDevice.getInstance().getDisplayHeight()/2;
        UiDevice.getInstance().swipe(x, y-150, x, y+150, 20);
        setFast();
    }
    //向前滚动
    public static boolean scrollForward() throws UiObjectNotFoundException {
        return getUiScrollabe().scrollForward(50);
    }
    //向后滚动
    public static boolean scrollBackward() throws UiObjectNotFoundException {
        return getUiScrollabe().scrollBackward(50);
    }
    /**
     * 等待
     */
    //等待对象出现
    public static void waitForUiObjectByText(String text) {
//		Date start = new Date();
//		boolean key = true;
//		while(key){
//			sleep(200);
//			UiObject it = new UiObject(new UiSelector().text(text));
//			if (it.exists()) {
//				key = false;
//			}
//			Date end = new Date();
//			long time = end.getTime() - start.getTime();
//			if (time>10000) {
//				output("超过10s没有出现！");
//				key = false;
//			}
//		}
        getUiObjectByText(text).waitForExists(10000);
    }
    public static void waitForUiObjectByStartText(String text) {
        getUiObjectByStartText(text).waitForExists(10000);
    }
    //等待控件出现
    public static void waitForUiObjectByClassName(String type) throws UiObjectNotFoundException {
        getUiObjectByClassName(type).waitForExists(10000);
    }
    public static void waitForUiObjectByTextContains(String text) {//等待对象出现
//		Date start = new Date();
//		boolean key = true;
//		while(key){
//			sleep(1000);
//			UiObject it = new UiObject(new UiSelector().textContains(text));
//			if (it.exists()) {
//				key = false;
//			}
//			Date end = new Date();
//			long time = end.getTime() - start.getTime();
//			if (time>10000) {
//				output("超过10s没有出现！");
//				key = false;
//			}
//		}
        getUiObjectByText(text).waitForExists(10000);
    }
    public static void waitForUiObjectByDesc(String desc) {//等待对象出现
//		Date start = new Date();
//		boolean key = true;
//		while(key){
//			sleep(1000);
//			UiObject it = new UiObject(new UiSelector().description(desc));
//			if (it.exists()) {
//				key = false;
//			}
//			Date end = new Date();
//			long time = end.getTime() - start.getTime();
//			if (time>10000) {
//				output("超过10s没有出现！");
//				key = false;
//			}
//		}
        getUiObjectByDesc(desc).waitForExists(10000);
    }
    public static void waitForUiObjectByResourceId(String id) {//等待对象出现
//		Date start = new Date();
//		boolean key = true;
//		while(key){
//			sleep(1000);
//			UiObject it = new UiObject(new UiSelector().resourceId(id));
//			if (it.exists()) {
//				key = false;
//			}
//			Date end = new Date();
//			long time = end.getTime() - start.getTime();
//			if (time>10000) {
//				output("超过10s没有出现！");
//				key = false;
//			}
//		}
        getUiObjectByResourceId(id).waitForExists(10000);
    }

    public static void waitForUiObject(UiSelector selector) {//等待对象出现
//		Date start = new Date();
//		boolean key = true;
//		while(key){
//			sleep(1000);
//			UiObject it = new UiObject(selector);
//			if (it.exists()) {
//				key = false;
//				}
//			Date end = new Date();
//			long time = end.getTime() - start.getTime();
//			if (time>10000) {
//				output("超过10秒没有出现！");
//				key = false;
//				}
//			}
        new UiObject(selector).waitForExists(10000);
    }
    //等待文本控件并点击
    public static void waitForClassNameAndClick(String type) throws UiObjectNotFoundException {
        waitForUiObjectByClassName(type);
//		getUiObjectByText(type).waitForExists(10000);
        getUiObjectByClassName(type).clickAndWaitForNewWindow();
    }
    public static void waitForTextAndClick(String text) throws UiObjectNotFoundException {
        waitForUiObjectByText(text);
//		getUiObjectByText(text).waitForExists(10000);
        getUiObjectByText(text).clickAndWaitForNewWindow();
    }
    public static void getUiObjectByResoureIdAndclickRightHalf(String id) throws UiObjectNotFoundException {
        //获取控件大小
        Rect sss = getUiObjectByResourceId(id).getBounds();
        //计算中心偏移量
        clickPiont(sss.centerX()+sss.width()/4, sss.centerY());
    }
    /**
     * 获取时间
     */
    //获取日期小时分钟
    public static String getDayHourMinute() {
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-HH-mm");
        String name = format.format(time);
        return name;
    }
    public static String getNow() {//获取当前时间
        Date time = new Date();
        SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String c = now.format(time);
        return c;
    }
    /**
     * 获取文本
     */
    public static String getTextByResourceId(String id) throws UiObjectNotFoundException {
        return getUiObjectByResourceId(id).getText();
    }
    public static String getDescByResourceI1d(String id) throws UiObjectNotFoundException {
        return getUiObjectByResourceId(id).getContentDescription();
    }
    public static String getTextByResourceIdClassName(String id,String type) throws UiObjectNotFoundException {
        return getUiObjectByResourceIdClassName(id, type).getText();
    }
    //获取兄弟控件的文本
    public static String getTextByBrother(String myid, String brotherid) throws UiObjectNotFoundException {
        return getUiObjectByResourceId(myid).getFromParent(new UiSelector().resourceId(brotherid)).getText();
    }
    /**
     * 清除中文文本
     */
    public static void clearTextByResourceId(String id) throws UiObjectNotFoundException {
        String name = getUiObjectByResourceId(id).getText();
//		output(name.length());
        UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_MOVE_END);
//		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_MOVE_HOME);
        //如果光标在最后
        pressTimes(KeyEvent.KEYCODE_DEL, name.length());
        //如果光标在最开始
//		pressTimes(KeyEvent.KEYCODE_FORWARD_DEL, name.length());
    }

    /**
     * 截图
     */

    public static String screenShot(String name) {//截图并命名
        File file = new File("/mnt/sdcard/123/");
        if (!file.exists()) {
            file.mkdirs();
        }
        File files = new File("/mnt/sdcard/123/"+name+".png");
        UiDevice.getInstance().takeScreenshot(files);
        output(name + ".png 截图成功！");
        String path = "/mnt/sdcard/123/" + name + ".png";
        return path;
    }
    public static String screenShot(int num) {//截图并命名
        File file = new File("/mnt/sdcard/123/");
        if (!file.exists()) {
            file.mkdirs();
        }
        File files = new File("/mnt/sdcard/123/"+num+".png");
        UiDevice.getInstance().takeScreenshot(files);
        output(num + ".png 截图成功！");
        String path = "/mnt/sdcard/123/" + num + ".png";
        return path;
    }

    public static void screenShot() {//截图name+time
        Context appContext = InstrumentationRegistry.getTargetContext();
        String name = AppUtils.getAppName(appContext);
        File file = new File("/mnt/sdcard/123/");
        if (!file.exists()) {
            file.mkdirs();
        }
        File files = new File(file.toString()+"/"+getDayHourMinute()+name+".png");
        UiDevice.getInstance().takeScreenshot(files);
        output("默认截图成功！");
    }
    //截取某个控件的图像
    public static Bitmap getBitmapByResourceId(String id) throws UiObjectNotFoundException {
        Rect rect = getUiObjectByResourceId(id).getVisibleBounds();//获取控件的rect对象
        String path = screenShot("test");//截图
        Bitmap bitmap = BitmapFactory.decodeFile(path);//创建并实例化bitmap对象
        bitmap = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());//截取bitmap实例
        return bitmap;
    }


    /**
     * 多功能
     */
    //压缩图片
    public static void compressPictureToJpeg(String oldPath, File newFile) throws FileNotFoundException {
        Bitmap bitmap = BitmapFactory.decodeFile(oldPath);//创建并实例化bitmap对象
        FileOutputStream out = new FileOutputStream(newFile);//创建文件输出流
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);//将图片转化为jpeg格式输出
    }
    //获取随机数
    public static int getRandomInt(int num) {
        return new Random().nextInt(num);
    }

    //屏幕提醒
    public static void warningTester() throws RemoteException {
        UiDevice.getInstance().sleep();//灭屏
        setLong();
        if (UiDevice.getInstance().isScreenOn()) {//获取屏幕状态
            return;//如果亮屏状态则结束运行
        } else {
            UiDevice.getInstance().wakeUp();//如果的灭屏状态则重新运行本方法
            warningTester();//递归
        }
    }

    public static void deleteScreenShot() {//删除截图文件夹
        File file = new File("/mnt/sdcard/123/");
        if (file.exists()) {//如果file存在
            File[] files = file.listFiles();//获取文件夹下文件列表
            for (int i = 0; i < files.length; i++) {//遍历删除
                files[i].delete();
            }
            file.delete();//最后删除文件夹，如果不存在直接删除文件夹
        } else {
            output("文件夹不存在！");
        }
    }

    public static void setShort() {//设置短等待
        Configurator.getInstance().setActionAcknowledgmentTimeout(500);
    }
    public static void setFast() {//设置短等待
        Configurator.getInstance().setActionAcknowledgmentTimeout(100);
    }
    public static void setLong() {//设置长等待
        Configurator.getInstance().setActionAcknowledgmentTimeout(1500);
    }
    //获取某一坐标点的颜色值
    public static int getColorPixel(int x, int y) {
        screenShot("test");//截图
        String path = "/mnt/sdcard/123/test.png";
        Bitmap bitmap = BitmapFactory.decodeFile(path);//新建并实例化bitmap对象
        int color = bitmap.getPixel(x, y);//获取坐标点像素颜色
//		output(color);//输出颜色值
        return color;
    }
    public static int getRedPixel(int x, int y) {
        screenShot("test");//截图
        String path = "/mnt/sdcard/123/test.png";
        Bitmap bitmap = BitmapFactory.decodeFile(path);//新建并实例化bitmap对象
        int color = bitmap.getPixel(x, y);//获取坐标点像素颜色
//		output(color);//输出颜色值
        int red = Color.red(color);
        return red;
    }
    public static int getGreenPixel(int x, int y) {
        screenShot("test");//截图
        String path = "/mnt/sdcard/123/test.png";
        Bitmap bitmap = BitmapFactory.decodeFile(path);//新建并实例化bitmap对象
        int color = bitmap.getPixel(x, y);//获取坐标点像素颜色
//		output(color);//输出颜色值
        int green = Color.green(color);
        return green;
    }
    public static int getBluePixel(int x, int y) {
        screenShot("test");//截图
        String path = "/mnt/sdcard/123/test.png";
        Bitmap bitmap = BitmapFactory.decodeFile(path);//新建并实例化bitmap对象
        int color = bitmap.getPixel(x, y);//获取坐标点像素颜色
//		output(color);//输出颜色值
        int blue = Color.blue(color);
        return blue;
    }
    public static int[] getRGBcolorPixel(int x, int y) {
        screenShot("testDemo");
        String path = "/mnt/sdcard/123/testDemo.png";
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        int color = bitmap.getPixel(x, y);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int[] rgb = {red, green, blue};
        return rgb;
    }
    //根据颜色判断状态
    public static boolean isBlue(UiObject uiObject) throws UiObjectNotFoundException {
        screenShot("test");//截图
        String path = "/mnt/sdcard/123/test.png";
        Bitmap bitmap = BitmapFactory.decodeFile(path);//新建并实例化bitmap对象
        Rect rect = uiObject.getVisibleBounds();
        int x = rect.left;
        int xx = rect.right;
        int y = rect.top;
        int yy = rect.bottom;
        List<Integer> blueColor = new ArrayList<Integer>();
        for (int i = x; i < xx; i++) {
            for (int k = y;k < yy;k++) {
                int color = bitmap.getPixel(i, k);//获取坐标点像素颜色
                int red = Color.blue(color);
                blueColor.add(red);
            }
        }
        int sum = 0;
        for (int i = 0;i<blueColor.size();i++) {
            sum += blueColor.get(i);
        }
//		output(sum/blueColor.size());
        return sum/blueColor.size() > 200?true:false;
    }
    /*
     * 图像对比
     * 默认图像宽高一致
     */
    public static boolean comparePicture(String path1, String path2, double limit) {
        Bitmap bitmap1 = BitmapFactory.decodeFile(path1);//创建并初始化bitmap对象
        Bitmap bitmap2 = BitmapFactory.decodeFile(path2);//创建并初始化bitmap对象
        int width = bitmap1.getWidth();//获取宽
        int height = bitmap1.getHeight();//获取高
        int total = 0;//统计相同次数
        int times = 0;//统计总次数
        //遍历像素点的颜色值，节省时间每次递增5个像素点
        for (int x = 0;x < width;x +=3) {
            for (int y = 0; y < height; y +=3) {
                int oldPic = bitmap1.getPixel(x, y);//获取颜色值
                int newPic = bitmap2.getPixel(x, y);//获取颜色值
//				int differ = Math.abs(ss - dd);//计算绝对差
                times++;
                if (oldPic == newPic) {//如果相等，则认为相同
                    total++;
                }
            }
        }
        double differ = total*1.0/times;
        output(differ);
        return differ > 0.99?true:false;//返回统计结果
    }
    //获取视频播放进度条
    public static double getVideoProgress(Bitmap bitmap) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        List<Integer> date = new ArrayList<Integer>();
        for (int i = 0;i < width; i++) {
            int color = bitmap.getPixel(i, height / 2);
            int red = Color.red(color);
//			output(red);
            date.add(red);
        }
        int date1 = 0,date2 = 0,date3 = 0,date4 = 0;
        int status1 = 0,status2 = 0;
        for (int i = 1;i < date.size() - 1;i++) {
            if (date.get(i) == 255 && status1 == 0) {
                status1++;
                date1 = i;
            }
            if (date.get(i) == 238 && status2 == 0) {
                status2++;
                date2 = i;
            }
            if (date.get(i + 1) < 238 && date.get(i) == 238) {
                date3 = i;
            }
            if (date.get(i + 1) < 165 && date.get(i) == 165) {
                date4 = i;
            }
        }
//		output(date1, date2, date3, date4);
//		output((date2 + date3 - date1 * 2.00)/(date4 - date1)/2);
        return (date2 + date3 - date1 * 2.00)/(date4 - date1)/2;
    }

    //画圆的方法
    public static void circle(int x, int y, int r) {
        double d = (double) (Math.PI/30);//角度
        double[] xxx = new double[61];
        for(int i=0;i<61;i++){
            xxx[i]=Math.cos(i*d);
        }
        //获取x坐标
        double[] yyy = new double[61];
        for(int i=1;i<61;i++){
            yyy[i]=Math.sin(i*d);
        }
        //获取y坐标
        int[] xxx1 = new int[61];
        for(int i=0;i<61;i++){
            xxx1[i]=(int) (xxx[i]*200);
        }
        //转化坐标值类型
        int[] yyy1 = new int[61];
        for(int i=0;i<61;i++){
            yyy1[i]=(int) (yyy[i]*200);
        }
        //转化坐标值类型
        Point[] p = new Point[61];
        for(int i=0;i<61;i++){
            p[i]=new Point();
            p[i].x = xxx1[i]+x;
            p[i].y = y-yyy1[i]+50;
        }
        //建立点数组
        UiDevice.getInstance().swipe(p, 2);
    }
    //画心形的方法
    public static void heart(int x, int y,int r) {
        double d = (double) (Math.PI/30);
        double[] angle = new double[61];//设置角度差
        for(int i=0;i<61;i++){
            angle[i]=i*d;
        }
        //建立一个角度差double数组
        double[] ox = new double[61];
        for(int i=0;i<61;i++){
            ox[i]= r*(2*Math.cos(angle[i])-Math.cos(2*angle[i]));
        }
        //计算x坐标
        double[] oy = new double[61];
        for(int i=0;i<61;i++){
            oy[i]=r*(2*Math.sin(angle[i])-Math.sin(2*angle[i]));
        }
        //计算y坐标
        Point[] heart = new Point[61];
        for(int i=0;i<61;i++){
            heart[i] = new Point();
            heart[i].x = (int) oy[i]+x;
            heart[i].y = -(int) ox[i]+y;
        }
        //建立一个点数组，这里坐标一定要转化一下，不然是倒着的心形
        UiDevice.getInstance().swipe(heart, 2);
    }

}
