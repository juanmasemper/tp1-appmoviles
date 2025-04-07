package com.example.tp1_appmoviles

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ImageView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import java.text.Normalizer

fun obtenerDescripcion(nombre: String): String {
    return when (nombre.lowercase()) {
        "programación" -> "La programación es el proceso de diseñar, escribir, probar y mantener el código fuente de aplicaciones y sistemas. A través del uso de lenguajes como Java, Python, C++ y otros, los programadores crean soluciones que automatizan tareas, resuelven problemas y permiten el funcionamiento de aplicaciones móviles, sitios web y software empresarial. Es una habilidad clave en la era digital."

        "redes" -> "El área de redes se enfoca en la interconexión de dispositivos y sistemas para compartir información. Incluye el diseño, configuración y mantenimiento de redes locales (LAN), redes amplias (WAN), Wi-Fi, y protocolos como TCP/IP. Las redes permiten la comunicación entre computadoras, el acceso a internet y la colaboración en línea en todo tipo de organizaciones."

        "seguridad" -> "La seguridad informática se encarga de proteger los sistemas, redes y datos frente a accesos no autorizados, ataques, daños o robos. Esto abarca técnicas como el cifrado, firewalls, autenticación, auditorías de seguridad y concientización sobre ciberamenazas. Es un campo crucial para garantizar la privacidad y el funcionamiento confiable de los entornos digitales."

        "hardware" -> "El hardware comprende todos los componentes físicos de una computadora o dispositivo electrónico, como el procesador, la memoria RAM, discos duros, placas madre, fuentes de poder y periféricos. El conocimiento del hardware es esencial para el armado, mantenimiento y reparación de equipos, así como para entender su rendimiento y compatibilidad."

        else -> "Esta preferencia no tiene una descripción específica. Sin embargo, representa un área de interés que puede ser explorada y desarrollada en función de tus gustos y necesidades. Te invitamos a investigar más o hablar con un docente o profesional para conocer más sobre este tema."
    }
}

fun normalizar(texto: String): String {
    return Normalizer.normalize(texto, Normalizer.Form.NFD)
        .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
        .replace("ñ", "n")
        .replace(" ", "")
}

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

            val imageName = normalizar(preferencia.lowercase())
            val resId = resources.getIdentifier(imageName, "drawable", packageName)

            if (resId != 0) {
                img.setImageResource(resId)
            } else {
                img.setImageResource(R.drawable.imagen_por_defecto)
            }

            val finalResId = if (resId != 0) resId else R.drawable.imagen_por_defecto

            cardView.setOnClickListener {
                val intent = Intent(this, PreferenceDetailActivity::class.java)
                intent.putExtra("nombre", preferencia)
                intent.putExtra("descripcion", obtenerDescripcion(preferencia))
                intent.putExtra("imagen", finalResId)
                startActivity(intent)
            }

            layoutContainer.addView(cardView)
        }

        textPlatform.text = plataforma
    }
}