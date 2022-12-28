package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.view.MotionEvent

class StraightFigure(
    val mOrigin: PointF, var mCurrent: PointF, val mColor: Int
) : AbstractFigure() {

    override val mPaint = Paint()

    override fun onTouchEventDown(event: MotionEvent) {
        // mCurrent, mOrigin already initialized
    }

    override fun onTouchEventMove(event: MotionEvent) {
        mCurrent = PointF(event.x, event.y)
    }

    override fun onTouchEventUp(event: MotionEvent) {
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(mOrigin.x, mOrigin.y, mCurrent.x, mCurrent.y, mPaint)
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
        // Box list clears on top level
    }
}