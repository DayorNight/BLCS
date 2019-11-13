package blcs.lwb.utils.fragment.toolFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinPhone;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class LinPhoneFragment extends BaseFragment {
    @BindView(R.id.tv_phone_show)
    TextView tvPhoneShow;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_lin_phone;
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

    @OnClick(R.id.btn_phone_status)
    public void onViewClicked() {
        tvPhoneShow.setText(LinPhone.getDeviceAllInfo(activity));
    }
}
