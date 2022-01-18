package com.perficient.chartpoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarEntry
import com.perficient.chartpoc.databinding.ActivityMainBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet

import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.utils.MPPointF
import com.perficient.chartpoc.adapter.AdapterChartTimeFrameSelector
import com.perficient.chartpoc.adapter.ChartTimeFrame


class MainActivity : AppCompatActivity() {
    private lateinit var biding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)

        val barEntries = ArrayList<BarEntry>()
        val barEntriesCalibrating = ArrayList<BarEntry>()


        for (i in 0..5) {
            barEntriesCalibrating.add(BarEntry(i.toFloat(), i.toFloat() * 10).apply {
                icon = resources.getDrawable(R.drawable.ic_urgent_circle_small)
            })
        }
        for (i in 6..24) {
            val yRandom: Float = (50..100).random().toFloat()

            barEntries.add(BarEntry(i.toFloat(), yRandom).apply {
                // icon = resources.getDrawable(R.drawable.ic_urgent_circle_small)
            })
        }

        val adapter = AdapterChartTimeFrameSelector()

        biding.timeFrameRv.adapter = adapter

        adapter.addData(
            listOf(
                ChartTimeFrame("1D", true), ChartTimeFrame("7D"),
                ChartTimeFrame("30D"), ChartTimeFrame("1Y")
            )
        )

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

//    private fun setChart2() {
//
//        val entries: MutableList<BarEntry> = ArrayList()
//        entries.add(BarEntry(0f, 30f))
//        entries.add(BarEntry(1f, 80f))
//        entries.add(BarEntry(2f, 60f))
//        entries.add(BarEntry(3f, 50f))
//        // gap of 2f
//        // gap of 2f
//        entries.add(BarEntry(5f, 70f))
//        entries.add(BarEntry(6f, 60f))
//        val set2 = BarDataSet(entries, "BarDataSet")
//
//        val data2 = BarData(set2)
//        data2.barWidth = 0.9f // set custom bar width
//
//        biding.char2.setData(data2)
//        biding.char2.setFitBars(true) // make the x-axis fit exactly all bars
//
//        biding.char2.invalidate() // refresh
//
//    }
}