package com.cookiejarapps.weather.ui.chart.line.renderer.xaxis

import android.graphics.Typeface
import android.text.TextPaint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class XAxisDrawer(
  private val labelTextSize: TextUnit = 12.sp,
  private val dataTextSize: TextUnit = 14.sp,
  private val labelRatio: Int = 1,
  private val axisLineThickness: Dp = 1.dp,
  private val axisLineColor: Color = Color.Black
) : XAxisDrawerInterface {
  private val axisLinePaint = Paint().apply {
    isAntiAlias = true
    color = axisLineColor
    style = PaintingStyle.Stroke
  }

  override fun requiredHeight(drawScope: DrawScope): Float {
    return with(drawScope) {
      (3f / 2f) * (labelTextSize.toPx() + axisLineThickness.toPx())
    }
  }

  override fun drawAxisLine(
    drawScope: DrawScope,
    canvas: Canvas,
    drawableArea: Rect
  ) {
    with(drawScope) {
      val lineThickness = axisLineThickness.toPx()
      val y = drawableArea.top + (lineThickness / 2f)

      canvas.drawLine(
        p1 = Offset(
          x = drawableArea.left,
          y = y
        ),
        p2 = Offset(
          x = drawableArea.right,
          y = y
        ),
        paint = axisLinePaint.apply {
          strokeWidth = lineThickness
        }
      )
    }
  }

  override fun drawAxisLabels(
    drawScope: DrawScope,
    canvas: Canvas,
    drawableArea: Rect,
    labels: List<String>,
    data: List<String>
  ) {
    with(drawScope) {
      val labelPaint = TextPaint()
      labelPaint.textSize = labelTextSize.toPx()
      labelPaint.textAlign = android.graphics.Paint.Align.CENTER

      val dataPaint = TextPaint()
      dataPaint.textSize = dataTextSize.toPx()
      dataPaint.textAlign = android.graphics.Paint.Align.CENTER
      dataPaint.typeface = Typeface.DEFAULT_BOLD

      val labelIncrements = drawableArea.width / (labels.size - 1)
      labels.forEachIndexed { index, label ->
        if (index.rem(labelRatio) == 0) {
          val x = drawableArea.left + (labelIncrements * (index))
          val y = drawableArea.bottom

          canvas.nativeCanvas.drawText(data[index], x, y, dataPaint)

          canvas.nativeCanvas.drawText(label, x, y + dataTextSize.toPx(), labelPaint)
        }
      }
    }
  }
}