package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.Arrays;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.Interfaces.OnItemClickListener;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.ListAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.manager.FramentManages;
import butterknife.BindView;

public class JetpackFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView toolRecyclerView;
    private final String[] datas = {"DataBinding","Lifecycle","LiveData","Navigation","Paging","Room","ViewModel","WorkManager"};
    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    protected void initView() {
        ListAdapter jetpackAdapter = new ListAdapter();
        RecyclerUtil.init(activity, LinearLayoutManager.VERTICAL,jetpackAdapter , toolRecyclerView);
        jetpackAdapter.setNewData(Arrays.asList(datas));
        jetpackAdapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemClick(@NonNull RecyclerView.ViewHolder viewHolder, int pos, String content) {
                Log.e("TAG", "onItemClick: "+content );
                addFrament(content, content);
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
