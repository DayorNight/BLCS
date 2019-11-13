package blcs.lwb.utils.fragment.viewFragment.MagicIndicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import java.util.Arrays;
import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.ExamplePagerAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.indicator.navigator.ScaleCircleNavigator;
import butterknife.BindView;

public class CustomNavigatorFragment extends BaseFragment {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.magic_indicator1)
    MagicIndicator magicIndicator1;
    @BindView(R.id.magic_indicator2)
    MagicIndicator magicIndicator2;
    @BindView(R.id.magic_indicator3)
    MagicIndicator magicIndicator3;
    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN", "KITKAT", "LOLLIPOP", "M", "NOUGAT"};
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
        initMagicIndicator3();
    }
    private void initMagicIndicator1() {
        CircleNavigator circleNavigator = new CircleNavigator(activity);
        circleNavigator.setCircleCount(CHANNELS.length);
        circleNavigator.setCircleColor(Color.RED);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        magicIndicator1.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator1, mViewPager);
    }

    private void initMagicIndicator2() {
        CircleNavigator circleNavigator = new CircleNavigator(activity);
        circleNavigator.setFollowTouch(false);
        circleNavigator.setCircleCount(CHANNELS.length);
        circleNavigator.setCircleColor(Color.RED);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        magicIndicator2.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator2, mViewPager);
    }

    private void initMagicIndicator3() {
        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(activity);
        scaleCircleNavigator.setCircleCount(CHANNELS.length);
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.DKGRAY);
        scaleCircleNavigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        magicIndicator3.setNavigator(scaleCircleNavigator);
        ViewPagerHelper.bind(magicIndicator3, mViewPager);
    }
    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_custom_navigator;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }


}
