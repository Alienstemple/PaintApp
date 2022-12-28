package com.example.paintapp

import android.graphics.Path
import android.graphics.PointF

class FigureFactory {
    fun createFigure(figureType: FigureType, currentColor: Int, mCurrentPoint: PointF): AbstractFigure = when (figureType) {
        FigureType.RECT -> BoxFigure(mCurrentPoint, mCurrentPoint, currentColor)
        FigureType.LINE -> LineFigure(Path(), currentColor)
    }
}