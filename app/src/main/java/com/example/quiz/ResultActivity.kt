package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var username = intent.getStringExtra(Constants.USER_NAME)
        findViewById<TextView>(R.id.name).text = username

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        findViewById<TextView>(R.id.score).text = "Your score is $correctAnswers out of $totalQuestions"

        findViewById<Button>(R.id.finish_btn).setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish() // if activity is not finished, we can go back to the previous activity by clicking back button.
        }
    }
}