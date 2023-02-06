package blcs.lwb.utils.fragment.viewFragment.MPAndroidChart;
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import blcs.lwb.lwbtool.base.BaseVMFragment
import blcs.lwb.utils.R
import blcs.lwb.utils.databinding.FragmentCustomPieChartsBinding
import blcs.lwb.utils.viewmodel.EmptyViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

class CustomPieChartsFragment : BaseVMFragment<FragmentCustomPieChartsBinding, EmptyViewModel>(),
    OnChartValueSelectedListener{
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
            //是否使用百分比样式
            setUsePercentValues(true)
            description.apply {
                isEnabled = true
                text = "显示描述"
                textColor = Color.RED
                textSize = 26f
                //setPosition(0f,0f)//指定位置
            }
            // 设置 pading
            setExtraOffsets(0f, 10f, 0f, 0f)
            // 拖动后转速 1f 摩擦力忽略不计， 0f 摩擦力巨大
            dragDecelerationFrictionCoef = 1f
            // 拖动结束是否继续转动
            isDragDecelerationEnabled = true
            // 是否显示中心文案
            setDrawCenterText(true)
            // 中心文案
            centerText = getCenterContent()
            // 中心是否为空
            isDrawHoleEnabled = true
            // 中心颜色
            setHoleColor(Color.WHITE)
            // 透明圆
            setTransparentCircleColor(Color.WHITE)
            // 透明半径
            setTransparentCircleAlpha(60)
            // 中心圆的 半径
            holeRadius = 58f
            //透明圆的半径
            transparentCircleRadius = 70f
            //初始旋转角度
            rotationAngle = 0f
            //是否可以旋转
            isRotationEnabled = true
            //点击是否突出显示
            isHighlightPerTapEnabled = true
            //点击选中监听
            setOnChartValueSelectedListener(this@CustomPieChartsFragment)
            // 颜色说明
            legend.apply {
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(false)//设置图例是在图表内部绘制还是在图表外部绘制
                xEntrySpace = 7f
                yEntrySpace = 0f
                yOffset = 0f
            }
            //数据
            data = PieData(getPieData()).apply {
                //文字 样式
                setValueFormatter(PercentFormatter(binding.chart))
                setValueTextSize(12f)
                setValueTextColor(Color.WHITE)
                //label 样式
                setEntryLabelColor(Color.WHITE)
                setEntryLabelTextSize(12f)
            }
            //是否显示标签
            setDrawEntryLabels(true)
        }
    }

    /**
     * 中心文字
     */
    private fun getCenterContent(): SpannableString? {
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

    private fun getPieData() : PieDataSet{
        val pieEntry = PieEntry(25f, "AAAAAAAAAAAA", resources.getDrawable(R.mipmap.star))
        val pieEntry2 = PieEntry(25f, "BB", resources.getDrawable(R.mipmap.star))
        val pieEntry3 = PieEntry(25f, "CC", resources.getDrawable(R.mipmap.star))
        val pieEntry4 = PieEntry(25f, "DD", resources.getDrawable(R.mipmap.star))
        val listOf = listOf(pieEntry, pieEntry2, pieEntry3, pieEntry4)
        return PieDataSet(listOf, "颜色说明").apply {
            setDrawIcons(true)//是否显示图标
            setDrawValues(true)//是否显示值
            iconsOffset = MPPointF(0f, 0f)//图标位置
            sliceSpace = 10f//各部分
            selectionShift = 0f//padding
            colors = intArrayOf(Color.BLACK,Color.BLUE,Color.RED,Color.GREEN).toList() //设置显示颜色

        }
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        Log.e("TAG", "onValueSelected: ${e?.toString()}")
    }

    override fun onNothingSelected() {
        Log.e("TAG", "onNothingSelected: ", )
    }
}