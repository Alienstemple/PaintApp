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

        val drawView = findViewById<DrawView>(R.id.draw_rect_view)

        val btnReset = findViewById<Button>(R.id.reset_button)
        val btnChangeColor = findViewById<Button>(R.id.chng_color_button)
        val btnOpenPolygon = findViewById<Button>(R.id.draw_open_polygon)
        val btnPolygon = findViewById<Button>(R.id.draw_polygon)
        val btnRect = findViewById<Button>(R.id.draw_rect)
        val btnLine = findViewById<Button>(R.id.draw_line)
        val btnStraightLine = findViewById<Button>(R.id.draw_straight_line)
        val btnBack = findViewById<Button>(R.id.step_back)

        btnReset.setOnClickListener {
            drawView.reset()
        }

        btnChangeColor.setOnClickListener {
            drawView.currentColor = Color.argb(
                255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
            Log.d("Main", "Current color set = ${drawView.currentColor}")
        }

        btnOpenPolygon.setOnClickListener {
            drawView.figureType = FigureType.OPENPOLY
        }

        btnPolygon.setOnClickListener {
            drawView.figureType = FigureType.POLY
        }

        btnRect.setOnClickListener {
            drawView.figureType = FigureType.RECT
            Log.d("Tag", "Rectangular chosen")
        }

        btnLine.setOnClickListener {
            drawView.figureType = FigureType.LINE
        }

        btnStraightLine.setOnClickListener {
            drawView.figureType = FigureType.STRAIGHT
        }

        btnBack.setOnClickListener {
            drawView.back()
        }
    }
}