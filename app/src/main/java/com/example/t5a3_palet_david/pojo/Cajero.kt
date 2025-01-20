package com.example.t5a3_palet_david.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cajeros")
data class Cajero(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val direccion: String,
    val latitud: Double,
    val longitud: Double,
    val zoom: String = ""
) {
    constructor(direccion: String, latitud: Double, longitud: Double, zoom: String) :
            this(0, direccion, latitud, longitud, zoom)  // Este constructor usa id = 0
}


