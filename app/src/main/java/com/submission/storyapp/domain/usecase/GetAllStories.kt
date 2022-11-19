package com.submission.storyapp.domain.usecase

import com.submission.storyapp.data.repository.StoryRepository
import com.submission.storyapp.domain.models.dto.LoginResponseDto
import com.submission.storyapp.domain.models.dto.StoriesResponseDto
import com.submission.storyapp.domain.models.mapper.toDomain
import com.submission.storyapp.utillities.network.DataState
import com.submission.storyapp.utillities.network.apiCall
import com.submission.storyapp.utillities.network.handleThrowable
import com.submission.storyapp.utillities.usecase.DataStateUseCase
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.http.Header
import retrofit2.http.Query
import javax.inject.Inject

class GetAllStories @Inject constructor(
    val repository: StoryRepository
) : DataStateUseCase<GetAllStories.Params, StoriesResponseDto>(){

    data class Params(

        val token: String,
        val page: Int? = null,
        val size: Int? = null,
        val location: Int? = null
    )

    override suspend fun FlowCollector<DataState<StoriesResponseDto>>.execute(params: Params) {
        val response = repository.getAllStories(params.token,params.page,params.size,params.location)
        val service = apiCall { response }
        service.map { storiesResponse ->
            try {
                if (service is DataState.Success) {
                    emit(DataState.Success(storiesResponse.toDomain()))
                } else {
                    emit(DataState.Error(storiesResponse.message))
                }
            }
            catch (e : Throwable){
                emit(DataState.Empty(e.handleThrowable()))
            }
        }
    }

}