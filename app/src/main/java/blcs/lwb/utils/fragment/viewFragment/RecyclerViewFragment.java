package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.retrofit.RxHelper;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.bean.HomeItem;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.RecyclerAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecyclerViewFragment extends BaseFragment {

    @BindView(R.id.tool_recyclerView)
    RecyclerView recyclerView;

    private ArrayList<HomeItem> mDataList = new ArrayList<>();
    private ArrayList<String> titles;
    private ArrayList<String> fragment;
    private RecyclerAdapter mAdapter;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        mAdapter = new RecyclerAdapter(R.layout.home_item_view);
        recyclerView.setAdapter(mAdapter);
        //点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                addFrament(fragment.get(position), titles.get(position));
            }
        });

        initData();//加载数据
    }

    private void initData() {

        Observable.create(new ObservableOnSubscribe<List<HomeItem>>() {
            @Override
            public void subscribe(ObservableEmitter<List<HomeItem>> observableEmitter) throws Exception {
                fragment = MyUtils.getArray(activity, R.array.Recycler_fragment);
                titles = MyUtils.getArray(activity, R.array.Recycler_title);
                ArrayList<String> images = MyUtils.getArray(activity, R.array.Recycler_Image);
                for (int i = 0; i < titles.size(); i++) {
                    int mipmap = activity.getResources().getIdentifier(images.get(i), "mipmap", activity.getPackageName());
                    mDataList.add(new HomeItem(titles.get(i), mipmap));
                }
                observableEmitter.onNext(mDataList);
            }
        }).compose(RxHelper.observableIO2Main(this)).subscribe(new Consumer<List<HomeItem>>() {
            @Override
            public void accept(List<HomeItem> s) {
                if (mAdapter != null) mAdapter.setNewData(s);
            }
        });
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
