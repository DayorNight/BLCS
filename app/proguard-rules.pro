#=========友盟统计========
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
#========Agentweb=============
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**
-keepclassmembers class com.just.agentweb.sample.common.AndroidInterface{ *; }

#========BaseRecyclerViewAdapterHelper=============
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers public class * extends com.chad.library.adapter.base.BaseViewHolder {
           <init>(android.view.View);
}

#===============butterknife=============
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#===============BottomNavigationView=============
#-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
#    boolean mShiftingMode;
#}