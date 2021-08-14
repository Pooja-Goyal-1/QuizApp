package com.example.quiz

object Constants{

    const val USER_NAME: String = "username"
    const val TOTAL_QUESTIONS: String = "totalQuestions"
    const val CORRECT_ANSWERS: String = "correctAnswers"

    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        //1
        val que1 = Question(1,
            "What country does this flag belong to?",
            R.drawable.india,
            "India",
            "US",
            "Canada",
            "Australia",
            1
        )
        questionList.add(que1)

        //2
        val que2 = Question(2,
            "What country does this flag belong to?",
            R.drawable.us,
            "India",
            "US",
            "Canada",
            "Australia",
            2
        )
        questionList.add(que2)

        //3
        val que3 = Question(3,
            "What country does this flag belong to?",
            R.drawable.canada,
            "India",
            "US",
            "Canada",
            "Australia",
            3
        )
        questionList.add(que3)

        //4
        val que4 = Question(4,
            "What country does this flag belong to?",
            R.drawable.australia,
            "India",
            "US",
            "China",
            "Australia",
            4
        )
        questionList.add(que4)

        //5
        val que5= Question(5,
            "What country does this flag belong to?",
            R.drawable.china,
            "India",
            "China",
            "Canada",
            "Australia",
            2
        )
        questionList.add(que5)
        return questionList
    }
}