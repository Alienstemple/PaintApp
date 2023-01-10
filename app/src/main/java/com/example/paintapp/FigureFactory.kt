package com.example.paintapp

import android.graphics.Path
import android.graphics.PointF
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

class FigureFactory {
    fun createFigure(figureType: FigureType, @ColorRes currentColor: Int, currentPoint: PointF): AbstractFigure = when (figureType) {
        FigureType.RECT -> BoxFigure(currentColor)
        FigureType.LINE -> LineFigure(Path(), currentColor)
        FigureType.STRAIGHT -> StraightFigure(currentPoint, PointF(currentPoint.x, currentPoint.y), currentColor)  // TODO check
        FigureType.POLY -> PolygonFigure(Path(), currentColor)
        FigureType.OPENPOLY -> OpenPolyFigure(Path(), currentColor)
    }
}