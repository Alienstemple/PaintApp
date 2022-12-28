package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent

abstract class AbstractFigure {
    abstract val mPaint: Paint
    abstract fun onTouchEventDown(event: MotionEvent)
    abstract fun onTouchEventMove(event: MotionEvent)
    abstract fun onTouchEventUp(event: MotionEvent)
    abstract fun onDraw(canvas: Canvas)
    abstract fun setupPaint()
    abstract fun reset()
}