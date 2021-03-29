package com.cookiejarapps.weather.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookiejarapps.weather.ui.chart.line.LineChart

@Composable
fun LineChartScreen() {
    LineChartScreenContent()
}

@Composable
fun LineChartScreenContent() {
    val lineChartDataModel = LineChartDataModel()

    Column(
        modifier = Modifier.padding(
            horizontal = 18.dp,
            vertical = 18.dp
        )
    ) {
        LineChartRow(lineChartDataModel)
    }
}

@Composable
fun LineChartRow(lineChartDataModel: LineChartDataModel) {
    Box(
        modifier = Modifier
            .height(192.dp)
            .fillMaxWidth()
    ) {
        LineChart(
            lineChartData = lineChartDataModel.lineChartData,
            dataUnit = "C",
            horizontalOffset = lineChartDataModel.horizontalOffset,
            pointDrawer = lineChartDataModel.pointDrawer
        )
    }
}

@Preview
@Composable
fun LineChartPreview() = LineChartScreen()