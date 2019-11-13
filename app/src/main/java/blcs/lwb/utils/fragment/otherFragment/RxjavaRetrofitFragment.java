package blcs.lwb.utils.fragment.otherFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.retrofit.MyObserver;
import blcs.lwb.lwbtool.bean.Demo;
import blcs.lwb.lwbtool.retrofit.use.RequestUtils;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mrd on 2019/2/28.
 */

public class RxjavaRetrofitFragment extends BaseFragment {
    @BindView(R.id.tv_retrofit)
    TextView tvRetrofit;

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_rxjava_retrofit;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }


    @OnClick({R.id.btn_retrofit_get, R.id.btn_retrofit_getList})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_retrofit_get:
                RequestUtils.getDemo(this,new MyObserver<Demo>(activity) {
                    @Override
                    public void onSuccess(Demo result) {
                        tvRetrofit.setText(result.toString());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });
                break;
            case R.id.btn_retrofit_getList:
                RequestUtils.getDemoList(this,new MyObserver<List<Demo>>(activity) {
                        @Override
                        public void onSuccess(List<Demo> result) {
                            tvRetrofit.setText(result.toString());
                        }
                        @Override
                        public void onFailure(Throwable e, String errorMsg) {
                        }
                    });
                break;
        }
    }
}
