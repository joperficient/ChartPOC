package com.perficient.chartpoc.model

import com.github.mikephil.charting.formatter.ValueFormatter

data class ChartTimeFrame(val title: String,
                          var isSelected: Boolean= false,
                          var type: FormatterType,
                          var formatter: ValueFormatter? = null,
                          var listInput: List<ChartInput>) {

    class Builder(private val title: String) {

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

            return ChartTimeFrame(title, isSelected, type, XAxisDateFormatter(listInput, type), listInput)
        }

    }

}




