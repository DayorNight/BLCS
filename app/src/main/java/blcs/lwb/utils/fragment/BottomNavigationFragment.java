package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import blcs.lwb.lwbtool.RxToast;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import butterknife.BindView;
import butterknife.OnClick;

public class BottomNavigationFragment extends BaseFragment {
    @BindView(R.id.btn_Recommend)
    Button btnRecommend;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_reduce)
    Button btnReduce;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
//        Drawable icon = item.getIcon();
//        CharSequence title = item.getTitle();
//        int order = item.getOrder();
//        int itemId = item.getItemId();
//        LogUtils.e(item.toString());
        //设置默认选中item
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        //设置导航条目选中监听
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_bottom_1:
                        RxToast.showToastShort(activity, "选项一");
                        break;
                    case R.id.item_bottom_2:
                        RxToast.showToastShort(activity, "选项二");
                        break;
                    case R.id.item_bottom_3:
                        RxToast.showToastShort(activity, "选项三");
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
        return R.layout.fragment_bottom_navigation;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @OnClick({R.id.btn_add, R.id.btn_reduce,R.id.btn_Recommend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                if(bottomNavigationView.getMaxItemCount()==5){
                    RxToast.success(activity,"不能超过5");
                }else{
                    bottomNavigationView.inflateMenu(R.menu.menu_sss);
                }
                break;
            case R.id.btn_reduce:
                if(bottomNavigationView.getMaxItemCount()>1){
                    bottomNavigationView.getMenu().removeItem(bottomNavigationView.getItemIconSize());
                }else{
                    RxToast.success(activity,"不能少于1");
                }
                break;
        }
    }
}
