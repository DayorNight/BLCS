package blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.ArrayList;
import java.util.List;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.AnimationAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;

public class UpFetchDataFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;
    private AnimationAdapter mAdapter;
    List<String> datas =new ArrayList<>();
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        mAdapter = new AnimationAdapter(R.layout.layout_animation, datas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mRecyclerView.setAdapter(mAdapter);
        datas.addAll(MyUtils.getArray(activity, R.array.Other));
        mAdapter.setNewData(datas);
        mAdapter.setUpFetchEnable(true);
        /**
         * start fetch when scroll to position 2, default is 1.
         */
        mAdapter.setStartUpFetchPosition(2);
        mAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                startUpFetch();
            }
        });
    }

    private int count;

    private void startUpFetch() {
        count++;
        /**
         * set fetching on when start network request.
         */
        mAdapter.setUpFetching(true);
        /**
         * get data from internet.
         */
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addData(0, datas);
                /**
                 * set fetching off when network request ends.
                 */
                mAdapter.setUpFetching(false);
                /**
                 * set fetch enable false when you don't need anymore.
                 */
                if (count > 5) {
                    mAdapter.setUpFetchEnable(false);
                }
            }
        }, 300);
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
