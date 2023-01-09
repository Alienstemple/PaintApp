package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.view.MotionEvent

class PolygonFigure (
    val mPath: Path, val mColor: Int
) : AbstractFigure() {

    override val mPaint = Paint()
    private val vertices = HashMap<Int, PointF>()

    /**
     * Каждое новое касание - добавляем точку в HashMap
     */
    override fun onTouchEventDown(event: MotionEvent) {
        val pointerIndex = event.actionIndex
        val point = PointF(event.getX(pointerIndex), event.getY(pointerIndex))
        vertices[pointerIndex] = point
    }

    /**
     * Движение -> обновляем точку по индексу
     */
    override fun onTouchEventMove(event: MotionEvent) {
        val pointerIndex = event.actionIndex
        val point = PointF(event.getX(pointerIndex), event.getY(pointerIndex))
        vertices[pointerIndex] = point
    }

    override fun onTouchEventUp(event: MotionEvent) {
    }

    /**
     * Отрисовываем многоугольник - последовательность moveTo и drawPath
     */
    override fun onDraw(canvas: Canvas) {
        // Ставим начало пути в начальную точку
        vertices[0]?.let {
            mPath.moveTo(it.x, it.y)
        }
        // Путь "останавливается" в вершинах
        vertices.forEach {(k, v) ->
            if (k != 0) {
                mPath.moveTo(v.x, v.y)
            }
        }
        // И завершается в начальной точке
        vertices[0]?.let {
            mPath.moveTo(it.x, it.y)
        }
        // Наконец, происходит отрисовка фигуры
        canvas.drawPath(mPath, mPaint)
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