package blcs.lwb.utils.fragment.WeChat;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import blcs.lwb.lwbtool.View.FontSizeView;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.DensityUtils;
import blcs.lwb.lwbtool.utils.LogUtils;
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


    @Override
    protected int bindLayout() {
        return R.layout.fragment_font_size;
    }

    @Override
    protected void initView() {
        fsvFontSize.setChangeCallbackListener(new FontSizeView.OnChangeCallbackListener() {
            @Override
            public void onChangeListener(int position) {
                int sp;
                switch (position){
                    case 0:
                        sp=R.dimen.sp_small;
                        break;
                    case 1:
                        sp=R.dimen.sp_stander;
                        break;
                    case 2:
                        sp=R.dimen.sp_big1;
                        break;
                    case 3:
                        sp=R.dimen.sp_big2;
                        break;
                    case 4:
                        sp=R.dimen.sp_big3;
                        break;
                    case 5:
                        sp=R.dimen.sp_big4;
                        break;
                    case 6:
                        sp=R.dimen.sp_big5;
                        break;
                    case 7:
                        sp=R.dimen.sp_big6;
                        break;
                    default:
                        sp=R.dimen.sp_stander;
                            break;
                }
                int dimension = getResources().getDimensionPixelSize(sp);
                changeTextSize(dimension);
            }
        });

        //注意： 写在改变监听下面 —— 否则初始字体不会改变
        fsvFontSize.setDefaultPosition(1);
    }

    /**
     * 改变textsize 大小
     */
    private void changeTextSize(float dimension) {
        tv_font_size1.setTextSize(DensityUtils.px2sp(activity,dimension));
        tv_font_size2.setTextSize(DensityUtils.px2sp(activity,dimension));
        tv_font_size3.setTextSize(DensityUtils.px2sp(activity,dimension));
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
