package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME) // getting through intent from previous activity.

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        findViewById<TextView>(R.id.optionOne).setOnClickListener(this)
        findViewById<TextView>(R.id.optionTwo).setOnClickListener(this)
        findViewById<TextView>(R.id.optionThree).setOnClickListener(this)
        findViewById<TextView>(R.id.optionFour).setOnClickListener(this)


        findViewById<Button>(R.id.submit).setOnClickListener(this)

    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion(){

        val question = mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size){
            findViewById<Button>(R.id.submit).text = "FINISH"
        } else
        {
            findViewById<Button>(R.id.submit).text = "SUBMIT"
        }

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.progress = mCurrentPosition

        findViewById<TextView>(R.id.progress).text = "$mCurrentPosition" + "/" + progressBar.max

        findViewById<TextView>(R.id.question).text = question.question
        findViewById<ImageView>(R.id.image).setImageResource(question.image)
        findViewById<TextView>(R.id.optionOne).text = question.optionOne
        findViewById<TextView>(R.id.optionTwo).text = question.optionTwo
        findViewById<TextView>(R.id.optionThree).text = question.optionThree
        findViewById<TextView>(R.id.optionFour).text = question.optionFour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, findViewById<TextView>(R.id.optionOne))
        options.add(1, findViewById<TextView>(R.id.optionTwo))
        options.add(2, findViewById<TextView>(R.id.optionThree))
        options.add(3, findViewById<TextView>(R.id.optionFour))

        for (option in options){
            option.setTextColor(Color.parseColor("#FFFFFF"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.options_border)
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.optionOne -> {
                selectedOptionView(findViewById(R.id.optionOne), 1)
            }
            R.id.optionTwo -> {
                selectedOptionView(findViewById(R.id.optionTwo), 2)
            }
            R.id.optionThree -> {
                selectedOptionView(findViewById(R.id.optionThree), 3)
            }
            R.id.optionFour -> {
                selectedOptionView(findViewById(R.id.optionFour), 4)
            }
            R.id.submit -> {
                if(mSelectedOptionPosition==0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition<=mQuestionsList!!.size -> {
                            setQuestion()
                        }

                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }

                }
                else{
                    val question = mQuestionsList!![mCurrentPosition-1]
                    if(question.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_options_border)
                    }
                    else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_options_border)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        findViewById<Button>(R.id.submit).text = "FINISH"
                    }
                    else{
                        findViewById<Button>(R.id.submit).text = "GO TO THE NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                findViewById<TextView>(R.id.optionOne).background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                findViewById<TextView>(R.id.optionTwo).background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                findViewById<TextView>(R.id.optionThree).background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                findViewById<TextView>(R.id.optionFour).background = ContextCompat.getDrawable(this, drawableView)
            }

        }
    }

    private fun selectedOptionView( tv: TextView , selectedOptionNum: Int){

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_options_border)
    }
}