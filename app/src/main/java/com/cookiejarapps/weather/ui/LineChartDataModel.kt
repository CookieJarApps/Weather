package com.cookiejarapps.weather.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.LineChartData.Point
import com.cookiejarapps.weather.ui.chart.line.renderer.point.HollowCircularPointDrawer

class LineChartDataModel {
    var lineChartData by mutableStateOf(
        LineChartData(
            points = listOf(
                Point(0f, "1PM"),
                Point(2f, "2PM"),
                Point(6f, "3PM"),
                Point(6f, "4PM"),
                Point(4f, "5PM"),
                Point(2f, "6PM"),
                Point(0f, "7PM")
            )
        )
    )
    var horizontalOffset by mutableStateOf(5f)
    val pointDrawer = HollowCircularPointDrawer()
}