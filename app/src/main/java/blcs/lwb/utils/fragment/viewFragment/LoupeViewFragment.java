package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;

public class LoupeViewFragment extends BaseFragment {
    @Override
    protected int bindLayout() {
        return R.layout.fragment_loupe_view;
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
}
