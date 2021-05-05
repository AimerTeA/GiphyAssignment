package com.example.giphyassignment.data.remote

import com.example.giphyassignment.data.model.GiphyGif
import io.reactivex.Single

interface GiphyRemote {
    fun getTrendingGifs(limit: Int): Single<List<GiphyGif>>

    fun searchGifs(query: String, limit: Int): Single<List<GiphyGif>>
}