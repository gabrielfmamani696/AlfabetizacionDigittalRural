package com.example.alfabetizaciondigittalrural

import android.app.Application
import com.example.alfabetizaciondigittalrural.data.local.AppDatabase
import com.example.alfabetizaciondigittalrural.data.repository.UserRepository
import com.example.alfabetizaciondigittalrural.data.repository.EducationRepository

class AlfabetizacionApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}

class AppContainer(context: android.content.Context) {
    private val database by lazy { AppDatabase.getDatabase(context) }
    
    val userRepository by lazy {
        UserRepository(database.userDao())
    }
    
    val educationRepository by lazy {
        EducationRepository(database.educationDao())
    }
}
