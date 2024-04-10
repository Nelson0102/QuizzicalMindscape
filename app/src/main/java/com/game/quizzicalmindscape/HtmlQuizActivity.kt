package com.game.quizzicalmindscape

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HtmlQuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var option1RadioButton: RadioButton
    private lateinit var option2RadioButton: RadioButton
    private lateinit var option3RadioButton: RadioButton
    private lateinit var option4RadioButton: RadioButton
    private lateinit var nextButton: Button
    private lateinit var questionNumberTextView: TextView

    private val quizQuestions = listOf(
        QuizQuestion(
            "What does HTML stand for?",
            listOf("Hyper Text Markup Language", "Hyperlinks and Text Markup Language", "Home Tool Markup Language", "Hyper Tool Multi Language"),
            0
        ),
        QuizQuestion(
            "Which tag is used to define an unordered list in HTML?",
            listOf("<ul>", "<ol>", "<li>", "<list>"),
            0
        ),
        QuizQuestion(
            "Which HTML attribute is used to define inline styles?",
            listOf("style", "font", "class", "styles"),
            0
        ),
        QuizQuestion(
            "What is the correct HTML element for inserting a line break?",
            listOf("<br>", "<lb>", "<break>", "<line>"),
            0
        ),
        QuizQuestion(
            "Which character is used to indicate the end of an HTML tag?",
            listOf(":", "/", ".", "!"),
            1
        )
    )

    private var currentQuestionIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_htmlquiz)

        // Initialize views
        questionTextView = findViewById(R.id.questionTextView)
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup)
        option1RadioButton = findViewById(R.id.option1RadioButton)
        option2RadioButton = findViewById(R.id.option2RadioButton)
        option3RadioButton = findViewById(R.id.option3RadioButton)
        option4RadioButton = findViewById(R.id.option4RadioButton)
        nextButton = findViewById(R.id.nextButton)
        questionNumberTextView = findViewById(R.id.questionNumberTextView)

        displayQuestion()

        nextButton.setOnClickListener {
            val selectedOption = getSelectedOption()
            if (selectedOption != -1) {
                // Move to the next question
                currentQuestionIndex++
                if (currentQuestionIndex < quizQuestions.size) {
                    // Check answer
                    val currentQuestion = quizQuestions[currentQuestionIndex - 1]
                    if (selectedOption == currentQuestion.correctOptionIndex) {
                        // Correct answer
                        correctAnswers++
                    }
                    // Display next question
                    displayQuestion()
                } else {
                    // Quiz completed, launch ScoreActivity
                    launchScoreActivity()
                }
            } else {
                // No option selected
                Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayQuestion() {
        // Clear checked state of the RadioGroup
        optionsRadioGroup.clearCheck()

        val currentQuestion = quizQuestions[currentQuestionIndex]
        questionTextView.text = currentQuestion.question

        // Update question number text dynamically
        val questionNumberText = "Question ${currentQuestionIndex + 1} of ${quizQuestions.size}"
        questionNumberTextView.text = questionNumberText

        // Set options text
        val options = currentQuestion.options
        option1RadioButton.text = options[0]
        option2RadioButton.text = options[1]
        option3RadioButton.text = options[2]
        option4RadioButton.text = options[3]
    }

    private fun getSelectedOption(): Int {
        val selectedOptionId = optionsRadioGroup.checkedRadioButtonId
        return when (selectedOptionId) {
            R.id.option1RadioButton -> 0
            R.id.option2RadioButton -> 1
            R.id.option3RadioButton -> 2
            R.id.option4RadioButton -> 3
            else -> -1
        }
    }

    private fun launchScoreActivity() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("SCORE", correctAnswers)
        startActivity(intent)
        finish() // Finish the HtmlQuizActivity to prevent going back to it
    }

    data class QuizQuestion(
        val question: String,
        val options: List<String>,
        val correctOptionIndex: Int
    )
}
