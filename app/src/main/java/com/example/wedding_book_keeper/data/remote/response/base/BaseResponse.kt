package com.example.wedding_book_keeper.data.remote.response.base

import android.util.Log

data class BaseResponse<T>(
    val data: T?,
    val message: String,
    val code: String
)

fun <T> Result<BaseResponse<T>>.getResult(): Result<T>? {
    Log.d("TAG", "getResult: $this")
    this.onSuccess {
            response ->
        val data = response.data ?: return null
        Log.d("TAG", "getResult: $response")
        return Result.success(data)
    }.onFailure { throwable ->
        return Result.failure(throwable)
    }
    return Result.failure(IllegalStateException("알 수 없는 오류가 발생했습니다."))
}
