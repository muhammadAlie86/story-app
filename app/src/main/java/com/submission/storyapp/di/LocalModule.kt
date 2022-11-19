package com.submission.storyapp.di

import android.content.Context
import androidx.room.Room
import com.submission.storyapp.BuildConfig
import com.submission.storyapp.data.local.room.dao.RemoteKeysDao
import com.submission.storyapp.data.local.room.dao.StoryDao
import com.submission.storyapp.data.local.room.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

private const val DB_NAME = "db_name"

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return BuildConfig.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideStoryDao(appDatabase: AppDatabase): StoryDao {
        return appDatabase.storyDao()
    }

    @Provides
    @Singleton
    fun provideRemoteKeysDao(appDatabase: AppDatabase): RemoteKeysDao {
        return appDatabase.remoteKeysDao()
    }

}