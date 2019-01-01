package blcs.lwb.utils.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

import blcs.lwb.utils.Constants;
import blcs.lwb.utils.fragment.HomeTabFragment;

/**
 * Created by Administrator on 2017/12/23.
 * 首页viewPage适配
 */

public class ViewPagerHomeAdapter extends FragmentPagerAdapter {

    private  List<Fragment> list_Fragment;//viewpager中适配的 item

    public ViewPagerHomeAdapter(FragmentManager fm) {
        super(fm);
        if(list_Fragment==null){
            list_Fragment = new ArrayList<>();
        }
        list_Fragment.clear();
        for (int i=0;i<3;i++){
            HomeTabFragment homeTabFragment = new HomeTabFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.Adapter_Pos,i);
            homeTabFragment.setArguments(bundle);
            list_Fragment.add(homeTabFragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return list_Fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_Fragment.size();
    }
}
