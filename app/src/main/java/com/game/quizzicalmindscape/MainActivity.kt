package com.game.quizzicalmindscape

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var welcomeText: TextView
    private lateinit var logoutButton: ImageButton
    private lateinit var switchAccountButton: ImageButton
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Use onBackPressedDispatcher to handle back button press
        onBackPressedDispatcher.addCallback(this) {
            stopQuiz()
        }

        auth = FirebaseAuth.getInstance()

        // Initialize GoogleSignInClient
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build() // Remove setHostedDomain() to allow any Google account
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Find views by ID
        welcomeText = findViewById(R.id.welcomeText)
        logoutButton = findViewById(R.id.logoutButton)
        switchAccountButton = findViewById(R.id.switchAccountButton)

        // Set welcome text with user's display name
        val currentUser = auth.currentUser
        currentUser?.let {
            val displayName = it.displayName
            val welcomeMessage = getString(R.string.welcome_message, displayName)
            welcomeText.text = welcomeMessage
        }

        // Set click listener for logout button
        logoutButton.setOnClickListener {
            logoutUser()
        }

        // Set click listener for switch account button
        switchAccountButton.setOnClickListener {
            switchAccount()
        }

        // Set click listener for Java quiz button
        val javaButton: ImageButton = findViewById(R.id.javaButton)
        javaButton.setOnClickListener {
            startJavaQuizActivity()
        }

        // Set click listener for Python quiz button
        val pythonButton: ImageButton = findViewById(R.id.pythonButton)
        pythonButton.setOnClickListener {
            startPythonQuizActivity()
        }

        // Set click listener for HTML quiz button
        val htmlButton: ImageButton = findViewById(R.id.htmlButton)
        htmlButton.setOnClickListener {
            startHtmlQuizActivity()
        }

        // Set click listener for C++ quiz button
        val cppButton: ImageButton = findViewById(R.id.cppButton)
        cppButton.setOnClickListener {
            startCppQuizActivity()
        }

    }

    private fun stopQuiz() {
        finish()
    }

    override fun onPause() {
        super.onPause()
        // Stop the quiz activity if it's running
        stopQuizActivity()
    }

    private fun stopQuizActivity() {
        // Determine which quiz activity to stop based on the current activity
        val currentActivity = this.javaClass.simpleName
        val intent: Intent = when (currentActivity) {
            "JavaQuizActivity" -> Intent(this, JavaQuizActivity::class.java)
            "PythonQuizActivity" -> Intent(this, PythonQuizActivity::class.java)
            "HtmlQuizActivity" -> Intent(this, HtmlQuizActivity::class.java)
            "CppQuizActivity" -> Intent(this, CppQuizActivity::class.java)
            else -> return // Do nothing if the current activity is unknown
        }
        intent.putExtra("STOP_QUIZ", true)
        startActivity(intent)
    }

    private fun logoutUser() {
        auth.signOut()
        // Redirect to LoginActivity or any other desired screen
        startActivity(Intent(this, LoginActivity::class.java))
        finish() // Finish current activity to prevent going back to it
    }

    private fun switchAccount() {
        // Sign out from the current Google account
        auth.signOut()

        // Sign out from Google Sign-In client as well
        googleSignInClient.signOut().addOnCompleteListener(this) {
            // Initiate Google Sign-In process again to switch account
            val signInIntent = googleSignInClient.signInIntent
            startActivity(signInIntent)
        }
    }

    private fun startJavaQuizActivity() {
        val intent = Intent(this, JavaQuizActivity::class.java)
        startActivity(intent)
    }

    private fun startPythonQuizActivity() {
        val intent = Intent(this, PythonQuizActivity::class.java)
        startActivity(intent)
    }

    private fun startHtmlQuizActivity() {
        val intent = Intent(this, HtmlQuizActivity::class.java)
        startActivity(intent)
    }

    private fun startCppQuizActivity() {
        val intent = Intent(this, CppQuizActivity::class.java)
        startActivity(intent)
    }
}
