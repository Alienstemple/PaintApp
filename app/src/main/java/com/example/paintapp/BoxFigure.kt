package com.example.paintapp

import android.graphics.Canvas
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent
import androidx.annotation.ColorInt

class BoxFigure(
    @ColorInt paintColor: Int
) : AbstractFigure(paintColor) {

    private val vertices = HashMap<Int, PointF>()

    override fun onTouchEventDown(event: MotionEvent) {
        val pointerIndex = event.actionIndex
        val pointerId = event.getPointerId(pointerIndex)
        val point = PointF(event.getX(pointerIndex), event.getY(pointerIndex))
        vertices[pointerIndex] = point
        val vertMapToString: String = vertices.forEach { (k, v) -> "$k , ${v.x}, ${v.y}; " }.toString()
        Log.d("Poly", "Action.DOWN, index = $pointerIndex, id = ${pointerId}, (x,y) = (${point.x},${point.y})" +
                "vertices: $vertMapToString")
    }

    override fun onTouchEventMove(event: MotionEvent) {

        for (i in 0 until event.pointerCount) {
            val point = PointF(event.getX(i), event.getY(i))
            Log.d("Poly","Action.MOVE index = $i")
            vertices[i] = point
        }
    }

    /**
     * Multitouch, прямоугольник рисуется по максимальной площеди касания пальцев
     */
    override fun onDraw(canvas: Canvas) {
        val left = vertices.minBy { it.value.x }.value.x
        val right = vertices.maxBy { it.value.x }.value.x
        val top = vertices.maxBy { it.value.y }.value.y
        val bottom = vertices.minBy { it.value.y }.value.y
        canvas.drawRect(left, top, right, bottom, paint)
    }
}