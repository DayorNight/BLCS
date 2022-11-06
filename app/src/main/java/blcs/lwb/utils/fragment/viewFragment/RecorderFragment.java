package blcs.lwb.utils.fragment.viewFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaRecorder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author linweibin
 * @date 2021/12/30
 * @des 录屏
 */
public class RecorderFragment extends BaseFragment {
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.ttv_video)
    TextureView tvideo;

    private MediaRecorder recorder;
    private Camera camera;
    private boolean unPermissionCamera;
    private boolean unPermissionRecord;

    @OnClick(R.id.btn_start)
    public void onClick(View view) {
        if (btnStart.getText().toString().equals("开始")) {
            startRecorder();
            btnStart.setText("结束");
        } else {
            stopRecorder();
            btnStart.setText("开始");
        }
    }

    private void stopRecorder() {
        if (recorder != null) {
            recorder.setOnErrorListener(null);
            recorder.setOnInfoListener(null);
            recorder.setPreviewDisplay(null);
            recorder.stop();
            recorder.release();
            camera.stopPreview();
            camera.release();
            recorder = null;
        }
    }


    private void startRecorder() {
        if (unPermissionRecord){
            Toast.makeText(activity,"没有录音权限",Toast.LENGTH_SHORT).show();
            return;
        }
        if (unPermissionCamera){
            Toast.makeText(activity,"没有相机权限",Toast.LENGTH_SHORT).show();
            return;
        }
        camera = Camera.open();
        camera.setDisplayOrientation(90);
        camera.unlock();
        recorder = new MediaRecorder();
        recorder.setCamera(camera);
        /* 设置音频源 */
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        /* 设置视频源 */
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        /* 设置输出格式 */
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        /* 设置音频编码 */
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        /* 设置视频编码 */
        recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        /* 设置要捕获的视频的帧速率 */
        recorder.setVideoFrameRate(30);
        /* 设置视频大小 */
        recorder.setVideoSize(640,480);
        File file = new File(activity.getExternalFilesDir(""),System.currentTimeMillis()+".mp4");
        /* 设置视频输出文件 */
        LogUtils.i(file.getAbsolutePath());
        recorder.setOutputFile(file.getAbsolutePath());
        /* 设置 Surface 以显示录制的媒体（视频）的预览 */
        recorder.setPreviewDisplay(new Surface(tvideo.getSurfaceTexture()));
        recorder.setOnInfoListener((mr, what, extra) -> LogUtils.i("what: "+what+" extra: "+extra));
        recorder.setOnErrorListener((mr, what, extra) -> LogUtils.e("what: "+what+" extra: "+extra));
        /* recorder */
        recorder.setOrientationHint(90);
        try {
            recorder.prepare();
        } catch (IOException e) {
            LogUtils.e("prepare failed");
            e.printStackTrace();
        }
        recorder.start();
    }


    @Override
    protected int bindLayout() {
        return R.layout.fragment_recorder;
    }

    public static final int RECORD_CODE = 1000;
    public static final int CAMERA_CODE = 2000;
    @Override
    protected void initView() {
        unPermissionRecord = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED;
        if (unPermissionRecord){
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.RECORD_AUDIO},RECORD_CODE);
        }
        unPermissionCamera = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED;
        if (unPermissionCamera) {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRecorder();
    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
