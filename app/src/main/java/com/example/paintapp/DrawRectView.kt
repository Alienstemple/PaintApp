package com.example.paintapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.lang.StrictMath.max
import java.lang.StrictMath.min

class DrawRectView(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        flags = Paint.ANTI_ALIAS_FLAG
        color = Color.YELLOW
        strokeWidth = STROKE_WIDTH
        style = Paint.Style.STROKE
    }

    private lateinit var mCurrentBox: Box
    private var mBoxes: ArrayList<Box> = arrayListOf()

    fun reset() {
        mBoxes.clear()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action
        val mCurrentPoint = PointF(event!!.x, event!!.y)

        return when(action) {
            MotionEvent.ACTION_DOWN -> {
                mCurrentBox = Box(mCurrentPoint, mCurrentPoint)
                mBoxes.add(mCurrentBox)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                mCurrentBox.mCurrent = mCurrentPoint
                invalidate()
                true
            }
            MotionEvent.ACTION_UP -> {
                true
            }

            else -> {super.onTouchEvent(event)}
        }
    }

    override fun onDraw(canvas: Canvas?) {
        for (box in mBoxes) {
            val left = min(box.mOrigin.x, box.mCurrent.x)
            val right = max(box.mOrigin.x, box.mCurrent.x)
            val top = max(box.mOrigin.y, box.mCurrent.y)
            val bottom = min(box.mOrigin.y, box.mCurrent.y)
            canvas?.drawRect(left, top, right, bottom, mPaint)
        }
    }

    companion object {
        const val STROKE_WIDTH = 10f
    }
}