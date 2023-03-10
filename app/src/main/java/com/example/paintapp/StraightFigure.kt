package com.example.paintapp

import android.graphics.Canvas
import android.graphics.PointF
import android.view.MotionEvent
import androidx.annotation.ColorInt

class StraightFigure(
    private val origin: PointF, private val current: PointF, @ColorInt paintColor: Int
) : AbstractFigure(paintColor) {


    override fun onTouchEventDown(event: MotionEvent) {
    }

    override fun onTouchEventMove(event: MotionEvent) {
        current.set(event.x, event.y)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(origin.x, origin.y, current.x, current.y, paint)
    }
}