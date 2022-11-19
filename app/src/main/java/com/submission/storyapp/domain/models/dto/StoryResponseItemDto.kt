package com.submission.storyapp.domain.models.dto


data class StoryResponseItemDto (

    val photoUrl: String,

    val createdAt: String,

    val name: String,

    val description: String,

    val lon: Double?,

    val id: String,

    val lat: Double?
)