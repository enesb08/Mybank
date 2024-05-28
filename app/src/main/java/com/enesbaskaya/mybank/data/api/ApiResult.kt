package com.enesbaskaya.mybank.data.api

enum class ApiStatus {
    LOADING,
    SUCCESS,
    ERROR
}

sealed class ApiResult<out T>(
    val status: ApiStatus,
    val response: T?,
    val message: String?,
    val errorCode: String? = null,
) {
    data class Loading<out R>(val _data: R?=null) : ApiResult<R>(
        status = ApiStatus.LOADING,
        response = _data,
        message = null,
        errorCode = null
    )


    data class Success<out R>(val _data: R?) : ApiResult<R>(
        status = ApiStatus.SUCCESS,
        response = _data,
        message = null,
        errorCode = null
    )

    data class Error(val exception: String?, val code: String? = null) : ApiResult<Nothing>(
        status = ApiStatus.ERROR,
        response = null,
        message = exception,
        errorCode = code

    )


}