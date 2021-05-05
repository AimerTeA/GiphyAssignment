package com.example.giphyassignment.util

import com.example.giphyassignment.data.model.BaseResponse
import io.reactivex.Single

fun <T>Single<BaseResponse<T>>.getData(): Single<T> {
    return this.map { it.data }
}