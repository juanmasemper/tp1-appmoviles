package com.example.tp1_appmoviles

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val plataforma = intent.getStringExtra("plataforma_seleccionada")
        val preferencias = intent.getStringArrayListExtra("preferencias_seleccionadas") ?: arrayListOf()

        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
        }

        val textPlatform = findViewById<TextView>(R.id.textPlatform)
        val layoutContainer = findViewById<LinearLayout>(R.id.containerPreferencias)

        for (preferencia in preferencias) {
            val cardView = layoutInflater.inflate(R.layout.card_preference, layoutContainer, false)
            val img = cardView.findViewById<ImageView>(R.id.cardImage)
            val txt = cardView.findViewById<TextView>(R.id.cardText)

            txt.text = preferencia

            val imageName = preferencia.lowercase().replace(" ", "")
            val resId = resources.getIdentifier(imageName, "drawable", packageName)

            if (resId != 0) {
                img.setImageResource(resId)
            } else {
                img.setImageResource(R.drawable.imagen_por_defecto)
            }

            layoutContainer.addView(cardView)
        }

        textPlatform.text = getString(R.string.platform_selected, plataforma)
    }
}