package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.FileUtils;
import blcs.lwb.lwbtool.utils.LinDownloadAPk;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FileUtilsFragment extends BaseFragment {

    @BindView(R.id.tv_showRootFile)
    TextView tv_showRootFile;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_file_utils;
    }

    @Override
    protected void initView() {}

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

    @OnClick({R.id.btn_getRootFile, R.id.btn_new_fileCatalog, R.id.btn_new_folder, R.id.btn_new_file, R.id.btn_file_exist,
            R.id.btn_file_getSize, R.id.btn_file_getName, R.id.btn_file_copyName, R.id.btn_delete_file})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getRootFile://获取SD卡根目录
                LogUtils.e(FileUtils.getRootPath()+"  "+FileUtils.getRootPath().getPath());
                tv_showRootFile.setVisibility(View.VISIBLE);
                tv_showRootFile.setText(FileUtils.getRootPath().getName());
                break;
            case R.id.btn_new_fileCatalog://新建一个文件目录
                break;
            case R.id.btn_new_folder://创建一个文件夹
                break;
            case R.id.btn_new_file://创建一个文件
                break;
            case R.id.btn_file_exist://判断文件是否存在
                break;
            case R.id.btn_file_getSize://获取文件大小
                break;
            case R.id.btn_file_getName://获得文件名
                break;
            case R.id.btn_file_copyName://复制文件
                break;
            case R.id.btn_delete_file://删除文件
                break;
        }
    }
}
