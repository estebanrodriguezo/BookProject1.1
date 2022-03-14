package com.esteban.rodriguezo.bookproject11.ui.splash


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esteban.rodriguezo.bookproject11.databinding.ActivitySplashBinding
import com.esteban.rodriguezo.bookproject11.ui.bottom.BottomActivity
import com.esteban.rodriguezo.bookproject11.ui.login.LogingActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.concurrent.timerTask


class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
        auth = Firebase.auth

        val timer = Timer()
        timer.schedule(
            timerTask {
                if (auth.currentUser == null) goToLoginActivity() else goToMainActivity()
            }, 2000
        )
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LogingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, BottomActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}