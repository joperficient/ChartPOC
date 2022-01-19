package com.perficient.chartpoc.model

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlin.math.roundToInt

enum class FormatterType {
    DEFAULT,
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}

abstract class XAxisFormatter: ValueFormatter() {
    abstract val maximumValues: Int
}


object DayFormatter: XAxisFormatter() {
    override val maximumValues: Int
        get() = 24

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "$value"
    }
}

object WeekFormatter: XAxisFormatter() {
    private val days = arrayOf("Mo", "Tu", "Wed", "Th", "Fr", "Sa", "Su")
    override val maximumValues: Int
        get() = days.size


    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.roundToInt()) ?: value.toString()
    }
}

object MonthFormatter: XAxisFormatter() {
    private val weeks = arrayOf("1 Week", "2 Week", "3 Week", "4 Week")
    override val maximumValues: Int
        get() = weeks.size


    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return weeks.getOrNull(value.roundToInt()) ?: value.toString()
    }
}

object YearFormatter: XAxisFormatter() {
    private val months = arrayOf("Jan", "Feb", "March", "May", "Jun", "Jul", "Agost", "Sep", "Oct", "Nov", "Dic")
    override val maximumValues: Int
        get() = months.size

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {


        return months.getOrNull(value.toInt()) ?: value.toString()
    }
}