package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.view.MotionEvent

class StraightFigure(
    val origin: PointF, val current: PointF, val paintColor: Int
) : AbstractFigure() {


    override fun onTouchEventDown(event: MotionEvent) {
    }

    override fun onTouchEventMove(event: MotionEvent) {
        current.set(event.x, event.y)  // FIXME doesn't work
//        current = PointF(event.x, event.y)
    }

    override fun onTouchEventUp(event: MotionEvent) {
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(origin.x, origin.y, current.x, current.y, paint)
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
        // Box list clears on top level
    }
}