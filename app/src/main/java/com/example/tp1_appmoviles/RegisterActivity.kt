package com.example.tp1_appmoviles

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var backButton: Button // Nuevo botón

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEditText = findViewById(R.id.editTextName)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        repeatPasswordEditText = findViewById(R.id.editTextRepeatPassword)
        submitButton = findViewById(R.id.buttonSubmit)
        backButton = findViewById(R.id.buttonBack) // Referencia al botón

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()
            val repeatPassword = repeatPasswordEditText.text.toString()

            when {
                name.isEmpty() || email.isEmpty() -> {
                    Toast.makeText(this, "Nombre y Email no pueden estar vacíos", Toast.LENGTH_SHORT).show()
                }
                password.length < 6 -> {
                    Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                }
                password != repeatPassword -> {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "¡Registrado correctamente!", Toast.LENGTH_SHORT).show()
                    finish() // Vuelve al login
                }
            }
        }

        backButton.setOnClickListener {
            finish() // Vuelve al login sin hacer nada
        }
    }
}
