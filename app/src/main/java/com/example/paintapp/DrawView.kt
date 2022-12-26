package com.example.paintapp

import android.content.Context
import android.graphics.*
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
    private lateinit var mCurrentBox: Box
    private var mBoxes: ArrayList<Box> = arrayListOf()

    var figureType = FigureType.LINE

    fun reset() {
        when(figureType) {
            FigureType.LINE -> mPath.reset()
            FigureType.RECT -> mBoxes.clear()
        }
        invalidate()
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        return when(figureType) {
            FigureType.LINE -> onTouchRect(event)
            FigureType.RECT -> onTouchLine(event)
        }

    }

    private fun onTouchRect(event: MotionEvent?) = PointF(event!!.x, event!!.y).let{
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                mCurrentBox = Box(it, it)
                mBoxes.add(mCurrentBox)
                return@let true
            }
            MotionEvent.ACTION_MOVE -> {
                mCurrentBox.mCurrent = it
                invalidate()
                return@let true
            }
            MotionEvent.ACTION_UP -> {
                return@let true
            }

            else -> {super.onTouchEvent(event)}
        }
    }

    private fun onTouchLine(event: MotionEvent?) = when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath.moveTo(event.x, event.y)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                mPath.lineTo(event.x, event.y)
                invalidate()
                true
            }
            else -> super.onTouchEvent(event)
        }

    override fun onDraw(canvas: Canvas?) {
        when(figureType) {
            FigureType.LINE -> onDrawRect(canvas)
            FigureType.RECT -> onDrawLine(canvas)
        }
    }

    private fun onDrawLine(canvas: Canvas?) {
        canvas?.drawPath(mPath, mPaint)
    }

    private fun onDrawRect(canvas: Canvas?) {
        for (box in mBoxes) {
            val left = StrictMath.min(box.mOrigin.x, box.mCurrent.x)
            val right = StrictMath.max(box.mOrigin.x, box.mCurrent.x)
            val top = StrictMath.max(box.mOrigin.y, box.mCurrent.y)
            val bottom = StrictMath.min(box.mOrigin.y, box.mCurrent.y)
            canvas?.drawRect(left, top, right, bottom, mPaint)
        }
    }

    companion object {
        const val STROKE_WIDTH = 10f
    }
}