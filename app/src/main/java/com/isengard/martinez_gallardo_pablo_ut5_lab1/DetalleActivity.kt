package com.isengard.martinez_gallardo_pablo_ut5_lab1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        // Habilitamos la flecha de navegación hacia atrás en la Action Bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Inspección de Terreno"

        val tvTitulo = findViewById<TextView>(R.id.tvDetalleTitulo)
        val tvPeligro = findViewById<TextView>(R.id.tvDetallePeligro)
        val tvDescripcion = findViewById<TextView>(R.id.tvDetalleDescripcion)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // El objeto intent (en minúscula) ya viene integrado en la actividad.
        // Mediante getStringExtra("CLAVE") extraemos los datos empaquetados en el saco de extras.
        val nombre = intent.getStringExtra("NOMBRE_ETAPA") ?: "Etapa Desconocida"
        val peligro = intent.getStringExtra("PELIGRO_ETAPA") ?: "Desconocido"
        val descripcion = intent.getStringExtra("DESCRIPCION_ETAPA") ?: "Sin descripción histórica disponible."

        tvTitulo.text = nombre
        tvPeligro.text = "Nivel de Peligro: $peligro"
        tvDescripcion.text = descripcion

        btnVolver.setOnClickListener {
            // finish() cierra esta actividad y vuelve a la anterior en la pila (MainActivity)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}