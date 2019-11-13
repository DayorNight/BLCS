package blcs.lwb.utils.fragment.viewFragment.MPAndroidChart;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.widget.MyMarkerView;
import butterknife.BindView;

public class LineChartsFragment extends DemoBase implements SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {
    @BindView(R.id.chart1)
    LineChart chart;
    @BindView(R.id.seekBar2)
    SeekBar seekBarY;
    @BindView(R.id.seekBar1)
    SeekBar seekBarX;
    @BindView(R.id.tvXMax)
    TextView tvX;
    @BindView(R.id.tvYMax)
    TextView tvY;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_line_charts;
    }

    @Override
    protected void initView() {
        initMenu();
        seekBarX.setOnSeekBarChangeListener(this);
        seekBarY.setMax(180);
        seekBarY.setOnSeekBarChangeListener(this);

        {
            // background color
            chart.setBackgroundColor(Color.WHITE);

            // disable description text
            chart.getDescription().setEnabled(false);

            // enable touch gestures
            chart.setTouchEnabled(true);

            // set listeners
            chart.setOnChartValueSelectedListener(this);
            chart.setDrawGridBackground(false);

            // create marker to display box when values are selected
            MyMarkerView mv = new MyMarkerView(activity, R.layout.custom_marker_view);

            // Set the marker to the chart
            mv.setChartView(chart);
            chart.setMarker(mv);

            // enable scaling and dragging
            chart.setDragEnabled(true);
            chart.setScaleEnabled(true);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chart.setPinchZoom(true);
        }
        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(-50f);
        }


        {   // // Create Limit Lines // //
            LimitLine llXAxis = new LimitLine(9f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 10f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);
            llXAxis.setTypeface(tfRegular);

            LimitLine ll1 = new LimitLine(150f, "Upper Limit");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);
            ll1.setTypeface(tfRegular);

            LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
            ll2.setLineWidth(4f);
            ll2.enableDashedLine(10f, 10f, 0f);
            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            ll2.setTextSize(10f);
            ll2.setTypeface(tfRegular);

            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yAxis.addLimitLine(ll1);
            yAxis.addLimitLine(ll2);
//            xAxis.addLimitLine(llXAxis);
        }

        // add data
        seekBarX.setProgress(45);
        seekBarY.setProgress(180);
        setData(45, 180);

        // draw points over time
        chart.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);
    }

    private void initMenu() {
        //设置menuseekBar2
        activity.tlToolbar.inflateMenu(R.menu.line);
        //菜单点击事件
        activity.tlToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionToggleValues: {
                        List<ILineDataSet> sets = chart.getData()
                                .getDataSets();

                        for (ILineDataSet iSet : sets) {

                            LineDataSet set = (LineDataSet) iSet;
                            set.setDrawValues(!set.isDrawValuesEnabled());
                        }

                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleIcons: {
                        List<ILineDataSet> sets = chart.getData()
                                .getDataSets();

                        for (ILineDataSet iSet : sets) {

                            LineDataSet set = (LineDataSet) iSet;
                            set.setDrawIcons(!set.isDrawIconsEnabled());
                        }

                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleHighlight: {
                        if(chart.getData() != null) {
                            chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
                            chart.invalidate();
                        }
                        break;
                    }
                    case R.id.actionToggleFilled: {

                        List<ILineDataSet> sets = chart.getData()
                                .getDataSets();

                        for (ILineDataSet iSet : sets) {

                            LineDataSet set = (LineDataSet) iSet;
                            if (set.isDrawFilledEnabled())
                                set.setDrawFilled(false);
                            else
                                set.setDrawFilled(true);
                        }
                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleCircles: {
                        List<ILineDataSet> sets = chart.getData()
                                .getDataSets();

                        for (ILineDataSet iSet : sets) {

                            LineDataSet set = (LineDataSet) iSet;
                            if (set.isDrawCirclesEnabled())
                                set.setDrawCircles(false);
                            else
                                set.setDrawCircles(true);
                        }
                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleCubic: {
                        List<ILineDataSet> sets = chart.getData()
                                .getDataSets();

                        for (ILineDataSet iSet : sets) {

                            LineDataSet set = (LineDataSet) iSet;
                            set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                                    ? LineDataSet.Mode.LINEAR
                                    :  LineDataSet.Mode.CUBIC_BEZIER);
                        }
                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleStepped: {
                        List<ILineDataSet> sets = chart.getData()
                                .getDataSets();

                        for (ILineDataSet iSet : sets) {

                            LineDataSet set = (LineDataSet) iSet;
                            set.setMode(set.getMode() == LineDataSet.Mode.STEPPED
                                    ? LineDataSet.Mode.LINEAR
                                    :  LineDataSet.Mode.STEPPED);
                        }
                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleHorizontalCubic: {
                        List<ILineDataSet> sets = chart.getData()
                                .getDataSets();

                        for (ILineDataSet iSet : sets) {

                            LineDataSet set = (LineDataSet) iSet;
                            set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                                    ? LineDataSet.Mode.LINEAR
                                    :  LineDataSet.Mode.HORIZONTAL_BEZIER);
                        }
                        chart.invalidate();
                        break;
                    }
                    case R.id.actionTogglePinch: {
                        if (chart.isPinchZoomEnabled())
                            chart.setPinchZoom(false);
                        else
                            chart.setPinchZoom(true);

                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleAutoScaleMinMax: {
                        chart.setAutoScaleMinMaxEnabled(!chart.isAutoScaleMinMaxEnabled());
                        chart.notifyDataSetChanged();
                        break;
                    }
                    case R.id.animateX: {
                        chart.animateX(2000);
                        break;
                    }
                    case R.id.animateY: {
                        chart.animateY(2000, Easing.EaseInCubic);
                        break;
                    }
                    case R.id.animateXY: {
                        chart.animateXY(2000, 2000);
                        break;
                    }
                    case R.id.actionSave: {
                        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            saveToGallery();
                        } else {
                            requestStoragePermission(chart);
                        }
                        break;
                    }
                }
                return true;
            }
        });
    }

    private void setData(int count, float range) {
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) - 30;
            values.add(new Entry(i, val, getResources().getDrawable(R.mipmap.star)));
        }

        LineDataSet set1;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    if (chart==null) return 0;
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(activity, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart.setData(data);
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tvX.setText(String.valueOf(seekBarX.getProgress()));
        tvY.setText(String.valueOf(seekBarY.getProgress()));

        setData(seekBarX.getProgress(), seekBarY.getProgress());

        // redraw
        chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "LineChartsFragment");
    }

}
