package blcs.lwb.utils.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.fragment.HomeOtherFragment;
import blcs.lwb.utils.fragment.HomeResourcesFragment;
import blcs.lwb.utils.fragment.HomeUtilsFragment;
import blcs.lwb.utils.fragment.HomeViewFragment;

/**
 * Created by Administrator on 2017/12/23.
 * 首页viewPage适配
 */

public class ViewPagerHomeAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments =  new ArrayList<>();

    public ViewPagerHomeAdapter(FragmentManager fm) {
        super(fm);
        fragments.clear();
        fragments.add(new HomeUtilsFragment());
        fragments.add(new HomeViewFragment());
        fragments.add(new HomeOtherFragment());
        fragments.add(new HomeResourcesFragment());
    }

    @Override
    public Fragment getItem(int pos) {
        return fragments.get(pos);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
