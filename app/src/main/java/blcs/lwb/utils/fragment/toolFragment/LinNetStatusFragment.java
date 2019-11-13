package blcs.lwb.utils.fragment.toolFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinNetStatus;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;


public class LinNetStatusFragment extends BaseFragment {
    @BindView(R.id.btn_network_wifi)
    Button btnNetworkWifi;
    @BindView(R.id.btn_network_mobile)
    Button btnNetworkMobile;
    @BindView(R.id.btn_network_connect)
    Button btnNetworkConnect;
    @BindView(R.id.btn_network_status)
    Button btnNetworkStatus;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_network;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @OnClick({R.id.btn_network_wifi, R.id.btn_network_mobile, R.id.btn_network_connect, R.id.btn_network_setting, R.id.btn_network_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_network_wifi:
                boolean wifi = LinNetStatus.isWIFI(activity);
                btnNetworkWifi.setText(getString(R.string.whetherConnectWifi)+": "+wifi);
                break;
            case R.id.btn_network_mobile:
                boolean mobile = LinNetStatus.isMobile(activity);
                btnNetworkMobile.setText(getString(R.string.whetherConnectMobileNewWork)+": "+mobile);
                break;
            case R.id.btn_network_connect:
                boolean connected = LinNetStatus.isConnected(activity);
                btnNetworkConnect.setText(getString(R.string.whetherConnectNetWork)+": "+connected);
                break;
            case R.id.btn_network_setting:
                LinNetStatus.openSetting(activity);
                break;
            case R.id.btn_network_status:
                int netWorkType = LinNetStatus.getNetWorkType(activity);
                switch (netWorkType){
                    case LinNetStatus.NETWORKTYPE_INVALID:
                        btnNetworkStatus.setText(getString(R.string.getNetWorkStatus)+": 没有网络");
                        break;
                    case LinNetStatus.NETWORKTYPE_WAP:
                        btnNetworkStatus.setText(getString(R.string.getNetWorkStatus)+": WAP网络");
                        break;
                    case LinNetStatus.NETWORKTYPE_2G:
                        btnNetworkStatus.setText(getString(R.string.getNetWorkStatus)+": 2G网络");
                        break;
                    case LinNetStatus.NETWORKTYPE_3G:
                        btnNetworkStatus.setText(getString(R.string.getNetWorkStatus)+": 3G或3G以上网络");
                        break;
                    case LinNetStatus.NETWORKTYPE_WIFI:
                        btnNetworkStatus.setText(getString(R.string.getNetWorkStatus)+": WIFI网络");
                        break;
                }
                break;
        }
    }
}
