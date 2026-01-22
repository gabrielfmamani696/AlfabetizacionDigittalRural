package com.example.alfabetizaciondigittalrural.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tarjeta",
    foreignKeys = [
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id_leccion"],
            childColumns = ["id_leccion"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_tarjeta")
    val idTarjeta: Int = 0,

    @ColumnInfo(name = "id_leccion", index = true)
    val idLeccion: Int,

    @ColumnInfo(name = "orden_secuencia")
    val ordenSecuencia: Int,

    @ColumnInfo(name = "contenido_texto")
    val contenidoTexto: String, // Max chars as per RF-01

    @ColumnInfo(name = "tipo_fondo")
    val tipoFondo: String, // "COLOR_SOLIDO" or "SVG"

    @ColumnInfo(name = "data_fondo")
    val dataFondo: String // Hex or SVG Path
)
