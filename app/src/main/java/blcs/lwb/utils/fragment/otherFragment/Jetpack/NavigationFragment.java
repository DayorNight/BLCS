package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.OnClick;

public class NavigationFragment extends BaseFragment {
    @Override
    protected int bindLayout() {
        return R.layout.fragment_navigation;
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

    @OnClick(R.id.btn_navigation)
    public void onClick(){
        MyUtils.toUrl(this,"Navigation",getString(R.string.URL_Navigation));
    }
}
