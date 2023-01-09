package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.view.MotionEvent
import java.lang.StrictMath.max
import java.lang.StrictMath.min

class BoxFigure(
    val mOrigin: PointF, var mCurrent: PointF, val mColor: Int
) : AbstractFigure() {

    override val mPaint = Paint()

    override fun onTouchEventDown(event: MotionEvent) {
        // mCurrent, mOrigin already initialized
    }

    override fun onTouchEventMove(event: MotionEvent) {
        mCurrent.set(event.x, event.y)
    }

    override fun onTouchEventUp(event: MotionEvent) {
    }

    override fun onDraw(canvas: Canvas) {
        val left = min(mOrigin.x, mCurrent.x)
        val right = max(mOrigin.x, mCurrent.x)
        val top = max(mOrigin.y, mCurrent.y)
        val bottom = min(mOrigin.y, mCurrent.y)
        canvas.drawRect(left, top, right, bottom, mPaint)
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