package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.mvp.view.IMyFragment;
import blcs.lwb.utils.mvp.presenter.MyFragmentPresenter;
import butterknife.BindView;

/**
 * Created by WESTAKE on 2017/5/19.
 */
public class MyFragment extends BaseFragment implements IMyFragment {

    @BindView(R.id.tv_item_home_name)
    TextView tvItemHomeName;


    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return new MyFragmentPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.textview;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @Override
    public void Text() {
        tvItemHomeName.setText("1111111");
    }

}
