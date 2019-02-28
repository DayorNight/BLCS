package blcs.lwb.lwbtool;

import android.os.Environment;

public class Constants {
    /**
     *  intent
     */
    public final static String Intent_Go="跳转到哪个片段";

    public final static String phonePath=Environment.getExternalStorageDirectory().getAbsolutePath();

    public final static String Item_Name="Item_Name";
    public final static String Adapter_Pos="Adapter_Pos";
    public final static String URL="URL";


    //设置默认超时时间
    public static final int DEFAULT_TIME=10;
    public final static  String BaseUrl = "http://pndatsn5v.bkt.clouddn.com/";
    public final static  String retrofit = "retrofit.txt";
    public final static  String retrofitList = "retrofit_list.txt";
    /**
     * SP
     */

    public final static String SP_LANGUAGE="SP_LANGUAGE";
    public final static String SP_COUNTRY="SP_COUNTRY";
}
