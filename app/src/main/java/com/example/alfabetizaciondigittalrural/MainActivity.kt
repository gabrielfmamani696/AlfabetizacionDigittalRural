package com.example.alfabetizaciondigittalrural

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alfabetizaciondigittalrural.ui.onboarding.ProfileSetupScreen
import com.example.alfabetizaciondigittalrural.ui.onboarding.ProfileViewModel
import com.example.alfabetizaciondigittalrural.ui.theme.AlfabetizacionDigittalRuralTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appContainer = (application as AlfabetizacionApplication).container
        
        setContent {
            AlfabetizacionDigittalRuralTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AlfabetizacionApp(appContainer)
                }
            }
        }
    }
}

@Composable
fun AlfabetizacionApp(appContainer: AppContainer) {
    val navController = rememberNavController()
    val profileViewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModel.provideFactory(appContainer.userRepository)
    )
    
    val userExists by profileViewModel.userExists.collectAsState()

    if (userExists == null) {
        return
    }

    val startDestination = if (userExists == true) "dashboard" else "onboarding_profile"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("onboarding_profile") {
            ProfileSetupScreen(
                onProfileCreated = { alias, avatarId ->
                    profileViewModel.createProfile(alias, avatarId)
                    navController.navigate("dashboard") {
                        popUpTo("onboarding_profile") { inclusive = true }
                    }
                }
            )
        }
        composable("dashboard") {
            com.example.alfabetizaciondigittalrural.ui.MainScreen(
                appContainer = appContainer
            )
        }
    }
}