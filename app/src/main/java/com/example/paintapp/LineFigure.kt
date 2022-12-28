package com.example.paintapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class LineFigure(
    val mPath: Path, val mColor: Int
) : AbstractFigure() {

    override val mPaint = Paint()
//    private val mPath = Path()

    override fun onTouchEventDown(event: MotionEvent) {
        mPath.moveTo(event.x, event.y)
    }

    override fun onTouchEventMove(event: MotionEvent) {
        mPath.lineTo(event.x, event.y)  // TODO call invalidate() on upper level!
    }

    override fun onTouchEventUp(event: MotionEvent) {
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(mPath, mPaint)
    }

    override fun setupPaint() {
        mPaint.apply {
            flags = Paint.ANTI_ALIAS_FLAG
            color = mColor
            strokeWidth = DrawView.STROKE_WIDTH
            style = Paint.Style.STROKE
        }
    }

    override fun reset() {
        mPath.reset()
    }
}