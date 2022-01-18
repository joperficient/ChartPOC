package com.perficient.chartpoc.model

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter


object DayFormatter: ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "$value am"
    }
}

object WeekFormatter: ValueFormatter() {
    private val days = arrayOf("Mo", "Tu", "Wed", "Th", "Fr", "Sa", "Su")

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}

object MonthFormatter: ValueFormatter() {
    private val days = arrayOf("1 Week", "2 Week", "3 Week", "4 Week")

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}

object YearFormatter: ValueFormatter() {
    private val days = arrayOf("Jan", "May", "Oct", "Dec")

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}