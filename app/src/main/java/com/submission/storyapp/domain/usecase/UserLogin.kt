package com.submission.storyapp.domain.usecase

import com.submission.storyapp.data.repository.StoryRepository
import com.submission.storyapp.domain.models.dto.LoginResponseDto
import com.submission.storyapp.domain.models.mapper.toDomain
import com.submission.storyapp.utillities.network.DataState
import com.submission.storyapp.utillities.network.apiCall
import com.submission.storyapp.utillities.network.handleThrowable
import com.submission.storyapp.utillities.usecase.DataStateUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class UserLogin @Inject constructor(
    val repository: StoryRepository
) : DataStateUseCase<UserLogin.Params, LoginResponseDto>(){

    data class Params(
        val email: String,
        val password: String
    )

    override suspend fun FlowCollector<DataState<LoginResponseDto>>.execute(params: Params) {
        val response = repository.userLogin(params.email,params.password)
        val service = apiCall { response }
        service.map { loginResponse ->
            try {
                if (service is DataState.Success) {
                    emit(DataState.Success(loginResponse.toDomain()))
                } else {
                    emit(DataState.Error(loginResponse.message.toString()))
                }
            }
            catch (e : Throwable){
                emit(DataState.Empty(e.handleThrowable()))
            }
        }
    }

}