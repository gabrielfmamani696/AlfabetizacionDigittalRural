package com.example.alfabetizaciondigittalrural.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.alfabetizaciondigittalrural.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
    

    val userExists: StateFlow<Boolean?> = userRepository.user
        .map { it != null }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private val _createdSuccess = MutableStateFlow(false)
    val createdSuccess: StateFlow<Boolean> = _createdSuccess

    fun createProfile(alias: String, avatarId: Int) {
        if (alias.isBlank()) return
        viewModelScope.launch {
            userRepository.createUser(alias, avatarId)
            _createdSuccess.value = true
        }
    }

    companion object {
        fun provideFactory(
            userRepository: UserRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProfileViewModel(userRepository) as T
            }
        }
    }
}
