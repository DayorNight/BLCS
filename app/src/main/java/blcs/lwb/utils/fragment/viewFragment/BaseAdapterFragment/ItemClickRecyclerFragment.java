package blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;

import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.AnimationAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;

/**
 * 点击Recycler
 */
public class ItemClickRecyclerFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;
    private AnimationAdapter mAdapter;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mAdapter = new AnimationAdapter(R.layout.layout_animation, MyUtils.getArray(activity, R.array.games));
//        mAdapter.openLoadAnimation(mAdapter.SCALEIN);
        mRecyclerView.setAdapter(mAdapter);
        initClick();
    }
    //点击事件
    private void initClick() {
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxToast.info(activity,"onItemClick"+position);
            }
        });
        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                RxToast.info(activity,"onItemLongClick"+position);
                return true;
            }
        });

        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RxToast.info(activity,"onItemChildClick"+position);
            }
        });

        mAdapter.setOnItemChildLongClickListener(new OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                RxToast.info(activity,"onItemChildLongClick"+position);
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
        return R.layout.tool_recyclerview;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
