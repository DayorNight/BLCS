package blcs.lwb.utils.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import blcs.lwb.lwbtool.AppUtils;
import blcs.lwb.lwbtool.LogUtils;
import blcs.lwb.lwbtool.StringUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import butterknife.BindView;
import butterknife.OnClick;

public class AppUtilsFragment extends BaseFragment {
    @BindView(R.id.btn_getPackage)
    Button btnGetPackage;
    @BindView(R.id.tv_getPackage)
    TextView tvGetPackage;
    @BindView(R.id.btn_getVersionName)
    Button btnGetVersionName;
    @BindView(R.id.tv_getVersionName)
    TextView tvGetVersionName;
    @BindView(R.id.btn_getVersionCode)
    Button btnGetVersionCode;
    @BindView(R.id.tv_getVersionCode)
    TextView tvGetVersionCode;
    @BindView(R.id.btn_getInstalledPackages)
    Button btnGetInstalledPackages;
    @BindView(R.id.tv_getInstalledPackages)
    TextView tvGetInstalledPackages;
    @BindView(R.id.btn_getApplicationIcon)
    Button btnGetApplicationIcon;
    @BindView(R.id.tv_getApplicationIcon)
    ImageView tvGetApplicationIcon;
    @BindView(R.id.et_installApk)
    EditText etInstallApk;
    @BindView(R.id.btn_installApk)
    Button btnInstallApk;
    @BindView(R.id.btn_getChannel)
    Button btnGetChannel;
    @BindView(R.id.tv_getChannel)
    TextView tvGetChannel;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
//        etInstallApk.setText(activity.getPackageCodePath());
        etInstallApk.setText(""+ Environment.getExternalStorageDirectory().getAbsolutePath() + "/app.apk");
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_app;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @OnClick({R.id.btn_getPackage, R.id.btn_getVersionName, R.id.btn_getVersionCode, R.id.btn_getInstalledPackages, R.id.btn_getApplicationIcon, R.id.btn_installApk, R.id.btn_getChannel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getPackage:
                tvGetPackage.setText(AppUtils.getAppPackageName(activity));
                break;
            case R.id.btn_getVersionName:
                tvGetVersionName.setText(AppUtils.getVersionName(activity));
                break;
            case R.id.btn_getVersionCode:
                tvGetVersionCode.setText("" + AppUtils.getVersionCode(activity));
                break;
            case R.id.btn_getInstalledPackages:
                List<PackageInfo> installedPackages = AppUtils.getInstalledPackages(activity);
                StringBuilder stringBuilder = new StringBuilder();
                for (PackageInfo packageInfo : installedPackages) {
                    PackageManager packageManager = activity.getPackageManager();
                    String s = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                    stringBuilder.append(s + "/");
                    tvGetInstalledPackages.setText(stringBuilder);
                }
                break;
            case R.id.btn_getApplicationIcon:
                tvGetApplicationIcon.setImageDrawable(AppUtils.getApplicationIcon(activity));
                break;
            case R.id.btn_installApk:
                if(StringUtils.isNotEmpty(etInstallApk,true)){
                    AppUtils.installApk(activity,etInstallApk.getText().toString());
                }
                break;
            case R.id.btn_getChannel:
                tvGetChannel.setText(AppUtils.getChannel(activity));
                break;
        }
    }
}
