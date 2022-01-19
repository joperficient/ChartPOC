package com.perficient.chartpoc.source

import com.github.mikephil.charting.data.BarEntry
import com.perficient.chartpoc.R
import com.perficient.chartpoc.model.ChartData
import com.perficient.chartpoc.model.ChartInput
import com.perficient.chartpoc.model.ChartTimeFrame
import com.perficient.chartpoc.model.FormatterType

object MockSource {

    fun loadData(): ChartData {
        val charTimeDay = ChartTimeFrame.Builder("1D")
                .setAsSelected().setType(FormatterType.DAILY)
            .build(mutableListOf<ChartInput>().apply {
                for (i in 0..5) {
                    add(ChartInput(i.toFloat(), i.toFloat() * 10, isCalibration = true))
                }

                for (i in 6..24) {
                    val yRandom: Float = (50..100).random().toFloat()
                    add(ChartInput(i.toFloat(), yRandom))
                }
            })


        val week = ChartTimeFrame.Builder("7D")
            .setType(FormatterType.WEEKLY)
            .build(mutableListOf<ChartInput>().apply {
                for (i in 0..1) {
                    add(ChartInput(i.toFloat(), i.toFloat() * 10, isCalibration = true))
                }

                for (i in 2..7) {
                    val yRandom: Float = (50..100).random().toFloat()
                    add(ChartInput(i.toFloat(), yRandom))
                }
            })



        val monthly = ChartTimeFrame.Builder("30D")
            .setType(FormatterType.WEEKLY)
            .build(mutableListOf<ChartInput>().apply {
                for (i in 0..1) {
                    add(ChartInput(i.toFloat(), i.toFloat() * 10, isCalibration = true))
                }
                for (i in 2..30) {
                    val yRandom: Float = (50..100).random().toFloat()
                    add(ChartInput(i.toFloat(), yRandom))
                }
            })

        val yearly = ChartTimeFrame.Builder("Y")
            .setType(FormatterType.YEARLY)
            .build(mutableListOf<ChartInput>().apply {
                for (i in 0..1) {
                    add(ChartInput(i.toFloat(), i.toFloat() * 10, isCalibration = true))
                }
                for (i in 2..30) {
                    val yRandom: Float = (50..100).random().toFloat()
                    add(ChartInput(i.toFloat(), yRandom))
                }
            })


        return ChartData(mutableListOf(charTimeDay, week, monthly, yearly))
    }
}