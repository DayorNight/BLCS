## ----------------------------------
##      混淆基本指令
## ----------------------------------
-optimizationpasses 5   # 设置混淆的压缩比率 0 ~ 7
-dontusemixedcaseclassnames  # 混淆时不使用大小写混合，混淆后的类名为小写
-dontskipnonpubliclibraryclasses # 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclassmembers # 指定不去忽略非公共库的成员
-dontpreverify          # 混淆时不做预校验
-verbose                # 混淆时不记录日志
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

#四大组件不能混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

#Design包不混淆
-dontwarn android.support.design.**
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


## ----------------------------------
##      确保JavaBean不被混淆-否则Gson将无法将数据解析成具体对象
## ----------------------------------

#-keep class com.rair.circle.db.bean.** { *; }

## ----------------------------------
##      Gson混淆配置
## ----------------------------------

## ----------------------------------
##      微信 混淆配置https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=1417751808&token=&lang=zh_CN
## ----------------------------------

## ----------------------------------
##      Banner混淆配置
## ----------------------------------


## ----------------------------------
##      友盟统计
## ----------------------------------
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
-dontwarn com.just.agentweb.**

## ----------------------------------
##      BaseRecyclerViewAdapterHelper  https://github.com/CymChad/BaseRecyclerViewAdapterHelper
## ----------------------------------

-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter

## ----------------------------------
##      butterknife
## ----------------------------------
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
-dontwarn okio.**
-dontwarn okhttp3.**
#-dontwarn javax.annotation.**
#-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
#-dontwarn org.codehaus.mojo.animal_sniffer.*
#-dontwarn okhttp3.internal.platform.ConscryptPlatform

## ----------------------------------
##      retrofit2   https://github.com/square/retrofit/blob/master/retrofit/src/main/resources/META-INF/proguard/retrofit2.pro
## ----------------------------------
-dontwarn retrofit2.**
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
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


-keepattributes Signature
-keepattributes *Annotation*
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit.**
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}


-dontwarn java.lang.invoke.*

-dontwarn rx.internal.util.unsafe.**

## ----------------------------------
##      Fastjson
## ----------------------------------
-dontwarn com.alibaba.fastjson.**

## ----------------------------------
##     zxing
## ----------------------------------
-dontwarn com.google.zxing.**
## ----------------------------------
##      BottomNavigationView
## ----------------------------------

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

## ----------------------------------
##     ImmersionBar  https://github.com/gyf-dev/ImmersionBar
## ----------------------------------
-keep class com.gyf.barlibrary.* {*;}
-dontwarn com.gyf.barlibrary.**

## ----------------------------------
##     华为推送  https://developer.huawei.com/consumer/cn/service/hms/catalog/huaweipush_agent.html?page=hmssdk_huaweipush_devprepare_agent#5.3%20%E9%85%8D%E7%BD%AE%E6%B7%B7%E6%B7%86%E8%84%9A%E6%9C%AC%EF%BC%88%E5%BF%85%E9%80%89%EF%BC%89
## ----------------------------------
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable

## ----------------------------------
##     nineoldandroids
## ----------------------------------
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


#四大组件不能混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
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
## ----------------------------------
##      确保JavaBean不被混淆-否则Gson将无法将数据解析成具体对象
## ----------------------------------
#-keep class com.rair.circle.bean.** { *; }
#-keep class com.rair.circle.db.bean.** { *; }

## ----------------------------------
##      Banner混淆配置

## ----------------------------------
##      友盟统计
## ----------------------------------
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
-dontwarn com.just.agentweb.**

## ----------------------------------
##      BaseRecyclerViewAdapterHelper  https://github.com/CymChad/BaseRecyclerViewAdapterHelper
## ----------------------------------

-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter

## ----------------------------------
##      butterknife
## ----------------------------------
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
-dontwarn okio.**
-dontwarn okhttp3.**
#-dontwarn javax.annotation.**
#-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
#-dontwarn org.codehaus.mojo.animal_sniffer.*
#-dontwarn okhttp3.internal.platform.ConscryptPlatform

## ----------------------------------
##      retrofit2   https://github.com/square/retrofit/blob/master/retrofit/src/main/resources/META-INF/proguard/retrofit2.pro
## ----------------------------------
-dontwarn retrofit2.**
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
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


-keepattributes Signature
-keepattributes *Annotation*
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit.**
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}


-dontwarn java.lang.invoke.*
-dontwarn rx.internal.util.unsafe.**

## ----------------------------------
##      Fastjson
## ----------------------------------
-dontwarn com.alibaba.fastjson.**

## ----------------------------------
##     zxing
## ----------------------------------
-dontwarn com.google.zxing.**
#===============can't find referenced method 'int save(int)' in library class android.graphics.Canvas=============
-dontwarn com.mylhyl.zxing.scanner.**
## ----------------------------------
##      BottomNavigationView
## ----------------------------------

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
## ----------------------------------
##     ImmersionBar  https://github.com/gyf-dev/ImmersionBar
## ----------------------------------
-keep class com.gyf.barlibrary.* {*;}
-dontwarn com.gyf.barlibrary.**

## ----------------------------------
##     华为推送  https://developer.huawei.com/consumer/cn/service/hms/catalog/huaweipush_agent.html?page=hmssdk_huaweipush_devprepare_agent#5.3%20%E9%85%8D%E7%BD%AE%E6%B7%B7%E6%B7%86%E8%84%9A%E6%9C%AC%EF%BC%88%E5%BF%85%E9%80%89%EF%BC%89
## ----------------------------------
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable

## ----------------------------------
##     nineoldandroids
## ----------------------------------

## ----------------------------------
##     高德定位 https://lbs.amap.com/api/android-location-sdk/guide/create-project/dev-attention
## ----------------------------------

## ----------------------------------
##          greendao
## ----------------------------------
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties { *; }

# If you DO use SQLCipher:
-keep class org.greenrobot.greendao.database.SqlCipherEncryptedHelper { *; }

# If you do NOT use SQLCipher:
-dontwarn net.sqlcipher.database.**
# If you do NOT use RxJava:
-dontwarn rx.**
## ----------------------------------
##          XmlResourceParser
## ----------------------------------
-dontwarn org.xmlpull.v1.**