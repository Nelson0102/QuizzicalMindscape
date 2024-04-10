package com.game.quizzicalmindscape

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    private var displayScoreTextView: TextView? = null
    private var returnButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        displayScoreTextView = findViewById(R.id.displayScoreTextView)
        returnButton = findViewById(R.id.returnButton)

        // Get the score from the intent and display it
        val score = intent.getIntExtra("SCORE", 0)
        val scoreText = "$score/5"
        displayScoreTextView?.text = scoreText

        // Setup OnClickListener for returnButton
        returnButton?.setOnClickListener {
            // Implement the action to return to the quiz or home screen
            startActivity(Intent(this@ScoreActivity, MainActivity::class.java))
            finish() // Finish the ScoreActivity to prevent going back to it
        }
    }
}
