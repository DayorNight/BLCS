package blcs.lwb.utils.fragment.WeChat;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import blcs.lwb.lwbtool.View.FontSizeView;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.manager.AppManager;
import blcs.lwb.lwbtool.utils.AppUtils;
import blcs.lwb.lwbtool.utils.DensityUtils;
import blcs.lwb.lwbtool.utils.IntentUtils;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RxBus;
import blcs.lwb.lwbtool.utils.SPUtils;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.MainActivity;
import blcs.lwb.utils.MyApplication;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TODO 字体大小
 */

public class FontSizeFragment extends BaseFragment {
    @BindView(R.id.fsv_font_size)
    FontSizeView fsvFontSize;
    @BindView(R.id.tv_font_size1)
    TextView tv_font_size1;
    @BindView(R.id.tv_font_size2)
    TextView tv_font_size2;
    @BindView(R.id.tv_font_size3)
    TextView tv_font_size3;

    private float fontSizeScale;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_font_size;
    }

    @Override
    protected void initView() {
        // 配置系统缩放值为1
        RxBus.getDefault().post(new RxBus.Event<>(1));

        fsvFontSize.setChangeCallbackListener(new FontSizeView.OnChangeCallbackListener() {
            @Override
            public void onChangeListener(int position) {
                int dimension = getResources().getDimensionPixelSize(R.dimen.sp_stander);
                //字体倍数
                fontSizeScale = (float) (0.875 + 0.125 * position);
                LogUtils.e("======" + fontSizeScale);
                double v = fontSizeScale * (int) DensityUtils.px2sp(activity, dimension);
                changeTextSize((int) v);
            }
        });
        float  scale = (float) SPUtils.get(activity, Constants.SP_FontScale, 0.0f);
        if(scale>0.5){
            int pos = (int) ((scale - 0.875) / 0.125);
            LogUtils.e("===="+pos);
            //注意： 写在改变监听下面 —— 否则初始字体不会改变
            fsvFontSize.setDefaultPosition(pos);
        }else{
            //注意： 写在改变监听下面 —— 否则初始字体不会改变
            fsvFontSize.setDefaultPosition(1);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SPUtils.put(activity,Constants.SP_FontScale,fontSizeScale);
        //重启应用
        AppManager.getAppManager().finishAllActivity();
        IntentUtils.toActivity(activity, MainActivity.class,true);
    }

    /**
     * 改变textsize 大小
     */
    private void changeTextSize(int dimension) {
        tv_font_size1.setTextSize(dimension);
        tv_font_size2.setTextSize(dimension);
        tv_font_size3.setTextSize(dimension);
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }



}
