package blcs.lwb.utils.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.NotifyUtils;
import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.utils.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NotificationCompatFragment extends BaseFragment {
    @BindView(R.id.et_notification_content)
    EditText etNotificationContent;


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

    @OnClick({R.id.btn_notification_send_one, R.id.btn_notification_send, R.id.btn_notification_channel_send_one, R.id.btn_notification_channel_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_notification_send_one:
                NotifyUtils.show(activity, "您有一条新消息",  StringUtils.getString(etNotificationContent));
                break;
            case R.id.btn_notification_send:
                NotifyUtils.show1(activity, "您有一条新消息",  StringUtils.getString(etNotificationContent));
                break;
            case R.id.btn_notification_channel_send_one:
                NotifyUtils.show(activity, "您收到一条系统消息",  StringUtils.getString(etNotificationContent),NotifyUtils.OTHER_MESSAGE);
                break;
            case R.id.btn_notification_channel_send:
                NotifyUtils.show1(activity, "您收到一条系统消息",  StringUtils.getString(etNotificationContent),NotifyUtils.OTHER_MESSAGE);
                break;
        }

    }

}
