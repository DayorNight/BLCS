package blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.DemoMultipleItemRvAdapter;
import blcs.lwb.utils.adapter.BaseDemoAdapter.MultipleItem;
import blcs.lwb.utils.adapter.BaseDemoAdapter.MultipleItemQuickAdapter;
import blcs.lwb.utils.adapter.BaseDemoAdapter.NormalMultipleEntity;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;

/**
 * 复杂Item布局
 */
public class MultipleItemRecyclerFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.rg_multiple)
    RadioGroup rgMultiple;
    @BindView(R.id.rb_multiple1)
    RadioButton rbMultiple1;
    private List<MultipleItem> multipleItemData;
    private MultipleItemQuickAdapter mAdapter;
    private DemoMultipleItemRvAdapter multipleItemAdapter;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }
    @Override
    protected void initView() {
        GridLayoutManager manager = new GridLayoutManager(activity, 4);
        mRecyclerView.setLayoutManager(manager);
        initMultipleItemQuickAdapter();
        initDemoMultipleItemRvAdapter();
        rbMultiple1.setChecked(true);
        rgMultiple.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()){
                    case R.id.rb_multiple1:
                        mRecyclerView.setAdapter(mAdapter);
                        break;
                    case R.id.rb_multiple2:
                        mRecyclerView.setAdapter(multipleItemAdapter);
                        break;
                }
            }
        });
    }

    private void initDemoMultipleItemRvAdapter() {
        multipleItemAdapter = new DemoMultipleItemRvAdapter(MyUtils.getNormalMultipleEntities());
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                int type = MyUtils.getNormalMultipleEntities().get(position).type;
                if (type == NormalMultipleEntity.SINGLE_TEXT) {
                    return MultipleItem.TEXT_SPAN_SIZE;
                } else if (type == NormalMultipleEntity.SINGLE_IMG) {
                    return MultipleItem.IMG_SPAN_SIZE;
                } else {
                    return MultipleItem.IMG_TEXT_SPAN_SIZE;
                }
            }
        });
    }

    private void initMultipleItemQuickAdapter() {
        multipleItemData = MyUtils.getMultipleItemData();
        mAdapter = new MultipleItemQuickAdapter(activity, multipleItemData);
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return multipleItemData.get(position).getSpanSize();//占用列数
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_recycler_multiple;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
