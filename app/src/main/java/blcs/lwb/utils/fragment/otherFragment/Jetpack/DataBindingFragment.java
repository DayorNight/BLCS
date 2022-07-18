package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.os.Bundle;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.ListAdapter;
import blcs.lwb.utils.databinding.FragmentDatabindingBinding;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;

public class DataBindingFragment extends BaseFragment implements View.OnClickListener {

    private FragmentDatabindingBinding bind;
    private final List<String> datas = new ArrayList<>();
    private ListAdapter listAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_databinding;
    }

    @Override
    protected void initView() {
        bind = DataBindingUtil.bind(mView);
        bind.setLifecycleOwner((LifecycleOwner) this);
        bind.setClick(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        listAdapter = new ListAdapter();
        bind.setManager(linearLayoutManager);
        bind.setListAdapter(listAdapter);

    }
    //自定义属性 设置适配器
    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dataBinding:
                MyUtils.toUrl(this,"DataBinding",getString(R.string.URL_DataBinding));
                break;
            case R.id.btn_dataBinding_add:
                listAdapter.addData(bind.getContent());
                bind.setContent("");
                break;
        }

    }
}
