package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.bean.CityBean;
import blcs.lwb.lwbtool.utils.LinCountry;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.CountyAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class LinCountyFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView toolRecyclerView;
    private CountyAdapter mAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    protected void initView() {
        mAdapter = new CountyAdapter();
        RecyclerUtil.init(activity, OrientationHelper.VERTICAL, mAdapter, toolRecyclerView);
        List<CityBean> province = LinCountry.getProvince1(activity);
        mAdapter.setNewData(province);
        //item click
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CityBean bean = (CityBean) adapter.getData().get(position);
                List<CityBean> cityByI = LinCountry.getCityByI(activity, bean.getI());
                if (cityByI.size()>0){
                    mAdapter.setNewData(cityByI);
                }else{
                    RxToast.success(activity, bean.getN());
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
