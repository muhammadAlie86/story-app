package com.submission.storyapp.domain.models.dto

import com.submission.storyapp.data.remote.reponse.LoginResult

data class LoginResponseDto (

    val loginResult: LoginResult? = null,

    val error: Boolean? = null,

    val message: String? = null
)