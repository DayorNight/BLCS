package blcs.lwb.utils.fragment.toolFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinCommon;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class LinCommonFragment extends BaseFragment {


    @BindView(R.id.et_message)
    EditText etMessage;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_lincommon;
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

    @OnClick({R.id.btn_share_message, R.id.btn_send_email, R.id.btn_open_web, R.id.btn_copy_font, R.id.btn_show_progress, R.id.btn_custom_dialog})
    public void onViewClicked(View view) {
        String message = etMessage.getText().toString();
        switch (view.getId()) {
            case R.id.btn_share_message:
                LinCommon.shareInfo(activity, message);
                break;
            case R.id.btn_send_email:
                LinCommon.sendEmail(activity,message,"测试");
                break;
            case R.id.btn_open_web:
                LinCommon.openWebSite(activity, message);
                break;
            case R.id.btn_copy_font:
                LinCommon.copyText(activity, message);
                break;
            case R.id.btn_show_progress:
                LinCommon.showProgressDialog(activity, "进度条");
                break;
            case R.id.btn_custom_dialog:
                LinCommon.showToast(activity, R.layout.dialog_sure);
                break;
        }
    }
}
