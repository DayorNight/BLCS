package blcs.lwb.utils;

import android.widget.ImageView;
import blcs.lwb.lwbtool.base.BaseActivity;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.IntentUtils;
import blcs.lwb.lwbtool.utils.XStatusBar;
import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initView() {
        XStatusBar.setTransparent(this);
        iv.postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentUtils.toActivity(SplashActivity.this, MainActivity.class, true);
                finish();
            }
        }, 3000);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

}
