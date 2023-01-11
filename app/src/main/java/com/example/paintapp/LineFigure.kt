package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Path
import android.util.Log
import android.view.MotionEvent
import androidx.annotation.ColorInt

class LineFigure(
    @ColorInt paintColor: Int
) : AbstractFigure(paintColor) {
    private val linePath = Path()

    override fun onTouchEventDown(event: MotionEvent) {
        linePath.moveTo(event.x, event.y)
        Log.d("Line", "Touch down")
    }

    override fun onTouchEventMove(event: MotionEvent) {
        linePath.lineTo(event.x, event.y)  // call invalidate() on upper level!
        Log.d("Line", "Move")
    }

    override fun onTouchEventUp(event: MotionEvent) {
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(linePath, paint)
        Log.d("Line", "draw line")
    }

}