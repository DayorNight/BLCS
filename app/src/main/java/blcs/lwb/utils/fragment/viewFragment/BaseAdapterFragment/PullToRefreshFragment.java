package blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.PullToRefreshAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;

public class PullToRefreshFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mRefresh;
    private PullToRefreshAdapter mAdapter;
    private int mNextRequestPage = 1;

    private static final int PAGE_SIZE = 6;
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        mRefresh.setColorSchemeColors(Color.rgb(47, 223, 189));
        initAdapter();
        addHeadView();
//        mRefresh.setRefreshing(true);
        //添加点击事件
        mAdapter.setEnableLoadMore(false);
//        mRefresh.setRefreshing(true);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(activity, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtils.e("------Load-------");
                loadMore();
            }
        });
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    /**
     * 初始适配器
     */
    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mAdapter = new PullToRefreshAdapter(R.layout.item_img_text_view, MyUtils.getArray(activity,R.array.games));
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 添加头部
     */
    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        headView.findViewById(R.id.iv).setVisibility(View.GONE);
        ((TextView) headView.findViewById(R.id.tv)).setText("change load view");
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefresh.setRefreshing(true);
                refresh();
            }
        });
        mAdapter.addHeaderView(headView);
    }

    /**
     * 刷新
     */
    private void refresh() {
        mRefresh.setRefreshing(false);
        mAdapter.setEnableLoadMore(true);

    }

    /**
     * 加载更多
     */
    private void loadMore() {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_pulltorefresh;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

}
