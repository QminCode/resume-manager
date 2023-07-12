package com.example.resume.ui.fragment

import android.graphics.Color
import android.os.Bundle
import com.example.resume.base.BaseFragment
import com.example.resume.bean.Edu
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
        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        mViewModel.EduData.observe(this, {
            entries.add(
                PieEntry(
                    it.PhD.toFloat(),
                    "博士"
                )
            )
            entries.add(
                PieEntry(
                    it.master.toFloat(),
                    "硕士"
                )
            )
            entries.add(
                PieEntry(
                    it.undergraduate.toFloat(),
                    "本科"
                )
            )
            chart.centerText = "学历"
            val dataSet = PieDataSet(entries, "学历")
            setCharData(dataSet, chart)
        })
    }

    private fun setAgeData(chart : PieChart){
        val entries = ArrayList<PieEntry>()
        mViewModel.AgeData.observe(this,{
            entries.add(
                PieEntry(
                    it.age18to25.toFloat(),
                    "18到25岁"
                )
            )
            entries.add(
                PieEntry(
                    it.age26to30.toFloat(),
                    "26到30岁"
                )
            )
            entries.add(
                PieEntry(
                    it.age31to35.toFloat(),
                    "31到35岁"
                )
            )
            entries.add(
                PieEntry(
                    it.age35to.toFloat(),
                    "35岁以上"
                )
            )
            chart.centerText = "年龄"
            val dataSet = PieDataSet(entries, "年龄")
            setCharData(dataSet, chart)
        })

    }

    fun setExpData(chart : PieChart){
        val entries = ArrayList<PieEntry>()
        mViewModel.ExpData.observe(this,{
            entries.add(
                PieEntry(
                    it.exp0.toFloat(),
                    "0年"
                )
            )
            entries.add(
                PieEntry(
                    it.exp1to3.toFloat(),
                    "1-3年"
                )
            )
            entries.add(
                PieEntry(
                    it.exp3to5.toFloat(),
                    "3-5年"
                )
            )
            entries.add(
                PieEntry(
                   it.exp5to.toFloat(),
                    "5年以上"
                )
            )
            chart.centerText = "工作经验"
            val dataSet = PieDataSet(entries, "工作经验")
            setCharData(dataSet, chart)
        })

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


}