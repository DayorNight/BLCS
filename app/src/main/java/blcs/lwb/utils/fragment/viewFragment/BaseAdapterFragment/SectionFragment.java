package blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.util.List;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.bean.MySection;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.SectionAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;

/**
 * 分组布局
 */
public class SectionFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(activity,2));
        List<MySection> mData = MyUtils.getSampleData();
        SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_img_text_view, R.layout.def_section_head, mData);
        mRecyclerView.setAdapter(sectionAdapter);

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
