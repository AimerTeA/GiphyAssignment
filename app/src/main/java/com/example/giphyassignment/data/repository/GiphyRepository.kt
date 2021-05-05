package com.example.giphyassignment.data.repository

import com.example.giphyassignment.data.model.GiphyGif
import io.reactivex.Single

interface GiphyRepository {
    fun getTrendingGifs(limit: Int = 20): Single<List<GiphyGif>>

    fun searchGifs(query: String, limit: Int = 20): Single<List<GiphyGif>>
}