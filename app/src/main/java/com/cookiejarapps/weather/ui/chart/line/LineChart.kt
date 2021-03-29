package com.cookiejarapps.weather.ui.chart.line

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.cookiejarapps.weather.ui.chart.common.animation.drawingAnimation
import com.cookiejarapps.weather.ui.chart.line.LineChartUtils.calculateDrawableArea
import com.cookiejarapps.weather.ui.chart.line.LineChartUtils.calculateLinePath
import com.cookiejarapps.weather.ui.chart.line.LineChartUtils.calculatePointLocation
import com.cookiejarapps.weather.ui.chart.line.LineChartUtils.calculateXAxisDrawableArea
import com.cookiejarapps.weather.ui.chart.line.LineChartUtils.calculateXAxisLabelsDrawableArea
import com.cookiejarapps.weather.ui.chart.line.LineChartUtils.withProgress
import com.cookiejarapps.weather.ui.chart.line.renderer.point.HollowCircularPointDrawer
import com.cookiejarapps.weather.ui.chart.line.renderer.line.LineDrawer
import com.cookiejarapps.weather.ui.chart.line.renderer.line.SolidLineDrawer
import com.cookiejarapps.weather.ui.chart.line.renderer.point.PointDrawer
import com.cookiejarapps.weather.ui.chart.line.renderer.xaxis.XAxisDrawer
import com.cookiejarapps.weather.ui.chart.line.renderer.xaxis.XAxisDrawerInterface
import com.github.tehras.charts.line.LineChartData

@Composable
fun LineChart(
  modifier: Modifier = Modifier,
  lineChartData: LineChartData,
  dataUnit: String = "",
  animation: AnimationSpec<Float> = drawingAnimation(),
  pointDrawer: PointDrawer = HollowCircularPointDrawer(),
  lineDrawer: LineDrawer = SolidLineDrawer(),
  xAxisDrawer: XAxisDrawerInterface = XAxisDrawer(),
  horizontalOffset: Float = 5f
) {
  check(horizontalOffset in 0f..25f) {
    "Horizontal offset is the % offset from sides, " +
      "and should be between 0%-25%"
  }

  val transitionAnimation = remember(lineChartData.points) { Animatable(initialValue = 0f) }

  LaunchedEffect(lineChartData.points) {
    transitionAnimation.snapTo(0f)
    transitionAnimation.animateTo(1f, animationSpec = animation)
  }

  Canvas(modifier = modifier.fillMaxSize()) {
    drawIntoCanvas { canvas ->
      val xAxisDrawableArea = calculateXAxisDrawableArea(
        yAxisWidth = 18F,
        labelHeight = xAxisDrawer.requiredHeight(this),
        size = size
      )
      val xAxisLabelsDrawableArea = calculateXAxisLabelsDrawableArea(
        xAxisDrawableArea = xAxisDrawableArea,
        offset = horizontalOffset
      )
      val chartDrawableArea = calculateDrawableArea(
        xAxisDrawableArea = xAxisDrawableArea,
        size = size,
        offset = horizontalOffset
      )

      lineDrawer.drawLine(
        drawScope = this,
        canvas = canvas,
        linePath = calculateLinePath(
          drawableArea = chartDrawableArea,
          lineChartData = lineChartData,
          transitionProgress = transitionAnimation.value
        )
      )

      lineChartData.points.forEachIndexed { index, point ->
        withProgress(
          index = index,
          lineChartData = lineChartData,
          transitionProgress = transitionAnimation.value
        ) {
          val pointLocation = calculatePointLocation(drawableArea = chartDrawableArea, lineChartData = lineChartData, point = point, index = index)
          pointDrawer.drawPoint(
            drawScope = this,
            canvas = canvas,
            center = pointLocation
          )
        }
      }

      xAxisDrawer.drawAxisLine(
        drawScope = this,
        drawableArea = xAxisDrawableArea,
        canvas = canvas
      )

      xAxisDrawer.drawAxisLabels(
        drawScope = this,
        canvas = canvas,
        drawableArea = xAxisLabelsDrawableArea,
        labels = lineChartData.points.map { it.label },
        data = lineChartData.points.map {it.value.toString() + dataUnit }
      )
    }
  }
}
