package com.perficient.timeframechart

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

import android.view.LayoutInflater
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.MPPointF
import com.perficient.timeframechart.adapter.AdapterChartTimeFrameSelector
import com.perficient.timeframechart.databinding.TimeFrameChartViewBinding
import com.perficient.timeframechart.model.ChartData
import com.perficient.timeframechart.model.ChartTimeFrame

class TimeFrameChatView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {


    private lateinit var adapter: AdapterChartTimeFrameSelector
    private lateinit var biding: TimeFrameChartViewBinding

    init {
        inflateView(context)
    }

    private fun inflateView(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        biding = TimeFrameChartViewBinding.inflate(inflater, this, true)

    }

    fun setChart(data: ChartData) {

        adapter = AdapterChartTimeFrameSelector { selected ->
            data.timeFrames.filter { it != selected }.forEach { it.isSelected = false }
            adapter.updateData(data.timeFrames)
            setChartTimeFrame(selected)
        }

        biding.timeFrameRv.adapter = adapter

        adapter.addData(data.timeFrames)

        setChartTimeFrame(data.timeFrames[0])
    }



    private fun setChartTimeFrame(charTimeFrame: ChartTimeFrame) {
        val barEntries = ArrayList<BarEntry>()
        val barEntriesCalibrating = ArrayList<BarEntry>()

        charTimeFrame.listInput.forEachIndexed { index, it ->
            if (it.isCalibration) {
                barEntriesCalibrating.add(BarEntry(index.toFloat(), it.y).apply {
                    icon = resources.getDrawable(R.drawable.ic_urgent_circle_small)
                })
            } else {
                barEntries.add(BarEntry(index.toFloat(), it.y))
            }
        }

        val yAxis = biding.chart.getAxis(YAxis.AxisDependency.LEFT)
        yAxis.axisMaximum = 100f
        yAxis.axisMinimum = 0f
        yAxis.gridLineWidth = 2f
        yAxis.gridColor = resources.getColor(R.color.gray_char)
        yAxis.setLabelCount(3, true)
        yAxis.axisLineColor = resources.getColor(R.color.transparent)
        yAxis.textColor = resources.getColor(R.color.gray_calibrating)

        biding.chart.getAxis(YAxis.AxisDependency.RIGHT).isEnabled = false

        biding.chart.xAxis.setLabelCount(5, true)
        biding.chart.xAxis.setDrawGridLines(false)
        biding.chart.xAxis.setCenterAxisLabels(true)
        biding.chart.xAxis.valueFormatter = charTimeFrame.formatter

        biding.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        biding.chart.xAxis.textColor = resources.getColor(R.color.gray_calibrating)
        biding.chart.xAxis.textSize = 11f


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
        biding.chart.legend.isEnabled = false
        biding.chart.invalidate()
    }
}