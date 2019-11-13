package blcs.lwb.utils.fragment.viewFragment.MagicIndicator;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.TriangularPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.DummyPagerTitleView;

import java.util.Arrays;
import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.ExamplePagerAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class NoTabOnlyIndicatorFragment extends BaseFragment {
    @BindView(R.id.magic_indicator1)
    MagicIndicator magicIndicator1;
    @BindView(R.id.magic_indicator2)
    MagicIndicator magicIndicator2;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "NOUGAT", "DONUT"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
         ExamplePagerAdapter mAdapter = new ExamplePagerAdapter(mDataList);
        mViewPager.setAdapter(mAdapter);

        initMagicIndicator1();
        initMagicIndicator2();
    }
    private void initMagicIndicator1() {
        magicIndicator1.setBackgroundColor(Color.LTGRAY);
        CommonNavigator commonNavigator = new CommonNavigator(activity);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                return new DummyPagerTitleView(context);
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float lineHeight = context.getResources().getDimension(R.dimen.small_navigator_height);
                indicator.setLineHeight(lineHeight);
                indicator.setColors(Color.parseColor("#40c4ff"));
                return indicator;
            }
        });
        magicIndicator1.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator1, mViewPager);
    }

    private void initMagicIndicator2() {
        CommonNavigator commonNavigator = new CommonNavigator(activity);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                return new DummyPagerTitleView(context);
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                TriangularPagerIndicator indicator = new TriangularPagerIndicator(context);
                indicator.setReverse(true);
                float smallNavigatorHeight = context.getResources().getDimension(R.dimen.small_navigator_height);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setTriangleHeight((int) smallNavigatorHeight);
                indicator.setLineColor(Color.parseColor("#e94220"));
                return indicator;
            }
        });
        magicIndicator2.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator2, mViewPager);
    }
    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_notab_only;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

}
