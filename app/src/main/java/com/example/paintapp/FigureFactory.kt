package com.example.paintapp

import android.graphics.Path
import android.graphics.PointF
import androidx.annotation.ColorInt

class FigureFactory {
    fun createFigure(figureType: FigureType, @ColorInt currentColor: Int, currentPoint: PointF): AbstractFigure = when (figureType) {
        FigureType.RECT -> BoxFigure(currentColor)
        FigureType.LINE -> LineFigure(Path(), currentColor)
        FigureType.STRAIGHT -> StraightFigure(currentPoint, currentPoint, currentColor)
        FigureType.POLY -> PolygonFigure(Path(), currentColor)
        FigureType.OPENPOLY -> OpenPolyFigure(Path(), currentColor)
    }
}