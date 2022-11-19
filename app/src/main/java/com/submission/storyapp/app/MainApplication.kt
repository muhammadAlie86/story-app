package com.submission.storyapp.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
open class MainApplication :Application() {}