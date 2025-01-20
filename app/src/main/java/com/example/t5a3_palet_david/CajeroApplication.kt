package com.example.t5a3_palet_david

import android.app.Application
import androidx.room.Room
import com.example.t5a3_palet_david.bd.CajeroDatabase

class CajeroApplication : Application() {
    companion object {
        lateinit var database: CajeroDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, CajeroDatabase::class.java,
            "CajeroDatabase")
            .build()
    }
}