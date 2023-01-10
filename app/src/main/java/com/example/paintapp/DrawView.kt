package com.example.paintapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class DrawView(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private lateinit var mFigure: AbstractFigure
    private var mFigureList: ArrayList<AbstractFigure> = arrayListOf()

    var figureType = FigureType.RECT
    var currentColor = Color.BLACK

    fun reset() {
        mFigureList.clear()
        invalidate()
    }

    fun back() {
        mFigureList.removeLastOrNull()
        invalidate()
    }

//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        val action = event?.action
//        val mCurrentPoint = PointF(event!!.x, event.y)
//
//        return when (action) {
//            MotionEvent.ACTION_DOWN -> {
//
//                val currentColor = Color.argb(
//                    255,
//                    Random.nextInt(0, 255),
//                    Random.nextInt(0, 255),
//                    Random.nextInt(0, 255)
//                )
//
//                mFigure = FigureFactory().createFigure(figureType, currentColor, mCurrentPoint)
//                mFigure.setupPaint()
//                mFigure.onTouchEventDown(event)
//                mFigureList.add(mFigure)
//                true
//            }
//
//            MotionEvent.ACTION_MOVE -> {
//                mFigure.onTouchEventMove(event)
//                invalidate()
//                true
//            }
//            MotionEvent.ACTION_UP -> {
//                mFigure.onTouchEventUp(event)
//                true
//            }
//
//            else -> {
//                super.onTouchEvent(event)
//            }
//        }
//    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked
        val pointerIndex = event.actionIndex
        val pointerId = event.getPointerId(pointerIndex)
        val point = PointF(event.getX(pointerIndex), event.getY(pointerIndex))

        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                mFigure = FigureFactory().createFigure(figureType, currentColor, point)
                mFigure.setupPaint()
                mFigure.onTouchEventDown(event)
                mFigureList.add(mFigure)
                true
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                mFigure.onTouchEventDown(event)
                true
            }

            MotionEvent.ACTION_MOVE -> {
                // Запретим двигать нулевой индекс:
                Log.d("Poly", "Registered action.MOVE")
                mFigure.onTouchEventMove(event)
                invalidate()

                true
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_POINTER_UP -> {
                mFigure.onTouchEventUp(event)
                true
            }

            else -> {
                super.onTouchEvent(event)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            mFigureList.forEach { it.onDraw(canvas) }
        }

    }

    companion object {
        const val STROKE_WIDTH = 10f
    }

}