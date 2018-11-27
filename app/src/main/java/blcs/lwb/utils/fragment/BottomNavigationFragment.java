package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.List;

import blcs.lwb.lwbtool.BottomNavigationUtils;
import blcs.lwb.lwbtool.LogUtils;
import blcs.lwb.lwbtool.RxToast;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.manager.FramentManages;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class BottomNavigationFragment extends BaseFragment {
    @BindView(R.id.btn_Recommend)
    Button btnRecommend;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.bnv_menu)
    BottomNavigationView bnvMenu;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_reduce)
    Button btnReduce;
    @BindView(R.id.btn_add_layout)
    Button btnAddLayout;
    @BindView(R.id.btn_reduce_layout)
    Button btnReduceLayout;
    @BindView(R.id.cb_whether_remove_animation)
    CheckBox cbWRemoveA;
    @BindView(R.id.cb_whether_remove_animation1)
    CheckBox cbWRemoveA1;
    int i=0;
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        //设置默认选中item
        BottomNavigationUtils.setItem(bottomNavigationView,1);
//        bottomNavigationView.setLabelVisibilityMode(0);//默认动画
        //设置导航栏菜单项Item选中监听
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String title = item.getTitle().toString();
                switch (item.getItemId()) {
                    case R.id.item_bottom_1:
                        RxToast.showToastShort(activity, title);
                        break;
                    case R.id.item_bottom_2:
                        RxToast.showToastShort(activity, title);
                        break;
                    case R.id.item_bottom_3:
                        RxToast.showToastShort(activity, title);
                        break;
                    case 3:
                        RxToast.showToastShort(activity, title);
                        break;
                    case 4:
                        RxToast.showToastShort(activity, title);
                        break;
                }
                return true;
            }
        });
        cbWRemoveA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    BottomNavigationUtils.openAnimation(bottomNavigationView,false);
                } else {
                    BottomNavigationUtils.openAnimation(bottomNavigationView,true);
                }
            }
        });

        cbWRemoveA1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    BottomNavigationUtils.openAnimation(bnvMenu,false);
                } else {
                    BottomNavigationUtils.openAnimation(bnvMenu,true);
                }
            }
        });
        bnvMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String title = item.getTitle().toString();
                switch (item.getItemId()) {
                    case 0:
                        RxToast.showToastShort(activity, title);
                        break;
                    case 1:
                        RxToast.showToastShort(activity, title);
                        break;
                    case 2:
                        RxToast.showToastShort(activity, title);
                        break;
                    case 3:
                        RxToast.showToastShort(activity, title);
                        break;
                    case 4:
                        RxToast.showToastShort(activity, title);
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

    @OnClick({R.id.btn_add, R.id.btn_reduce, R.id.btn_Recommend, R.id.btn_add_layout, R.id.btn_reduce_layout})
    public void onViewClicked(View view) {
        int size = bottomNavigationView.getMenu().size();
        switch (view.getId()) {
            case R.id.btn_Recommend:
                Bundle bundle = new Bundle();
                bundle.putString(Constants.Item_Name,"BottomNavigationView");
                bundle.putString(Constants.URL,Constants.BottomNavigation_Utils);
                addFrament(R.id.fr_contain,  FramentManages.Demo, bundle, true);
                break;
            case R.id.btn_add:
                if (size == 5) {
                    RxToast.warning(activity, "不能超过5");
                } else {
                    bottomNavigationView.getMenu().add(0, size, size, "选项");
                    bottomNavigationView.getMenu().getItem(size).setIcon(R.mipmap.set);
                }
                break;
            case R.id.btn_reduce:
                if (size > 1) {
                    bottomNavigationView.getMenu().removeItem(size - 1);
                } else {
                    RxToast.warning(activity, "不能少于1");
                }
                break;
            case R.id.btn_add_layout:
                if(bnvMenu.getMenu().size() < 5){
                    Menu menu = bnvMenu.getMenu();
                    List<String> array = MyUtils.getArray(activity, R.array.BottomNavigationView);
                    menu.add(0,i,i,array.get(i));
                    MenuItem item = menu.findItem(i);
                    item.setIcon(Constants.img_menu[i]);
                    i++;
                }else{
                    RxToast.warning(activity, "不能超过5");
                }
                break;
            case R.id.btn_reduce_layout:
                LogUtils.e(""+bnvMenu.getMenu().size());
                if(bnvMenu.getMenu().size() >0){
                    if(i<2){
                        bnvMenu.setLabelVisibilityMode(1);
                    }
                    bnvMenu.getMenu().removeItem(bnvMenu.getMenu().size()-1);
                    i--;
                }else{
                    RxToast.success(activity, "已清空");

                }
                break;
        }
    }
}
