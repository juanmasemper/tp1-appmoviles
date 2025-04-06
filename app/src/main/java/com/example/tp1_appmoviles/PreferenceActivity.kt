package com.example.tp1_appmoviles

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val plataforma = intent.getStringExtra("plataforma_seleccionada")
        val preferencias = intent.getStringExtra("preferencias_seleccionadas") ?: ""

        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
        }

        val textPlatform = findViewById<TextView>(R.id.textPlatform)
        val textPreferences = findViewById<TextView>(R.id.textPreferences)

        val imgProgramacion = findViewById<ImageView>(R.id.imgProgramacion)
        val imgRedes = findViewById<ImageView>(R.id.imgRedes)
        val imgSeguridad = findViewById<ImageView>(R.id.imgSeguridad)
        val imgHardware = findViewById<ImageView>(R.id.imgHardware)

        // Mostrar solo las imágenes que estén incluidas en la lista de preferencias seleccionadas
        imgProgramacion.visibility = if (preferencias.contains("Programación")) View.VISIBLE else View.GONE
        imgRedes.visibility = if (preferencias.contains("Redes")) View.VISIBLE else View.GONE
        imgSeguridad.visibility = if (preferencias.contains("Seguridad")) View.VISIBLE else View.GONE
        imgHardware.visibility = if (preferencias.contains("Hardware")) View.VISIBLE else View.GONE

        textPlatform.text = "Plataforma seleccionada: $plataforma"
        textPreferences.text = "Preferencias: $preferencias"
    }
}