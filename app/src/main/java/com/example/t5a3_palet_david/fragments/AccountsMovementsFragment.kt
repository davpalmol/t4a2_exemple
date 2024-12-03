package com.example.t5a3_palet_david.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.databinding.FragmentAccountsMovementsBinding
import com.example.t5a3_palet_david.pojo.Cuenta
import com.example.t5a3_palet_david.pojo.Movimiento

class AccountsMovementsFragment : Fragment() {

    private lateinit var binding: FragmentAccountsMovementsBinding
    private var moviminetos: List<Movimiento> = listOf()
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
        return binding.root
    }


}