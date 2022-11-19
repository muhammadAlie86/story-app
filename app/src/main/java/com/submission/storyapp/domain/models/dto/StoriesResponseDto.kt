package com.submission.storyapp.domain.models.dto

data class StoriesResponseDto (
        val storyResponseItems: List<StoryResponseItemDto>,

        val error: Boolean,

        val message: String

        )