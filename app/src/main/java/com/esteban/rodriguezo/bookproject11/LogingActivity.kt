package com.esteban.rodriguezo.bookproject11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.esteban.rodriguezo.bookproject11.databinding.ActivityLogingBinding
import kotlin.math.sin

class LogingActivity : AppCompatActivity() {

    private lateinit var  logingBinding: ActivityLogingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logingBinding = ActivityLogingBinding.inflate(layoutInflater)
        setContentView(logingBinding.root)

        var emailReceived : String? = ""
        var passwordReceived : String? = ""

        val credentials = intent.extras
        if (credentials != null){
            emailReceived = credentials.getString("email")
            passwordReceived = credentials.getString("password")
        }

        logingBinding.registerTextView.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        with(logingBinding){
            singInButton.setOnClickListener{
                val email=emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (email == emailReceived && password == passwordReceived && email.isNotEmpty() && password.isNotEmpty()){
                    val intent = Intent(this@LogingActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LogingActivity,"Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}