package com.perficient.chartpoc.model

data class ChartInput(
    val x: Float,
    val y: Float,
    val isCalibration: Boolean = false,
    val nonCollectedYet: Boolean = false
)

fun createNonCollectedChartInput(y: Float) = ChartInput(0f, y, false, true)