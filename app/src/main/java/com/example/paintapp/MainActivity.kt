package com.example.paintapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val drawView = findViewById<DrawView>(R.id.draw_view)
        val drawRectView = findViewById<DrawRectView>(R.id.draw_rect_view)

        val btnReset = findViewById<Button>(R.id.reset_button)
        val btnRect = findViewById<Button>(R.id.draw_rect)
        val btnLine = findViewById<Button>(R.id.draw_line)

        btnReset.setOnClickListener {
//            drawView.reset()
            drawRectView.reset()
//            lineFigureView.reset()
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
    }
}