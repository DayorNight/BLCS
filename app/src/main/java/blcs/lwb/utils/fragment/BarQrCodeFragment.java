package blcs.lwb.utils.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinQrCode;
import blcs.lwb.lwbtool.utils.MyUtils;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.utils.R;
import butterknife.BindView;
import butterknife.OnClick;

public class BarQrCodeFragment extends BaseFragment {
    @BindView(R.id.et_bar_qr)
    EditText etBarQr;
    @BindView(R.id.iv_bar_qr)
    ImageView ivBarQr;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_bar_qr;
    }

    @Override
    protected void initView() {
        ivBarQr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bitmap bitmap =((BitmapDrawable)ivBarQr.getDrawable()).getBitmap();
                String text = LinQrCode.decodeFromPhoto(bitmap).getText();
                RxToast.success(activity,text);
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


    @OnClick({R.id.btn_QrCode, R.id.btn_barCode})
    public void onViewClicked(View view) {
        String content = etBarQr.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_QrCode:
                if(content!=null&&content.length()>0) LinQrCode.createQRCode(content,ivBarQr);
                break;
            case R.id.btn_barCode:
                if(content!=null&&content.length()>0) {
                    if(StringUtils.isNumberOrAlpha(content)) {
                        LinQrCode.createBarCode(content,ivBarQr);
                    } else{
                        MyUtils.toast(activity, "只限数字与字母");
                    }
                }
                break;
            case R.id.btn_custom_qrCode:
                if(content!=null&&content.length()>0){
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
                    LinQrCode.createQRCode(content,bitmap,ivBarQr);
                }
                break;
            case R.id.btn_scan_code:
                break;
        }
    }
}
