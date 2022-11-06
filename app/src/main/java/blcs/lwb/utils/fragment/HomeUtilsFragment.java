package blcs.lwb.utils.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.Constants;
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
public class HomeUtilsFragment extends Fragment implements IHomeTabView{

    @BindView(R.id.tool_recyclerView)
    RecyclerView recycler;
    private Unbinder bind;
    private View mView=null;
    private HomeTabAdapter adapter;
    private HomeTabPresenter presenter;
    private FragmentActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            activity = getActivity();
            mView = inflater.inflate(R.layout.tool_recyclerview, container, false);
            bind = ButterKnife.bind(this, mView);
            presenter = new HomeTabPresenter(this);
            presenter.init();
        }
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetch();
        if(bind != null){
            mView=null;
            bind.unbind();
            bind = null;
        }
    }

    @Override
    public void Recycler_init() {
        adapter = new HomeTabAdapter();
        RecyclerUtil.init(activity,OrientationHelper.VERTICAL,adapter,recycler);
        adapter.setNewData(MyUtils.getArray(activity, R.array.Utils));
    }

    @Override
    public void Recycler_click() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                String item = (String) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.Item_Name,item);
                MyUtils.toFragment(activity,bundle,item);
            }
        });
    }
}
