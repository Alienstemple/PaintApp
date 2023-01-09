package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.Log
import android.view.MotionEvent

class LineFigure(
    val mPath: Path, val mColor: Int
) : AbstractFigure() {

    override val mPaint = Paint()
//    private val mPath = Path()

    override fun onTouchEventDown(event: MotionEvent) {
        mPath.moveTo(event.x, event.y)
        Log.d("Line", "Touch down")
    }

    override fun onTouchEventMove(event: MotionEvent) {
        mPath.lineTo(event.x, event.y)  // TODO call invalidate() on upper level!
        Log.d("Line", "Move")
    }

    override fun onTouchEventUp(event: MotionEvent) {
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(mPath, mPaint)
        Log.d("Line", "draw line")
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