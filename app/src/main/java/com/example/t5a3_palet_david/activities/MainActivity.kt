package com.example.t5a3_palet_david.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t5a3_palet_david.bd.MiBancoOperacional
import com.example.t5a3_palet_david.databinding.ActivityMainBinding
import com.example.t5a3_palet_david.pojo.Cliente

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textFieldDNI.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.layaoutDNI.error = null
            }
        }

        binding.textFieldPass.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.layaoutPass.error = null
            }
        }

        binding.btEntrar.setOnClickListener {
            val dni = "11111111A"
            val contrasenya = "1234"

            val cliente = Cliente()
            cliente.setNif(dni)
            cliente.setClaveSeguridad(contrasenya)

            val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

            //Obtindre el dni
            //val dni = binding.textFieldDNI.text.toString().trim()
            //val contrasenya = binding.textFieldPass.text.toString().trim()

            if (dni.isEmpty()) {
                binding.layaoutDNI.error = "El dni esta vacio"
            } else {
                binding.layaoutDNI.error = null;
            }

            if (contrasenya.isEmpty()) {
                binding.layaoutPass.error = "La contrasenya esta vacia"
            } else {
                binding.layaoutPass.error = null
            }

            if (dni.isNotEmpty() && contrasenya.isNotEmpty()) {

                val clienteLogeado = mbo?.login(cliente) ?: -1
                println("cuentas del cliente: " + mbo?.getCuentas(cliente))
                if (clienteLogeado == -1) {
                    Toast.makeText(this, "El cliente no se encuentra registrado", Toast.LENGTH_LONG).show()
                } else {
                    val intent = Intent(this, MainActivity2::class.java)
                    intent.putExtra("Cliente", clienteLogeado)
                    startActivity(intent)
                }
            }


        }

        binding.btSalir.setOnClickListener {
            finishAffinity()
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}