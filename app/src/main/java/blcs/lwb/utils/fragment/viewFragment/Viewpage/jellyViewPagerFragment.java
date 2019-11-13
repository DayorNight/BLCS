package blcs.lwb.utils.fragment.viewFragment.Viewpage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import blcs.lwb.lwbtool.View.JellyViewPager.JellyViewPager;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.TestFragPagerAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class jellyViewPagerFragment extends BaseFragment {
    @BindView(R.id.jellyViewPager)
    JellyViewPager jellyViewPager;
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        jellyViewPager.setAdapter(new TestFragPagerAdapter(activity.getSupportFragmentManager()));
        jellyViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case 1: //正在滑动
                        break;
                    case 2: //滑动结束
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
            }

        });
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_jellyview;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
