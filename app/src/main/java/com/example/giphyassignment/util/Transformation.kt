package com.example.giphyassignment.util

import com.example.giphyassignment.data.model.BaseResponse
import com.example.giphyassignment.data.model.GiphyGif
import com.example.giphyassignment.data.model.GiphyGifObject
import io.reactivex.Single

fun <T> Single<BaseResponse<T>>.getData(): Single<T> {
    return this.map { it.data }
}

fun Single<List<GiphyGif>>.toObject(): Single<List<GiphyGifObject>> {
    return this.map {
        it.map {
            GiphyGifObject(it.gifId, it.images.previewGif.url, it.username, it.title)
        }
    }
}