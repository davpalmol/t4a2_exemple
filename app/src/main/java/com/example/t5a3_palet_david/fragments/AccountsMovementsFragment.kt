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
import com.example.t5a3_palet_david.adapters.MovimientosAdapter
import com.example.t5a3_palet_david.bd.MiBancoOperacional
import com.example.t5a3_palet_david.databinding.FragmentAccountsMovementsBinding
import com.example.t5a3_palet_david.pojo.Cliente
import com.example.t5a3_palet_david.pojo.Cuenta
import com.example.t5a3_palet_david.pojo.Movimiento

class AccountsMovementsFragment : Fragment() {

    private lateinit var movimientosAdapter: MovimientosAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration
    private lateinit var binding: FragmentAccountsMovementsBinding
    private lateinit var cuenta: Cuenta

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
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsMovementsBinding.inflate(inflater, container, false)
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)

        val listaMovimientos: List<Movimiento> = mbo?.getMovimientos(cuenta as Cuenta) as List<Movimiento>

        movimientosAdapter = MovimientosAdapter(listaMovimientos)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.recyclerViewMovimientos.apply {
            layoutManager = linearLayoutManager
            adapter = movimientosAdapter
            addItemDecoration(itemDecoration)
        }
        return binding.root
    }


}