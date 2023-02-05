package blcs.lwb.utils.fragment.viewFragment.MPAndroidChart;
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import blcs.lwb.lwbtool.base.BaseVMFragment
import blcs.lwb.utils.R
import blcs.lwb.utils.databinding.FragmentCustomPieChartsBinding
import blcs.lwb.utils.viewmodel.EmptyViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

class CustomPieChartsFragment : BaseVMFragment<FragmentCustomPieChartsBinding, EmptyViewModel>(){
    override fun getViewModelClass(): Class<EmptyViewModel> {
        return EmptyViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_custom_pie_charts
    }
    private val parties = arrayOf(
        "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
        "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
        "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
        "Party Y", "Party Z"
    )
    override fun initView() {
        super.initView()
        binding.chart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            setExtraOffsets(5f, 10f, 5f, 5f)
            dragDecelerationFrictionCoef = 0.95f
            centerText = generateCenterSpannableText()
            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)
            setTransparentCircleColor(Color.WHITE)
            setTransparentCircleAlpha(110)
            holeRadius = 58f
            transparentCircleRadius = 61f
            setDrawCenterText(true)
            rotationAngle = 0f
            isRotationEnabled = true
            isHighlightPerTapEnabled = true
//            setOnChartValueSelectedListener(this)
            animateY(1400, Easing.EaseInOutQuad)
            legend.apply {
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                orientation = Legend.LegendOrientation.VERTICAL
                setDrawInside(false)
                xEntrySpace = 7f
                yEntrySpace = 0f
                yOffset = 0f
            }
            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(12f)
            val pieData = PieData(getPieData(3,10f))
            pieData.setValueFormatter(PercentFormatter(binding.chart))
            pieData.setValueTextSize(11f)
            pieData.setValueTextColor(Color.WHITE)
            data = pieData
            highlightValues(null)
            invalidate()
        }
    }

    private fun generateCenterSpannableText(): SpannableString? {
        val s = SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.8f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }


    private fun setData(count: Int, range: Float) {
        val entries = ArrayList<PieEntry>()
        for (i in 0 until count) {
            entries.add(
                PieEntry(
                    (Math.random() * range + range / 5).toFloat(),
                    parties.get(i % parties.size),
                    resources.getDrawable(R.mipmap.star)
                )
            )
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(binding.chart))
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        binding.chart.data = data
        // undo all highlights
        binding.chart.highlightValues(null)
        binding.chart.invalidate()
    }

    private fun getPieData(count: Int, range: Float) : PieDataSet{
        val entries = ArrayList<PieEntry>()
        for (i in 0 until 5) {
            entries.add(
                PieEntry(
                    (Math.random() * range + range / 5).toFloat(),
                    parties.get(i % parties.size),
                    resources.getDrawable(R.mipmap.star)
                )
            )
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors
        return  dataSet;
    }
}