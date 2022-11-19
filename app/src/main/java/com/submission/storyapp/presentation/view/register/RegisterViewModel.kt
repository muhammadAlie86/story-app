package com.submission.storyapp.presentation.view.register

import com.submission.storyapp.domain.usecase.UserRegister
import com.submission.storyapp.utillities.base.mvvm.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRegister: UserRegister
) : MvvmViewModel(){

    fun userRegister(name: String, email: String, password: String){
        safeLaunch {
            val params = UserRegister.Params(name, email, password)
            execute(userRegister(params))
        }
    }
}