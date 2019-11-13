package blcs.lwb.utils.fragment.toolFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import blcs.lwb.lwbtool.utils.DensityUtils;
import blcs.lwb.lwbtool.utils.ScreenUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.dialog.dialogFragment.LinCustomDialogFragment;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class ScreenUtilsFragment extends BaseFragment {
    @BindView(R.id.btn_getScreenWidth)
    Button btnGetScreenWidth;
    @BindView(R.id.btn_getScreenHeight)
    Button btnGetScreenHeight;
    @BindView(R.id.btn_getDensity)
    Button btnGetDensity;
    @BindView(R.id.btn_getScaledDensity)
    Button btnGetScaledDensity;
    @BindView(R.id.btn_getStatusHeight)
    Button btnGetStatusHeight;
    @BindView(R.id.btn_getSnapShotWithStatusBar)
    Button btnGetSnapShotWithStatusBar;
    @BindView(R.id.btn_getSnapShotWithoutStatusBar)
    Button btnGetSnapShotWithoutStatusBar;
    @BindView(R.id.btn_getScreenSize)
    Button btnGetScreenSize;

    private LinCustomDialogFragment dialogFragment;


    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        dialogFragment = LinCustomDialogFragment.init();
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_screen;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @OnClick({R.id.btn_getScreenWidth, R.id.btn_getScreenHeight, R.id.btn_getDensity, R.id.btn_getScaledDensity, R.id.btn_getStatusHeight, R.id.btn_getSnapShotWithStatusBar, R.id.btn_getSnapShotWithoutStatusBar, R.id.btn_getScreenSize})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getScreenWidth:
                btnGetScreenWidth.setText(getString(R.string.getScreenWidth)+ ScreenUtils.getScreenWidth(activity)+"px");
                break;
            case R.id.btn_getScreenHeight:
                btnGetScreenHeight.setText(getString(R.string.getScreenHeight)+ ScreenUtils.getScreenHeight(activity)+"px");
                break;
            case R.id.btn_getDensity:
                btnGetDensity.setText(getString(R.string.getDensity)+ ScreenUtils.getDensity(activity));
                break;
            case R.id.btn_getScaledDensity:
                btnGetScaledDensity.setText(getString(R.string.getScaledDensity)+ ScreenUtils.getScaledDensity(activity));
                break;
            case R.id.btn_getStatusHeight:
                btnGetStatusHeight.setText(getString(R.string.getStatusHeight)+ ScreenUtils.getStatusHeight(activity));
                break;
            case R.id.btn_getSnapShotWithStatusBar:
                dialogFragment.setImage(ScreenUtils.getSnapShotWithStatusBar(activity)).setImagePadding(DensityUtils.dip2px(activity, 50)).setType(LinCustomDialogFragment.TYPE_IMAGE).show(getFragmentManager());
                break;
            case R.id.btn_getSnapShotWithoutStatusBar:
                dialogFragment.setImage(ScreenUtils.getSnapShotWithoutStatusBar(activity)).setImagePadding(DensityUtils.dip2px(activity, 50)).setType(LinCustomDialogFragment.TYPE_IMAGE).show(getFragmentManager());
                break;
            case R.id.btn_getScreenSize:
                btnGetScreenSize.setText(getString(R.string.getScreenSize)+ ScreenUtils.getScreenSize(activity));
                break;
        }
    }
}
