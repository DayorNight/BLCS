package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.Arrays;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.MpAndroidChartAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.manager.FramentManages;
import butterknife.BindView;

public class MPAndroidChartFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView toolRecyclerView;

    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    protected void initView() {
        String[] str = {"一、Line Charts","Basic -> Simple line chart",
                "二、Bar Charts","Basic -> Simple bar chart","Basic 2 -> Variation of the simple bar chart",
                "三、Pie Charts","Basic -> Simple pie chart",
                "四、Other Charts","Combined Chart -> Bar and line chart together",
                "五、Scrolling Charts","Multiple -> Various types of charts as fragments"};
        MpAndroidChartAdapter mAdapter = new MpAndroidChartAdapter();
        RecyclerUtil.init(activity, OrientationHelper.VERTICAL, mAdapter, toolRecyclerView,true);
        mAdapter.setNewData(Arrays.asList(str));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.Item_Name,FramentManages.LineCharts);
                switch (position){
                    case 1:
                        addFrament(FramentManages.LineCharts, bundle);
                        break;
                    case 3:
                        addFrament(FramentManages.BarCharts, bundle);
                        break;
                    case 4:
                        addFrament(FramentManages.BarCharts2, bundle);
                        break;
                    case 6:
                        addFrament(FramentManages.PieCharts, bundle);
                        break;
                    case 8:
                        addFrament(FramentManages.OtherCharts, bundle);
                        break;
                    case 10:
                        addFrament(FramentManages.ScrollingCharts, bundle);
                        break;
                }
            }
        });
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
