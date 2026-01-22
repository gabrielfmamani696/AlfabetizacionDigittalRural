package com.example.alfabetizaciondigittalrural.ui.onboarding

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.alfabetizaciondigittalrural.ui.theme.HighContrastBlue
import com.example.alfabetizaciondigittalrural.ui.theme.HighContrastWhite
import java.util.Locale

@Composable
fun ProfileSetupScreen(
    onProfileCreated: (String, Int) -> Unit
) {
    var alias by remember { mutableStateOf("") }
    var selectedAvatarId by remember { mutableStateOf<Int?>(null) }
    var isError by remember { mutableStateOf(false) }
    
    val context = LocalContext.current
    var tts: TextToSpeech? by remember { mutableStateOf(null) }

    DisposableEffect(context) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("es", "ES")
            }
        }
        onDispose {
            tts?.stop()
            tts?.shutdown()
        }
    }

    val speakInstructions = {
        tts?.speak("Escribe tu nombre y elige un dibujo", TextToSpeech.QUEUE_FLUSH, null, null)
    }

    val avatars = listOf(1, 2, 3, 4, 5, 6)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Crea tu Perfil",
                style = MaterialTheme.typography.displayMedium
            )
            IconButton(onClick = { speakInstructions() }) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Escuchar instrucciones",
                    modifier = Modifier.size(48.dp),
                    tint = HighContrastBlue
                )
            }
        }


        OutlinedTextField(
            value = alias,
            onValueChange = {
                alias = it
                isError = false
            },
            label = { Text("Escribe tu nombre") },
            textStyle = MaterialTheme.typography.headlineMedium,
            singleLine = true,
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
             text = "Elige un dibujo:",
             style = MaterialTheme.typography.headlineSmall,
             modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))


        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(avatars) { avatarId ->
                AvatarItem(
                    avatarId = avatarId,
                    isSelected = selectedAvatarId == avatarId,
                    onClick = { selectedAvatarId = avatarId }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = {
                if (alias.isNotBlank() && selectedAvatarId != null) {
                    onProfileCreated(alias, selectedAvatarId!!)
                } else {
                    isError = true
                    if (alias.isBlank()) {

                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (alias.isNotBlank() && selectedAvatarId != null) HighContrastBlue else Color.Gray,
                contentColor = HighContrastWhite
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            enabled = alias.isNotBlank() && selectedAvatarId != null
        ) {
            Text(
                text = "CONTINUAR",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
fun AvatarItem(
    avatarId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .border(
                width = if (isSelected) 4.dp else 1.dp,
                color = if (isSelected) HighContrastBlue else Color.Gray,
                shape = CircleShape
            )
            .background(if (isSelected) HighContrastBlue.copy(alpha = 0.1f) else Color.Transparent)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.Filled.Face,
            contentDescription = "Avatar $avatarId",
            modifier = Modifier.size(48.dp),
            tint = if (isSelected) HighContrastBlue else Color.Gray
        )
    }
}
