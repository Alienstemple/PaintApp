package com.example.paintapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawView = findViewById<DrawView>(R.id.draw_view)
        val btnReset = findViewById<Button>(R.id.reset_button)
        val btnRect = findViewById<Button>(R.id.draw_rect)
        val btnLine = findViewById<Button>(R.id.draw_line)

        btnReset.setOnClickListener {
            drawView.reset()
        }

        btnRect.setOnClickListener {
//            drawView.currentColor = Color.BLUE
        }

        btnLine.setOnClickListener {
//            drawView.currentColor = Color.GREEN
        }
    }
}