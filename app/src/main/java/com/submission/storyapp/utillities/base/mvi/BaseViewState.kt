package com.submission.storyapp.utillities.base.mvi

sealed interface BaseViewState<out T> {

    object Loading : BaseViewState<Nothing>
    object Empty : BaseViewState<Nothing>
    data class Data<T>(val value: T) : BaseViewState<T>
    data class Error(val throwable: Throwable) : BaseViewState<Nothing>

}