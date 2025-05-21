package com.example.bobabliss

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Create or open SharedPreferences
        sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Save to SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("password", password)
                editor.apply()

                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                // 👉 Navigate to TeaMenuActivity
                val intent = Intent(this, TeaMenuActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter both username and password.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
