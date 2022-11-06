package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.Interfaces.OnItemClickListener;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.ListAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;

public class CoordinatorLayoutFragment extends BaseFragment {
    @BindView(R.id.rvToDoList)
    RecyclerView rvCoordinator;
    @BindView(R.id.fb_button)
    FloatingActionButton fbBotton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_coordinator;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("CoordinatorLayout");
//        activity.setSupportActionBar(toolbar);

        ArrayList<String> array = MyUtils.getArray(getActivity(), R.array.games);
        ListAdapter mAdapter = new ListAdapter();
        RecyclerUtil.init(getActivity(),LinearLayoutManager.VERTICAL,mAdapter,rvCoordinator);
        mAdapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemClick(@NonNull RecyclerView.ViewHolder viewHolder, int pos, String content) {
                Snackbar.make(fbBotton, array.get(pos), Snackbar.LENGTH_LONG)
                        .setAction("???", null)
                        .setActionTextColor(getResources().getColor(R.color.colorAccent))
                        .setDuration(3000).show();
            }
        });
        mAdapter.setNewData(array);
    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
