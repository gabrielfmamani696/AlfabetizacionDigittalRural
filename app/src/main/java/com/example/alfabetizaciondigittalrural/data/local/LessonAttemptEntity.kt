package com.example.alfabetizaciondigittalrural.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "intento_leccion",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id_leccion"],
            childColumns = ["id_leccion"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LessonAttemptEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_intento")
    val idIntento: Int = 0,

    @ColumnInfo(name = "id_usuario", index = true)
    val idUsuario: Int,

    @ColumnInfo(name = "id_leccion", index = true)
    val idLeccion: Int,

    @ColumnInfo(name = "calificacion_obtenida")
    val calificacionObtenida: Int,

    @ColumnInfo(name = "fecha_intento")
    val fechaIntento: LocalDateTime = LocalDateTime.now(),

    @ColumnInfo(name = "completado_exitosamente")
    val completadoExitosamente: Boolean
)
