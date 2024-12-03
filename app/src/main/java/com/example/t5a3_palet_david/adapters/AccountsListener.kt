package com.example.t5a3_palet_david.adapters

import com.example.t5a3_palet_david.pojo.Cuenta

interface AccountsListener {
    fun onCuentaSeleccionada(cuenta: Cuenta)
}