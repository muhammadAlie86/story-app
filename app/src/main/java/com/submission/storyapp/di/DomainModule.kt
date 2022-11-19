package com.submission.storyapp.di

import com.submission.storyapp.domain.di.StoryDomain
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(
    includes = [
        StoryDomain::class
    ]
)
@InstallIn(SingletonComponent::class)
class DomainModule