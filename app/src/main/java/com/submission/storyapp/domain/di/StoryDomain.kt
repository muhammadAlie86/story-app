package com.submission.storyapp.domain.di

import com.submission.storyapp.data.repository.StoryRepository
import com.submission.storyapp.domain.usecase.GetAllStories
import com.submission.storyapp.domain.usecase.UploadImage
import com.submission.storyapp.domain.usecase.UserLogin
import com.submission.storyapp.domain.usecase.UserRegister
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class StoryDomain {

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: StoryRepository): UserLogin {
        return UserLogin(repository)
    }

    @Singleton
    @Provides
    fun provideRegisterUseCase(repository: StoryRepository): UserRegister {
        return UserRegister(repository)
    }

    @Singleton
    @Provides
    fun provideUploadImageUseCase(repository: StoryRepository): UploadImage {
        return UploadImage(repository)
    }

    @Singleton
    @Provides
    fun provideGetAllStoriesUseCase(repository: StoryRepository): GetAllStories {
        return GetAllStories(repository)
    }
}