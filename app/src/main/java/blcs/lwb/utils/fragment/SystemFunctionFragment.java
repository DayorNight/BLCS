package blcs.lwb.utils.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.InputStream;
import java.io.OutputStream;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinPermission;
import blcs.lwb.lwbtool.utils.LinToPermission;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.R;
import butterknife.OnClick;

public class SystemFunctionFragment extends BaseFragment {
    public static int VOICE_RESULT_CODE = 100;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_system_function;
    }

    @Override
    protected void initView() {
        if(LinPermission.checkPermission(activity,new int[]{0,7})){

        }else{
            LinPermission.requestMultiplePermission(activity,new int[]{0,7});
        }
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

    @OnClick({R.id.btn_voice, R.id.btn_video, R.id.btn_camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            https://www.cnblogs.com/stnlcd/p/7151413.html
            case R.id.btn_voice:
                Intent intent=new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);

                startActivityForResult(intent,VOICE_RESULT_CODE);
                break;
            case R.id.btn_video:
                break;
            case R.id.btn_camera:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("===requestCode "+requestCode);
        LogUtils.e("===resultCode "+resultCode);
        LogUtils.e("===data "+data);
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode ==VOICE_RESULT_CODE){
                LogUtils.e("===data "+data);
            }
        }
    }

}
