package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import androidx.annotation.ColorInt

abstract class AbstractFigure(@ColorInt val figureColor: Int) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        flags = Paint.ANTI_ALIAS_FLAG
        color = figureColor
        strokeWidth = DrawView.STROKE_WIDTH
        style = Paint.Style.STROKE
    }
    abstract fun onTouchEventDown(event: MotionEvent)
    abstract fun onTouchEventMove(event: MotionEvent)
    abstract fun onTouchEventUp(event: MotionEvent)
    abstract fun onDraw(canvas: Canvas)
}