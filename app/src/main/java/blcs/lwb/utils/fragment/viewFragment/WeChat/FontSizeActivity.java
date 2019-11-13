package blcs.lwb.utils.fragment.viewFragment.WeChat;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import blcs.lwb.lwbtool.View.FontSizeView;
import blcs.lwb.lwbtool.base.BaseActivity;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.manager.AppManager;
import blcs.lwb.lwbtool.utils.DensityUtils;
import blcs.lwb.lwbtool.utils.IntentUtils;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.SPUtils;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.MainActivity;
import blcs.lwb.utils.R;
import butterknife.BindView;

public class FontSizeActivity extends BaseActivity {

    @BindView(R.id.tl_toolbar)
    Toolbar tlToolbar;
    @BindView(R.id.fsv_font_size)
    FontSizeView fsvFontSize;
    @BindView(R.id.tv_font_size1)
    TextView tv_font_size1;
    @BindView(R.id.tv_font_size2)
    TextView tv_font_size2;
    @BindView(R.id.tv_font_size3)
    TextView tv_font_size3;
    private float fontSizeScale;
    private boolean isChange;//用于监听字体大小是否有改动
    private int defaultPos;
    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initView() {
        initToolbar();
        //滑动返回监听
        fsvFontSize.setChangeCallbackListener(new FontSizeView.OnChangeCallbackListener() {
            @Override
            public void onChangeListener(int position) {
                int dimension = getResources().getDimensionPixelSize(R.dimen.sp_stander);
                //根据position 获取字体倍数
                fontSizeScale = (float) (0.875 + 0.125 * position);
                //放大后的sp单位
                double v = fontSizeScale * (int) DensityUtils.px2sp(FontSizeActivity.this, dimension);
                //改变当前页面大小
                changeTextSize((int) v);
                isChange = !(position==defaultPos);
            }
        });
        float  scale = (float) SPUtils.get(this, Constants.SP_FontScale, 0.0f);
        if (scale > 0.5) {
            defaultPos = (int) ((scale - 0.875) / 0.125);
        } else {
            defaultPos=1;
        }
        //注意： 写在改变监听下面 —— 否则初始字体不会改变
        fsvFontSize.setDefaultPosition(defaultPos);
    }

    /**
     * 改变textsize 大小
     */
    private void changeTextSize(int dimension) {
        tv_font_size1.setTextSize(dimension);
        tv_font_size2.setTextSize(dimension);
        tv_font_size3.setTextSize(dimension);
    }

    private void initToolbar() {
        tlToolbar.setNavigationIcon(R.mipmap.ic_back_white);
        tlToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isChange){
                    SPUtils.put(FontSizeActivity.this,Constants.SP_FontScale,fontSizeScale);
                    //重启应用
                    AppManager.getAppManager().finishAllActivity();
                    IntentUtils.toActivity(FontSizeActivity.this, MainActivity.class,true);
                }else{
                    finish();
                }
            }
        });
        tlToolbar.setTitle("字体大小");
        tlToolbar.setTitleTextColor(Color.WHITE);
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_font_size;
    }

    /**
     * 重新配置缩放系数
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res =super.getResources();
        Configuration config = res.getConfiguration();
        config.fontScale= 1;//1 设置正常字体大小的倍数
        res.updateConfiguration(config,res.getDisplayMetrics());
        return res;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtils.e("keyCode " +keyCode);
        LogUtils.e("event " +event.getAction());
        return super.onKeyDown(keyCode, event);
    }

}