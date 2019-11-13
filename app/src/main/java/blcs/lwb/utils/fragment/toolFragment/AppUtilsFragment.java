package blcs.lwb.utils.fragment.toolFragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import blcs.lwb.lwbtool.utils.AppUtils;
import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class AppUtilsFragment extends BaseFragment {
    @BindView(R.id.btn_getPackage)
    Button btnGetPackage;
    @BindView(R.id.btn_getVersionName)
    Button btnGetVersionName;
    @BindView(R.id.btn_getVersionCode)
    Button btnGetVersionCode;
    @BindView(R.id.btn_getInstalledPackages)
    Button btnGetInstalledPackages;
    @BindView(R.id.tv_getInstalledPackages)
    TextView tvGetInstalledPackages;
    @BindView(R.id.btn_getApplicationIcon)
    Button btnGetApplicationIcon;
    @BindView(R.id.et_installApk)
    EditText etInstallApk;
    @BindView(R.id.btn_installApk)
    Button btnInstallApk;
    @BindView(R.id.btn_getChannel)
    Button btnGetChannel;
    @BindView(R.id.img_getApplicationIcon)
    ImageView imgGetApplicationIcon;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        etInstallApk.setText(Constants.phonePath + "/app.apk");
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
                btnGetPackage.setText(getString(R.string.getPackage)+AppUtils.getAppPackageName(activity));
                break;
            case R.id.btn_getVersionName:
                btnGetVersionName.setText(getString(R.string.getVersionName)+AppUtils.getVersionName(activity));
                break;
            case R.id.btn_getVersionCode:
                btnGetVersionCode.setText(getString(R.string.getVersionCode) + AppUtils.getVersionCode(activity));
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
                 imgGetApplicationIcon.setImageDrawable(AppUtils.getApplicationIcon(activity));
                break;
            case R.id.btn_installApk:
                if(StringUtils.isNotEmpty(etInstallApk,true)){
                    AppUtils.installApk(activity,etInstallApk.getText().toString());
                }
                break;
            case R.id.btn_getChannel:
                btnGetChannel.setText(getString(R.string.getChannel)+AppUtils.getChannel(activity));
                break;
        }
    }
}
