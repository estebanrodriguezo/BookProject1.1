package com.esteban.rodriguezo.bookproject11.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.esteban.rodriguezo.bookproject11.databinding.ActivityRegisterBinding
import com.esteban.rodriguezo.bookproject11.ui.login.LogingActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        with(registerBinding) {
            registerBinding.registerButton.setOnClickListener {
                val email =emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val repPassword = repPasswordEditText.text.toString()

                if (password == repPassword){
                    val intent = Intent(this@RegisterActivity, LogingActivity::class.java)
                    intent.putExtra("email",email)
                    intent.putExtra("password",password)
                    startActivity(intent)
                }else
                    Toast.makeText(applicationContext, "Las Contraseñas deben ser iguales",Toast.LENGTH_SHORT).show()

            }
        }
    }
}
