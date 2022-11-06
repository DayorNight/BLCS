package blcs.lwb.lwbtool.utils.dialog;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Calendar;

import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.utils.TimeUtil;
import blcs.lwb.lwbtool.utils.wheelview.DateArrayAdapter;
import blcs.lwb.lwbtool.utils.wheelview.NumericWheelAdapter;
import blcs.lwb.lwbtool.utils.wheelview.OnWheelChangedListener;
import blcs.lwb.lwbtool.utils.wheelview.WheelView;


/**
 * @author vondear
 */
public class RxDialogWheelYearMonthDay extends RxDialog {
    private WheelView mYearView;
    private WheelView mMonthView;
    private WheelView mDayView;
    private int curYear;
    private int curMonth;
    private int curDay;
    private TextView mTvSure;
    private TextView mTvCancle;
    private CheckBox mCheckBoxDay;
    private Calendar mCalendar;
    private LinearLayout llType;
    private final String[] mMonths = new String[]{"01", "02", "03",
            "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private final String[] mDays = new String[]{"01", "02", "03", "04", "05", "06", "07",
            "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private int beginYear = 0;
    private int endYear = 0;
    private final int divideYear = endYear - beginYear;

    public RxDialogWheelYearMonthDay(Context mContext) {
        super(mContext);
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        build();
    }

    public RxDialogWheelYearMonthDay(Context mContext, int beginYear) {
        super(mContext);
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.beginYear = beginYear;
        build();
    }

    public RxDialogWheelYearMonthDay(Context mContext, int beginYear, int endYear) {
        super(mContext);
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.beginYear = beginYear;
        this.endYear = endYear;
        build();
    }

    public RxDialogWheelYearMonthDay(Context mContext, TextView tvTime) {
        super(mContext);
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        build();
        tvTime.setText(curYear + "年" + mMonths[curMonth] + "月");
    }

    public int getBeginYear() {
        return beginYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getDivideYear() {
        return divideYear;
    }

    public void setMonthType(Boolean isTrue) {
        if (isTrue) {
            llType.setVisibility(View.INVISIBLE);
        } else {
            llType.setVisibility(View.VISIBLE);
        }
        mCheckBoxDay.setChecked(!isTrue);

    }

    private void build() {
        mCalendar = Calendar.getInstance();
        final View dialogView1 = LayoutInflater.from(mContext).inflate(R.layout.dialog_year_month_day, null);

        OnWheelChangedListener listener = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateDays(mYearView, mMonthView, mDayView);
            }
        };

        curYear = mCalendar.get(Calendar.YEAR);
        if (beginYear == 0) {
            beginYear = curYear - 5;
        }
        if (endYear == 0) {
            endYear = curYear;
        }
        if (beginYear > endYear) {
            endYear = beginYear;
        }

        //mYearView
        mYearView = dialogView1.findViewById(R.id.wheelView_year);
        mYearView.setBackgroundResource(R.mipmap.transparent_bg);
        mYearView.setWheelBackground(R.mipmap.transparent_bg);
        mYearView.setWheelForeground(R.drawable.wheel_val_holo);
        mYearView.setShadowColor(0xFFDADCDB, 0x88DADCDB, 0x00DADCDB);
        mYearView.setViewAdapter(new DateNumericAdapter(mContext, beginYear, endYear, endYear - beginYear));
        mYearView.setCurrentItem(endYear - beginYear);
        mYearView.addChangingListener(listener);


        // mMonthView
        mMonthView = dialogView1
                .findViewById(R.id.wheelView_month);
        mMonthView.setBackgroundResource(R.mipmap.transparent_bg);
        mMonthView.setWheelBackground(R.mipmap.transparent_bg);
        mMonthView.setWheelForeground(R.drawable.wheel_val_holo);
        mMonthView.setShadowColor(0xFFDADCDB, 0x88DADCDB, 0x00DADCDB);
        curMonth = mCalendar.get(Calendar.MONTH);
        mMonthView.setViewAdapter(new DateArrayAdapter(mContext, mMonths, curMonth));
        mMonthView.setCurrentItem(curMonth);
        mMonthView.addChangingListener(listener);


        //mDayView
        mDayView = dialogView1.findViewById(R.id.wheelView_day);
        updateDays(mYearView, mMonthView, mDayView);
        curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mDayView.setCurrentItem(curDay - 1);
        mDayView.setBackgroundResource(R.mipmap.transparent_bg);
        mDayView.setWheelBackground(R.mipmap.transparent_bg);
        mDayView.setWheelForeground(R.drawable.wheel_val_holo);
        mDayView.setShadowColor(0xFFDADCDB, 0x88DADCDB, 0x00DADCDB);

        mTvSure = dialogView1.findViewById(R.id.tv_sure);
        mTvCancle = dialogView1.findViewById(R.id.tv_cancel);
        llType = dialogView1.findViewById(R.id.ll_month_type);

        mCheckBoxDay = dialogView1.findViewById(R.id.checkBox_day);
        mCheckBoxDay.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mDayView.setVisibility(View.VISIBLE);
                } else {
                    mDayView.setVisibility(View.GONE);
                }
            }
        });

        getLayoutParams().gravity = Gravity.CENTER;
        setContentView(dialogView1);
    }


    public WheelView getDayView() {
        return mDayView;
    }

    public int getCurDay() {
        return curDay;
    }

    public CheckBox getCheckBoxDay() {
        return mCheckBoxDay;
    }

    public WheelView getYearView() {
        return mYearView;
    }

    public WheelView getMonthView() {
        return mMonthView;
    }

    private int getCurYear() {
        return curYear;
    }

    private int getCurMonth() {
        return curMonth;
    }

    public TextView getSureView() {
        return mTvSure;
    }

    public TextView getCancleView() {
        return mTvCancle;
    }

    private String[] getMonths() {
        return mMonths;
    }

    private String[] getDays() {
        return mDays;
    }

    public int getSelectorYear() {
        return beginYear + getYearView().getCurrentItem();
    }

    public String getSelectorMonth() {
        return getMonths()[getMonthView().getCurrentItem()];
    }

    public String getSelectorDay() {
        return getDays()[getDayView().getCurrentItem()];
    }

    /**
     * Updates mDayView wheel. Sets max mDays according to selected mMonthView and mYearView
     */
    private void updateDays(WheelView year, WheelView month, WheelView day) {
        mCalendar.set(Calendar.YEAR, beginYear + year.getCurrentItem());
        mCalendar.set(Calendar.MONTH, month.getCurrentItem());
        int maxDay = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int maxDays = TimeUtil.getDaysByYearMonth(beginYear + year.getCurrentItem(), month.getCurrentItem() + 1);
        day.setViewAdapter(new DateNumericAdapter(mContext, 1, maxDays, mCalendar.get(Calendar.DAY_OF_MONTH) - 1));
        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
        day.setCurrentItem(curDay - 1, true);


    }


    /**
     * Adapter for numeric wheels. Highlights the current value.
     */
    private class DateNumericAdapter extends NumericWheelAdapter {
        // Index of current item
        int currentItem;
        // Index of item to be highlighted
        int currentValue;

        /**
         * Constructor
         */
        public DateNumericAdapter(Context mContext, int minValue, int maxValue, int current) {
            super(mContext, minValue, maxValue);
            this.currentValue = current;
            setTextSize(16);
        }

        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            /*if (currentItem == currentValue) {
                view.setTextColor(0xFF0000F0);
			}*/
            view.setTypeface(Typeface.SANS_SERIF);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }
}
