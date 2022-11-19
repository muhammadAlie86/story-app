package com.submission.storyapp.utillities.base.app

abstract class NetworkConfig {
    abstract fun baseUrl(): String

    abstract fun timeOut(): Long

    open fun isDev() = false
}