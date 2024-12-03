package com.example.t5a3_palet_david.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.databinding.ItemCuentaBinding
import com.example.t5a3_palet_david.databinding.ItemListAccountsBinding
import com.example.t5a3_palet_david.pojo.Cuenta

class AccountsAdapter(
    private val cuentas: List<Cuenta>,
    private val listener: AccountsListener
): RecyclerView.Adapter<AccountsAdapter.ViewHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_accounts,
            parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cuenta = cuentas.get(position)
        with(holder){
            binding.textViewNumeroCuenta.text = cuenta.getNumeroCuenta()
            binding.textViewSaldo.text = cuenta.getSaldoActual().toString()
            binding.textViewBanco.text = cuenta.getBanco()
        }
    }

    override fun getItemCount(): Int = cuentas.size

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding = ItemListAccountsBinding.bind(view)
        fun setListener(cuenta: Cuenta){
            binding.root.setOnClickListener {
                listener.onCuentaSeleccionada(cuenta)
            }
        }
    }

}

