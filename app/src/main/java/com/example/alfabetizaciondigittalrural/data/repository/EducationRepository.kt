package com.example.alfabetizaciondigittalrural.data.repository

import com.example.alfabetizaciondigittalrural.data.local.EducationDao
import com.example.alfabetizaciondigittalrural.data.local.LessonEntity
import kotlinx.coroutines.flow.Flow

class EducationRepository(private val educationDao: EducationDao) {
    
    fun getAllLessons(): Flow<List<LessonEntity>> = educationDao.getAllLessons()

    suspend fun insertLesson(lesson: LessonEntity) {
        educationDao.insertLesson(lesson)
    }

    suspend fun deleteLesson(lesson: LessonEntity) {
        educationDao.deleteLesson(lesson)
    }
}
