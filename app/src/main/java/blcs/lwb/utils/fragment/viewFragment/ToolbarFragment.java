package blcs.lwb.utils.fragment.viewFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class ToolbarFragment extends BaseFragment {
    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        toolbar();
    }
//    <!--app:popupTheme 设置弹出菜单的样式-->

    private void toolbar() {

        //设置导航栏图标
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationContentDescription("导航栏");
        //设置Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setLogoDescription("logo");
        //设置标题
        toolbar.setTitle("Toolbar");
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setTitleTextAppearance(activity, R.style.Toolbar_Title);
        //设置小标题
        toolbar.setSubtitle("小标题");
        toolbar.setSubtitleTextAppearance(activity,R.style.Toolbar_SubTitle);
        //设置menu
        toolbar.inflateMenu(R.menu.menu_sss);
        //菜单点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_1:
                        Toast.makeText(activity, "action_1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_2:
                        Toast.makeText(activity, "action_2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_3:
                        Toast.makeText(activity, "action_3", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_4:
                        Toast.makeText(activity, "action_4", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        //导航栏点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "导航栏", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_toolbar;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
