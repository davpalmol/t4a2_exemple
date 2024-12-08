package com.example.t5a3_palet_david.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.adapters.AccountsListener
import com.example.t5a3_palet_david.fragments.AccountsFragment
import com.example.t5a3_palet_david.fragments.AccountsMovementsFragment
import com.example.t5a3_palet_david.pojo.Cliente
import com.example.t5a3_palet_david.pojo.Cuenta

class GlobalPositionDetailsActivity : AppCompatActivity(), AccountsListener {

    private lateinit var cliente: Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_position_details)

        // Obtener el cliente desde el intent, si es necesario
        cliente = intent.getSerializableExtra("Cliente") as Cliente

        // Mostrar el fragmento de cuentas
        if (savedInstanceState == null) {
            val accountsFragment = AccountsFragment.newInstance(cliente)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag_movimiento, accountsFragment)
                .commit()
        }
    }

    // Implementación de la interfaz para manejar la selección de cuenta
    override fun onCuentaSeleccionada(cuenta: Cuenta) {
        val movimientoFragment = AccountsMovementsFragment.newInstance(cuenta)

        // Reemplazar el fragmento de movimientos en el contenedor correspondiente
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_movimiento, movimientoFragment)
            .addToBackStack(null) // Permite volver a la lista de cuentas
            .commit()
    }
}
