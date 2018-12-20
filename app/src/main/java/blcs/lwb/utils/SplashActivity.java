package blcs.lwb.utils;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import blcs.lwb.lwbtool.base.BaseActivity;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.fragment.IntentUtilsFragment;
import butterknife.BindView;
import butterknife.OnClick;


public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    @BindView(R.id.btn_close)
    Button btnClose;
    @BindView(R.id.tv_request)
    TextView tvRequest;
    private int ResultCode= 2;
    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initView() {
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        if(bundle!=null){
            tvRequest.setText(bundle.getString(IntentUtilsFragment.Key));
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }


    @OnClick(R.id.btn_close)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                setResult(ResultCode);
                finish();
                break;
        }
    }
}
