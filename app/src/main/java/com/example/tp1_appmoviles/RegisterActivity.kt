package com.example.tp1_appmoviles

import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var backButton: Button

    private var isPasswordVisible = false
    private var isRepeatPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEditText = findViewById(R.id.editTextName)
        emailEditText = findViewById(R.id.editTextEmail)
        usernameEditText = findViewById(R.id.editTextUsername)
        passwordEditText = findViewById(R.id.editTextPassword)
        repeatPasswordEditText = findViewById(R.id.editTextRepeatPassword)
        submitButton = findViewById(R.id.buttonSubmit)
        backButton = findViewById(R.id.buttonBack)

        // Mostrar/Ocultar contraseña
        setupPasswordToggle(passwordEditText, isPassword = true)
        setupPasswordToggle(repeatPasswordEditText, isPassword = false)

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString()
            val repeatPassword = repeatPasswordEditText.text.toString()

            when {
                name.isEmpty() || email.isEmpty() || username.isEmpty() -> {
                    showToast("Todos los campos son obligatorios")
                }

                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    showToast("Ingresá un email válido")
                }

                password.length < 6 -> {
                    showToast("La contraseña debe tener al menos 6 caracteres")
                }

                password != repeatPassword -> {
                    showToast("Las contraseñas no coinciden")
                }

                else -> {
                    showToast("¡Registrado correctamente!")
                    finish()
                }
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupPasswordToggle(editText: EditText, isPassword: Boolean) {
        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_closed, 0)

        editText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                val drawable = editText.compoundDrawables[drawableEnd]
                if (drawable != null && event.rawX >= (editText.right - drawable.bounds.width())) {
                    val isVisible = if (isPassword) {
                        isPasswordVisible = !isPasswordVisible
                        isPasswordVisible
                    } else {
                        isRepeatPasswordVisible = !isRepeatPasswordVisible
                        isRepeatPasswordVisible
                    }

                    val selection = editText.selectionEnd
                    editText.inputType = if (isVisible)
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    else
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

                    val icon = if (isVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed
                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0)
                    editText.setSelection(selection)
                    v.performClick()
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
