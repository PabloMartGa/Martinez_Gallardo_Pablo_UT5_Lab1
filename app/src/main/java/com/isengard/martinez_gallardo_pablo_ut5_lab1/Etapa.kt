package com.isengard.martinez_gallardo_pablo_ut5_lab1.model

data class Etapa(
    val id: Int,
    val nombre: String,
    val peligro: String,
    val descripcion: String,
    val imagenResId: Int,
    val region: String = "Tierra Media"
)