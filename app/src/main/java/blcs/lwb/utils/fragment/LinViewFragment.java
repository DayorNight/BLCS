package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import blcs.lwb.lwbtool.View.customView.WeChatFlowPieView;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import butterknife.BindView;

public class LinViewFragment extends BaseFragment {

    @BindView(R.id.wcpv)
    WeChatFlowPieView wcpv;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_lin_view;
    }

    @Override
    protected void initView() {
        wcpv.setData(1,3,6);
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

}
