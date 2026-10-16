package com.isengard.martinez_gallardo_pablo_ut5_lab1

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.isengard.martinez_gallardo_pablo_ut5_lab1.adapter.EtapaAdapter
import com.isengard.martinez_gallardo_pablo_ut5_lab1.model.Etapa

class MainActivity : AppCompatActivity() {

    private lateinit var lvEtapas: ListView
    private lateinit var listaEtapas: List<Etapa>
    private lateinit var adapter: EtapaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvEtapas = findViewById(R.id.lvEtapas)

        // Inicializamos los datos de la Comunidad del Anillo
        listaEtapas = obtenerEtapas()

        // Asignamos el adaptador personalizado
        adapter = EtapaAdapter(this, listaEtapas)
        lvEtapas.adapter = adapter

        // VITAL: Vinculamos el ListView al sistema de menús contextuales de Android.
        // Sin esta línea, las pulsaciones largas sobre las filas NO dispararán el menú contextual.
        registerForContextMenu(lvEtapas)
    }

    // ----------------------------------------------------
    // 1. MENÚ DE OPCIONES SUPERIOR (Action Bar / Toolbar)
    // ----------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu., menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_abandonar -> {
                mostrarDialogoAbandonar()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarDialogoAbandonar() {
        AlertDialog.Builder(this)
            .setTitle("Abandonar Misión")
            .setMessage("¿Deseas renunciar a la misión del Anillo y cerrar la aplicación?")
            .setPositiveButton("Abandonar") { _, _ ->
                // Cierra la aplicación de forma controlada y limpia toda la pila
                finishAffinity()
            }
            .setNegativeButton("Continuar Ruta", null)
            .show()
    }

    // ----------------------------------------------------
    // 2. MENÚ CONTEXTUAL FLOTANTE (Pulsación larga en ListView)
    // ----------------------------------------------------
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_contextual_etapa, menu)

        // Obtenemos la posición de la fila sobre la que se hizo la pulsación larga
        val info = menuInfo as? AdapterView.AdapterContextMenuInfo
        info?.let {
            val etapa = listaEtapas[it.position]
            menu?.setHeaderTitle("Opciones: ${etapa.nombre}")
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as? AdapterView.AdapterContextMenuInfo ?: return super.onContextItemSelected(item)
        val etapaSeleccionada = listaEtapas[info.position]

        return when (item.itemId) {
            R.id.action_inspeccionar_terreno -> {
                // Navegamos hacia DetalleActivity mediante un Intent Explícito
                // inyectando como extras el nombre de la etapa y su nivel de peligro
                val intent = Intent(this, DetalleActivity::class.java).apply {
                    putExtra("NOMBRE_ETAPA", etapaSeleccionada.nombre)
                    putExtra("PELIGRO_ETAPA", etapaSeleccionada.peligro)
                    putExtra("DESCRIPCION_ETAPA", etapaSeleccionada.descripcion)
                }
                startActivity(intent)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun obtenerEtapas(): List<Etapa> {
        return listOf(
            Etapa(1, "La Comarca (Bolsón Cerrado)", "Bajo", "Punto de partida de la misión.", R.drawable.etapa_comarca),
            Etapa(2, "Rivendel (Imladris)", "Bajo", "El Concilio de Elrond forja la Comunidad.", R.drawable.etapa_rivendel),
            Etapa(3, "Las Minas de Moria", "Extremo", "Tierra subterránea del Daño de Durin.", R.drawable.etapa_moria),
            Etapa(4, "Bosque de Lothlórien", "Medio", "Refugio élfico de la Dama Galadriel.", R.drawable.etapa_lorien),
            Etapa(5, "Amon Hen", "Alto", "Ruptura de la Comunidad y sacrificio de Boromir.", R.drawable.etapa_amon_hen),
            Etapa(6, "Las Ciénagas de los Muertos", "Alto", "Velas fétidas y caídos de la Guerra.", R.drawable.etapa_cienagas),
            Etapa(7, "Paso de Cirith Ungol", "Extremo", "Antro de Ella-Laraña y guardia orca.", R.drawable.etapa_cirith_ungol),
            Etapa(8, "El Monte del Destino", "Crítico", "Las Grietas del Destino en Gorgoroth.", R.drawable.etapa_monte_destino)
        )
    }
}