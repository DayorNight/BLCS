package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.View.LuckPanLayout;
import blcs.lwb.lwbtool.View.RotatePan;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.MyUtils;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.TurnTableAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class TurnTableViewFragment extends BaseFragment {
    @BindView(R.id.luckpan_layout)
    LuckPanLayout luckpanLayout;
    @BindView(R.id.rotatePan)
    RotatePan rotatePan;
    @BindView(R.id.rl_project)
    RecyclerView rlProject;
    private String[] strs ;
    private String[] imgs ;
    private List<String> checked_Strs=new ArrayList<>();
    private List<String> checked_imgs=new ArrayList<>();
    private TurnTableAdapter adapter;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        strs = getResources().getStringArray(R.array.games);
        imgs = getResources().getStringArray(R.array.imgs);
        initRecyclerView();
        luckpanLayout.setAnimationEndListener(new LuckPanLayout.AnimationEndListener() {
            @Override
            public void endAnimation(int position) {
                //动画停止操作
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(OrientationHelper.HORIZONTAL);
        rlProject.setLayoutManager(manager);
        adapter = new TurnTableAdapter(Arrays.asList(strs));
        rlProject.setAdapter(adapter);
        adapter.setOnItemClick(new TurnTableAdapter.OnItemClickListener() {
            @Override
            public void onclick(CompoundButton box, int pos, boolean isChecked) {
                if(isChecked){
                    if(!checked_Strs.contains(strs[pos])){
                        checked_Strs.add(strs[pos]);
                        checked_imgs.add(imgs[pos]);
                        rotatePan.setStr(checked_Strs,checked_imgs);
                    }
                }else{
                    if(checked_Strs.contains(strs[pos])){
                        checked_Strs.remove(strs[pos]);
                        checked_imgs.remove(imgs[pos]);
                        rotatePan.setStr(checked_Strs,checked_imgs);
                    }
                }
            }
        });

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_turntable;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @OnClick(R.id.go)
    public void onViewClicked() {
        luckpanLayout.rotate(-1,100);
    }
}
