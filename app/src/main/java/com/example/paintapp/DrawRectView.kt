package com.example.paintapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.lang.StrictMath.max
import java.lang.StrictMath.min
import kotlin.random.Random

class DrawRectView(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private lateinit var mFigure: AbstractFigure
    private var mFigureList: ArrayList<AbstractFigure> = arrayListOf()

    var figureType = FigureType.RECT

    fun reset() {
        mFigureList.clear()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action
        val mCurrentPoint = PointF(event!!.x, event.y)

        return when (action) {
            MotionEvent.ACTION_DOWN -> {

                val currentColor = Color.argb(
                    255,
                    Random.nextInt(0, 255),
                    Random.nextInt(0, 255),
                    Random.nextInt(0, 255)
                )

                mFigure = BoxFigure(mCurrentPoint, mCurrentPoint, currentColor)
//                mFigure = LineFigure(Path(), currentColor)  // TODO add if on FigType
                mFigure.setupPaint()
                mFigure.onTouchEventDown(event)
                mFigureList.add(mFigure)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                mFigure.onTouchEventMove(event)
                invalidate()
                true
            }
            MotionEvent.ACTION_UP -> {
                true
            }

            else -> {
                super.onTouchEvent(event)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        for (figure in mFigureList) {
            figure.onDraw(canvas)
        }
    }

}