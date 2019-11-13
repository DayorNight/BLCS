package blcs.lwb.utils.fragment.viewFragment.MagicIndicator;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.manager.FramentManages;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.OnClick;

public class MagicIndicatorFragment extends BaseFragment {

    private List<String> array;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        array = MyUtils.getArray(activity, R.array.MagicIndicator);
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_magic_indicator;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
    @OnClick({R.id.scrollable_tab, R.id.fixed_tab, R.id.dynamic_tab, R.id.no_tab_only_indicator, R.id.work_with_fragment_container, R.id.tab_with_badge_view, R.id.load_custom_layout, R.id.custom_navigator})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();

        switch (view.getId()) {
            case R.id.scrollable_tab:
                bundle.putString(Constants.Item_Name,array.get(0));
                addFrament(FramentManages.ScrollableTab,bundle);
                break;
            case R.id.fixed_tab:
                bundle.putString(Constants.Item_Name,array.get(1));
                addFrament(FramentManages.FixedTab,bundle);
                break;
            case R.id.dynamic_tab:
                bundle.putString(Constants.Item_Name,array.get(2));
                addFrament(FramentManages.DynamicTab,bundle);
                break;
            case R.id.no_tab_only_indicator:
                bundle.putString(Constants.Item_Name,array.get(3));
                addFrament(FramentManages.NoTabOnlyIndicator,bundle);
                break;
            case R.id.work_with_fragment_container:
                bundle.putString(Constants.Item_Name,array.get(4));
                addFrament(FramentManages.BadgeTab,bundle);
                break;
            case R.id.tab_with_badge_view:
                bundle.putString(Constants.Item_Name,array.get(5));
                addFrament(FramentManages.FragmentContainer,bundle);
                break;
            case R.id.load_custom_layout:
                bundle.putString(Constants.Item_Name,array.get(6));
                addFrament(FramentManages.LoadCustomLayout,bundle);
                break;
            case R.id.custom_navigator:
                bundle.putString(Constants.Item_Name,array.get(7));
                addFrament(FramentManages.CustomNavigator,bundle);
                break;
        }
    }
}
