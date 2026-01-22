package com.example.alfabetizaciondigittalrural.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "leccion")
data class LessonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_leccion")
    val idLeccion: Int = 0,

    @ColumnInfo(name = "uuid_global")
    val uuidGlobal: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "titulo")
    val titulo: String,

    @ColumnInfo(name = "tema")
    val tema: String,

    @ColumnInfo(name = "autor_original")
    val autorOriginal: String, // Alias of creator

    @ColumnInfo(name = "fecha_creacion")
    val fechaCreacion: LocalDateTime = LocalDateTime.now(),

    @ColumnInfo(name = "es_creada_por_usuario")
    val esCreadaPorUsuario: Boolean = false,

    @ColumnInfo(name = "uuid_autor_original")
    val uuidAutorOriginal: String,

    @ColumnInfo(name = "imagen_ruta")
    val imagenRuta: String? = null
)
