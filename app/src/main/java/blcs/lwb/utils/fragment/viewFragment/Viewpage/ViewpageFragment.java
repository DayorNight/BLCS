package blcs.lwb.utils.fragment.viewFragment.Viewpage;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.manager.FramentManages;
import butterknife.BindView;
import butterknife.OnClick;

public class ViewpageFragment extends BaseFragment {
    @BindView(R.id.btn_jelly)
    Button btnJelly;

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
        return R.layout.fragment_viewpage;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }


    @OnClick({R.id.btn_jelly, R.id.btn_swipe})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.btn_jelly:
                bundle.putString(Constants.Item_Name,"JellyViewPager");
                addFrament(FramentManages.jellyViewPager,bundle);
                break;
            case R.id.btn_swipe:
                bundle.putString(Constants.Item_Name,"SwipeCard");
                addFrament(FramentManages.SwipeCard,bundle);
                break;
        }

    }
}
