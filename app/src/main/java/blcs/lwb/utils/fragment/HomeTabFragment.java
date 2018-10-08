package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.just.agentweb.LogUtils;

import blcs.lwb.lwbtool.RxToast;
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
    private FragmentActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            activity = getActivity();
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
        int view;
        switch (position){
            case 0:
                view= R.array.Utils;
                break;
            case 1:
                view = R.array.View;
                break;
            case 2:
                view = R.array.Other;
                break;
            default:
                view = R.array.Utils;break;
        }
        adapter = new HomeTabAdapter(activity, MyUtils.getArray(getActivity(), view));
        recycler.setAdapter(adapter);
    }

    @Override
    public void Recycler_click() {
        /**
         * 点击Item
         */
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object bean) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.Item_Name,(String)bean);
                switch ((String)bean){
                    case FramentManages.Demo:
                        toDemo(bundle);
                        break;
                    case FramentManages.Umeng:
                        bundle.putString(Constants.URL,Constants.URL_Umeng);
                        toDemo(bundle);
                        break;
                    case FramentManages.Umeng_Package:
                        bundle.putString(Constants.URL,Constants.URL_UmengPackage);
                        toDemo(bundle);
                        break;
                    case FramentManages.APK_Sign:
                        bundle.putString(Constants.URL,Constants.APK_Sign);
                        toDemo(bundle);
                        break;
                    case FramentManages.Log_Utils:
                        bundle.putString(Constants.URL,Constants.LOG_Utils);
                        toDemo(bundle);
                        break;
                    case FramentManages.String_Utils:
                        toFragment(bundle,FramentManages.String_Utils);
                        break;
                    case FramentManages.EditText_Utils:
                        toFragment(bundle,FramentManages.EditText_Utils);
                        break;
                    case FramentManages.Intent_Utils:
                        toFragment(bundle,FramentManages.Intent_Utils);
                        break;
                    case FramentManages.App_Utils:
                        toFragment(bundle,FramentManages.App_Utils);
                        break;
                    case FramentManages.Screen_Utils:
                        toFragment(bundle,FramentManages.Screen_Utils);
                        break;
                    case FramentManages.Bitmap_Utils:
                        toFragment(bundle,FramentManages.Bitmap_Utils);
                        break;
                    case FramentManages.RxToast:
                        toFragment(bundle,FramentManages.RxToast);
                        break;
                    case FramentManages.BottomNavigation:
                        toFragment(bundle,FramentManages.BottomNavigation);
                        break;
                        default:
                            RxToast.warning(activity,getString(R.string.function_unopen));
                            break;
                }
            }
        });
    }


    public void toDemo(Bundle bundle){
        activity.startActivity(PublicFragmentActivity.createIntent(activity, FramentManages.Demo, bundle));
    }

    public void toFragment(Bundle bundle,String tag){
        activity.startActivity(PublicFragmentActivity.createIntent(activity,tag, bundle));
    }
}
