package com.example.t5a3_palet_david.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.databinding.ItemCajerosBinding
import com.example.t5a3_palet_david.pojo.Cajero

class CajeroAdapter(private var cajeros: MutableList<Cajero>, private var listener: onClickListener) : RecyclerView.Adapter<CajeroAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCajerosBinding.bind(view)

        fun setListener(cajero: Cajero) {
            binding.root.setOnClickListener {
                listener.onClick(cajero)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cajeros, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("CajeroAdapter", "Número de cajeros en la lista: ${cajeros.size}")
        return cajeros.size
    }

    fun add(cajeros: List<Cajero>) {
        this.cajeros.addAll(cajeros)
        notifyDataSetChanged()
        Log.d("CajeroAdapter", "Cajeros agregados: ${cajeros.size}")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cajero = cajeros[position]
        holder.setListener(cajero)
        holder.binding.numAtm.text = cajero.id.toString()
        holder.binding.direccionAtm.text = cajero.direccion
        Log.d("CajeroAdapter", "Mostrando cajero: ${cajero.direccion}")
    }

    fun setCajeros(cajeros: MutableList<Cajero>) {
        this.cajeros.clear()
        this.cajeros.addAll(cajeros)
        notifyDataSetChanged()
        Log.d("CajeroAdapter", "Lista de cajeros actualizada en el RecyclerView")
    }
}
