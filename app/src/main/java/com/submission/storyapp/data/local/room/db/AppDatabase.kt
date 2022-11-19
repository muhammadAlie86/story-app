package com.submission.storyapp.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.submission.storyapp.data.local.models.RemoteKeys
import com.submission.storyapp.data.local.models.Story
import com.submission.storyapp.data.local.room.converter.StringListConverter
import com.submission.storyapp.data.local.room.dao.RemoteKeysDao
import com.submission.storyapp.data.local.room.dao.StoryDao

@Database(
    entities = [RemoteKeys::class, Story::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}