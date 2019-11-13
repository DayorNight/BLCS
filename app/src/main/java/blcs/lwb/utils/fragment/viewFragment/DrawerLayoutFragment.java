package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import blcs.lwb.lwbtool.View.DragFloatButton;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class DrawerLayoutFragment extends BaseFragment {
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.dragFloatButton)
    DragFloatButton dragFloatButton;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_drawerlayout;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }


    @OnClick({R.id.btn_start, R.id.floatingActionButton,R.id.dragFloatButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                drawerLayout.openDrawer(GravityCompat.START);
//        drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.floatingActionButton:
                Snackbar.make(floatingActionButton, "show", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(activity, "Undo", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }

    }
}
