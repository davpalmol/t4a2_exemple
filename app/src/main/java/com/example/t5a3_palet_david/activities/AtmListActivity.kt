package com.example.t5a3_palet_david.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t5a3_palet_david.CajeroApplication
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.adapters.CajeroAdapter
import com.example.t5a3_palet_david.adapters.onClickListener
import com.example.t5a3_palet_david.databinding.ActivityAtmListBinding
import com.example.t5a3_palet_david.pojo.Cajero

class AtmListActivity : AppCompatActivity(), onClickListener {

    private lateinit var binding: ActivityAtmListBinding
    private lateinit var mAdapter: CajeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        val cajerosEntityLists: List<Cajero> = listOf(
            Cajero("Carrer del Clariano, 1, 46021 Valencia, España", 39.4760077, -0.3524475, ""),
            Cajero("Avinguda del Cardenal Benlloch, 65, 46021 Valencia, España", 39.4710366, -0.3547525, ""),
            Cajero("Av. del Port, 237, 46011 Valencia, España", 39.4616199, -0.3376299, ""),
            Cajero("Carrer del Batxiller, 6, 46010 Valencia, España", 39.4826729, -0.3639119, ""),
        )

        Thread {
            val cajeroDao = CajeroApplication.database.cajeroDao()

            // Verificar si hay datos en la BD antes de insertarlos
            val existingCajeros = cajeroDao.getAllCajeros()
            Log.d("AtmListActivity", "Cajeros en la BD antes de insertar: ${existingCajeros.size}")

            if (existingCajeros.isEmpty()) {
                cajeroDao.insertAll(cajerosEntityLists)
                Log.d("AtmListActivity", "Datos insertados en la BD")
            }

            // Recuperar los cajeros de la base de datos
            val cajerosDesdeBD = cajeroDao.getAllCajeros()
            Log.d("AtmListActivity", "Cajeros recuperados de la BD: ${cajerosDesdeBD.size}")

            // Actualizar la UI en el hilo principal
            runOnUiThread {
                if (cajerosDesdeBD.isNotEmpty()) {
                    mAdapter.setCajeros(cajerosDesdeBD.toMutableList())
                    Log.d("AtmListActivity", "Datos enviados al RecyclerView")
                } else {
                    Log.e("AtmListActivity", "No se encontraron datos en la BD después de la inserción")
                }
            }
        }.start()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        mAdapter = CajeroAdapter(mutableListOf(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = mAdapter
        Log.d("AtmListActivity", "RecyclerView configurado")
    }

    override fun onClick(cajero: Cajero) {
        Log.d("AtmListActivity", "Cajero clickeado: ${cajero.direccion}")
    }
}
