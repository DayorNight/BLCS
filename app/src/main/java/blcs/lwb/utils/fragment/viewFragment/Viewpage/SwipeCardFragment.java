package blcs.lwb.utils.fragment.viewFragment.Viewpage;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.manager.SwipeCardLayoutManager;
import blcs.lwb.lwbtool.utils.CardConfig;
import blcs.lwb.utils.bean.SwipeCardBean;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.SwipeCardAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class SwipeCardFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView activityReview;
    private SwipeCardAdapter mAdatper;
    private ArrayList<SwipeCardBean> mList=new ArrayList<>();
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        for (int i = 0; i < 5; i++) {
            SwipeCardBean swpe = new SwipeCardBean();
            swpe.resoutimage = Constants.images[i];
            swpe.title = "美丽" + i;
            mList.add(swpe);
        }
        setData();
    }
    private void setData() {
        SwipeCardLayoutManager swmanamger = new SwipeCardLayoutManager(getActivity());
        activityReview.setLayoutManager(swmanamger);
        mAdatper = new SwipeCardAdapter(mList, activity);
        activityReview.setAdapter(mAdatper);
        CardConfig.initConfig(getActivity());
        ItemTouchHelper.Callback callback=new SwipeCardCallBack(mList,mAdatper,activityReview);
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(activityReview);
    }
    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_swipecard;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
