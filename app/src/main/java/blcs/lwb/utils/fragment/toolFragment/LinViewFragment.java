package blcs.lwb.utils.fragment.toolFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import blcs.lwb.lwbtool.View.customView.SelectedRangeView;
import blcs.lwb.lwbtool.View.customView.WeChatFlowPieView;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class LinViewFragment extends BaseFragment {

    @BindView(R.id.wcpv)
    WeChatFlowPieView wcpv;
    @BindView(R.id.sr_range)
    SelectedRangeView srRange;
    @BindView(R.id.tv_value_range)
    TextView tvValueRange;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_lin_view;
    }

    @Override
    protected void initView() {
        wcpv.setData(1,3,6);

        srRange.addOnChangeListener(new SelectedRangeView.OnChangeListener() {
            private int valueB;
            private int valueA;

            @Override
            public void getValueA(int value) {
                valueA = value;
                tvValueRange.setText(String.format( "取值范围：%s—%s",value,valueB));
            }

            @Override
            public void getValueB(int value) {
                valueB = value;
                tvValueRange.setText(String.format( "取值范围：%s—%s",valueA,value));
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
