package com.example.paintapp

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent

class OpenPolyFigure (
        val path: Path, val paintColor: Int
    ) : AbstractFigure() {

        override val paint = Paint()
        private val vertices = HashMap<Int, PointF>()

        /**
         * Каждое новое касание - добавляем точку в HashMap
         */
        override fun onTouchEventDown(event: MotionEvent) {
            val pointerIndex = event.actionIndex
            val pointerId = event.getPointerId(pointerIndex)
            val point = PointF(event.getX(pointerIndex), event.getY(pointerIndex))
            vertices[pointerIndex] = point
            val vertMapToString: String = vertices.forEach { (k, v) -> "$k , ${v.x}, ${v.y}; " }.toString()
            Log.d("OpenPoly", "Action.DOWN, index = $pointerIndex, id = ${pointerId}, (x,y) = (${point.x},${point.y})" +
                    "vertices: $vertMapToString")

        }

        /**
         * Движение -> обновляем точку по индексу
         */
        override fun onTouchEventMove(event: MotionEvent) {
            for (i in 0 until event.pointerCount) {
                val point = PointF(event.getX(i), event.getY(i))
                Log.d("OpenPoly","Action.MOVE index = $i")
                vertices[i] = point
            }
            reset()
        }

        override fun onTouchEventUp(event: MotionEvent) {
        }

        /**
         * Отрисовываем многоугольник - последовательность moveTo и drawPath
         */
        override fun onDraw(canvas: Canvas) {
            // Ставим начало пути в начальную точку
            vertices[0]?.let {
                path.moveTo(it.x, it.y)
            }
            // Путь "останавливается" в вершинах
            vertices.forEach {(k, v) ->
                if (k != 0) {
                    path.lineTo(v.x, v.y)
                    path.moveTo(v.x, v.y)
                }
            }
            // Наконец, происходит отрисовка фигуры
            canvas.drawPath(path, paint)
        }

        override fun setupPaint() {
            paint.apply {
                flags = Paint.ANTI_ALIAS_FLAG
                color = paintColor
                strokeWidth = DrawView.STROKE_WIDTH
                style = Paint.Style.STROKE
            }
        }

        override fun reset() {
            path.reset()
        }
    }