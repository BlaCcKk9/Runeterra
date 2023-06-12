package com.lms.worldoflol.common

sealed class Resource<T>(val data: T? = null, val error: ErrorType? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: ErrorType, data: T? = null) : Resource<T>(data, error)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

sealed class ErrorType{
    object NoInternetConnection : ErrorType()
    object NotFound: ErrorType()
    object HTTP: ErrorType()
}