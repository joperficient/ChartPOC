package com.perficient.timeframechart.model

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat

enum class FormatterType(val format: String) {
    DEFAULT("DD, hh::mm aa"),
    DAILY("hh:mm aa"),
    WEEKLY("EEE, dd"),
    MONTHLY("MMM, dd"),
    YEARLY("MMM, dd")
}


class XAxisDateFormatter(private val items: List<ChartInput>,
                         private val type: FormatterType): ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return SimpleDateFormat(type.format).format(items[value.toInt()].x)
    }
}
