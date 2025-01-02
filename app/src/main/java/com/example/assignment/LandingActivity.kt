package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        val toRegisterButton = findViewById<Button>(R.id.btnToRegister)
        val toLoginButton = findViewById<Button>(R.id.btnToLogin)

        //when user click register button, switch to register fragment
        toRegisterButton.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, RegisterFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //when user click login button, switch to login fragment
        toLoginButton.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, Login())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}

