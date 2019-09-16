package blcs.lwb.utils.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.MyUtils;
import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.lwbtool.utils.camera.RxBarCode;
import blcs.lwb.lwbtool.utils.camera.RxQRCode;
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
        String trim = etBarQr.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_QrCode:
                if(trim!=null&&trim.length()>0) RxQRCode.builder(trim).into(ivBarQr);
//                RxQRCode.createQRCode(trim, ivBarQr);
                break;
            case R.id.btn_barCode:
                if(trim!=null&&trim.length()>0) {
                    if(StringUtils.isNumberOrAlpha(trim)) {
                        RxBarCode.builder(trim).codeColor(Color.BLACK).backColor(Color.WHITE).into(ivBarQr);
                    } else{
                        MyUtils.toast(activity, "只限数字与字母");
                    }
                }
                break;
        }
    }
}
