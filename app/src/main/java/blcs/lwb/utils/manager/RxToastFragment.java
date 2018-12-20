package blcs.lwb.utils.manager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class RxToastFragment extends BaseFragment {
    @BindView(R.id.btn_error_toast)
    Button btnErrorToast;
    @BindView(R.id.btn_success_toast)
    Button btnSuccessToast;
    @BindView(R.id.btn_info_toast)
    Button btnInfoToast;
    @BindView(R.id.btn_warning_toast)
    Button btnWarningToast;
    @BindView(R.id.btn_normal_toast)
    Button btnNormalToast;
    @BindView(R.id.btn_normal_toast_img)
    Button btnNormalToastImg;
    @BindView(R.id.btn_sys_toast)
    Button btnSysToast;

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
        return R.layout.fragment_rxtool;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @OnClick({R.id.btn_error_toast, R.id.btn_success_toast, R.id.btn_info_toast, R.id.btn_warning_toast,
            R.id.btn_normal_toast, R.id.btn_normal_toast_img, R.id.btn_sys_toast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_error_toast:
                RxToast.error(getActivity(), "这是一个提示错误的Toast！");
                break;
            case R.id.btn_success_toast:
                RxToast.success(getActivity(), "这是一个提示成功的Toast!");
                break;
            case R.id.btn_info_toast:
                RxToast.info(getActivity(), "这是一个提示信息的Toast!");
                break;
            case R.id.btn_warning_toast:
                RxToast.warning(activity,"这是一个提示警告的Toast!");
                break;
            case R.id.btn_normal_toast:
                RxToast.normal(activity, "这是一个普通的没有ICON的Toast");
                break;
            case R.id.btn_normal_toast_img:
                Drawable icon = getResources().getDrawable(R.mipmap.set);
                RxToast.normal(activity, "这是一个普通的包含ICON的Toast", icon);
                break;
            case R.id.btn_sys_toast:
                RxToast.showToastShort(activity, "这是一个系统的Toast");
                break;
        }
    }
}
