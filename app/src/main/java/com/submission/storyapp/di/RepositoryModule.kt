package com.submission.storyapp.di

import com.submission.storyapp.data.local.AuthPreferencesDataSource
import com.submission.storyapp.data.remote.service.ApiService
import com.submission.storyapp.data.repository.AuthRepository
import com.submission.storyapp.data.repository.StoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideStoryRepository(
        service: ApiService
    ) = StoryRepository(service)


    @Singleton
    @Provides
    fun provideAuthRepository(
        preferencesDataSource: AuthPreferencesDataSource
    ) = AuthRepository(preferencesDataSource)


}