package com.example.t5a3_palet_david.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t5a3_palet_david.R

class TransferActivity : AppCompatActivity() {

    private lateinit var spinner2: Spinner
    private lateinit var etNumeroCuenta: EditText
    private lateinit var etImporte: EditText
    private lateinit var switchJustifiante: CheckBox
    private lateinit var btnMostrarMensaje: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transfer)

        spinner2 = findViewById(R.id.spinner2Trans)
        etNumeroCuenta = findViewById(R.id.etNumeroCuentaTrans)
        etImporte = findViewById(R.id.etCantidadTrans)
        switchJustifiante = findViewById(R.id.justificanteTrans)
        btnMostrarMensaje = findViewById(R.id.butEnviarTrans)

        val radioGroup = findViewById<RadioGroup>(R.id.radiogroupTrans)
        val propiaRadioButton = findViewById<RadioButton>(R.id.propiaTrans)

        //cuenta propia por defecto
        propiaRadioButton.isChecked = true;

        //configurar el listener
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.propiaTrans) {
                spinner2.visibility = View.VISIBLE
                etNumeroCuenta.visibility = View.GONE
            } else if (checkedId == R.id.ajenaTrans) {
                spinner2.visibility = View.GONE
                etNumeroCuenta.visibility = View.VISIBLE
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spNumeros:Spinner = findViewById<Spinner>(R.id.spinnerTrans)
        val numeros = resources.getStringArray(R.array.numeros_cuentas)
        val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,numeros)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNumeros.adapter = adapter

        val spNumeros2:Spinner = spinner2
        val numeros2 = resources.getStringArray(R.array.numeros_cuentas)
        val adapter2= ArrayAdapter(this,android.R.layout.simple_spinner_item,numeros2)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNumeros2.adapter = adapter2

        val spDivisas:Spinner = findViewById<Spinner>(R.id.divisasTrans)
        val divisas = resources.getStringArray(R.array.strdivisas)
        val adapter3= ArrayAdapter(this,android.R.layout.simple_spinner_item,divisas)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spDivisas.adapter = adapter3

        btnMostrarMensaje.setOnClickListener {
            val cuentaOrigen = spNumeros.selectedItem.toString()
            val cuentaDestino = if (radioGroup.checkedRadioButtonId == R.id.propiaTrans) {
                spinner2.selectedItem.toString()
            } else {
                etNumeroCuenta.text.toString()
            }
            val importe = etImporte.text.toString()
            val divisa = spDivisas.selectedItem.toString()
            val justificante = if (switchJustifiante.isChecked) "Enviar Justificante" else ""

            val mensaje = """
                Cuenta origen: $cuentaOrigen
                Cuenta destino: $cuentaDestino
                Importe: $importe $divisa
                $justificante
             """.trimIndent()

            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

        }

    }
}