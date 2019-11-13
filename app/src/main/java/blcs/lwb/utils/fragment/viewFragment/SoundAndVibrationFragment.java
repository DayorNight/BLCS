package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RxTool.RxBeepTool;
import blcs.lwb.lwbtool.utils.RxTool.RxVibrateTool;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 声音与震动
 */
public class SoundAndVibrationFragment extends BaseFragment {

    @BindView(R.id.sc_sound)
    SwitchCompat scSound;
    @BindView(R.id.sc_vibration)
    SwitchCompat scVibration;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_sound_vibration;
    }

    @Override
    protected void initView() {
        scSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RxBeepTool.isOpenBeep(isChecked);
            }
        });
        scVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RxVibrateTool.isVibrate(isChecked);
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


    @OnClick({R.id.btn_sound, R.id.btn_system_beep, R.id.btn_music_beep, R.id.btn_vibration})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sound:
                RxBeepTool.playBeep(activity, R.raw.music);
                break;
            case R.id.btn_system_beep:
                RxBeepTool.systemBeep(activity);
                break;
            case R.id.btn_music_beep:
                RxBeepTool.playSound(activity, R.raw.beep);
                break;

            case R.id.btn_vibration:
                RxVibrateTool.vibrateOnce(activity, 1000);
                break;
        }
    }

}
