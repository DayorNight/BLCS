package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.dialog.RxDialog;
import blcs.lwb.lwbtool.utils.dialog.RxDialogAcfunVideoLoading;
import blcs.lwb.lwbtool.utils.dialog.RxDialogEditSureCancel;
import blcs.lwb.lwbtool.utils.dialog.RxDialogLoading;
import blcs.lwb.lwbtool.utils.dialog.RxDialogScaleView;
import blcs.lwb.lwbtool.utils.dialog.RxDialogShapeLoading;
import blcs.lwb.lwbtool.utils.dialog.RxDialogSure;
import blcs.lwb.lwbtool.utils.dialog.RxDialogSureCancel;
import blcs.lwb.lwbtool.utils.dialog.RxDialogWheelYearMonthDay;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class Common_DialogFragment extends BaseFragment {
    private RxDialogWheelYearMonthDay mRxDialogWheelYearMonthDay;
    @BindView(R.id.button_DialogWheelYearMonthDay)
    Button buttonDialogWheelYearMonthDay;
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_dialog;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }


    @OnClick({R.id.button_tran, R.id.button_DialogSure, R.id.button_DialogSureCancle, R.id.button_DialogEditTextSureCancle, R.id.button_DialogWheelYearMonthDay, R.id.button_DialogShapeLoading, R.id.button_DialogLoadingProgressAcfunVideo, R.id.button_DialogLoadingspinkit, R.id.button_DialogScaleView, R.id.activity_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_tran:
                RxDialog rxDialog = new RxDialog(activity, R.style.tran_dialog);
                View view1 = LayoutInflater.from(activity).inflate(R.layout.image, null);
                ImageView pageItem = view1.findViewById(R.id.page_item);
                pageItem.setImageResource(R.mipmap.coin);
                rxDialog.setContentView(view1);
                rxDialog.show();
                break;
            case R.id.button_DialogSure:
                final RxDialogSure rxDialogSure = new RxDialogSure(getActivity());
                rxDialogSure.getLogoView().setImageResource(R.mipmap.img_sleep);
                rxDialogSure.getSureView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogSure.cancel();
                    }
                });
                rxDialogSure.show();
                break;
            case R.id.button_DialogSureCancle:
                final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(getActivity());
                rxDialogSureCancel.getTitleView().setBackgroundResource(R.mipmap.img_sleep);
                rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogSureCancel.cancel();
                    }
                });
                rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogSureCancel.cancel();
                    }
                });
                rxDialogSureCancel.show();
                break;
            case R.id.button_DialogEditTextSureCancle:
                final RxDialogEditSureCancel rxDialogEditSureCancel = new RxDialogEditSureCancel(getActivity());
                rxDialogEditSureCancel.getTitleView().setBackgroundResource(R.mipmap.img_sleep);
                rxDialogEditSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogEditSureCancel.cancel();
                    }
                });
                rxDialogEditSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogEditSureCancel.cancel();
                    }
                });
                rxDialogEditSureCancel.show();
                break;
            case R.id.button_DialogWheelYearMonthDay:
                if (mRxDialogWheelYearMonthDay == null) {
                    initWheelYearMonthDayDialog();
                }
                mRxDialogWheelYearMonthDay.show();
                break;
            case R.id.button_DialogShapeLoading:
                RxDialogShapeLoading rxDialogShapeLoading = new RxDialogShapeLoading(getActivity());
                rxDialogShapeLoading.show();
                break;
            case R.id.button_DialogLoadingProgressAcfunVideo:
                new RxDialogAcfunVideoLoading(getActivity()).show();
                break;
            case R.id.button_DialogLoadingspinkit:
                RxDialogLoading rxDialogLoading = new RxDialogLoading(getActivity());
                rxDialogLoading.show();
                break;
            case R.id.button_DialogScaleView:
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(getActivity());
                rxDialogScaleView.setImage(R.mipmap.ic1);
                rxDialogScaleView.show();
                break;
            case R.id.activity_dialog:
                break;
        }
    }


    private void initWheelYearMonthDayDialog() {
        // ------------------------------------------------------------------选择日期开始
        mRxDialogWheelYearMonthDay = new RxDialogWheelYearMonthDay(getActivity(), 1994, 2018);
        mRxDialogWheelYearMonthDay.getSureView().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        if (mRxDialogWheelYearMonthDay.getCheckBoxDay().isChecked()) {
                            buttonDialogWheelYearMonthDay.setText(
                                    mRxDialogWheelYearMonthDay.getSelectorYear() + "年"
                                            + mRxDialogWheelYearMonthDay.getSelectorMonth() + "月"
                                            + mRxDialogWheelYearMonthDay.getSelectorDay() + "日");
                        } else {
                            buttonDialogWheelYearMonthDay.setText(
                                    mRxDialogWheelYearMonthDay.getSelectorYear() + "年"
                                            + mRxDialogWheelYearMonthDay.getSelectorMonth() + "月");
                        }
                        mRxDialogWheelYearMonthDay.cancel();
                    }
                });
        mRxDialogWheelYearMonthDay.getCancleView().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        mRxDialogWheelYearMonthDay.cancel();
                    }
                });
        // ------------------------------------------------------------------选择日期结束
    }

}
