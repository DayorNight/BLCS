package blcs.lwb.utils.fragment.viewFragment;

import android.annotation.SuppressLint;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinCommon;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.systemSetting.SDCardUtils;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.OnClick;

/**
 * @author linweibin
 * @date 2021/12/30
 * @des 录屏
 */
public class RecorderFragment extends BaseFragment {

    private MediaRecorder recorder;

    @OnClick(R.id.btn_start)
    public void onClick(View view){
        recorder.start();
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_recorder;
    }

    @Override
    protected void initView() {

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        recorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        recorder.setVideoFrameRate(30);
        recorder.setVideoEncodingBitRate(6000000);
//      recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()+"/recoreder.3gp");
        File file = new File("xxx.mp4");
        recorder.setOutputFile(file.getAbsolutePath());
        try {
            recorder.prepare();
        } catch (IOException e) {
            LogUtils.e("prepare failed");
            e.printStackTrace();
        }
        recorder.setOnInfoListener((mr, what, extra) -> LogUtils.i("what: "+what+" extra: "+extra));
        recorder.setOnErrorListener((mr, what, extra) -> LogUtils.e("what: "+what+" extra: "+extra));
    }

    @Override
    public void onStop() {
        super.onStop();
        recorder.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recorder.release();
    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
