package blcs.lwb.utils.fragment.viewFragment.MPAndroidChart;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import butterknife.BindView;

public class BarCharts2Fragment extends DemoBase implements SeekBar.OnSeekBarChangeListener {
    @BindView(R.id.chart1)
    BarChart chart;
    @BindView(R.id.seekBar2)
    SeekBar seekBarY;
    @BindView(R.id.seekBar1)
    SeekBar seekBarX;
    @BindView(R.id.tvXMax)
    TextView tvX;
    @BindView(R.id.tvYMax)
    TextView tvY;

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "BarCharts2Fragment");
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_bar_charts2;
    }

    @Override
    protected void initView() {
        initMenu();

        seekBarX.setOnSeekBarChangeListener(this);
        seekBarY.setOnSeekBarChangeListener(this);
        chart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        // setting data
        seekBarX.setProgress(10);
        seekBarY.setProgress(100);
        // add a nice and smooth animation
        chart.animateY(1500);
        chart.getLegend().setEnabled(false);
    }

    private void initMenu() {
        activity.tlToolbar.inflateMenu(R.menu.bar);
        activity.tlToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.actionToggleValues: {
                        for (IDataSet set : chart.getData().getDataSets())
                            set.setDrawValues(!set.isDrawValuesEnabled());

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
                    case R.id.actionToggleBarBorders: {
                        for (IBarDataSet set : chart.getData().getDataSets())
                            ((BarDataSet)set).setBarBorderWidth(set.getBarBorderWidth() == 1.f ? 0.f : 1.f);

                        chart.invalidate();
                        break;
                    }
                    case R.id.animateX: {
                        chart.animateX(2000);
                        break;
                    }
                    case R.id.animateY: {
                        chart.animateY(2000);
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

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < seekBarX.getProgress(); i++) {
            float multi = (seekBarY.getProgress() + 1);
            float val = (float) (Math.random() * multi) + multi / 3;
            values.add(new BarEntry(i, val));
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Data Set");
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            chart.setData(data);
            chart.setFitBars(true);
        }

        chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
