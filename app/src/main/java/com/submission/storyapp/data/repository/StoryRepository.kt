package com.submission.storyapp.data.repository

import com.submission.storyapp.data.remote.reponse.FileUploadResponse
import com.submission.storyapp.data.remote.reponse.LoginResponse
import com.submission.storyapp.data.remote.reponse.RegisterResponse
import com.submission.storyapp.data.remote.reponse.StoriesResponse
import com.submission.storyapp.data.remote.service.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class StoryRepository @Inject constructor(
    val service : ApiService
){
    suspend fun userLogin(
        email: String,
        password: String
    ): LoginResponse = service.userLogin(email, password)

    suspend fun userRegister(
        name: String,
        email: String,
        password: String
    ): RegisterResponse = service.userRegister(name, email, password)

    suspend fun getAllStories(
        token: String,
        page: Int? = null,
        size: Int? = null,
        location: Int? = null
    ): StoriesResponse = service.getAllStories(token, page, size, location)


    suspend fun uploadImage(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody,
        lat: RequestBody?,
        lon: RequestBody?
    ): FileUploadResponse = service.uploadImage(token, file, description, lat, lon)
}