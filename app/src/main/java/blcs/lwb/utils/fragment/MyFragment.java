package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;

import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.mvp.presenter.MyFragmentPresenter;
import blcs.lwb.utils.mvp.view.IMyFragment;
import butterknife.BindView;

/**
 * Created by WESTAKE on 2017/5/19.
 */
public class MyFragment extends BaseFragment implements IMyFragment {

    @BindView(R.id.ll_Agentview)
    LinearLayout llAgentview;
    private AgentWeb mAgentWeb;

    @Override
    public void setMiddleTitle(Toolbar title) { }

    @Override
    protected void initView() { }

    @Override
    protected BasePresenter bindPresenter() {
        return new MyFragmentPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @Override
    public void Text() {
        Bundle arguments = getArguments();
        String url = arguments.getString(Constants.URL);
        LogUtils.e("======="+url);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llAgentview, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);

//        if (!mAgentWeb.back()){
//            AgentWebFragment.this.getActivity().finish();
//        }
    }


    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
