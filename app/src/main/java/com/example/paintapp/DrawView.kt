package com.example.paintapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawView(
    context: Context,
    attributeSet: AttributeSet? = null,
//    defStyleAttr: Int = 0
) : View(context, attributeSet /*, defStyleAttr*/) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        flags = Paint.ANTI_ALIAS_FLAG
        color = Color.YELLOW
        strokeWidth = STROKE_WIDTH
        style = Paint.Style.STROKE
    }

    private val mPath = Path()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action
        var x: Float = event?.x!!.toFloat()
        var y: Float = event?.y!!.toFloat()

        return when(action) {
            MotionEvent.ACTION_DOWN -> {
                mPath.moveTo(x, y)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                mPath.lineTo(x, y)
                invalidate()
                true
            }
            else -> super.onTouchEvent(event)
        }

    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(mPath, mPaint)
    }

    companion object {
        const val STROKE_WIDTH = 10f
    }
}