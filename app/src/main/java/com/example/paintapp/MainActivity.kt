package com.example.paintapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val drawView = findViewById<DrawView>(R.id.draw_view)
        val drawRectView = findViewById<DrawRectView>(R.id.draw_rect_view)

        val btnReset = findViewById<Button>(R.id.reset_button)
        val btnChangeColor = findViewById<Button>(R.id.chng_color_button)
        val btnPolygon = findViewById<Button>(R.id.draw_polygon)
        val btnRect = findViewById<Button>(R.id.draw_rect)
        val btnLine = findViewById<Button>(R.id.draw_line)
        val btnStraightLine = findViewById<Button>(R.id.draw_straight_line)
        val btnBack = findViewById<Button>(R.id.step_back)

        btnReset.setOnClickListener {
//            drawView.reset()
            drawRectView.reset()
//            lineFigureView.reset()
        }

        btnChangeColor.setOnClickListener {
            drawRectView.currentColor = Color.argb(
                255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
        }

        btnPolygon.setOnClickListener {
            drawRectView.figureType = FigureType.POLY
        }

        btnRect.setOnClickListener {
//            drawView.currentColor = Color.BLUE
            drawRectView.figureType = FigureType.RECT
            Log.d("Tag", "Rectangular chosen")
        }

        btnLine.setOnClickListener {
//            drawView.currentColor = Color.GREEN
            drawRectView.figureType = FigureType.LINE
        }

        btnStraightLine.setOnClickListener{
            drawRectView.figureType = FigureType.STRAIGHT
        }

        btnBack.setOnClickListener {
            drawRectView.back()
        }
    }
}