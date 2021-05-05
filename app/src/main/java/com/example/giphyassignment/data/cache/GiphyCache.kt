package com.example.giphyassignment.data.cache

import com.example.giphyassignment.data.model.GiphyGifObject
import io.reactivex.Completable
import io.reactivex.Single

interface GiphyCache {
    fun saveGif(gif: GiphyGifObject): Completable

    fun getSavedGifs(): Single<List<GiphyGifObject>>

    fun deleteGif(gif: GiphyGifObject): Completable
}