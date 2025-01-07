package com.example.t5a3_palet_david.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.adapters.AccountsListener
import com.example.t5a3_palet_david.fragments.AccountsFragment
import com.example.t5a3_palet_david.fragments.AccountsMovementsFragment
import com.example.t5a3_palet_david.pojo.Cliente
import com.example.t5a3_palet_david.pojo.Cuenta
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.util.Log

class GlobalPositionDetailsActivity : AppCompatActivity(), AccountsListener {

    private lateinit var cuenta: Cuenta
    private var currentMovementsFragment: AccountsMovementsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_position_details)

        // Obtener el cliente desde el intent
        cuenta = intent.getSerializableExtra("Cuenta") as Cuenta
        Log.d("GlobalPositionDetails", "Cuenta recibida: $cuenta")

        // Mostrar el fragmento de cuentas al iniciar
        if (savedInstanceState == null) {
            val accountsMovementFragment = AccountsMovementsFragment.newInstance(cuenta)
            currentMovementsFragment = accountsMovementFragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, accountsMovementFragment)
                .commit()
            Log.d("GlobalPositionDetails", "Fragmento de movimientos cargado.")
        }

        // Configurar el BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            Log.d("GlobalPositionDetails", "Item seleccionado: ${item.itemId}")
            currentMovementsFragment?.let { fragment ->
                when (item.itemId) {
                    R.id.nav_all -> fragment.filterMovimientos("all")
                    R.id.nav_incomes -> fragment.filterMovimientos("income")
                    R.id.nav_expenses -> fragment.filterMovimientos("expense")
                    R.id.nav_transfers -> fragment.filterMovimientos("transfer")
                }
                Log.d("GlobalPositionDetails", "Filtro aplicado: ${item.itemId}")
            }
            true
        }
    }

    override fun onCuentaSeleccionada(cuenta: Cuenta) {
        Log.d("GlobalPositionDetails", "Cuenta seleccionada: $cuenta")
        val movimientoFragment = AccountsMovementsFragment.newInstance(cuenta)
        currentMovementsFragment = movimientoFragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, movimientoFragment)
            .addToBackStack(null)
            .commit()
        Log.d("GlobalPositionDetails", "Fragmento de movimientos reemplazado.")
    }
}
