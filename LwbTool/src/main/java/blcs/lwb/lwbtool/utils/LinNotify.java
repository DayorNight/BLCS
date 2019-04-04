package blcs.lwb.lwbtool.utils;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import blcs.lwb.lwbtool.R;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT;

/**
 * TODO 通知栏工具 BLCS
 * https://developer.android.google.cn/reference/android/support/v4/app/NotificationCompat.Builder
 */
public class LinNotify {

    public static final String NEW_MESSAGE = "chat";
    public static final String NEW_GROUP = "chat_group";
    public static final String OTHER_MESSAGE = "other";
    public static final String Ticker = "您有一条新的消息";
    public static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    public static int notifyId = 0;

    /**
     * TODO 适配 Android8.0  创建通知渠道
     * tips：可以写在MainActivity中，也可以写在Application中，实际上可以写在程序的任何位置，
     * 只需要保证在通知弹出之前调用就可以了。并且创建通知渠道的代码只在第一次执行的时候才会创建，
     * 以后每次执行创建代码系统会检测到该通知渠道已经存在了，因此不会重复创建，也并不会影响任何效率。
     */
    public static void setNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = NEW_MESSAGE;
            String channelName = "新消息通知";
            createNotificationChannel(context, channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            channelId = OTHER_MESSAGE;
            channelName = "其他通知";
            createNotificationChannel(context, channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
        }
    }

    /**
     * 创建配置通知渠道
     * @param channelId   渠道id
     * @param channelName 渠道nanme
     * @param importance  优先级
     */
    @TargetApi(Build.VERSION_CODES.O)
    private static void createNotificationChannel(Context context, String channelId, String channelName, int importance) {
//        createNotifycationGroup(context,channelId,channelName);
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(false);//禁止该渠道使用角标
//        channel.setGroup(channelId); //设置渠道组id
        // 配置通知渠道的属性
//        channel .setDescription("渠道的描述");
        // 设置通知出现时的闪灯（如果 android 设备支持的话）
        channel.enableLights(true);
        // 设置通知出现时的震动（如果 android 设备支持的话）
        channel.enableVibration(false);
        //如上设置使手机：静止1秒，震动2秒，静止1秒，震动3秒
//        channel.setVibrationPattern(new long[]{1000, 2000, 1000,3000});
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);//设置锁屏是否显示通知
        channel.setLightColor(Color.BLUE);
        channel.setBypassDnd(true);//设置是否可以绕过请勿打扰模式
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 创建渠道组(若通知渠道比较多的情况下可以划分渠道组)
     * @param groupId
     * @param groupName
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createNotifycationGroup(Context context,String groupId, String groupName) {
        NotificationChannelGroup group = new NotificationChannelGroup(groupId, groupName);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannelGroup(group);
    }

    /**
     * TODO: 发送通知（刷新前面的通知）
     *
     * @param context
     * @param contentTitle 标题
     * @param contentText  内容
     */
    public static void show(Context context, String contentTitle, String contentText, PendingIntent pendingIntent) {
        show(context, contentTitle, contentText,null, 0, NEW_MESSAGE, pendingIntent);
    }

    /**
     * TODO: 发送自定义通知（刷新前面的通知）
     *
     * @param context
     * @param contentTitle 标题
     * @param contentText  内容
     */
    public static void show(Context context, String contentTitle, String contentText,RemoteViews views ,PendingIntent pendingIntent) {
        show(context, contentTitle, contentText, views,0, NEW_MESSAGE, pendingIntent);
    }

    /**
     * 发送通知（刷新前面的通知，指定通知渠道）
     *
     * @param contentTitle 标题
     * @param contentText  内容
     * @param channelId    渠道id
     */
    public static void show(Context context, String contentTitle, String contentText, String channelId, PendingIntent pendingIntent) {
        show(context, contentTitle, contentText,null, 0, channelId, pendingIntent);
    }

    /**
     * 发送自定义通知（刷新前面的通知，指定通知渠道）
     *
     * @param contentTitle 标题
     * @param contentText  内容
     * @param channelId    渠道id
     */
    public static void show(Context context, String contentTitle, String contentText,RemoteViews views, String channelId, PendingIntent pendingIntent) {
        show(context, contentTitle, contentText,views, 0, channelId, pendingIntent);
    }

    /**
     * 发送多条通知（默认通知渠道）
     *
     * @param contentTitle 标题
     * @param contentText  内容
     */
    public static void showMuch(Context context, String contentTitle, String contentText, PendingIntent pendingIntent) {
        show(context, contentTitle, contentText,null, ++notifyId, NEW_MESSAGE, pendingIntent);
    }

    /**
     * 发送多条自定义通知（默认通知渠道）
     *
     * @param contentTitle 标题
     * @param contentText  内容
     */
    public static void showMuch(Context context, String contentTitle, String contentText,RemoteViews views, PendingIntent pendingIntent) {
        show(context, contentTitle, contentText,views, ++notifyId, NEW_MESSAGE, pendingIntent);
    }

    /**
     * 发送多条通知（指定通知渠道）
     *
     * @param contentTitle 标题
     * @param contentText  内容
     * @param channelId    渠道id
     */
    public static void showMuch(Context context, String contentTitle, String contentText, String channelId, PendingIntent pendingIntent) {
        show(context, contentTitle, contentText,null, ++notifyId, channelId, pendingIntent);
    }


    /**
     * 发送多条自定义通知（指定通知渠道）
     *
     * @param contentTitle 标题
     * @param contentText  内容
     * @param channelId    渠道id
     */
    public static void showMuch(Context context, String contentTitle, String contentText, String channelId,RemoteViews views, PendingIntent pendingIntent) {
        show(context, contentTitle, contentText,views, ++notifyId, channelId, pendingIntent);
    }

    /**
     * 发送通知（设置默认：大图标/小图标/小标题/副标题/优先级/首次弹出文本）
     *
     * @param contentTitle 标题
     * @param contentText  内容
     * @param notifyId     通知栏id
     * @param channelId    设置渠道id
     * @param pendingIntent          意图类
     */
    public static void show(Context context, String contentTitle, String contentText, RemoteViews views,int notifyId, String channelId, PendingIntent pendingIntent) {
        show(context, 0, 0, contentTitle, null, contentText, PRIORITY_DEFAULT, null,views ,notifyId, channelId, pendingIntent);
    }

    /**
     * 发送通知
     *
     * @param largeIcon    大图标
     * @param smallIcon    小图标
     * @param contentTitle 标题
     * @param subText      小标题/副标题
     * @param contentText  内容
     * @param priority     优先级
     * @param ticker       通知首次弹出时，状态栏上显示的文本
     * @param notifyId     定义是否显示多条通知栏
     * @param pendingIntent          意图类
     */
    public static void show(Context context, int largeIcon,
                            int smallIcon, String contentTitle,
                            String subText, String contentText,
                            int priority, String ticker, RemoteViews view,
                            int notifyId, String channelId, PendingIntent pendingIntent) {
        //flags
        // FLAG_ONE_SHOT:表示此PendingIntent只能使用一次的标志
        // FLAG_IMMUTABLE:指示创建的PendingIntent应该是不可变的标志
        // FLAG_NO_CREATE : 指示如果描述的PendingIntent尚不存在，则只返回null而不是创建它。
        // FLAG_CANCEL_CURRENT :指示如果所描述的PendingIntent已存在，则应在生成新的PendingIntent,取消之前PendingIntent
        // FLAG_UPDATE_CURRENT : 指示如果所描述的PendingIntent已存在，则保留它，但将其额外数据替换为此新Intent中的内容
//
        //获取通知服务管理器
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        //判断应用通知是否打开
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!openNotificationChannel(context, manager, channelId)) return;
        }

        //创建 NEW_MESSAGE 渠道通知栏  在API级别26.1.0中推荐使用此构造函数 Builder(context, 渠道名)
        NotificationCompat.Builder builder = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) ? new NotificationCompat.Builder(context, channelId) : new NotificationCompat.Builder(context);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon == 0 ? R.mipmap.ic_launcher : largeIcon)) //设置自动收报机和通知中显示的大图标。
                .setSmallIcon(smallIcon == 0 ? R.mipmap.ic_launcher : smallIcon) // 小图标
                .setContentText(TextUtils.isEmpty(contentText)? null : contentText) // 内容
                .setContentTitle(TextUtils.isEmpty(contentTitle) ? null : contentTitle) // 标题
                .setSubText(TextUtils.isEmpty(subText) ? null : subText) // APP名称的副标题
                .setPriority(priority) //设置优先级 PRIORITY_DEFAULT
                .setTicker(TextUtils.isEmpty(ticker) ? Ticker : ticker) // 设置通知首次弹出时，状态栏上显示的文本
                .setContent(view)
                .setWhen(System.currentTimeMillis()) // 设置通知发送的时间戳
                .setShowWhen(true)//设置是否显示时间戳
                .setAutoCancel(true)// 点击通知后通知在通知栏上消失
                .setDefaults(Notification.PRIORITY_HIGH)// 设置默认的提示音、振动方式、灯光等 使用的默认通知选项
                .setContentIntent(pendingIntent) // 设置通知的点击事件
                //锁屏状态下显示通知图标及标题 1、VISIBILITY_PUBLIC 在所有锁定屏幕上完整显示此通知/2、VISIBILITY_PRIVATE 隐藏安全锁屏上的敏感或私人信息/3、VISIBILITY_SECRET 不显示任何部分
                .setVisibility(Notification.VISIBILITY_PUBLIC)//部分手机没效果
                .setFullScreenIntent(pendingIntent,true)//悬挂式通知8.0需手动打开
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
     * 判断应用渠道通知是否打开（适配8.0）
     * @return true 打开
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static Boolean openNotificationChannel(Context context, NotificationManager manager, String channelId) {
        //判断通知是否有打开
        if (!isNotificationEnabled(context)) {
            toNotifySetting(context, null);
            return false;
        }
        //判断渠道通知是否打开
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel(channelId);
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                //没打开调往设置界面
                toNotifySetting(context, channel.getId());
                return false;
            }
        }

        return true;
    }

    /**
     * 判断应用通知是否打开
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isNotificationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            return notificationManagerCompat.areNotificationsEnabled();
        }
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        Class appOpsClass = null;
        /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, context.getApplicationInfo().uid, context.getPackageName()) == AppOpsManager.MODE_ALLOWED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 手动打开应用通知
     */
    public static void toNotifySetting(Context context, String channelId) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //适配 8.0及8.0以上(8.0需要先打开应用通知，再打开渠道通知)
            if(TextUtils.isEmpty(channelId)){
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            }else{
                intent.setAction(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//适配 5.0及5.0以上
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {// 适配 4.4及4.4以上
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else {
            intent.setAction(Settings.ACTION_SETTINGS);
        }
        context.startActivity(intent);
    }

}
