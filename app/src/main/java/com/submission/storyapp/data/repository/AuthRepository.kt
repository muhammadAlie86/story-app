package com.submission.storyapp.data.repository

import com.submission.storyapp.data.local.AuthPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val preferencesDataSource: AuthPreferencesDataSource
) {
    suspend fun saveAuthToken(token: String) {
        preferencesDataSource.saveAuthToken(token)
    }
    fun getAuthToken(): Flow<String?> = preferencesDataSource.getAuthToken()
}