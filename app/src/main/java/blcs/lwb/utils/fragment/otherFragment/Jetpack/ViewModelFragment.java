package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.DemoViewModel;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ViewModelFragment extends BaseFragment {
    @BindView(R.id.tv_viewModel_life)
    TextView tvViewModelLife;
    @BindView(R.id.tv_viewModel_dependency)
    TextView tvViewModelDependency;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_viewmodel;
    }

    @Override
    protected void initView() {
        DemoViewModel viewModel = ViewModelProviders.of(this).get(DemoViewModel.class);
        tvViewModelLife.setText(viewModel.tip);
        tvViewModelDependency.setText(viewModel.useViewModel());
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


    @OnClick(R.id.btn_viewModel)
    public void onViewClicked() {
        MyUtils.toUrl(this,"ViewModel",getString(R.string.URL_ViewModel));
    }
}
