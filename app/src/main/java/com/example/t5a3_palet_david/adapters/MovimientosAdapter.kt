package com.example.t5a3_palet_david.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.databinding.ItemListMovementsBinding
import com.example.t5a3_palet_david.databinding.ItemMovimientoBinding
import com.example.t5a3_palet_david.pojo.Movimiento

class MovimientosAdapter(private var movimientos: List<Movimiento>) :
    RecyclerView.Adapter<MovimientosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movements, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movimiento = movimientos[position]
        holder.bind(movimiento)
    }

    override fun getItemCount(): Int = movimientos.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemListMovementsBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(movimiento: Movimiento) {
            binding.textViewDescripcion.text = movimiento.getDescripcion()
            binding.textViewImporte.text = movimiento.getImporte().toString()
        }
    }

    fun updateData(newMovimientos: List<Movimiento>) {
        movimientos = newMovimientos
        notifyDataSetChanged()
    }

}
