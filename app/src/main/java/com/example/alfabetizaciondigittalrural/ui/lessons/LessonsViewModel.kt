package com.example.alfabetizaciondigittalrural.ui.lessons

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.alfabetizaciondigittalrural.data.local.LessonEntity
import com.example.alfabetizaciondigittalrural.data.repository.EducationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class LessonsViewModel(private val educationRepository: EducationRepository) : ViewModel() {

    val lessons: StateFlow<List<LessonEntity>> = educationRepository.getAllLessons()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun shareLesson(context: Context, lesson: LessonEntity) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "¡Mira esta lección: ${lesson.titulo} por ${lesson.autorOriginal}!")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    fun deleteLesson(lesson: LessonEntity) {
        viewModelScope.launch {
            educationRepository.deleteLesson(lesson)
        }
    }

    // Helper for verification/demo purposes
    fun addDummyLesson() {
        viewModelScope.launch {
            val dummy = LessonEntity(
                titulo = "Aprende a usar WhatsApp",
                tema = "Comunicación",
                autorOriginal = "Sistema",
                uuidAutorOriginal = UUID.randomUUID().toString(),
                imagenRuta = null // Placeholder for now
            )
            educationRepository.insertLesson(dummy)
        }
    }

    companion object {
        fun provideFactory(
            educationRepository: EducationRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LessonsViewModel(educationRepository) as T
            }
        }
    }
}
