package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
//https://www.jianshu.com/p/30555a9b83ef
public class LinApn {
    public Context context;
    /**
     * 当前连接APN
     * */
    private Uri APN_URI_NOW =Uri.parse("content://telephony/carriers/current");
    /**
     * 所有APN
     * */
    private Uri APN_URI = Uri.parse("content://telephony/carriers");
    private Uri CURRENT_APN_URI = Uri.parse("content://telephony/carriers/preferapn");
    // 新增一个cmnet接入点

    public LinApn(Context context){
        this.context = context;
    }

    //检查权限
    public static Boolean checkPermission(Activity activity){
        if(!LinPermission.checkPermission(activity,9)){
            LinPermission.requestPermission(activity,9);
            return false;
        }
        return true;
    }

    public int addAPN(String apnName, String apn) {
        int id = -1;
        String NUMERIC = getSIMInfo();
        if (NUMERIC == null) {
            return -1;
        }
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        //apn中文描述
        values.put("name", apnName);
        //apn名称
        values.put("apn", apn);
        //apn类型
        values.put("type", "default");
        values.put("numeric", NUMERIC);
        values.put("mcc", NUMERIC.substring(0, 3));
        values.put("mnc", NUMERIC.substring(3, NUMERIC.length()));
        //代理
        values.put("proxy", "");
        //端口
        values.put("port", "");
        //彩信代理
        values.put("mmsproxy", "");
        //彩信端口
        values.put("mmsport", "");
        //用户名
        values.put("user", "");
        //服务器
        values.put("server", "");
        //密码
        values.put("password", "");
        //MMSC
        values.put("mmsc", "");
        Cursor c = null;
        Uri newRow = resolver.insert(APN_URI, values);
        if (newRow != null) {
            c = resolver.query(newRow, null, null, null, null);
            int idIndex = c.getColumnIndex("_id");
            c.moveToFirst();
            id = c.getShort(idIndex);
        }
        if (c != null) {
            c.close();
        }
        return id;
    }

    protected String getSIMInfo() {
        TelephonyManager iPhoneManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return iPhoneManager.getSimOperator();
    }
    // 设置接入点
    public void SetAPN(int id) {
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("apn_id", id);
        resolver.update(CURRENT_APN_URI, values, null, null);
    }

    public Boolean checkAPN(String apn) {
        // 检查系统已经写入的APN
        Cursor cr = context.getContentResolver().query(APN_URI, null, null, null, null);
        while (cr != null && cr.moveToNext()) {
//            Log.d("apn", "APN: "+ cr.getString(cr.getColumnIndex("apn")));
            if(cr.getString(cr.getColumnIndex("apn")).equals(apn)){
                return true;
            }
        }
        return false;
    }

    public static void addAPN(Activity activity){
        //SIM卡的状态
        TelephonyManager telephonyManager = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);
        LinApn apn = new LinApn(activity);
        Boolean hasCMIOT = apn.checkAPN("CMIOT");
        Boolean hasCMMTM = apn.checkAPN("CMMTM");
        Log.e("apn", "hasCMIOT: "+hasCMIOT);
        Log.e("apn", "hasCMMTM: "+hasCMMTM);
        if(! hasCMIOT || !hasCMMTM){
            int simState = telephonyManager.getSimState();
            //如果有SIM卡
            if (simState == TelephonyManager.SIM_STATE_READY) {
                if(!hasCMIOT) {
                    apn.addAPN("移动物联网卡APN", "CMIOT");
                }else if(!hasCMMTM){
                    apn.addAPN("移动物联网卡APN","CMMTM");
                }
            }else {
                Log.e("apn", "no SIMCard ");
            }
        }
    }


}
