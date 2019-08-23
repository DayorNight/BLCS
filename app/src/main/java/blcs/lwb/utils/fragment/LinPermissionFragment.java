package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;


public class LinPermissionFragment extends BaseFragment {

    @Override
    protected int bindLayout() {
        return R.layout.fragment_permission;
    }

    @Override
    protected void initView() {
        // 调用带权限检查的 showCamera 方法
//        MainActivityPermissionsDispatcher.showCameraWithCheck(this);
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
