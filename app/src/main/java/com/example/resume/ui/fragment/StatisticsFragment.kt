package com.example.resume.ui.fragment

import android.graphics.Color
import android.os.Bundle
import com.example.resume.base.BaseFragment
import com.example.resume.databinding.FragmentStatisticsBinding
import com.example.resume.ui.fragment.viewmodel.StatisticsViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate


/**
 * 统计和数据可视化
 */
class StatisticsFragment : OnChartValueSelectedListener,BaseFragment<StatisticsViewModel, FragmentStatisticsBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        //初始化饼图
        val eduChart = mBind.eduChart
        val ageChart = mBind.ageChart
        val expChart = mBind.expChart
        initChart(eduChart)
        initChart(ageChart)
        initChart(expChart)
        mViewModel.getAgeData()
        mViewModel.getEduData()
        mViewModel.getExpData()
        setEduData(eduChart)
        setAgeData(ageChart)
        setExpData(expChart)
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }

    override fun onNothingSelected() {

    }

    /**
      * @description 初始化饼图
      * @param
      * @return
      */
    private fun initChart(eduChart : PieChart){
        eduChart.description.isEnabled = false
        eduChart.setExtraOffsets(5f, 10f, 5f, 5f)
        eduChart.dragDecelerationFrictionCoef = 0.95f
        eduChart.extraTopOffset
        eduChart.setExtraOffsets(20f, 0f, 20f, 0f)
        eduChart.isDrawHoleEnabled = true
        eduChart.setHoleColor(Color.WHITE)

        eduChart.setTransparentCircleColor(Color.WHITE)
        eduChart.setTransparentCircleAlpha(110)

        eduChart.holeRadius = 40f
        eduChart.transparentCircleRadius = 61f

        eduChart.setDrawCenterText(true)

        eduChart.rotationAngle = 0f
        // enable rotation of the chart by touch
        // enable rotation of the chart by touch
        eduChart.isRotationEnabled = true
        eduChart.isHighlightPerTapEnabled = true


        eduChart.setOnChartValueSelectedListener(this)
        eduChart.animateY(1400, Easing.EaseInOutQuad)
        val l = eduChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }

    private fun setEduData(chart : PieChart){

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        mViewModel.eduData.observe(this) {
            val entries = ArrayList<PieEntry>()
            if(it.doctor != 0){entries.add(
                PieEntry(
                    it.doctor.toFloat(),
                    "博士"
                )
            )}

            if(it.master != 0){entries.add(
                PieEntry(
                    it.master.toFloat(),
                    "硕士"
                )
            )}

            if(it.undergraduate != 0){entries.add(
                PieEntry(
                    it.undergraduate.toFloat(),
                    "本科"
                )
            )}

            if(it.juniorCollege != 0){entries.add(
                PieEntry(
                    it.juniorCollege.toFloat(),
                    "大专"
                )
            )}

            if(it.highSchool != 0){
                entries.add(
                    PieEntry(
                        it.highSchool.toFloat(),
                        "高中"
                    )
                )
            }

            if(it.middle != 0){entries.add(
                PieEntry(
                    it.middle.toFloat(),
                    "初中"
                )
            )}

            if(it.primary != 0){entries.add(
                PieEntry(
                    it.primary.toFloat(),
                    "小学"
                )
            )}

            chart.centerText = "学历"
            val dataSet = PieDataSet(entries, "学历")
            setCharData(dataSet, chart)
        }
    }

    private fun setAgeData(chart : PieChart){
        mViewModel.ageData.observe(this) {
            val entries = ArrayList<PieEntry>()

            if(it[0].age1825 != 0){entries.add(
                PieEntry(
                    it[0].age1825.toFloat(),
                    "18到25岁"
                )
            )}

            if(it[0].age2630 != 0){entries.add(
                PieEntry(
                    it[0].age2630.toFloat(),
                    "26到30岁"
                )
            )}

            if(it[0].age3135 != 0){entries.add(
                PieEntry(
                    it[0].age3135.toFloat(),
                    "31到35岁"
                )
            )}

            if(it[0].age35 != 0){entries.add(
                PieEntry(
                    it[0].age35.toFloat(),
                    "35岁以上"
                )
            )}

            chart.centerText = "年龄"
            val dataSet = PieDataSet(entries, "年龄")
            setCharData(dataSet, chart)
        }
    }

    fun setExpData(chart : PieChart){
        mViewModel.expData.observe(this) {
            val entries = ArrayList<PieEntry>()
            if(it.year0 != 0){entries.add(
                PieEntry(
                    it.year0.toFloat(),
                    "0年"
                )
            )}

            if(it.`_$Year13189` !=0){entries.add(
                PieEntry(
                    it.`_$Year13189`.toFloat(),
                    "1-3年"
                )
            )}

            if(it.`_$Year45321` !=0){entries.add(
                PieEntry(
                    it.`_$Year45321`.toFloat(),
                    "3-5年"
                )
            )}

            if(it.`_$Year563` != 0){entries.add(
                PieEntry(
                    it.`_$Year563`.toFloat(),
                    "5年以上"
                )
            )}

            chart.centerText = "工作经验"

            val dataSet = PieDataSet(entries, "工作经验")
            setCharData(dataSet, chart)
        }

    }
    /**
      * @description 设置圆饼的属性
      * @param 
      * @return 
      */
    private fun setCharData(dataSet : PieDataSet, chart : PieChart){
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.colors = getColors()
        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f

        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTextColor(Color.BLACK)
        chart.data = data

        chart.highlightValues(null)
        chart.invalidate()
    }



    /**
     * 得到颜色数组
     */
    private fun getColors() : ArrayList<Int>{

        // 为饼图加颜色
        val colors = java.util.ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())
        return colors
    }

    override fun onResume() {
        mViewModel.getAgeData()
        mViewModel.getEduData()
        mViewModel.getExpData()
        super.onResume()
    }


}