package com.submission.storyapp.data.local.room.converter

import androidx.room.TypeConverter
import com.submission.storyapp.utillities.extension.fromJson
import com.submission.storyapp.utillities.extension.toJson

class StringListConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}