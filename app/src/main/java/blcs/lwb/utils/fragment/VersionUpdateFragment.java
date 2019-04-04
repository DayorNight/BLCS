package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.AppUtils;
import blcs.lwb.lwbtool.utils.DownloadUtils;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.lwbtool.utils.dialog.dialogFragment.BaseDialogFragment;
import blcs.lwb.lwbtool.utils.dialog.dialogFragment.LinCustomDialogFragment;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VersionUpdateFragment extends BaseFragment {
    @BindView(R.id.tv_version_name)
    TextView tvVersionName;
    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_version_update;
    }

    @Override
    protected void initView() {
        tvVersionName.setText(AppUtils.getAppName(activity));
        tvVersionCode.setText("Version "+AppUtils.getVersionName(activity));

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

    @OnClick({R.id.ll_version_update_function, R.id.ll_version_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_version_update_function:
                RxToast.info(activity,getString(R.string.function_unopen));
                break;
            case R.id.ll_version_update:
                LinCustomDialogFragment.init().setTitle("发现新版本")
                        .setContent("是否更新?")
                        .setType(LinCustomDialogFragment.TYPE_CANCLE)
                        .setOnClickListener(new LinCustomDialogFragment.OnSureCancleListener() {
                            @Override
                            public void clickSure(String EdiText) {
                                DownloadUtils.downApk(activity, Constants.InstallApk);
                            }
                            @Override
                            public void clickCancle() {
                            }
                        }).show(getFragmentManager());
                break;
        }
    }
}
