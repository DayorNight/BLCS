package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.ArrayList;
import java.util.List;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.LearnWebsiteAdapter;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;

public class LearnWebsiteFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;
    private ArrayList<String> dataUrl;

    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(activity,3));
        LearnWebsiteAdapter mAdapter = new LearnWebsiteAdapter();
        mRecyclerView.setAdapter(mAdapter);
        List<String> data = getData();
        mAdapter.setNewData(data);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyUtils.toUrl(LearnWebsiteFragment.this,data.get(position),dataUrl.get(position));
            }
        });
    }

    /**
     * 获取数据
     * @return
     */
    private List<String> getData() {
        Bundle arguments = getArguments();
        String item = arguments.getString(Constants.Item_Name);
        ArrayList<String> array = MyUtils.getArray(activity, R.array.Resources);
        if (array.get(2).equals(item)){
            dataUrl = MyUtils.getArray(activity, R.array.Url_GitHubSources);
            return MyUtils.getArray(activity, R.array.GitHubSources);
        }else if (array.get(1).equals(item)){
            dataUrl = MyUtils.getArray(activity, R.array.Url_LearnWebsite);
            return MyUtils.getArray(activity, R.array.LearnWebsite);
        }else {
            dataUrl = MyUtils.getArray(activity, R.array.Url_LearnLanguage);
            return MyUtils.getArray(activity, R.array.LearnLanguage);
        }
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
