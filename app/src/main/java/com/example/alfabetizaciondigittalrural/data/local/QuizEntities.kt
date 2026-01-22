package com.example.alfabetizaciondigittalrural.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cuestionario",
    foreignKeys = [
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id_leccion"],
            childColumns = ["id_leccion"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuizEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_cuestionario")
    val idCuestionario: Int = 0,

    @ColumnInfo(name = "id_leccion", index = true)
    val idLeccion: Int,

    @ColumnInfo(name = "titulo_quiz")
    val tituloQuiz: String
)

@Entity(
    tableName = "pregunta",
    foreignKeys = [
        ForeignKey(
            entity = QuizEntity::class,
            parentColumns = ["id_cuestionario"],
            childColumns = ["id_cuestionario"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_pregunta")
    val idPregunta: Int = 0,

    @ColumnInfo(name = "id_cuestionario", index = true)
    val idCuestionario: Int,

    @ColumnInfo(name = "enunciado")
    val enunciado: String
)

@Entity(
    tableName = "respuesta",
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id_pregunta"],
            childColumns = ["id_pregunta"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_respuesta")
    val idRespuesta: Int = 0,

    @ColumnInfo(name = "id_pregunta", index = true)
    val idPregunta: Int,

    @ColumnInfo(name = "texto_opcion")
    val textoOpcion: String,

    @ColumnInfo(name = "es_correcta")
    val esCorrecta: Boolean
)
