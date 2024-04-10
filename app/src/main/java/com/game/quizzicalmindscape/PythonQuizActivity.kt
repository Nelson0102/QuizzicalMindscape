package com.game.quizzicalmindscape
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PythonQuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var option1RadioButton: RadioButton
    private lateinit var option2RadioButton: RadioButton
    private lateinit var option3RadioButton: RadioButton
    private lateinit var option4RadioButton: RadioButton
    private lateinit var nextButton: Button
    private lateinit var questionNumberTextView: TextView

    // Define Python quiz questions here
    private val quizQuestions = listOf(
        QuizQuestion(
            "What is the output of the following code?\n" +
                    "\n" +
                    "x = 5\n" +
                    "x += 3\n" +
                    "print(x)",
            listOf("5", "8", "3", "Syntax Error"),
            1
        ),
        QuizQuestion(
            "What does the 'len()' function do in Python?",
            listOf("Returns the length of a string or list", "Returns the largest item in an iterable", "Returns the smallest item in an iterable", "Returns the total sum of items in an iterable"),
            0
        ),
        QuizQuestion(
            "Which of the following data types is immutable in Python?",
            listOf("List", "Tuple", "Set", "Dictionary"),
            1
        ),
        QuizQuestion(
            "What is the correct way to declare a list in Python?",
            listOf("[1, 2, 3]", "{1, 2, 3}", "(1, 2, 3)", "{1: 'a', 2: 'b', 3: 'c'}"),
            0
        ),
        QuizQuestion(
            "Which statement is used to exit a loop in Python?",
            listOf("break", "stop", "end", "exit"),
            0
        )
    )

    private var currentQuestionIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pythonquiz)

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
        finish() // Finish the PythonQuizActivity to prevent going back to it
    }

    data class QuizQuestion(
        val question: String,
        val options: List<String>,
        val correctOptionIndex: Int
    )
}
