package blcs.lwb.utils.fragment.viewFragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinNotify;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class NotificationCompatFragment extends BaseFragment {
    @BindView(R.id.et_notification_title)
    EditText et_notification_title;
    @BindView(R.id.et_notification_content)
    EditText etNotificationContent;
    @BindView(R.id.btn_notfication_show_tatus)
    Button show_tatus;

    private int progress = 0;
    private RemoteViews mViews;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_notification;
    }


    @Override
    protected void initView() {
        mViews = new RemoteViews(activity.getPackageName(), R.layout.custom_notify);
        mViews.setTextViewText(R.id.tv_custom_notify,"正在下载");
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

    @OnClick({R.id.btn_notfication_settings, R.id.btn_notification_send_one,
            R.id.btn_notification_send, R.id.btn_notification_channel_send_one,
            R.id.btn_notification_channel_send, R.id.btn_notification_custom, R.id.btn_notification_much_custom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_notfication_settings:
                LinNotify.toNotifySetting(activity, null);
                break;
            case R.id.btn_notification_send_one:
                LinNotify.show(activity, StringUtils.getString(et_notification_title), StringUtils.getString(etNotificationContent), null);
                break;
            case R.id.btn_notification_send:

                LinNotify.showMuch(activity, StringUtils.getString(et_notification_title), StringUtils.getString(etNotificationContent), null);
                break;
            case R.id.btn_notification_channel_send_one:
                LinNotify.show(activity, StringUtils.getString(et_notification_title), StringUtils.getString(etNotificationContent), LinNotify.OTHER_MESSAGE, null);
                break;
            case R.id.btn_notification_channel_send:
                LinNotify.showMuch(activity, StringUtils.getString(et_notification_title), StringUtils.getString(etNotificationContent), LinNotify.OTHER_MESSAGE, null);
                break;
            case R.id.btn_notification_custom:
                mViews.setTextViewText(R.id.tv_custom_notify_number,++progress+"0%");
                mViews.setProgressBar(R.id.pb_custom_notify,10,progress,false);
                LinNotify.show(activity,"", "",mViews, null);
                break;
            case R.id.btn_notification_much_custom:
                mViews.setTextViewText(R.id.tv_custom_notify_number,++progress+"0%");
                mViews.setProgressBar(R.id.pb_custom_notify,10,progress,false);
                LinNotify.showMuch(activity, "", "",mViews, null);
                break;
        }
    }
}
