package com.submission.storyapp.domain.usecase

import com.submission.storyapp.data.repository.StoryRepository
import com.submission.storyapp.domain.models.dto.FileUploadResponseDto
import com.submission.storyapp.domain.models.dto.StoriesResponseDto
import com.submission.storyapp.domain.models.mapper.toDomain
import com.submission.storyapp.utillities.network.DataState
import com.submission.storyapp.utillities.network.apiCall
import com.submission.storyapp.utillities.network.handleThrowable
import com.submission.storyapp.utillities.usecase.DataStateUseCase
import kotlinx.coroutines.flow.FlowCollector
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UploadImage @Inject constructor(
    val repository: StoryRepository
) : DataStateUseCase<UploadImage.Params, FileUploadResponseDto>(){

    data class Params(

        val token: String,
        val file: MultipartBody.Part,
        val description: RequestBody,
        val lat: RequestBody?,
        val lon: RequestBody?
    )

    override suspend fun FlowCollector<DataState<FileUploadResponseDto>>.execute(params: Params) {
        val response = repository.uploadImage(params.token,params.file,params.description,params.lat,params.lon)
        val service = apiCall { response }
        service.map { fileUploadResponse ->
            try {
                if (service is DataState.Success) {
                    emit(DataState.Success(fileUploadResponse.toDomain()))
                } else {
                    emit(DataState.Error(fileUploadResponse.message.toString()))
                }
            }
            catch (e : Throwable){
                emit(DataState.Empty(e.handleThrowable()))
            }
        }
    }

}