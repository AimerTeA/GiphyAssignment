package com.example.giphyassignment.data.repository

import com.example.giphyassignment.data.model.GiphyGifObject
import io.reactivex.Completable
import io.reactivex.Single

interface GiphyRepository {
    fun getTrendingGifs(limit: Int = 20, offset: Int): Single<List<GiphyGifObject>>

    fun searchGifs(query: String, limit: Int = 20, offset: Int): Single<List<GiphyGifObject>>

    fun saveGif(gif: GiphyGifObject): Completable

    fun getSavedGifs(): Single<List<GiphyGifObject>>

    fun deleteGif(gif: GiphyGifObject): Completable
}