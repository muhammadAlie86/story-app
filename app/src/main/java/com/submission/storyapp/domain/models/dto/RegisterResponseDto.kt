package com.submission.storyapp.domain.models.dto

import com.google.gson.annotations.SerializedName

data class RegisterResponseDto (
    val error: Boolean,

    val message: String
)