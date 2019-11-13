package blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.Random;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.bean.Person;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.ExpandableItemAdapter;
import blcs.lwb.utils.adapter.BaseDemoAdapter.provider.Level0Item;
import blcs.lwb.utils.adapter.BaseDemoAdapter.provider.Level1Item;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

/**
 * 树形Recycler
 */
public class ExpandableItemFragment extends BaseFragment {

    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        ArrayList<MultiItemEntity> datas = generateData();
        final ExpandableItemAdapter mAdapter = new ExpandableItemAdapter(datas);
        final GridLayoutManager manager = new GridLayoutManager(activity, 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.getItemViewType(position) == ExpandableItemAdapter.TYPE_PERSON ? 1 : manager.getSpanCount();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        // important! setLayoutManager should be called after setAdapter
        mRecyclerView.setLayoutManager(manager);
        //开启所有菜单
//        mAdapter.expandAll();
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

    private ArrayList<MultiItemEntity> generateData() {
        int lv0Count = 9;
        int lv1Count = 3;
        int personCount = 5;

        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};
        Random random = new Random();

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item("This is " + i + "th item in Level 0", "subtitle of " + i);
            for (int j = 0; j < lv1Count; j++) {
                Level1Item lv1 = new Level1Item("Level 1 item: " + j, "(no animation)");
                for (int k = 0; k < personCount; k++) {
                    lv1.addSubItem(new Person(nameList[k], random.nextInt(40)));
                }
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        res.add(new Level0Item("This is " + lv0Count + "th item in Level 0", "subtitle of " + lv0Count));
        return res;
    }

}
