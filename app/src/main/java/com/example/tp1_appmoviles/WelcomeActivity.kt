package com.example.tp1_appmoviles

import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var imagePlatform: ImageView
    private lateinit var radioAndroid: RadioButton
    private lateinit var radioIOS: RadioButton
    private lateinit var checkOther: CheckBox
    private lateinit var editOther: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Vinculación de elementos visuales
        imagePlatform = findViewById(R.id.imagePlatform)
        radioAndroid = findViewById(R.id.radioAndroid)
        radioIOS = findViewById(R.id.radioIOS)
        checkOther = findViewById(R.id.checkOther)
        editOther = findViewById(R.id.editTextOtherPreference)
        val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)

        // Mostrar logo según plataforma seleccionada
        radioAndroid.setOnClickListener {
            imagePlatform.setImageResource(R.drawable.android_logo)
        }

        radioIOS.setOnClickListener {
            imagePlatform.setImageResource(R.drawable.ios_logo)
        }

        // Mostrar el campo "Otra" si se tilda
        checkOther.setOnCheckedChangeListener { _, isChecked ->
            editOther.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        // Confirmar selección al tocar el botón
        buttonConfirm.setOnClickListener {
            val plataforma = when {
                radioAndroid.isChecked -> "Android"
                radioIOS.isChecked -> "iOS"
                else -> "No seleccionada"
            }

            val preferencias = mutableListOf<String>()
            if (findViewById<CheckBox>(R.id.checkProgramming).isChecked) preferencias.add("Programación")
            if (findViewById<CheckBox>(R.id.checkNetworking).isChecked) preferencias.add("Redes")
            if (findViewById<CheckBox>(R.id.checkSecurity).isChecked) preferencias.add("Seguridad")
            if (findViewById<CheckBox>(R.id.checkHardware).isChecked) preferencias.add("Hardware")
            if (checkOther.isChecked) {
                val otra = editOther.text.toString().trim()
                if (otra.isNotEmpty()) preferencias.add("Otra: $otra")
                else preferencias.add("Otra")
            }

            val mensaje = "Plataforma: $plataforma\nPreferencias: ${preferencias.joinToString(", ")}"
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()

            val intent = Intent(this, PreferenceActivity::class.java)
            intent.putExtra("plataforma_seleccionada", plataforma)
            intent.putExtra("preferencias_seleccionadas", preferencias.joinToString(", "))
            startActivity(intent)
        }
    }
}
