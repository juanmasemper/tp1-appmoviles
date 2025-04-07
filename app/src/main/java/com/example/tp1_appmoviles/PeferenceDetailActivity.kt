package com.example.tp1_appmoviles

import android.os.Bundle
import android.widget.Button

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PreferenceDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference_detail_activity)

        val nombre = intent.getStringExtra("nombre") ?: "Sin nombre"
        val descripcion = intent.getStringExtra("descripcion") ?: "Sin descripción"

        val txtTitulo = findViewById<TextView>(R.id.textoTitulo)
        val txtDescripcion = findViewById<TextView>(R.id.textoDescripcion)
        val btnBack = findViewById<Button>(R.id.buttonBackDetail)

        txtTitulo.text = nombre
        txtDescripcion.text = descripcion

        btnBack.setOnClickListener {
            finish()
        }
    }
}