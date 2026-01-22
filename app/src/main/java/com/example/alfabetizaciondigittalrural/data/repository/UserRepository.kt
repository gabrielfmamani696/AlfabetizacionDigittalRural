package com.example.alfabetizaciondigittalrural.data.repository

import com.example.alfabetizaciondigittalrural.data.local.UserDao
import com.example.alfabetizaciondigittalrural.data.local.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    
    val user: Flow<UserEntity?> = userDao.getUser()

    suspend fun createUser(alias: String, avatarId: Int) {
        val newUser = UserEntity(alias = alias, avatarId = avatarId)
        userDao.insertUser(newUser)
    }

    suspend fun updateStreak(userId: Int, streak: Int) {
        userDao.updateStreak(userId, streak)
    }
}
