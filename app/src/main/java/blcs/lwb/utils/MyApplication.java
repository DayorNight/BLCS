package blcs.lwb.utils;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import blcs.lwb.lwbtool.LogUtils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("开启");

        initU_APP();

    }

    /**
     * 友盟统计集成
     */
    private void initU_APP() {
//      appkey   channel  设备类型  推送业务的secret
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE, null);
//      场景类型设置接口
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
//      secretkey设置接口，防止AppKey被盗用，secretkey需要网站申请 需要企业认证
//        MobclickAgent.setSecret(this, "s10bacedtyz");
//      错误统计  提交错误报告
//        MobclickAgent.reportError(mContext, "Parameter Error");
//      设置日志加密
        UMConfigure.setEncryptEnabled(true);
    }
}
