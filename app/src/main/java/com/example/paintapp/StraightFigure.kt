package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.view.MotionEvent
import androidx.annotation.ColorInt

class StraightFigure(
    val origin: PointF, val current: PointF, @ColorInt paintColor: Int
) : AbstractFigure(paintColor) {


    override fun onTouchEventDown(event: MotionEvent) {
    }

    override fun onTouchEventMove(event: MotionEvent) {
        current.set(event.x, event.y)
    }

    override fun onTouchEventUp(event: MotionEvent) {
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(origin.x, origin.y, current.x, current.y, paint)
    }

    override fun reset() {
        // Box list clears on top level
    }
}