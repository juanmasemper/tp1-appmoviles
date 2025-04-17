package com.example.tp1_appmoviles

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.editTextUsername)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)
        registerButton = findViewById(R.id.buttonRegister)

        // Acceso al sistema
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            if (username == "Juan Torres" && password == "1234utn") {
                startActivity(Intent(this, WelcomeActivity::class.java))
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        // Ir a pantalla de registro
        registerButton.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }

        // Mostrar/ocultar contraseña con icono
        passwordEditText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2 // Ícono al final del campo
                val drawable = passwordEditText.compoundDrawables[drawableEnd]

                if (drawable != null && event.rawX >= (passwordEditText.right - drawable.bounds.width())) {
                    isPasswordVisible = !isPasswordVisible
                    val selection = passwordEditText.selectionEnd

                    // Alternar tipo de texto e ícono
                    passwordEditText.inputType = if (isPasswordVisible) {
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    } else {
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    }

                    passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0,
                        if (isPasswordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed,
                        0
                    )

                    passwordEditText.setSelection(selection)

                    // Buena práctica para accesibilidad
                    v.performClick()
                    return@setOnTouchListener true
                }
            }
            false
        }
    }
}
