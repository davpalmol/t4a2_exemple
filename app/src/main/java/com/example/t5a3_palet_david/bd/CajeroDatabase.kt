package com.example.t5a3_palet_david.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.t5a3_palet_david.dao.CajeroDAO
import com.example.t5a3_palet_david.pojo.Cajero

@Database(entities = arrayOf(Cajero::class), version = 1)
abstract class CajeroDatabase : RoomDatabase(){
    abstract fun cajeroDao():CajeroDAO
}