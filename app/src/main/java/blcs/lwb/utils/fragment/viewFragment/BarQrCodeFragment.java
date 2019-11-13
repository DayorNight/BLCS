package blcs.lwb.utils.fragment.viewFragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.yzq.zxinglibrary.common.Constant;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinQrCode;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class BarQrCodeFragment extends BaseFragment {
    @BindView(R.id.et_bar_qr)
    EditText etBarQr;
    @BindView(R.id.iv_bar_qr)
    ImageView ivBarQr;
    @BindView(R.id.tv_code)
    TextView tvCode;
    private int REQUEST_CODE = 200;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_bar_qr;
    }

    @Override
    protected void initView() {
        ivBarQr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {//识别二维码/条形码
                Bitmap bitmap = ((BitmapDrawable) ivBarQr.getDrawable()).getBitmap();
                Result result = LinQrCode.decodeFromPhoto(bitmap);
                if (result!=null) tvCode.setText(result.getText());
                return false;
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

    @OnClick({R.id.btn_QrCode, R.id.btn_barCode, R.id.btn_custom_qrCode, R.id.btn_scan_code})
    public void onViewClicked(View view) {
        String content = etBarQr.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_QrCode://生成二维码
                if (content != null && content.length() > 0) LinQrCode.createQRCode(content, ivBarQr);
                break;
            case R.id.btn_barCode://生成条形码
                if (content != null && content.length() > 0) {
                    if (LinQrCode.isNumberOrAlpha(content)) {
                        LinQrCode.createBarCode(content, ivBarQr);
                    } else {
                        Toast.makeText(activity, "只限数字与字母", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_custom_qrCode://生成LOGO二维码
                if (content != null && content.length() > 0) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_app512);
                    LinQrCode.createQRCode(content, bitmap, ivBarQr);
                }
                break;
            case R.id.btn_scan_code://扫描二维码
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {//申请权限
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
                } else { //已申请
                    LinQrCode.startScan(activity, REQUEST_CODE);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                tvCode.setText("扫描结果为：" + content);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //权限申请成功
        if (requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            LinQrCode.startScan(activity, REQUEST_CODE);
        }
    }
}
