package blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.AnimationAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 设置空布局
 */
public class EmptyViewFragment extends BaseFragment {
    @BindView(R.id.btn_reset)
    FloatingActionButton mReset;
    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private AnimationAdapter mAdapter;
    private View errorView;
    private View notDataView;
    List<String> datas = new ArrayList<>();

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mAdapter = new AnimationAdapter(R.layout.layout_animation, datas);
        mRecyclerView.setAdapter(mAdapter);
        loadingView();

    }

    /**
     * 加载布局
     */
    private void loadingView() {
//        关联加载布局
//        mAdapter.setEmptyView(R.layout.loading_view, (ViewGroup) mRecyclerView.getParent());
        //没有数据显示页面
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        //没有网络显示页面
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter.setEmptyView(notDataView);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setEmptyView(R.layout.loading_view, (ViewGroup) mRecyclerView.getParent());
                notDataView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onRefresh();
                    }
                }, 3000);

            }
        });
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setEmptyView(R.layout.loading_view, (ViewGroup) mRecyclerView.getParent());
                notDataView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onRefresh();
                    }
                }, 3000);
            }
        });
    }

    private boolean mError = true;
    private boolean mNoData = true;

    private void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mError) {
                    mAdapter.setEmptyView(errorView);
                    mError = false;
                } else {
                    if (mNoData) {
                        mAdapter.setEmptyView(notDataView);
                        mNoData = false;
                    } else {
                        mAdapter.setNewData(MyUtils.getArray(activity, R.array.games));
                    }
                }
            }
        }, 1000);
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_emptyview;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @OnClick(R.id.btn_reset)
    public void onViewClicked() {
        mError = true;
        mNoData = true;
        mAdapter.setNewData(null);
        onRefresh();
    }
}
