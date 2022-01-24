package com.perficient.chartpoc.source

import com.perficient.timeframechart.model.ChartData
import com.perficient.timeframechart.model.ChartInput
import com.perficient.timeframechart.model.ChartTimeFrame
import com.perficient.timeframechart.model.FormatterType
import java.util.*

object MockSource {

    fun loadData(): ChartData {

        val yearly = ChartTimeFrame.Builder("Y")
            .setType(FormatterType.YEARLY)
            .build(mutableListOf<ChartInput>().apply {
                for (i in 0..1) {
                    add(ChartInput(getDatePlus(Calendar.MONTH, i), i.toFloat() * 10, isCalibration = true))
                }
                for (i in 2..11) {
                    val yRandom: Float = (50..100).random().toFloat()
                    add(ChartInput(getDatePlus(Calendar.MONTH, i), yRandom))
                }
            })


        return ChartData(mutableListOf(createDayMock(), createWeekly(), createMonthly(), yearly))
    }

    private fun createMonthly(): ChartTimeFrame {


        return  ChartTimeFrame.Builder("30D")
            .setType(FormatterType.MONTHLY)
            .build(mutableListOf<ChartInput>().apply {
                for (i in 0..1) {
                    add(ChartInput(getDatePlus(Calendar.WEEK_OF_YEAR, i), i.toFloat() * 10, isCalibration = true))
                }
                for (i in 2..30) {

                    val yRandom: Float = (50..100).random().toFloat()
                    add(ChartInput(getDatePlus(Calendar.WEEK_OF_YEAR, i), yRandom))
                }
            })
    }

    private fun createWeekly(): ChartTimeFrame {

        return ChartTimeFrame.Builder("7D")
            .setType(FormatterType.WEEKLY)
            .build(mutableListOf<ChartInput>().apply {
                for (i in 0..1) {

                    add(ChartInput(getDatePlus(Calendar.DAY_OF_YEAR, i), i.toFloat() * 10, isCalibration = true))
                }

                for (i in 2..7) {
                    val yRandom: Float = (50..100).random().toFloat()
                    add(ChartInput(getDatePlus(Calendar.DAY_OF_YEAR, i), yRandom))
                }
            })
    }

    private fun createDayMock(): ChartTimeFrame {


        val charTimeDay = ChartTimeFrame.Builder("1D")
            .setAsSelected().setType(FormatterType.DAILY)
            .build(mutableListOf<ChartInput>().apply {
                for (i in 0..5) {
                    add(ChartInput(getDatePlus(Calendar.HOUR, i), i.toFloat() * 10, isCalibration = true))
                }

                for (i in 6..24) {
                    val yRandom: Float = (50..100).random().toFloat()
                    add(ChartInput(getDatePlus(Calendar.HOUR, i), yRandom))
                }
            })
        return charTimeDay

    }

    fun getDatePlus(field: Int, amount: Int): Date {
        val now = Date()
        val nowCalendar = Calendar.getInstance()
        nowCalendar.timeInMillis = now.time
        nowCalendar.add(field, amount)
        return nowCalendar.time
    }


}