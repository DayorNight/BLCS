package blcs.lwb.utils.fragment.otherFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class OnCrashFragment extends BaseFragment {
    @BindView(R.id.btn_crash)
    Button btnCrash;

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
        return R.layout.fragment_crash;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }


    @OnClick(R.id.btn_crash)
    public void onViewClicked() {
        throw new RuntimeException("I'm a cool exception and I crashed the main thread!");
    }
}
