package com.example.t5a3_palet_david.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t5a3_palet_david.adapters.MovimientosAdapter
import com.example.t5a3_palet_david.bd.MiBancoOperacional
import com.example.t5a3_palet_david.databinding.FragmentAccountsMovementsBinding
import com.example.t5a3_palet_david.pojo.Cuenta
import com.example.t5a3_palet_david.pojo.Movimiento
import android.util.Log

class AccountsMovementsFragment : Fragment() {

    private lateinit var movimientosAdapter: MovimientosAdapter
    private lateinit var binding: FragmentAccountsMovementsBinding
    private lateinit var cuenta: Cuenta
    private lateinit var listaMovimientosOriginal: List<Movimiento>

    companion object {
        @JvmStatic
        fun newInstance(cuenta: Cuenta) =
            AccountsMovementsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("cuenta", cuenta)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cuenta = it.getSerializable("cuenta") as Cuenta
            Log.d("AccountsMovementsFragment", "Cuenta recibida: $cuenta")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountsMovementsBinding.inflate(inflater, container, false)

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)
        listaMovimientosOriginal = (mbo?.getMovimientos(cuenta) ?: emptyList()) as List<Movimiento>
        Log.d("AccountsMovementsFragment", "Movimientos cargados: ${listaMovimientosOriginal.size}")

        movimientosAdapter = MovimientosAdapter(listaMovimientosOriginal)
        binding.recyclerViewMovimientos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movimientosAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        return binding.root
    }

    fun filterMovimientos(filter: String) {
        val filteredList = when (filter) {
            "all" -> listaMovimientosOriginal
            "income" -> listaMovimientosOriginal.filter { it.getTipo() == 0 }
            "expense" -> listaMovimientosOriginal.filter { it.getTipo() == 1 }
            "transfer" -> listaMovimientosOriginal.filter { it.getTipo() == 2 }
            else -> listaMovimientosOriginal
        }
        Log.d("AccountsMovementsFragment", "Filtro aplicado: $filter, Movimientos filtrados: ${filteredList.size}")
        movimientosAdapter.updateData(filteredList)
    }
}
