package com.isengard.martinez_gallardo_pablo_ut5_lab1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.isengard.martinez_gallardo_pablo_ut5_lab1.R
import com.isengard.martinez_gallardo_pablo_ut5_lab1.model.Etapa

class EtapaAdapter(
    private val context: Context,
    private val listaEtapas: List<Etapa>
) : BaseAdapter() {

    override fun getCount(): Int = listaEtapas.size

    override fun getItem(position: Int): Any = listaEtapas[position]

    override fun getItemId(position: Int): Long = listaEtapas[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vista: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            vista = LayoutInflater.from(context).inflate(R.layout.item_etapa, parent, false)
            viewHolder = ViewHolder(
                ivMiniatura = vista.findViewById(R.id.ivMiniatura),
                tvNombre = vista.findViewById(R.id.tvNombreEtapa),
                tvPeligro = vista.findViewById(R.id.tvNivelPeligro)
            )
            vista.tag = viewHolder
        } else {
            vista = convertView
            viewHolder = vista.tag as ViewHolder
        }


        val etapa = listaEtapas[position]
        viewHolder.tvNombre.text = etapa.nombre
        viewHolder.tvPeligro.text = "Peligrosidad: ${etapa.peligro}"
        viewHolder.ivMiniatura.setImageResource(etapa.imagenResId)

        return vista
    }


    private class ViewHolder(
        val ivMiniatura: ImageView,
        val tvNombre: TextView,
        val tvPeligro: TextView
    )
}