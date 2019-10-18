package blcs.lwb.utils;

import android.os.Environment;

public class Constants {


    public final static String InstallApk="https://blcs.oss-cn-shenzhen.aliyuncs.com/app/BLCS.apk";
    /**
     *  intent
     */
    public final static String Intent_Go="跳转到哪个片段";
    public final static String phonePath=Environment.getExternalStorageDirectory().getAbsolutePath();
    public final static String Item_Name="Item_Name";

//    友盟统计appkey
    public final static String U_Appkey="5b98c6f98f4a9d317b00002f";

    public final static String URL="URL";

    //图片
    public final static int[] img_menu={R.mipmap.img_util,R.mipmap.img_view,R.mipmap.img_other,R.mipmap.set,R.mipmap.ic_check_white_48dp};

    //JellyViewPager
    public static final String KEY = "image";
    public static int [] images = {R.mipmap.ic1, R.mipmap.ic2,R.mipmap.ic3, R.mipmap.ic4, R.mipmap.ic5};
    public static final String SP_FontScale="字体大小调整";
    public static final String SP_MultiLanguage="SP_MultiLanguage";

}
