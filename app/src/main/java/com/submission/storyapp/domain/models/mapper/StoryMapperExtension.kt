package com.submission.storyapp.domain.models.mapper

import com.submission.storyapp.data.remote.reponse.*
import com.submission.storyapp.domain.models.dto.*

fun StoriesResponse.toDomain() = StoriesResponseDto(
    storyResponseItems = listOf(),
    error = error,
    message = message
)

fun StoryResponseItem.toDomain() = StoryResponseItemDto(
    photoUrl = photoUrl ,
    createdAt = createdAt,
    name = name,
    description = description,
    lon = lon,
    id = id,
    lat = lat
)
fun RegisterResponse.toDomain() = RegisterResponseDto(
    error = error,
    message = message
)
fun LoginResult.toDomain() = LoginResultDto(
    name = name,
    userId = userId,
    token = token
)
fun LoginResponse.toDomain() = LoginResponseDto(
    loginResult = loginResult,
    error = error,
    message = message
)
fun FileUploadResponse.toDomain() = FileUploadResponseDto(
    error = error,
    message = message
)