package com.example.paintapp

import android.graphics.Path
import android.graphics.PointF

class FigureFactory {
    fun createFigure(figureType: FigureType, currentColor: Int, mCurrentPoint: PointF): AbstractFigure = when (figureType) {
        FigureType.RECT -> BoxFigure(currentColor)
        FigureType.LINE -> LineFigure(Path(), currentColor)
        FigureType.STRAIGHT -> StraightFigure(mCurrentPoint, mCurrentPoint, currentColor)
        FigureType.POLY -> PolygonFigure(Path(), currentColor)
        FigureType.OPENPOLY -> OpenPolyFigure(Path(), currentColor)
    }
}