package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent

abstract class AbstractFigure {
    abstract val mPaint: Paint
    abstract fun onTouchEvent(event: MotionEvent?): Boolean
    abstract fun onDraw(canvas: Canvas?)
    abstract fun setupPaint()
    abstract fun reset()
}