package com.perficient.timeframechart.model

import java.util.*

data class ChartInput(
    val x: Date,
    val y: Float,
    val isCalibration: Boolean = false,
    val nonCollectedYet: Boolean = false
)
