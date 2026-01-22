package com.example.alfabetizaciondigittalrural.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM usuario LIMIT 1")
    fun getUser(): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("UPDATE usuario SET puntos_totales = :points WHERE id_usuario = :userId")
    suspend fun updatePoints(userId: Int, points: Int)

    @Query("UPDATE usuario SET racha_actual_dias = :streak WHERE id_usuario = :userId")
    suspend fun updateStreak(userId: Int, streak: Int)
}
