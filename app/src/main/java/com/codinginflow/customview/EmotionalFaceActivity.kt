package com.codinginflow.customview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codinginflow.customview.widget.EmotionalFace

class EmotionalFaceActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emotional_face)

        findViewById<EmotionalFace>(R.id.happyButton).setOnClickListener {
            findViewById<EmotionalFace>(R.id.emotionalFaceView).happinessState = EmotionalFace.HAPPY
        }

        findViewById<EmotionalFace>(R.id.sadButton).setOnClickListener {
            findViewById<EmotionalFace>(R.id.emotionalFaceView).happinessState = EmotionalFace.SAD
        }
    }
}