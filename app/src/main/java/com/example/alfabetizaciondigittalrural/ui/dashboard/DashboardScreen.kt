package com.example.alfabetizaciondigittalrural.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alfabetizaciondigittalrural.data.repository.UserRepository
import com.example.alfabetizaciondigittalrural.ui.theme.HighContrastBlack
import com.example.alfabetizaciondigittalrural.ui.theme.HighContrastBlue
import com.example.alfabetizaciondigittalrural.ui.theme.HighContrastWhite

@Composable
fun DashboardScreen(
    userRepository: UserRepository,
    modifier: Modifier = Modifier
) {
    val viewModel: DashboardViewModel = viewModel(
        factory = DashboardViewModel.provideFactory(userRepository)
    )
    val user by viewModel.user.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        user?.let { currentUser ->
            Text(
                text = "Hola, ${currentUser.alias}",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 24.dp)
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MetricCard(
                    icon = Icons.Default.Star,
                    label = "Puntos",
                    value = currentUser.puntosTotales.toString(),
                    color = HighContrastBlue
                )
                MetricCard(
                    icon = Icons.Default.Star,
                    label = "Días",
                    value = currentUser.rachaActualDias.toString(),
                    color = HighContrastBlue
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "Tu Progreso",
                style = MaterialTheme.typography.headlineMedium
            )

        }
    }
}

@Composable
fun MetricCard(
    icon: ImageVector,
    label: String,
    value: String,
    color: Color
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .border(2.dp, HighContrastBlack, CardDefaults.shape),
        colors = CardDefaults.cardColors(
            containerColor = HighContrastWhite
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.height(48.dp).width(48.dp)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.displayLarge,
                color = HighContrastBlack
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = HighContrastBlack
            )
        }
    }
}
