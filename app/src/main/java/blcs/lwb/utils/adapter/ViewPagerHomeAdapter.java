package blcs.lwb.utils.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import blcs.lwb.utils.fragment.HomeOtherFragment;
import blcs.lwb.utils.fragment.HomeUtilsFragment;
import blcs.lwb.utils.fragment.HomeViewFragment;

/**
 * Created by Administrator on 2017/12/23.
 * 首页viewPage适配
 */

public class ViewPagerHomeAdapter extends FragmentPagerAdapter {

    public ViewPagerHomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        if(pos==0){
            return new HomeUtilsFragment();
        }else if (pos == 1){
            return new HomeViewFragment();
        }else {
            return new HomeOtherFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
