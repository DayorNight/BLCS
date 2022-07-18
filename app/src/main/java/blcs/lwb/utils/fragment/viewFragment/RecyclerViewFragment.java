package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.RecyclerAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class RecyclerViewFragment extends BaseFragment {

    @BindView(R.id.tool_recyclerView)
    RecyclerView recyclerView;

    private RecyclerAdapter mAdapter;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
//        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
//        mAdapter = new RecyclerAdapter(R.layout.home_item_view);
//        recyclerView.setAdapter(mAdapter);
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
}
