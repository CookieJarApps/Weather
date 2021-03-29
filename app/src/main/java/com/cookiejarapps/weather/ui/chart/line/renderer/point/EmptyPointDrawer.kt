package com.cookiejarapps.weather.ui.chart.line.renderer.point

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope

object EmptyPointDrawer : PointDrawer {
  override fun drawPoint(
    drawScope: DrawScope,
    canvas: Canvas,
    center: Offset
  ) {
  }
}