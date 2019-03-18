package blcs.lwb.utils.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinNotify;
import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.utils.MainActivity;
import blcs.lwb.utils.R;
import butterknife.BindView;
import butterknife.OnClick;

public class NotificationCompatFragment extends BaseFragment {
    @BindView(R.id.et_notification_title)
    EditText et_notification_title;
    @BindView(R.id.et_notification_content)
    EditText etNotificationContent;
    @BindView(R.id.btn_notfication_show_tatus)
    Button show_tatus;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_notification;
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

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            boolean open = LinNotify.isNotificationEnabled(activity);
            show_tatus.setText("获取应用是否开启通知状态：" + String.valueOf(open));
        }
    }

    @OnClick({R.id.btn_notfication_settings, R.id.btn_notification_send_one, R.id.btn_notification_send, R.id.btn_notification_channel_send_one, R.id.btn_notification_channel_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_notfication_settings:
                LinNotify.toNotifySetting(activity, null);
                break;
            case R.id.btn_notification_send_one:
                LinNotify.show(activity, StringUtils.getString(et_notification_title), StringUtils.getString(etNotificationContent), null);
                break;
            case R.id.btn_notification_send:
                LinNotify.showMuch(activity, StringUtils.getString(et_notification_title), StringUtils.getString(etNotificationContent), MainActivity.class);
                break;
            case R.id.btn_notification_channel_send_one:
                LinNotify.show(activity, StringUtils.getString(et_notification_title), StringUtils.getString(etNotificationContent), LinNotify.OTHER_MESSAGE, MainActivity.class);
                break;
            case R.id.btn_notification_channel_send:
                LinNotify.showMuch(activity, StringUtils.getString(et_notification_title), StringUtils.getString(etNotificationContent), LinNotify.OTHER_MESSAGE, MainActivity.class);
                break;
        }
    }

}
