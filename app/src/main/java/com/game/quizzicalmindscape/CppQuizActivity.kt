package com.game.quizzicalmindscape

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CppQuizActivity : AppCompatActivity() {

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
            "What is the output of the following code in C++?\n" +
                    "\n" +
                    "#include <iostream>\n" +
                    "using namespace std;\n" +
                    "int main() {\n" +
                    "    int x = 5;\n" +
                    "    cout << ++x;\n" +
                    "    return 0;\n" +
                    "}",
            listOf("5", "6", "Compilation error", "Undefined behavior"),
            1
        ),
        QuizQuestion(
            "Which data type in C++ is used to store a single character?",
            listOf("char", "string", "character", "int"),
            0
        ),
        QuizQuestion(
            "What is the correct way to declare a constant in C++?",
            listOf("const int constantValue = 10;", "final int constantValue = 10;", "int constantValue = 10;", "static const int constantValue = 10;"),
            0
        ),
        QuizQuestion(
            "Which of the following is NOT a valid C++ keyword?",
            listOf("main", "include", "void", "nullptr"),
            1
        ),
        QuizQuestion(
            "What is the output of the following code snippet?\n" +
                    "\n" +
                    "#include <iostream>\n" +
                    "using namespace std;\n" +
                    "int main() {\n" +
                    "    int x = 5;\n" +
                    "    if (x == 5) {\n" +
                    "        cout << \"x is 5\";\n" +
                    "    } else {\n" +
                    "        cout << \"x is not 5\";\n" +
                    "    }\n" +
                    "    return 0;\n" +
                    "}",
            listOf("x is 5", "x is not 5", "Compilation error", "Undefined behavior"),
            0
        )
    )

    private var currentQuestionIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cppquiz)

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
        finish() // Finish the CppQuizActivity to prevent going back to it
    }

    data class QuizQuestion(
        val question: String,
        val options: List<String>,
        val correctOptionIndex: Int
    )
}
