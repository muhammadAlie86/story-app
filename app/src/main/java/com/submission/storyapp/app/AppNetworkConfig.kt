package com.submission.storyapp.app

import com.submission.storyapp.BuildConfig
import com.submission.storyapp.utillities.base.app.NetworkConfig

class AppNetworkConfig : NetworkConfig() {
    override fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }

    override fun timeOut(): Long {
        return 30L
    }

    override fun isDev(): Boolean {
        return BuildConfig.DEBUG
    }
}