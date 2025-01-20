package com.example.t5a3_palet_david.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.t5a3_palet_david.pojo.Cajero

@Dao
interface CajeroDAO {

    @Query("SELECT * FROM cajeros")
    fun getAllCajeros() : MutableList<Cajero>

    @Insert
    fun insertAll(CajeroEntityList : List<Cajero>)

    @Insert
    fun addCajero(cajeroEntity: Cajero)

    @Update
    fun updateCajero(cajeroEntity: Cajero)

    @Delete
    fun deleteCajero(cajeroEntity: Cajero)

}