package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.bean.HomeItem;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.RecyclerAdapter;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;

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
                Bundle bundle = new Bundle();
                bundle.putString(Constants.Item_Name,titles.get(position));
                addFrament(fragment.get(position),bundle);
            }
        });

        initData();//加载数据
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                fragment = MyUtils.getArray(activity, R.array.Recycler_fragment);
                titles = MyUtils.getArray(activity, R.array.Recycler_title);
                ArrayList<String> images = MyUtils.getArray(activity, R.array.Recycler_Image);
                for (int i = 0; i < titles.size(); i++) {
                    int mipmap = activity.getResources().getIdentifier(images.get(i), "mipmap", activity.getPackageName());
                    mDataList.add(new HomeItem(titles.get(i),mipmap));
                }
                //刷新UI
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        if(mAdapter!=null) mAdapter.setNewData(mDataList);
                    }
                });
            }
        }).start();

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
