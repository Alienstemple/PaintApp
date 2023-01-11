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

    private lateinit var figure: AbstractFigure
    private var figureList: ArrayList<AbstractFigure> = arrayListOf()

    var figureType = FigureType.RECT
    var currentColor = Color.BLACK

    fun reset() {
        figureList.clear()
        invalidate()
    }

    fun back() {
        figureList.removeLastOrNull()
        invalidate()
    }

        override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked
        val pointerIndex = event.actionIndex
        val pointerId = event.getPointerId(pointerIndex)
        val point = PointF(event.getX(pointerIndex), event.getY(pointerIndex))

        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("Main", "CurrentColor = $currentColor")
                figure = FigureFactory().createFigure(figureType, currentColor, point)
                figure.onTouchEventDown(event)
                figureList.add(figure)
                true
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                figure.onTouchEventDown(event)
                true
            }

            MotionEvent.ACTION_MOVE -> {
                // Запретим двигать нулевой индекс:
                Log.d("Poly", "Registered action.MOVE")
                figure.onTouchEventMove(event)
                invalidate()

                true
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_POINTER_UP -> {
                figure.onTouchEventUp(event)
                true
            }

            else -> {
                super.onTouchEvent(event)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            figureList.forEach { it.onDraw(canvas) }
        }

    }

    companion object {
        const val STROKE_WIDTH = 10f
    }

}