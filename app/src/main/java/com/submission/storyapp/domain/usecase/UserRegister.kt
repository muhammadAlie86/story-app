package com.submission.storyapp.domain.usecase

import com.submission.storyapp.data.repository.StoryRepository
import com.submission.storyapp.domain.models.dto.LoginResponseDto
import com.submission.storyapp.domain.models.dto.RegisterResponseDto
import com.submission.storyapp.domain.models.mapper.toDomain
import com.submission.storyapp.utillities.network.DataState
import com.submission.storyapp.utillities.network.apiCall
import com.submission.storyapp.utillities.network.handleThrowable
import com.submission.storyapp.utillities.usecase.DataStateUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class UserRegister @Inject constructor(
    val repository: StoryRepository
) : DataStateUseCase<UserRegister.Params, RegisterResponseDto>(){

    data class Params(
        val name: String,
        val email: String,
        val password: String
    )

    override suspend fun FlowCollector<DataState<RegisterResponseDto>>.execute(params: Params) {
        val response = repository.userRegister(params.name,params.email,params.password)
        val service = apiCall { response }
        service.map { registerResponse ->
            try {
                if (service is DataState.Success) {
                    emit(DataState.Success(registerResponse.toDomain()))
                } else {
                    emit(DataState.Error(registerResponse.message))
                }
            }
            catch (e : Throwable){
                emit(DataState.Empty(e.handleThrowable()))
            }
        }
    }

}