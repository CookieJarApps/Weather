package com.cookiejarapps.weather.ui.chart.line

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.line.LineChartData

object LineChartUtils {
  fun calculateDrawableArea(
    xAxisDrawableArea: Rect,
    size: Size,
    offset: Float
  ): Rect {
    val horizontalOffset = xAxisDrawableArea.width * offset / 100f

    return Rect(
      left = 0f,
      top = 0f,
      bottom = xAxisDrawableArea.top,
      right = size.width - horizontalOffset
    )
  }

  fun calculateXAxisDrawableArea(
    yAxisWidth: Float,
    labelHeight: Float,
    size: Size
  ): Rect {
    val top = size.height - labelHeight

    return Rect(
      left = yAxisWidth,
      top = top,
      bottom = size.height,
      right = size.width
    )
  }

  fun calculateXAxisLabelsDrawableArea(
    xAxisDrawableArea: Rect,
    offset: Float
  ): Rect {
    val horizontalOffset = xAxisDrawableArea.width * offset / 100f

    return Rect(
      left = xAxisDrawableArea.left + horizontalOffset,
      top = xAxisDrawableArea.top,
      bottom = xAxisDrawableArea.bottom,
      right = xAxisDrawableArea.right - horizontalOffset
    )
  }

  fun calculatePointLocation(
    drawableArea: Rect,
    lineChartData: LineChartData,
    point: LineChartData.Point,
    index: Int
  ): Offset {
    val x = (index.toFloat() / (lineChartData.points.size - 1))
    val y = ((point.value - lineChartData.minYValue) / lineChartData.yRange)

    return Offset(
      x = (x * drawableArea.width) + drawableArea.left,
      y = drawableArea.height - (y * drawableArea.height)
    )
  }

  fun withProgress(
    index: Int,
    lineChartData: LineChartData,
    transitionProgress: Float,
    showWithProgress: (progress: Float) -> Unit
  ) {
    val size = lineChartData.points.size
    val toIndex = (size * transitionProgress).toInt() + 1

    if (index == toIndex) {
      val sizeF = lineChartData.points.size.toFloat()
      val perIndex = (1f / sizeF)
      val down = (index - 1) * perIndex

      showWithProgress((transitionProgress - down) / perIndex)
    } else if (index < toIndex) {
      showWithProgress(1f)
    }
  }

  fun calculateLinePath(
    drawableArea: Rect,
    lineChartData: LineChartData,
    transitionProgress: Float
  ): Path = Path().apply {
    var prevPointLocation: Offset? = null
    lineChartData.points.forEachIndexed { index, point ->
      withProgress(
        index = index,
        transitionProgress = transitionProgress,
        lineChartData = lineChartData
      ) { progress ->
        val pointLocation = calculatePointLocation(
          drawableArea = drawableArea,
          lineChartData = lineChartData,
          point = point,
          index = index
        )

        if (index == 0) {
          moveTo(pointLocation.x, pointLocation.y)
        } else {
          if (progress <= 1f) {
            val prevX = prevPointLocation!!.x
            val prevY = prevPointLocation!!.y

            val x = (pointLocation.x - prevX) * progress + prevX
            val y = (pointLocation.y - prevY) * progress + prevY

            lineTo(x, y)
          } else {
            lineTo(pointLocation.x, pointLocation.y)
          }
        }

        prevPointLocation = pointLocation
      }
    }
  }
}