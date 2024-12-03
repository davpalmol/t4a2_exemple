package com.example.t5a3_palet_david.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.adapters.AccountsAdapter
import com.example.t5a3_palet_david.adapters.AccountsListener
import com.example.t5a3_palet_david.databinding.ActivityGlobalPositionBinding
import com.example.t5a3_palet_david.fragments.AccountsFragment
import com.example.t5a3_palet_david.fragments.AccountsMovementsFragment
import com.example.t5a3_palet_david.pojo.Cliente
import com.example.t5a3_palet_david.pojo.Cuenta

class GlobalPositionActivity : AppCompatActivity(), AccountsListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration
    private lateinit var binding: ActivityGlobalPositionBinding
    private lateinit var cuentasAdapter: AccountsAdapter
    private lateinit var frgAccounts: AccountsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Suponemos que las cuentas vienen de una base de datos o API, aquí se crea una lista ficticia de cuentas.
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente

        frgAccounts = AccountsFragment.newInstance(cliente as Cliente)

        supportFragmentManager.beginTransaction()
            .add(R.id.contenedorFragments, frgAccounts).commit()

        frgAccounts.setCuentasListener(this)
    }


    override fun onCuentaSeleccionada(cuenta: Cuenta) {
        if (cuenta != null) {
            val hayDetalle = findViewById<View?>(R.id.frag_movimiento) != null
            val movimientoFragment = AccountsMovementsFragment.newInstance(cuenta)

            if (hayDetalle) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_movimiento, movimientoFragment)
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contenedorFragments, movimientoFragment)
                    .commit()
            }
        }
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        // Si hay fragmentos en el backstack, regresa al anterior
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}
