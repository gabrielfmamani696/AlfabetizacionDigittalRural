package com.example.alfabetizaciondigittalrural.ui.lessons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.alfabetizaciondigittalrural.data.local.LessonEntity
import com.example.alfabetizaciondigittalrural.data.repository.EducationRepository
import com.example.alfabetizaciondigittalrural.ui.theme.HighContrastBlack
import com.example.alfabetizaciondigittalrural.ui.theme.HighContrastBlue
import com.example.alfabetizaciondigittalrural.ui.theme.HighContrastError
import com.example.alfabetizaciondigittalrural.ui.theme.HighContrastWhite

@Composable
fun LessonsScreen(
    educationRepository: EducationRepository
) {
    val viewModel: LessonsViewModel = viewModel(
        factory = LessonsViewModel.provideFactory(educationRepository)
    )
    val lessons by viewModel.lessons.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Lecciones Disponibles",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (lessons.isEmpty()) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No hay lecciones aún.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.addDummyLesson() }) {
                        Text("Crear Lección de Prueba")
                    }
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(lessons) { lesson ->
                    LessonItem(
                        lesson = lesson,
                        onShareClick = { viewModel.shareLesson(context, lesson) },
                        onDeleteClick = { viewModel.deleteLesson(lesson) },
                        onEditClick = { /* TODO: Navigate to Edit Screen */ }
                    )
                }
            }
        }
    }
}

@Composable
fun LessonItem(
    lesson: LessonEntity,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = HighContrastWhite
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image Section
            if (lesson.imagenRuta != null) {
                AsyncImage(
                    model = lesson.imagenRuta,
                    contentDescription = "Imagen de la lección",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .padding(end = 16.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(end = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Face, // Using Face as placeholder since Image might be missing
                        contentDescription = "Sin imagen",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                Text(
                    text = lesson.titulo,
                    style = MaterialTheme.typography.headlineSmall,
                    color = HighContrastBlack
                )
                Text(
                    text = "Tema: ${lesson.tema}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HighContrastBlack
                )
                Text(
                    text = "Autor: ${lesson.autorOriginal}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        
// Action Buttons Row
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onShareClick) {
                Icon(Icons.Filled.Share, contentDescription = "Compartir", tint = HighContrastBlue)
            }
            IconButton(onClick = onEditClick) {
                Icon(Icons.Filled.Edit, contentDescription = "Editar", tint = HighContrastBlack)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Filled.Delete, contentDescription = "Eliminar", tint = HighContrastError)
            }
        }
    }
}
