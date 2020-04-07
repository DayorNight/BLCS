package blcs.lwb.utils.fragment.viewFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 悬浮窗
 * @Author BLCS
 * @Time 2020/4/6 18:27
 */
public class FloatingWindowFragment extends BaseFragment {

    private Intent intent;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_floating_window;
    }
    @Override
    public void setMiddleTitle(Toolbar title) {
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        intent = new Intent(activity, FloatingWindowService.class);
        if (!Settings.canDrawOverlays(activity)) {
            Toast.makeText(activity, "当前无权限，请授权", Toast.LENGTH_SHORT);
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.getPackageName())), 0);
        } else {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (!Settings.canDrawOverlays(activity)) {
                Toast.makeText(activity, "授权失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "授权成功", Toast.LENGTH_SHORT).show();
                activity.startService(intent);
            }
        }
    }



    @OnClick({R.id.btn_start,R.id.btn_stop})
    public void viewClick(View view){
        switch (view.getId()){
            case R.id.btn_start:
                if (!FloatingWindowService.isStarted){
                    activity.startService(intent);
                }
                break;
            case R.id.btn_stop:
                activity.stopService(intent);
                break;
        }
    }


    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
