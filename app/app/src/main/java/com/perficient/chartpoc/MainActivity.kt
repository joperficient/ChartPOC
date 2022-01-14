package com.perficient.chartpoc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry
import com.perficient.chartpoc.databinding.ActivityMainBinding
import kotlin.random.Random
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.components.LimitLine

import com.github.mikephil.charting.components.YAxis





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)

        val barEntries = ArrayList<BarEntry>()

        for(i in 1 .. 24) {
            val yRandom: Float = (0..100).random().toFloat()
            barEntries.add(BarEntry(i.toFloat(), yRandom))
        }

        //biding.chart.getAxisLeft().isEnabled = false
        //biding.chart.getAxis(YAxis.AxisDependency.LEFT).isEnabled = false
        biding.chart.getAxis(YAxis.AxisDependency.LEFT).axisMaximum = 100f
        biding.chart.getAxis(YAxis.AxisDependency.LEFT).axisMinimum = 0f

        biding.chart.getAxis(YAxis.AxisDependency.LEFT).setLabelCount(3, true)

        biding.chart.getAxis(YAxis.AxisDependency.RIGHT).isEnabled = false

        biding.chart.xAxis.isEnabled = false
        //biding.chart.


        val set = BarDataSet(barEntries, "Testing label")
        set.color = resources.getColor(R.color.gray_char)
        val data = BarData(set)


        biding.chart.data = data





        biding.chart.invalidate()





    }
}