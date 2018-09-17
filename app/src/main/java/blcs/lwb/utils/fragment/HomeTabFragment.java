package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import blcs.lwb.lwbtool.base.BaseAdapter;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.manager.FramentManages;
import blcs.lwb.utils.PublicFragmentActivity;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.HomeTabAdapter;
import blcs.lwb.utils.mvp.presenter.HomeTabPresenter;
import blcs.lwb.utils.mvp.view.IHomeTabView;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * RecyclerView
 */
public class HomeTabFragment extends Fragment implements IHomeTabView{

    @BindView(R.id.tool_recyclerView)
    RecyclerView recycler;
    private Unbinder bind;
    private View mView;
    private HomeTabAdapter adapter;
    private HomeTabPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.tool_recyclerview, container, false);
            bind = ButterKnife.bind(this, mView);
            presenter = new HomeTabPresenter(this);
            presenter.onAttach(this);
            presenter.init();
        }
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetch();
        if(bind != null){
            bind.unbind();
            bind = null;
        }
    }

    @Override
    public void Recycler_init() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(manager);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(), OrientationHelper.VERTICAL));
        int position = getArguments().getInt(Constants.Adapter_Pos);
        switch (position){
            case 0:
                adapter = new HomeTabAdapter(getActivity(), MyUtils.getArray(getActivity(), R.array.Utils));
                break;
            case 1:
                adapter = new HomeTabAdapter(getActivity(), MyUtils.getArray(getActivity(), R.array.View));
                break;
            case 2:
                adapter = new HomeTabAdapter(getActivity(), MyUtils.getArray(getActivity(), R.array.Other));
                break;
            default:adapter = new HomeTabAdapter(getActivity(), MyUtils.getArray(getActivity(),R.array.Utils));
        }
        recycler.setAdapter(adapter);
    }

    @Override
    public void Recycler_click() {
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object bean) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.Item_Name,(String)bean);
                switch ((String)bean){
                    case FramentManages.DemoFragment:
                        getActivity().startActivity(PublicFragmentActivity.createIntent(getActivity(), FramentManages.DemoFragment, bundle));
                        break;
                        default:break;
                }
            }
        });
    }
}
