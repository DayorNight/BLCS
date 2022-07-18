package blcs.lwb.utils.fragment.viewFragment.MPAndroidChart;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import butterknife.BindView;

public class PieChartsFragment extends DemoBase implements SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {
    @BindView(R.id.chart1)
    PieChart chart;
    @BindView(R.id.seekBar2)
    SeekBar seekBarY;
    @BindView(R.id.seekBar1)
    SeekBar seekBarX;
    @BindView(R.id.tvXMax)
    TextView tvX;
    @BindView(R.id.tvYMax)
    TextView tvY;

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }
    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "PieChartsFragment");
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_pie_charts;
    }

    @Override
    protected void initView() {
        initMenu();

        seekBarX.setOnSeekBarChangeListener(this);
        seekBarY.setOnSeekBarChangeListener(this);

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterTextTypeface(tfLight);
        chart.setCenterText(generateCenterSpannableText());

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        chart.setOnChartValueSelectedListener(this);

        seekBarX.setProgress(4);
        seekBarY.setProgress(10);

        chart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);
    }

    private void initMenu() {
        activity.tlToolbar.inflateMenu(R.menu.pie);
        activity.tlToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.actionToggleValues: {
                        for (IDataSet<?> set : chart.getData().getDataSets())
                            set.setDrawValues(!set.isDrawValuesEnabled());

                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleIcons: {
                        for (IDataSet<?> set : chart.getData().getDataSets())
                            set.setDrawIcons(!set.isDrawIconsEnabled());

                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleHole: {
                        chart.setDrawHoleEnabled(!chart.isDrawHoleEnabled());
                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleMinAngles: {
                        if (chart.getMinAngleForSlices() == 0f)
                            chart.setMinAngleForSlices(36f);
                        else
                            chart.setMinAngleForSlices(0f);
                        chart.notifyDataSetChanged();
                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleCurvedSlices: {
                        boolean toSet = !chart.isDrawRoundedSlicesEnabled() || !chart.isDrawHoleEnabled();
                        chart.setDrawRoundedSlices(toSet);
                        if (toSet && !chart.isDrawHoleEnabled()) {
                            chart.setDrawHoleEnabled(true);
                        }
                        if (toSet && chart.isDrawSlicesUnderHoleEnabled()) {
                            chart.setDrawSlicesUnderHole(false);
                        }
                        chart.invalidate();
                        break;
                    }
                    case R.id.actionDrawCenter: {
                        chart.setDrawCenterText(!chart.isDrawCenterTextEnabled());
                        chart.invalidate();
                        break;
                    }
                    case R.id.actionToggleXValues: {

                        chart.setDrawEntryLabels(!chart.isDrawEntryLabelsEnabled());
                        chart.invalidate();
                        break;
                    }
                    case R.id.actionTogglePercent:
                        chart.setUsePercentValues(!chart.isUsePercentValuesEnabled());
                        chart.invalidate();
                        break;
                    case R.id.animateX: {
                        chart.animateX(1400);
                        break;
                    }
                    case R.id.animateY: {
                        chart.animateY(1400);
                        break;
                    }
                    case R.id.animateXY: {
                        chart.animateXY(1400, 1400);
                        break;
                    }
                    case R.id.actionToggleSpin: {
                        chart.spin(1000, chart.getRotationAngle(), chart.getRotationAngle() + 360, Easing.EaseInOutCubic);
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
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    parties[i % parties.length],
                    getResources().getDrawable(R.mipmap.star)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
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
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {

    }
}
