package com.perficient.chartpoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.BarEntry
import com.perficient.chartpoc.databinding.ActivityMainBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet

import com.github.mikephil.charting.components.YAxis


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)

        val barEntries = ArrayList<BarEntry>()
        val barEntriesCalibrating = ArrayList<BarEntry>()


        for (i in 0..5) {

            barEntriesCalibrating.add(BarEntry(i.toFloat(), i.toFloat() * 10).apply {
                icon = resources.getDrawable(R.drawable.ic_urgent_circle_small)
            })
        }
        for(i in 6 .. 24) {
            val yRandom: Float = (50..100).random().toFloat()

            barEntries.add(BarEntry(i.toFloat(), yRandom).apply {
               // icon = resources.getDrawable(R.drawable.ic_urgent_circle_small)
            })
        }

        //biding.chart.getAxisLeft().isEnabled = false
        //biding.chart.getAxis(YAxis.AxisDependency.LEFT).isEnabled = false
        val yAxis = biding.chart.getAxis(YAxis.AxisDependency.LEFT)
        yAxis.axisMaximum = 100f
        yAxis.axisMinimum = 0f
        yAxis.gridColor = resources.getColor(R.color.gray_char)
        yAxis.gridLineWidth = 2f
        yAxis.setLabelCount(3, true)

        biding.chart.getAxis(YAxis.AxisDependency.RIGHT).isEnabled = false

        biding.chart.xAxis.isEnabled = false
        //biding.chart.setDrawValueAboveBar(false)
        //biding.chart.


        val set = BarDataSet(barEntries, "Testing label")
        val setCalibrating = BarDataSet(barEntriesCalibrating, "Calibrating label")

        set.color = resources.getColor(R.color.gray_char)
        set.setDrawValues(false)

        setCalibrating.color = resources.getColor(R.color.gray_calibrating)
        setCalibrating.setDrawValues(false)

        val data = BarData(set, setCalibrating)

        biding.chart.data = data





        biding.chart.invalidate()





    }
}