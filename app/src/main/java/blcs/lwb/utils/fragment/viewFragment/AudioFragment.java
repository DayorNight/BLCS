package blcs.lwb.utils.fragment.viewFragment;

import android.support.v7.widget.Toolbar;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;

/**
 * @author linweibin
 * @date 2021/12/30
 * @des 播放音频
 */
public class AudioFragment extends BaseFragment {
    @Override
    protected int bindLayout() {
        return R.layout.fragment_audio;
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
}
