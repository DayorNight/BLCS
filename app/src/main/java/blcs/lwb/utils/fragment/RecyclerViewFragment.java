package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import blcs.lwb.lwbtool.RecyclerViewUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.RecyclerAdatapter;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RecyclerViewFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_changeOrientation)
    Button btnChangeOrientation;
    @BindView(R.id.btn_table_Layout)
    Button btnTableLayout;
    @BindView(R.id.btn_WaterFall_View)
    Button btnWaterFallView;
    Unbinder unbinder;
    private LinearLayoutManager manager;
    private boolean changeLayouot;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        //创建布局管理器
        manager = new LinearLayoutManager(activity);
        //manager.scrollToPosition(3);
        RecyclerAdatapter mAdatapter = new RecyclerAdatapter(activity, MyUtils.getArray(activity, R.array.Utils));
        recyclerView.setAdapter(mAdatapter);
        RecyclerViewUtils.changeLayouot(activity, recyclerView, manager, OrientationHelper.VERTICAL);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }


    @OnClick({R.id.btn_changeOrientation, R.id.btn_table_Layout, R.id.btn_WaterFall_View})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_changeOrientation:
                if (changeLayouot) {
                    RecyclerViewUtils.changeLayouot(activity, recyclerView, manager, OrientationHelper.VERTICAL);
                } else {
                    RecyclerViewUtils.changeLayouot(activity, recyclerView, manager, OrientationHelper.HORIZONTAL);
                }
                changeLayouot = !changeLayouot;
                break;
            case R.id.btn_table_Layout:
                break;
            case R.id.btn_WaterFall_View:
                break;
        }
    }
}
