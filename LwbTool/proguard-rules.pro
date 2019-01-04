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

#===============can't find referenced method 'int save(int)' in library class android.graphics.Canvas=============
-dontwarn com.mylhyl.zxing.scanner.**