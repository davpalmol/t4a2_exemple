package com.example.t5a3_palet_david.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.adapters.MovimientosAdapter
import com.example.t5a3_palet_david.bd.MiBancoOperacional
import com.example.t5a3_palet_david.databinding.ActivityMovimientosBinding
import com.example.t5a3_palet_david.pojo.Cliente
import com.example.t5a3_palet_david.pojo.Cuenta
import com.example.t5a3_palet_david.pojo.Movimiento

class MovimientosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovimientosBinding
    private lateinit var movimientosAdapter: MovimientosAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovimientosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recibimos el cliente y sus cuentas desde el Intent
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        val cuentas = mbo?.getCuentas(cliente) as List<Cuenta>

        // Verificamos si hay cuentas
        if (cuentas.isEmpty()) {
            Toast.makeText(this, "No tienes cuentas registradas", Toast.LENGTH_SHORT).show()
            return
        }

        // Configuramos el Spinner con las cuentas
        val numerosDeCuenta = cuentas.map { cuenta ->
            cuenta.getNumeroCuenta() ?: "sin numero"
        }

        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, numerosDeCuenta)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCuentas.adapter = adapterSpinner

        // Configuración del RecyclerView
        movimientosAdapter = MovimientosAdapter(arrayListOf()) // Inicialmente sin movimientos
        linearLayoutManager = LinearLayoutManager(this)
        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.recyclerViewMovimientos.apply {
            layoutManager = linearLayoutManager
            adapter = movimientosAdapter
            addItemDecoration(itemDecoration)
        }

        //Configuracion spinner
        binding.spinnerCuentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val cuentaSeleccionada = cuentas[position] //Objeto cuenta correpondiente
                movimientosAdapter = MovimientosAdapter(mbo?.getMovimientos(cuentaSeleccionada) as List<Movimiento>)
                binding.recyclerViewMovimientos.adapter = movimientosAdapter // actualiza el adapter con los movimientos de la cuenta
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // nada
            }

        }

        //COnfigurar bordes para el disenyo
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewMovimientos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}

