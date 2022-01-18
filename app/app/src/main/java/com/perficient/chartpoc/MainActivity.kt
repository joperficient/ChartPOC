package com.perficient.chartpoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarEntry
import com.perficient.chartpoc.databinding.ActivityMainBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet

import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.perficient.chartpoc.adapter.AdapterChartTimeFrameSelector
import com.perficient.chartpoc.adapter.ChartTimeFrame
import com.perficient.chartpoc.model.DayFormatter
import com.perficient.chartpoc.model.MonthFormatter
import com.perficient.chartpoc.model.WeekFormatter
import com.perficient.chartpoc.model.YearFormatter


class MainActivity : AppCompatActivity() {
    private lateinit var biding: ActivityMainBinding
    private lateinit var adapter : AdapterChartTimeFrameSelector

    private val timeFrames = mutableListOf(
        ChartTimeFrame("1D", true, formatter = DayFormatter),
        ChartTimeFrame("7D", formatter = WeekFormatter),
        ChartTimeFrame("30D", formatter = MonthFormatter),
        ChartTimeFrame("1Y", formatter = YearFormatter)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)

        adapter = AdapterChartTimeFrameSelector { selected ->
            timeFrames.filter { it != selected }.forEach { it.isSelected = false }
            adapter.updateData(timeFrames)
            setChart(selected.formatter)
        }

        biding.timeFrameRv.adapter = adapter

        adapter.addData(timeFrames)

        setChart(timeFrames[0].formatter)

    }

    private fun setChart(formatter: ValueFormatter) {
        val barEntries = ArrayList<BarEntry>()
        val barEntriesCalibrating = ArrayList<BarEntry>()

        for (i in 0..5) {
            barEntriesCalibrating.add(BarEntry(i.toFloat(), i.toFloat() * 10).apply {
                icon = resources.getDrawable(R.drawable.ic_urgent_circle_small)
            })
        }

        for (i in 6..24) {
            val yRandom: Float = (50..100).random().toFloat()
            barEntries.add(BarEntry(i.toFloat(), yRandom))
        }

        val yAxis = biding.chart.getAxis(YAxis.AxisDependency.LEFT)
        yAxis.axisMaximum = 100f
        yAxis.axisMinimum = 0f
        yAxis.gridLineWidth = 2f
        yAxis.gridColor = resources.getColor(R.color.gray_char)
        yAxis.setLabelCount(3, true)
        yAxis.axisLineColor = resources.getColor(R.color.transparent)

        biding.chart.getAxis(YAxis.AxisDependency.RIGHT).isEnabled = false

        biding.chart.xAxis.setLabelCount(5, true)
        biding.chart.xAxis.setDrawGridLines(false)
        biding.chart.xAxis.setCenterAxisLabels(true)
        biding.chart.xAxis.valueFormatter = formatter

        biding.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        val set = BarDataSet(barEntries, "Bar entries")
        val setCalibrating = BarDataSet(barEntriesCalibrating, "Calibrating time")

        set.color = resources.getColor(R.color.gray_char)
        set.setDrawValues(false)

        setCalibrating.color = resources.getColor(R.color.gray_calibrating)
        setCalibrating.setDrawValues(false)
        setCalibrating.iconsOffset = MPPointF(0f, -2f)

        val data = BarData(set, setCalibrating)
        data.barWidth = 0.5f

        biding.chart.data = data
        biding.chart.invalidate()
    }

}