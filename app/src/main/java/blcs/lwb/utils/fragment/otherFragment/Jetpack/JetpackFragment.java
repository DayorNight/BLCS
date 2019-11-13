package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.Arrays;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.ListAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.manager.FramentManages;
import butterknife.BindView;

public class JetpackFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView toolRecyclerView;
    private String[] datas = {"DataBinding","Lifecycle","LiveData","Navigation","Paging","Room","ViewModel","WorkManager"};
    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    protected void initView() {
        ListAdapter jetpackAdapter = new ListAdapter();
        RecyclerUtil.init(activity, LinearLayoutManager.VERTICAL,jetpackAdapter , toolRecyclerView);
        jetpackAdapter.setNewData(Arrays.asList(datas));
        jetpackAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                addFrament(datas[position], datas[position]);
            }
        });
    }

    @Override
    public void setMiddleTitle(Toolbar title) {
        title.setTitle(FramentManages.Jetpack);
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

}
