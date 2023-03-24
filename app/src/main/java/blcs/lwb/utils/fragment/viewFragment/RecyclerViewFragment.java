package blcs.lwb.utils.fragment.viewFragment;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import blcs.lwb.lwbtool.View.DividerItem;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.ListAdapter;
import blcs.lwb.utils.adapter.ListItemAnimatorAdapter;
import blcs.lwb.utils.adapter.RecyclerAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.widget.CustomItemDivider;
import butterknife.BindView;

public class RecyclerViewFragment extends BaseFragment {

    @BindView(R.id.tool_recyclerView)
    RecyclerView recyclerView;

    private ListItemAnimatorAdapter mAdapter;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }
    private final String[] datas = {"数据","数据","数据","数据","数据","数据","数据","数据"};
    @Override
    protected void initView() {
        mAdapter = new ListItemAnimatorAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL,false));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(getCustomItemDivider());
        recyclerView.setItemAnimator(getDefaultItemAnimator());
        mAdapter.setNewData(Arrays.asList(datas));
        recyclerView.setBackgroundColor(Color.parseColor("#f0f0f0"));
    }

    @NonNull
    private DefaultItemAnimator getDefaultItemAnimator() {
        DefaultItemAnimator animator =  new DefaultItemAnimator();
        animator.setAddDuration(1000);
        animator.setChangeDuration(1000);
        animator.setMoveDuration(1000);
        animator.setRemoveDuration(1000);
        return animator;
    }

    @NonNull
    private CustomItemDivider getCustomItemDivider() {
        CustomItemDivider customItemDivider = new CustomItemDivider(getContext(), OrientationHelper.VERTICAL);
        ShapeDrawable drawable = new ShapeDrawable();
        drawable.getPaint().setStyle(Paint.Style.FILL);
        drawable.getPaint().setColor(Color.parseColor("#f0f0f0"));
        drawable.setIntrinsicHeight(50);
        Drawable[] layers = {drawable};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        customItemDivider.setDrawable(layerDrawable);
        return customItemDivider;
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
