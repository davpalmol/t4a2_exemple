package com.example.t5a3_palet_david.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.adapters.AccountsAdapter
import com.example.t5a3_palet_david.adapters.AccountsListener
import com.example.t5a3_palet_david.bd.MiBancoOperacional
import com.example.t5a3_palet_david.databinding.FragmentAccountsBinding
import com.example.t5a3_palet_david.pojo.Cliente
import com.example.t5a3_palet_david.pojo.Cuenta

private const val ARG_CLIENTE = "cliente"

class AccountsFragment : Fragment(), AccountsListener {

    private lateinit var binding: FragmentAccountsBinding
    private lateinit var cuentaAdapter: AccountsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var cliente: Cliente

    private lateinit var listener: AccountsListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable(ARG_CLIENTE) as Cliente
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)

        val listaCuentas: List<Cuenta> = mbo?.getCuentas(cliente as Cliente) as List<Cuenta>

        cuentaAdapter = AccountsAdapter(listaCuentas, this)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.recyclerViewCuentas.apply {
            layoutManager = linearLayoutManager
            adapter = cuentaAdapter
            addItemDecoration(itemDecoration)
        }

        return binding.root
    }

    fun setCuentasListener(listener: AccountsListener){
        this.listener = listener
    }



    companion object {
        @JvmStatic
        fun newInstance(c: Cliente) =
            AccountsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, c)
                }
            }
    }

    override fun onCuentaSeleccionada(cuenta: Cuenta) {
        if (listener != null) {
            listener.onCuentaSeleccionada(cuenta)
        }
    }
}