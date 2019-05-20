package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.FileUtils;
import blcs.lwb.lwbtool.utils.LinDownloadAPk;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FileUtilsFragment extends BaseFragment {

    @Override
    protected int bindLayout() {
        return R.layout.fragment_file_utils;
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


    @OnClick({R.id.btn_file_exist, R.id.btn_file_size})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_file_exist:
                LogUtils.e("File "+LinDownloadAPk.APK_UPGRADE);
                File file = new File(LinDownloadAPk.APK_UPGRADE);
                boolean exists = file.exists();
                LogUtils.e("File "+exists);
                LogUtils.e("File "+FileUtils.size(file.length()));
                break;
            case R.id.btn_file_size:
                break;
        }
    }
}
