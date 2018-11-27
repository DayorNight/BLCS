package blcs.lwb.utils.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.Bean.HomeItem;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.SplashActivity;
import blcs.lwb.utils.adapter.RecyclerAdapter;
import butterknife.BindView;

public class RecyclerViewFragment extends BaseFragment {

    @BindView(R.id.tool_recyclerView)
    RecyclerView recyclerView;

    private ArrayList<HomeItem> mDataList;
    private static final Class<?>[] ACTIVITY = {SplashActivity.class, SplashActivity.class, SplashActivity.class, SplashActivity.class, SplashActivity.class, SplashActivity.class, SplashActivity.class, SplashActivity.class, SplashActivity.class, SplashActivity.class,SplashActivity.class,SplashActivity.class};
    private static final String[] TITLE = {"Animation", "MultipleItem", "Header/Footer", "PullToRefresh", "Section", "EmptyView", "DragAndSwipe", "ItemClick", "ExpandableItem", "DataBinding", "UpFetchData", "SectionMultipleItem"};
    private static final String[] Fragment = {"AnimationRecycler", "MultipleItemRecycler", "Header/FooterRecycler", "PullToRefreshRecycler", "SectionRecycler", "EmptyViewRecycler", "DragAndSwipeRecycler", "ItemClickRecycler", "ExpandableItemRecycler", "DataBindingRecycler", "UpFetchDataRecycler", "SectionMultipleItemRecycler"};
    private static final int[] IMG = {R.mipmap.gv_animation, R.mipmap.gv_multipleltem, R.mipmap.gv_header_and_footer, R.mipmap.gv_pulltorefresh, R.mipmap.gv_section, R.mipmap.gv_empty, R.mipmap.gv_drag_and_swipe, R.mipmap.gv_item_click, R.mipmap.gv_expandable, R.mipmap.gv_databinding,R.drawable.gv_up_fetch, R.mipmap.gv_multipleltem};

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        initData();//加载数据
        GridLayoutManager manager = new GridLayoutManager(activity, 3);
        recyclerView.setLayoutManager(manager);
        RecyclerAdapter mAdapter = new RecyclerAdapter(R.layout.home_item_view, mDataList);
        recyclerView.setAdapter(mAdapter);
        //点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.Item_Name,TITLE[position]);
                addFrament(Fragment[position],bundle);
            }
        });
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setActivity(ACTIVITY[i]);
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
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
