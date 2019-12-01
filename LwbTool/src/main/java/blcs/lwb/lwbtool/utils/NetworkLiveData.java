package blcs.lwb.lwbtool.utils;

import android.arch.lifecycle.LiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

//Demo1扩展LiveData类(监听网络状态)
public class NetworkLiveData extends LiveData<NetworkInfo> {

    private final Context mContext;
    static NetworkLiveData mNetworkLiveData;
    private NetworkReceiver mNetworkReceiver;
    private final IntentFilter mIntentFilter;

    private static final String TAG = "NetworkLiveData";

    public NetworkLiveData(Context context) {
        mContext = context.getApplicationContext();
        mNetworkReceiver = new NetworkReceiver();
        mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }
    //单例
    public static NetworkLiveData getInstance(Context context) {
        if (mNetworkLiveData == null) {
            mNetworkLiveData = new NetworkLiveData(context);
        }
        return mNetworkLiveData;
    }

    @Override
    protected void onActive() {
        super.onActive();
        //当组件处于激活状态时注册广播
        mContext.registerReceiver(mNetworkReceiver, mIntentFilter);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        //当组件处于销毁状态时注销广播
        mContext.unregisterReceiver(mNetworkReceiver);
    }
    //监听网络状态
    private static class NetworkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            getInstance(context).setValue(activeNetwork);
        }
    }
}
