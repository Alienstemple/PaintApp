package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.Log
import android.view.MotionEvent

class LineFigure(
    val linePath: Path, val paintColor: Int
) : AbstractFigure() {

    override val paint = Paint()
//    private val linePath = Path()

    override fun onTouchEventDown(event: MotionEvent) {
        linePath.moveTo(event.x, event.y)
        Log.d("Line", "Touch down")
    }

    override fun onTouchEventMove(event: MotionEvent) {
        linePath.lineTo(event.x, event.y)  // TODO call invalidate() on upper level!
        Log.d("Line", "Move")
    }

    override fun onTouchEventUp(event: MotionEvent) {
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(linePath, paint)
        Log.d("Line", "draw line")
    }

    override fun setupPaint() {
        paint.apply {
            flags = Paint.ANTI_ALIAS_FLAG
            color = paintColor
            strokeWidth = DrawView.STROKE_WIDTH
            style = Paint.Style.STROKE
        }
    }

    override fun reset() {
        linePath.reset()
    }
}