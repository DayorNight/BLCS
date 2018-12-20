package blcs.lwb.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import com.umeng.analytics.MobclickAgent;
import java.util.List;

import blcs.lwb.lwbtool.base.BaseAppCompatActivity;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.adapter.ViewPagerHomeAdapter;
import blcs.lwb.utils.mvp.view.IMainView;
import blcs.lwb.utils.mvp.presenter.MainPresenter;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity implements IMainView {

    @BindView(R.id.main_bottom)
    BottomNavigationView mainBottom;
    @BindView(R.id.main_viewpage)
    ViewPager mainViewpage;
    int[] img_menu={R.mipmap.img_util,R.mipmap.img_view,R.mipmap.img_other};

    private int pos;//当前页面
    @Override
    protected BasePresenter bindPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void Title() {

    }

    @Override
    public void BottomMenu() {
        List<String> menus = MyUtils.getArray(this,R.array.bottom_menu);
        Menu menu = mainBottom.getMenu();
        for (int i = 0; i < menus.size(); i++) {
            menu.add(1, i, i, menus.get(i));
            MenuItem item = menu.findItem(i);
            item.setIcon(img_menu[i]);
        }
        mainBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                pos =  menuItem.getItemId();
                switch (menuItem.getItemId()) {
                    case 0:
                        mainViewpage.setCurrentItem(0);
                        break;
                    case 1:
                        mainViewpage.setCurrentItem(1);
                        break;
                    case 2:
                        mainViewpage.setCurrentItem(2);
                        break;
                    case 3:
                        mainViewpage.setCurrentItem(3);
                        break;
                    case 4:
                        mainViewpage.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void ViewPage() {
        ViewPagerHomeAdapter adapter = new ViewPagerHomeAdapter(getSupportFragmentManager());
        mainViewpage.setAdapter(adapter);
        mainViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                pos = position;
                mainBottom.setSelectedItemId(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
