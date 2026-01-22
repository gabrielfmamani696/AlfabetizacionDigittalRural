package com.example.alfabetizaciondigittalrural.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface EducationDao {
    // Lesson Operations
    @Query("SELECT * FROM leccion")
    fun getAllLessons(): Flow<List<LessonEntity>>

    @Query("SELECT * FROM leccion WHERE id_leccion = :lessonId")
    suspend fun getLessonById(lessonId: Int): LessonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonEntity): Long

    @androidx.room.Delete
    suspend fun deleteLesson(lesson: LessonEntity)

    // Card Operations
    @Query("SELECT * FROM tarjeta WHERE id_leccion = :lessonId ORDER BY orden_secuencia ASC")
    fun getCardsForLesson(lessonId: Int): Flow<List<CardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(cards: List<CardEntity>)

    // Quiz Operations
    @Transaction
    @Query("SELECT * FROM cuestionario WHERE id_leccion = :lessonId")
    suspend fun getQuizForLesson(lessonId: Int): QuizEntity?

    @Query("SELECT * FROM pregunta WHERE id_cuestionario = :quizId")
    suspend fun getQuestionsForQuiz(quizId: Int): List<QuestionEntity>

    @Query("SELECT * FROM respuesta WHERE id_pregunta = :questionId")
    suspend fun getAnswersForQuestion(questionId: Int): List<AnswerEntity>
}
