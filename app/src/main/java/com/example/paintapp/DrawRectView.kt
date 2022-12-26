package com.example.paintapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.lang.StrictMath.max
import java.lang.StrictMath.min

class DrawRectView(
    context: Context,
    attributeSet: AttributeSet? = null,
//    defStyleAttr: Int = 0
) : View(context, attributeSet /*, defStyleAttr*/) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        flags = Paint.ANTI_ALIAS_FLAG
        strokeWidth = STROKE_WIDTH
        style = Paint.Style.STROKE
    }

    private val mPath = Path()

    private val boxes: ArrayList<Box> = ArrayList()

    var currentColor: Int = 0

    fun reset() {
//        mPath.reset()
        boxes.clear()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val action = event?.action
        var x: Float = event?.x!!.toFloat()
        var y: Float = event?.y!!.toFloat()
        var currentPoint = PointF(x, y)

        var mCurrentBox = Box(currentPoint)

        mPaint.color = currentColor   // TODO

        return when (action) {
            MotionEvent.ACTION_DOWN -> {
//                mPath.moveTo(x, y)
                mCurrentBox = Box(currentPoint)
                boxes.add(mCurrentBox)
                Log.d(
                    "DrawViewLog",
                    "Action down, current point: ${mCurrentBox.mOrigin}, ${mCurrentBox.mCurrent}"
                )
                true
            }
            MotionEvent.ACTION_MOVE -> {
//                mPath.lineTo(x, y)
                if (mCurrentBox != null) {
                    mCurrentBox.mCurrent = currentPoint
                    invalidate()
                }
                Log.d(
                    "DrawViewLog",
                    "Move, current point: ${mCurrentBox.mOrigin}, ${mCurrentBox.mCurrent}"
                )
                true

            }
            MotionEvent.ACTION_CANCEL -> {
//                mCurrentBox = null  // TODO Ask?
                true
            }
            else -> super.onTouchEvent(event)
        }

    }

    override fun onDraw(canvas: Canvas?) {
//        canvas?.drawPath(mPath, mPaint)
        for (box in boxes) {
            val left = min(box.mOrigin.x, box.mCurrent.x)
            val right = max(box.mOrigin.x, box.mCurrent.x)
            val top = min(box.mOrigin.y, box.mCurrent.y)
            val bottom = max(box.mOrigin.y, box.mCurrent.y)

            canvas?.drawRect(left, right, top, bottom, mPaint)
            Log.d("DrViewLog", "Box: $left, $right, $top, $bottom")
        }
    }

    companion object {
        const val STROKE_WIDTH = 10f
    }
}