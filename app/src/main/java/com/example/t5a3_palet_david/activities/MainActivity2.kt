package com.example.t5a3_palet_david.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.t5a3_palet_david.R
import com.example.t5a3_palet_david.pojo.Cliente

class MainActivity2 : AppCompatActivity() {

    private lateinit var btnPosicionGlobal: Button
    private lateinit var btnMovimientos: Button
    private lateinit var btnTransfencias: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuramos la vista
        setContentView(R.layout.activity_main2)

        // Recibimos el cliente desde el Intent
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente

        // Inicializamos los botones
        btnPosicionGlobal = findViewById(R.id.btnPosicionGlobal)
        btnMovimientos = findViewById(R.id.btnMovimientos)
        btnTransfencias = findViewById(R.id.btnTransferencias)

        // Configuramos los listeners para los botones

        btnPosicionGlobal.setOnClickListener {
            // Crear un Intent para abrir la pantalla de "Posición Global"
            val intent = Intent(this, GlobalPositionActivity::class.java)
            // Pasamos el cliente al Intent para que pueda acceder a sus cuentas
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        btnMovimientos.setOnClickListener {
            // Crear un Intent para abrir la pantalla de "Movimientos"
            val intent = Intent(this, MovimientosActivity::class.java)
            // Pasamos el cliente al Intent para que pueda acceder a sus cuentas
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        btnTransfencias.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }
    }
}
