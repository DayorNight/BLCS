package blcs.lwb.utils.fragment.otherFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;

public class DependConfigFragment extends BaseFragment {
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_leak_canary;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
