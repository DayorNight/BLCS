## ----------------------------------
##      混淆基本指令
## ----------------------------------
-optimizationpasses 5   # 设置混淆的压缩比率 0 ~ 7
-dontusemixedcaseclassnames  # 混淆时不使用大小写混合，混淆后的类名为小写
-dontskipnonpubliclibraryclasses # 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclassmembers # 指定不去忽略非公共库的成员
-dontpreverify          # 混淆时不做预校验
-verbose                # 混淆时不记录日志
-ignorewarning          # 忽略警告
-dontshrink             # 代码优化
-dontoptimize           # 不优化输入的类文件
-keepattributes *Annotation*,InnerClasses  # 保留注解不混淆
-keepattributes Signature       # 避免混淆泛型
-keepattributes SourceFile,LineNumberTable  # 保留代码行号，方便异常信息的追踪
-optimizations !code/simplification/cast,!field/*,!class/merging/*  # 混淆采用的算法
-dump class_files.txt           # dump.txt文件列出apk包内所有class的内部结构
-printseeds seeds.txt           # seeds.txt文件列出未混淆的类和成员
-printusage unused.txt          # usage.txt文件列出从apk中删除的代码
-printmapping mapping.txt       # mapping.txt文件列出混淆前后的映射


## ----------------------------------
##      Android基本配置
## ----------------------------------
#support.v4不能混淆
-dontwarn android.support.v4.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }
#support.v7不能混淆
-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }
#四大组件不能混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
# 保留继承的
-keep public class * implements android.support.v4.**
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
#Design包不混淆
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
#避免混淆自定义控件类的 get/set 方法和构造函数
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#所有的native方法不被混淆
-keepclasseswithmembers class * {
    native <methods>;
}
#自定义View构造方法不混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet,int);
}
#枚举不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#release版不打印log
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
    public static *** w(...);
}
#不混淆资源类
-keep class **.R$* {*;}
-keepclassmembers class **.R$* {
    public static <fields>;
}
#避免layout中onclick方法（android:onclick="onClick"）混淆
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
#避免回调函数 onXXEvent 混淆
-keepclassmembers class * {
    void *(*Event);
}
#避免Parcelable混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#避免Serializable接口的子类中指定的某些成员变量和方法混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keepclassmembers class * implements java.io.Serializable { *; }
## ----------------------------------
##      #WebView混淆配置
## ----------------------------------

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
## ----------------------------------
##      确保JavaBean不被混淆-否则Gson将无法将数据解析成具体对象
## ----------------------------------
-keep class blcs.lwb.utils.bean.** { *; }
-keep class blcs.lwb.lwbtool.bean.** { *; }
#-keep class com.rair.circle.db.bean.** { *; }

## ----------------------------------
##      Gson混淆配置
## ----------------------------------
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }


## ----------------------------------
##      微信 混淆配置https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=1417751808&token=&lang=zh_CN
## ----------------------------------
-keep class com.tencent.mm.opensdk.** {
    *;
}
-keep class com.tencent.wxop.** {
    *;
}
-keep class com.tencent.mm.sdk.** {
    *;
}
## ----------------------------------
##      Banner混淆配置
## ----------------------------------

-keep class com.youth.banner.** {
    *;
 }

## ----------------------------------
##      友盟统计
## ----------------------------------
-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep public class blcs.lwb.lwbtool.R$*{
public static final int *;
}

## ----------------------------------
##      Agentweb
## ----------------------------------
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**
-keepclassmembers class com.just.agentweb.sample.common.AndroidInterface{ *; }

## ----------------------------------
##      BaseRecyclerViewAdapterHelper  https://github.com/CymChad/BaseRecyclerViewAdapterHelper
## ----------------------------------

-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}

## ----------------------------------
##      butterknife
## ----------------------------------
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}


## ----------------------------------
##      okhttp3   https://github.com/square/okhttp/blob/master/okhttp/src/main/resources/META-INF/proguard/okhttp3.pro
## ----------------------------------
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
#-dontwarn javax.annotation.**
#-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
#-dontwarn org.codehaus.mojo.animal_sniffer.*
#-dontwarn okhttp3.internal.platform.ConscryptPlatform

## ----------------------------------
##      retrofit2   https://github.com/square/retrofit/blob/master/retrofit/src/main/resources/META-INF/proguard/retrofit2.pro
## ----------------------------------
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#-keepattributes Signature, InnerClasses, EnclosingMethod
#-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
#-keepclassmembers,allowshrinking,allowobfuscation interface * {
#    @retrofit2.http.* <methods>;
#}
#-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
#-dontwarn javax.annotation.**
#-dontwarn kotlin.Unit
#-dontwarn retrofit2.KotlinExtensions
#-if interface * { @retrofit2.http.* <methods>; }
#-keep,allowobfuscation interface <1>

## ----------------------------------
##      RxJava、RxAndroid混淆配置
## ----------------------------------

-dontwarn org.mockito.**
-dontwarn org.junit.**
-dontwarn org.robolectric.**

-keep class rx.** { *; }
-keep interface rx.** { *; }

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-dontwarn okio.**
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep class sun.misc.Unsafe { *; }

-dontwarn java.lang.invoke.*

-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}
-dontwarn rx.internal.util.unsafe.**

## ----------------------------------
##      Fastjson
## ----------------------------------
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*; }

## ----------------------------------
##     zxing
## ----------------------------------
-keep class com.google.zxing.** {*;}
-dontwarn com.google.zxing.**
## ----------------------------------
##      BottomNavigationView
## ----------------------------------
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
    boolean mShiftingMode;
}

## ----------------------------------
##      Glide4.9.0  https://github.com/bumptech/glide
## ----------------------------------
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public class * extends com.bumptech.glide.module.AppGlideModule
 -keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

## ----------------------------------
##     eventbus  http://greenrobot.org/eventbus/documentation/proguard
## ----------------------------------
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
## ----------------------------------
##     ImmersionBar  https://github.com/gyf-dev/ImmersionBar
## ----------------------------------
-keep class com.gyf.barlibrary.* {*;}
-dontwarn com.gyf.barlibrary.**

## ----------------------------------
##     华为推送  https://developer.huawei.com/consumer/cn/service/hms/catalog/huaweipush_agent.html?page=hmssdk_huaweipush_devprepare_agent#5.3%20%E9%85%8D%E7%BD%AE%E6%B7%B7%E6%B7%86%E8%84%9A%E6%9C%AC%EF%BC%88%E5%BF%85%E9%80%89%EF%BC%89
## ----------------------------------
-ignorewarning
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}
-keep class com.huawei.android.hms.agent.**{*;}

## ----------------------------------
##     nineoldandroids
## ----------------------------------
-keep class com.nineoldandro#https://www.jianshu.com/p/d1cbe2fdb82c
#https://www.jianshu.com/p/90feb5c50cce

## ----------------------------------
##      混淆基本指令
## ----------------------------------
-optimizationpasses 5   # 设置混淆的压缩比率 0 ~ 7
-dontusemixedcaseclassnames  # 混淆时不使用大小写混合，混淆后的类名为小写
-dontskipnonpubliclibraryclasses # 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclassmembers # 指定不去忽略非公共库的成员
-dontpreverify          # 混淆时不做预校验
-verbose                # 混淆时不记录日志
-ignorewarning          # 忽略警告
-dontshrink             # 代码优化
-dontoptimize           # 不优化输入的类文件
-keepattributes *Annotation*,InnerClasses  # 保留注解不混淆
-keepattributes Signature       # 避免混淆泛型
-keepattributes SourceFile,LineNumberTable  # 保留代码行号，方便异常信息的追踪
-optimizations !code/simplification/cast,!field/*,!class/merging/*  # 混淆采用的算法
-dump class_files.txt           # dump.txt文件列出apk包内所有class的内部结构
-printseeds seeds.txt           # seeds.txt文件列出未混淆的类和成员
-printusage unused.txt          # usage.txt文件列出从apk中删除的代码
-printmapping mapping.txt       # mapping.txt文件列出混淆前后的映射


## ----------------------------------
##      Android基本配置
## ----------------------------------
#support.v4不能混淆
-dontwarn android.support.v4.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }
#support.v7不能混淆
-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }
#四大组件不能混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
# 保留继承的
-keep public class * implements android.support.v4.**
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
#Design包不混淆
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
#避免混淆自定义控件类的 get/set 方法和构造函数
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#所有的native方法不被混淆
-keepclasseswithmembers class * {
    native <methods>;
}
#自定义View构造方法不混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet,int);
}
#枚举不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#release版不打印log
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
    public static *** w(...);
}
#不混淆资源类
-keep class **.R$* {*;}
-keepclassmembers class **.R$* {
    public static <fields>;
}
#避免layout中onclick方法（android:onclick="onClick"）混淆
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
#避免回调函数 onXXEvent 混淆
-keepclassmembers class * {
    void *(*Event);
}
#避免Parcelable混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#避免Serializable接口的子类中指定的某些成员变量和方法混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keepclassmembers class * implements java.io.Serializable { *; }
#WebView混淆配置
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
## ----------------------------------
##      确保JavaBean不被混淆-否则Gson将无法将数据解析成具体对象
## ----------------------------------
#-keep class com.rair.circle.bean.** { *; }
#-keep class com.rair.circle.db.bean.** { *; }

## ----------------------------------
##      Gson混淆配置
## ----------------------------------
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }


## ----------------------------------
##      微信 混淆配置https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=1417751808&token=&lang=zh_CN
## ----------------------------------
-keep class com.tencent.mm.opensdk.** {
    *;
}
-keep class com.tencent.wxop.** {
    *;
}
-keep class com.tencent.mm.sdk.** {
    *;
}
## ----------------------------------
##      Banner混淆配置
## ----------------------------------

-keep class com.youth.banner.** {
    *;
 }

## ----------------------------------
##      友盟统计
## ----------------------------------
-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep public class blcs.lwb.lwbtool.R$*{
public static final int *;
}

## ----------------------------------
##      Agentweb
## ----------------------------------
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**
-keepclassmembers class com.just.agentweb.sample.common.AndroidInterface{ *; }

## ----------------------------------
##      BaseRecyclerViewAdapterHelper  https://github.com/CymChad/BaseRecyclerViewAdapterHelper
## ----------------------------------

-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}

## ----------------------------------
##      butterknife
## ----------------------------------
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}


## ----------------------------------
##      okhttp3   https://github.com/square/okhttp/blob/master/okhttp/src/main/resources/META-INF/proguard/okhttp3.pro
## ----------------------------------
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
#-dontwarn javax.annotation.**
#-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
#-dontwarn org.codehaus.mojo.animal_sniffer.*
#-dontwarn okhttp3.internal.platform.ConscryptPlatform

## ----------------------------------
##      retrofit2   https://github.com/square/retrofit/blob/master/retrofit/src/main/resources/META-INF/proguard/retrofit2.pro
## ----------------------------------
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#-keepattributes Signature, InnerClasses, EnclosingMethod
#-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
#-keepclassmembers,allowshrinking,allowobfuscation interface * {
#    @retrofit2.http.* <methods>;
#}
#-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
#-dontwarn javax.annotation.**
#-dontwarn kotlin.Unit
#-dontwarn retrofit2.KotlinExtensions
#-if interface * { @retrofit2.http.* <methods>; }
#-keep,allowobfuscation interface <1>

## ----------------------------------
##      RxJava、RxAndroid混淆配置
## ----------------------------------

-dontwarn org.mockito.**
-dontwarn org.junit.**
-dontwarn org.robolectric.**

-keep class rx.** { *; }
-keep interface rx.** { *; }

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-dontwarn okio.**
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep class sun.misc.Unsafe { *; }

-dontwarn java.lang.invoke.*

-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}
-dontwarn rx.internal.util.unsafe.**

## ----------------------------------
##      Fastjson
## ----------------------------------
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*; }

## ----------------------------------
##     zxing
## ----------------------------------
-keep class com.google.zxing.** {*;}
-dontwarn com.google.zxing.**
#===============can't find referenced method 'int save(int)' in library class android.graphics.Canvas=============
-dontwarn com.mylhyl.zxing.scanner.**
## ----------------------------------
##      BottomNavigationView
## ----------------------------------
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
    boolean mShiftingMode;
}

## ----------------------------------
##      Glide4.9.0  https://github.com/bumptech/glide
## ----------------------------------
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public class * extends com.bumptech.glide.module.AppGlideModule
 -keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }
 # for DexGuard only
# -keepresourcexmlelements manifest/application/meta-data@value=GlideModule

## ----------------------------------
##     eventbus  http://greenrobot.org/eventbus/documentation/proguard
## ----------------------------------
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
## ----------------------------------
##     ImmersionBar  https://github.com/gyf-dev/ImmersionBar
## ----------------------------------
-keep class com.gyf.barlibrary.* {*;}
-dontwarn com.gyf.barlibrary.**

## ----------------------------------
##     华为推送  https://developer.huawei.com/consumer/cn/service/hms/catalog/huaweipush_agent.html?page=hmssdk_huaweipush_devprepare_agent#5.3%20%E9%85%8D%E7%BD%AE%E6%B7%B7%E6%B7%86%E8%84%9A%E6%9C%AC%EF%BC%88%E5%BF%85%E9%80%89%EF%BC%89
## ----------------------------------
-ignorewarning
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}
-keep class com.huawei.android.hms.agent.**{*;}

## ----------------------------------
##     nineoldandroids
## ----------------------------------
-keep class com.nineoldandroids.animation.** { *; }
-keep interface com.nineoldandroids.animation.** { *; }
-keep class com.nineoldandroids.view.** { *; }
-keep interface com.nineoldandroids.view.** { *; }

## ----------------------------------
##     高德定位 https://lbs.amap.com/api/android-location-sdk/guide/create-project/dev-attention
## ----------------------------------
# 3D 地图 V5.0.0之前：
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.amap.mapcore.*{*;}
-keep   class com.amap.api.trace.**{*;}
#    3D 地图 V5.0.0之后：
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.**{*;}
-keep   class com.amap.api.trace.**{*;}
#    定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
#    搜索
-keep   class com.amap.api.services.**{*;}
#    2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}
#    导航
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}

## ----------------------------------
##     nineoldandroids
## ----------------------------------
-keep class com.nineoldandroids.animation.** { *; }
-keep interface com.nineoldandroids.animation.** { *; }
-keep class com.nineoldandroids.view.** { *; }
-keep interface com.nineoldandroids.view.** { *; }
## ----------------------------------
##     高德定位 https://lbs.amap.com/api/android-location-sdk/guide/create-project/dev-attention
## ----------------------------------
# 3D 地图 V5.0.0之前：
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.amap.mapcore.*{*;}
-keep   class com.amap.api.trace.**{*;}
#    3D 地图 V5.0.0之后：
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.**{*;}
-keep   class com.amap.api.trace.**{*;}
#    定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
#    搜索
-keep   class com.amap.api.services.**{*;}
#    2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}
#    导航
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}
## ----------------------------------
##    获取应用大小  AIDL混淆配置
## ----------------------------------
-keepnames public interface android.content.pm.IPackageStatsObserver { *; }
-keepnames public interface android.content.pm.IPackageDataObserver { *; }
-keepnames public interface android.content.pm.PackageStats { *; }
-keep class android.content.pm.IPackageStatsObserver

## ----------------------------------
##          greendao
## ----------------------------------
-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class net.sqlcipher.database.**{*;}
-keep public interface net.sqlcipher.database.**
-dontwarn net.sqlcipher.database.**
-dontwarn org.greenrobot.greendao.**

## ----------------------------------
##          XmlResourceParser
## ----------------------------------
-keep class org.xmlpull.v1.** { *;}
-dontwarn org.xmlpull.v1.**