package com.example.giphyassignment.data.remote

import com.example.giphyassignment.data.model.GiphyGifObject
import io.reactivex.Single

interface GiphyRemote {
    fun getTrendingGifs(limit: Int): Single<List<GiphyGifObject>>

    fun searchGifs(query: String, limit: Int): Single<List<GiphyGifObject>>
}