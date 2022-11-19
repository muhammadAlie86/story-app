package com.submission.storyapp.utillities.room.converter

import androidx.room.TypeConverter
import com.submission.storyapp.utillities.extension.fromJson
import com.submission.storyapp.utillities.extension.toJson

class StringConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}