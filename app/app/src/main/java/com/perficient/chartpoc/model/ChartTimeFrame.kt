package com.perficient.chartpoc.model

import com.github.mikephil.charting.formatter.ValueFormatter


data class ChartTimeFrame(val title: String,
                          var isSelected: Boolean= false,
                          var type: FormatterType,
                          var formatter: ValueFormatter? = null,
                          var listInput: List<ChartInput>) {

    class Builder(val title: String) {

        private var isSelected: Boolean= false
        private var type: FormatterType = FormatterType.DEFAULT

        fun setAsSelected(): Builder {
            isSelected = true
            return this
        }

        fun setType(type: FormatterType): Builder {
            this.type = type
            return this
        }

        fun build(listInput: MutableList<ChartInput>): ChartTimeFrame {
            val formatter = when (type) {
                FormatterType.DEFAULT -> null
                FormatterType.DAILY -> DayFormatter
                FormatterType.WEEKLY -> WeekFormatter
                FormatterType.MONTHLY -> MonthFormatter
                FormatterType.YEARLY -> YearFormatter
            }

            if (formatter != null) {
                if (formatter.maximumValues >= listInput.size) {
                    for (i in listInput.size .. formatter.maximumValues) {
                        listInput.add(createNonCollectedChartInput(i.toFloat()))
                    }
                }
            } // Need to auto complete base on the type of view

            return ChartTimeFrame(title, isSelected, type, formatter, listInput)
        }

    }

}




