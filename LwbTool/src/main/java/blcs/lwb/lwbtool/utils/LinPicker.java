package blcs.lwb.lwbtool.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import java.util.Calendar;
import blcs.lwb.lwbtool.R;

/**
 * 选择器
 * 日期选择器
 * 时间选择器
 */
public class LinPicker {
    public final static int TYPE_DATY_HIDE_YEAR = 1;
    public final static int TYPE_DATY_HIDE_DAY = 2;


    /**
     * 日期选择器
     */
    public static void showDate(Context context,int type,String title,OnDatePickerListener onDateTimePickerListener){
        Calendar calendar = Calendar.getInstance();
        showDate(context,title,type,0,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),onDateTimePickerListener);
    }

    /**
     * 日期选择器
     */
    public static void showDate(Context context,String title,int year,int month,int day,OnDatePickerListener onDateTimePickerListener){
        showDate(context,title,AlertDialog.THEME_HOLO_LIGHT,0,year,month,day,onDateTimePickerListener);
    }
    /**
     * 日期选择器
     * @param title 标题
     * @param alertDialog 背景：  AlertDialog.THEME_HOLO_DARK
     * @param type  隐藏年或日：LinPicker.TYPE_DATY_HIDE_YEAR/LinPicker.TYPE_DATY_HIDE_DAY
     * @param year 默认年
     * @param month 默认月
     * @param day   默认日
     * @param onDateTimePickerListener
     */
    public static void showDate(Context context,String title,int alertDialog,int type,int year,int month,int day,OnDatePickerListener onDateTimePickerListener){

        DatePickerDialog dialog = new DatePickerDialog(context, alertDialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;//月份加一
                if (onDateTimePickerListener != null) {
                    onDateTimePickerListener.onConfirm(year, month, dayOfMonth);
                }
            }
        }, year, month - 1, day);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (onDateTimePickerListener != null) {
                    onDateTimePickerListener.onCancel();
                }
            }
        });
        if (!TextUtils.isEmpty(title)) {
            dialog.setTitle(title);
        }
        setSpecialDatePicker(dialog,type);
        dialog.show();
    }

    private static void setSpecialDatePicker( DatePickerDialog dialog,int state) {
        try {
            DatePicker dp = dialog.getDatePicker();
            NumberPicker view0 = (NumberPicker) ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(0); //获取最前一位的宽度
            NumberPicker view1 = (NumberPicker) ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(1); //获取中间一位的宽度
            NumberPicker view2 = (NumberPicker) ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2);//获取最后一位的宽度

            //年的最大值为2100
            //月的最大值为11
            //日的最大值为28,29,30,31
            int value0 = view0.getMaxValue();//获取第一个View的最大值
            int value1 = view1.getMaxValue();//获取第二个View的最大值
            int value2 = view2.getMaxValue();//获取第三个View的最大值

            if (state == 1) {//隐藏年, 只显示月和日
                if (value0 > 252) {
                    view0.setVisibility(View.GONE);
                } else if (value1 > 252) {
                    view1.setVisibility(View.GONE);
                } else if (value2 > 252) {
                    view2.setVisibility(View.GONE);
                }
            } else if (state == 2) {//隐藏日, 只显示年和月
                if (value0 > 25 && value0 < 252) {
                    view0.setVisibility(View.GONE);
                } else if (value1 > 25 && value1 < 252) {
                    view1.setVisibility(View.GONE);
                } else if (value2 > 25 && value2 < 252) {
                    view2.setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 时间选择器
     */
    public static void showTimer(Context context,int type, String title,final OnTimerPickerListener onTimerPickerListener){
        Calendar calendar = Calendar.getInstance();
        showTimer(context,title,type,calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),true,onTimerPickerListener);
    }

    /**
     * 时间选择器
     */
    public static void showTimer(Context context, String title, int hour, int minute, final OnTimerPickerListener onTimerPickerListener){
        showTimer(context,title,AlertDialog.THEME_HOLO_LIGHT,hour,minute,true,onTimerPickerListener);
    }
    /**
     * 时间选择器
     * @param title 标题
     * @param alertDialog 背景：AlertDialog.THEME_HOLO_DARK
     * @param hour 小时
     * @param minute 分钟
     * @param is24HourView 24小时制
     * @param onTimerPickerListener
     */
    public static void showTimer(Context context, String title, int alertDialog, int hour, int minute, boolean is24HourView, final OnTimerPickerListener onTimerPickerListener) {
        TimePickerDialog dialog = new TimePickerDialog(context, alertDialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                if (onTimerPickerListener != null) {
                    onTimerPickerListener.onConfirm(hour, minute);
                }
            }
        }, hour, minute, is24HourView);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (onTimerPickerListener != null) {
                    onTimerPickerListener.onCancel();
                }
            }
        });
        if (!TextUtils.isEmpty(title)) {
            dialog.setTitle(title);
        }
        dialog.show();
    }

    /**
     * 日期选择器监听
     */
    public interface OnDatePickerListener {
        void onConfirm(int year, int month, int day);
        void onCancel();
    }

    /**
     * 时间选择器监听
     */
    public interface OnTimerPickerListener {
        void onConfirm(int hour, int minute);
        void onCancel();
    }

}
