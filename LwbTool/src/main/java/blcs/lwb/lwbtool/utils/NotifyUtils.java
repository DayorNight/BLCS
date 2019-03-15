package blcs.lwb.lwbtool.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import blcs.lwb.lwbtool.R;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT;

/**
 * TODO 通知栏工具 BLCS
 *https://developer.android.google.cn/reference/android/support/v4/app/NotificationCompat.Builder
 * 悬浮窗适配 https://www.jianshu.com/p/1649c2bd249c
 */
public class NotifyUtils {
    public static final int RequestCode=1;
    public static final String NEW_MESSAGE="chat";
    public static final String NEW_GROUP="chat_group";
    public static final String OTHER_MESSAGE="other";
    public static final String Ticker="您有一条新的消息";

    public static int notifyId = 0;
    /**
     *  适配 Android8.0  设置通知渠道
     * 可以写在MainActivity中，也可以写在Application中，实际上可以写在程序的任何位置，
     * 只需要保证在通知弹出之前调用就可以了。并且创建通知渠道的代码只在第一次执行的时候才会创建，
     * 以后每次执行创建代码系统会检测到该通知渠道已经存在了，因此不会重复创建，也并不会影响任何效率。
     * @param context
     */
    public static void setNotificationChannel(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = NEW_MESSAGE;
            String channelName = "新消息通知";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(context,channelId, channelName, importance);
            channelId = OTHER_MESSAGE;
            channelName = "其他通知";
            int importance1 = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(context,channelId, channelName, importance1);
        }
    }

    /**
     * 创建通知渠道
     * @param context
     * @param channelId  渠道id
     * @param channelName  渠道nanme
     * @param importance  优先级
     */
    @TargetApi(Build.VERSION_CODES.O)
    private static void createNotificationChannel(Context context,String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(false);//禁止该渠道使用角标
        // 配置通知渠道的属性
//        channel .setDescription("渠道的描述");
        // 设置通知出现时的闪灯（如果 android 设备支持的话）
        channel .enableLights(true);
        // 设置通知出现时的震动（如果 android 设备支持的话）
        channel.enableVibration(true);
        //如上设置使手机：静止1秒，震动2秒，静止1秒，震动3秒
//        channel.setVibrationPattern(new long[]{1000, 2000, 1000,3000});
        channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);//锁屏显示通知
        channel.setLightColor(Color.BLUE);
        channel.setBypassDnd(true);//设置是否可以绕过请勿打扰模式
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 判断渠道通知是否关闭 跳转系统设置通知界面
     * @param manager
     */
    public static Boolean openNotificationChannel(Context context,NotificationManager manager,String channelId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel(channelId);
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                context.startActivity(intent);
                Toast.makeText(context, "请手动打开通知", Toast.LENGTH_SHORT).show();
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * 发送通知
     * @param context
     * @param largeIcon 大图标
     * @param smallIcon 小图标
     * @param contentTitle 标题
     * @param subText 小标题/副标题
     * @param contentText 内容
     * @param priority 优先级
     * @param ticker  通知首次弹出时，状态栏上显示的文本
     * @param notifyId  定义是否显示多条通知栏
     */
    public  static  void show(Context context,int largeIcon,
                              int smallIcon,String contentTitle,
                              String subText,String contentText,
                              int priority, String ticker,
                              int notifyId,String channelId) {
        //flags
        // FLAG_ONE_SHOT:表示此PendingIntent只能使用一次的标志
        // FLAG_IMMUTABLE:指示创建的PendingIntent应该是不可变的标志
        // FLAG_NO_CREATE : 指示如果描述的PendingIntent尚不存在，则只返回null而不是创建它。
        // FLAG_CANCEL_CURRENT :指示如果所描述的PendingIntent已存在，则应在生成新的PendingIntent,取消之前PendingIntent
        // FLAG_UPDATE_CURRENT : 指示如果所描述的PendingIntent已存在，则保留它，但将其额外数据替换为此新Intent中的内容

//        Intent intent = new Intent(context, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context,RequestCode,intent,FLAG_UPDATE_CURRENT);

        //获取通知服务管理器
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if(openNotificationChannel(context,manager,channelId)) return;
        NotificationCompat.Builder builder;
        //创建 NEW_MESSAGE 渠道通知栏  在API级别26.1.0中推荐使用此构造函数 Builder(context, 渠道名)
        builder =(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)? new NotificationCompat.Builder(context, channelId) :new NotificationCompat.Builder(context);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),largeIcon == 0 ? R.mipmap.ic_launcher : largeIcon)) //设置自动收报机和通知中显示的大图标。
                .setSmallIcon(smallIcon == 0 ? R.mipmap.ic_launcher:smallIcon) // 小图标
                .setContentText(contentText==null ? null:contentText) // 内容
                .setContentTitle(contentTitle==null ? null:contentTitle) // 标题
                .setSubText(subText==null ? null:subText) // APP名称的副标题
                .setPriority(priority) //设置优先级 PRIORITY_DEFAULT
                .setTicker(ticker==null?Ticker:ticker) // 设置通知首次弹出时，状态栏上显示的文本
                .setWhen(System.currentTimeMillis()) // 设置通知发送的时间戳
                .setShowWhen(true)//设置是否显示时间戳
                .setAutoCancel(true)// 点击通知后通知在通知栏上消失
                .setDefaults(Notification.PRIORITY_HIGH)// 设置默认的提示音、振动方式、灯光等 使用的默认通知选项
//                .setContentIntent(pendingIntent) // 设置通知的点击事件
                //锁屏状态下显示通知图标及标题 1、VISIBILITY_PUBLIC 在所有锁定屏幕上完整显示此通知/2、VISIBILITY_PRIVATE 隐藏安全锁屏上的敏感或私人信息/3、VISIBILITY_SECRET 不显示任何部分
                .setVisibility(Notification.VISIBILITY_PRIVATE)
//                .setColorized(true)
//                .setGroupSummary(true)//将此通知设置为一组通知的组摘要
//                .setGroup(NEW_GROUP)//使用组密钥
//                .setDeleteIntent(pendingIntent)//当用户直接从通知面板清除通知时 发送意图
//                .setFullScreenIntent(pendingIntent,true)
//                .setContentInfo("大文本")//在通知的右侧设置大文本。
//                .setContent(RemoteViews RemoteView)//设置自定义通知栏
//                .setColor(Color.parseColor("#ff0000"))
//                .setLights()//希望设备上的LED闪烁的argb值以及速率
//                .setTimeoutAfter(3000)//指定取消此通知的时间（如果尚未取消）。
                ;

        // 通知栏id
        manager.notify(notifyId, builder.build()); // build()方法需要的最低API为16 ,
    }

    /**
     * 发送通知（刷新前面的通知）
     * @param context
     * @param contentTitle 标题
     * @param contentText 内容
     */
    public  static  void show(Context context,String contentTitle,String contentText){
        show(context,0,0,contentTitle,null,contentText,PRIORITY_DEFAULT,null,0,NEW_MESSAGE);
    }
    /**
     * 发送通知（刷新前面的通知）
     * @param context
     * @param contentTitle 标题
     * @param contentText 内容
     * @param channelId 渠道id
     */
    public  static  void show(Context context,String contentTitle,String contentText,String channelId){
        show(context,0,0,contentTitle,null,contentText,PRIORITY_DEFAULT,null,0,channelId);
    }
    /**
     * 发送通知
     * @param context
     * @param contentTitle 标题
     * @param contentText 内容
     */
    public  static  void show1(Context context,String contentTitle,String contentText){
        show(context,0,0,contentTitle,null,contentText,PRIORITY_DEFAULT,null,++notifyId,NEW_MESSAGE);
    }

    /**
     * 发送通知
     * @param context
     * @param contentTitle 标题
     * @param contentText 内容
     * @param channelId 渠道id
     */
    public  static  void show1(Context context,String contentTitle,String contentText,String channelId){
        show(context,0,0,contentTitle,null,contentText,PRIORITY_DEFAULT,null,++notifyId,channelId);
    }

    /**
     * 发送通知多条通知消息
     * @param context
     * @param contentTitle 标题
     * @param contentText 内容
     * @param notifyId 设置不同id 显示多条通知栏
     * @param channelId 设置渠道id
     */
    public  static  void show(Context context,String contentTitle,String contentText,int notifyId,String channelId){
        show(context,0,0,contentTitle,null,contentText,PRIORITY_DEFAULT,null,notifyId,channelId);
    }

}
