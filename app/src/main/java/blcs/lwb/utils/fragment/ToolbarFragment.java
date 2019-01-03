package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
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

    private void toolbar() {
        //主标题
        toolbar.setTitle("主标题");
        //小标题
        toolbar.setSubtitle("小标题");
        //设置Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        //设置toolbar
        activity.setSupportActionBar(toolbar);
        //左边的小箭头（注意需要在setSupportActionBar(toolbar)之后才有效果）
        toolbar.setNavigationIcon(R.mipmap.back);
        //菜单点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_bottom_1:
                        Toast.makeText(activity, "Search !", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_bottom_2:
                        Toast.makeText(activity, "Notificationa !", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_bottom_3:
                        Toast.makeText(activity, "Settings !", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
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
