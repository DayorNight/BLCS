package blcs.lwb.utils.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.Date;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinPicker;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.utils.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PickerFragment extends BaseFragment {

    @Override
    protected int bindLayout() {
        return R.layout.fragment_picker;
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


    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.btn_time_selector, R.id.btn_date_selector, R.id.btn_date_selector_open, R.id.btn_options_selector_open})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_time_selector:
                LinPicker.showTimer(activity, "", new LinPicker.OnTimerPickerListener() {
                    @Override
                    public void onConfirm(int hour, int minute) {
                        RxToast.info(activity,hour+"-"+minute);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
            case R.id.btn_date_selector:
                LinPicker.showDate(activity, null, new LinPicker.OnDatePickerListener() {
                    @Override
                    public void onConfirm(int year, int month, int day) {
                        RxToast.info(activity,year+"-"+month+"-"+day);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
            case R.id.btn_date_selector_open:
                TimePickerView pvTime = new TimePickerBuilder(activity, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        RxToast.info(activity,date.getClass().toString());
                    }
                })
//                        .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
//                        .setCancelText("Cancel")//取消按钮文字
//                        .setSubmitText("Sure")//确认按钮文字
//                        .setContentSize(18)//滚轮文字大小
//                        .setTitleSize(20)//标题文字大小
//                        .setTitleText("Title")//标题文字
//                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
//                        .isCyclic(true)//是否循环滚动
//                        .setTitleColor(Color.BLACK)//标题文字颜色
//                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                        .setRangDate(startDate,endDate)//起始终止年月日设定
//                        .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
//                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .isDialog(true)//是否显示为对话框样式
                        .build();
                pvTime.show();
                break;
            case R.id.btn_options_selector_open:
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //返回的分别是三个级别的选中位置
                        RxToast.info(activity,options1+"-"+option2+"-"+options3);
                    }
                }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
                    }
                })
//                        .setSubmitText("确定")//确定按钮文字
//                        .setCancelText("取消")//取消按钮文字
//                        .setTitleText("城市选择")//标题
//                        .setSubCalSize(18)//确定和取消文字大小
//                        .setTitleSize(20)//标题文字大小
//                        .setTitleColor(Color.BLACK)//标题文字颜色
//                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                        .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
//                        .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
//                        .setContentTextSize(18)//滚轮文字大小
//                        .setLinkage(false)//设置是否联动，默认true
//                        .setLabels("省", "市", "区")//设置选择的三级单位
//                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .setCyclic(false, false, false)//循环与否
//                        .setSelectOptions(1, 1, 1)  //设置默认选中项
//                        .setOutSideCancelable(false)//点击外部dismiss default true
//                        .isDialog(true)//是否显示为对话框样式
//                        .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                        .build();
                //填充数据
//              pvOptions.setPicker(options1Items, options2Items, options3Items);
                pvOptions.show();
                break;

        }
    }
}
