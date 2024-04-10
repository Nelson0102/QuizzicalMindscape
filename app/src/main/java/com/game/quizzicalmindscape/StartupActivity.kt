package com.game.quizzicalmindscape
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class StartupActivity : AppCompatActivity() {

    private val splashDelay: Long = 4000 // 4 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup) // assuming activity_startup.xml is your layout file
        // Use a delay to show the startup screen
        Handler(Looper.getMainLooper()).postDelayed({
            // Navigate to the login activity after the delay
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Finish the startup activity
        }, splashDelay)
    }
}

