package com.example.giphyassignment.data.remote

import com.example.giphyassignment.data.model.GiphyGif
import com.example.giphyassignment.util.getData
import io.reactivex.Single
import javax.inject.Inject

class GiphyRemoteImp @Inject constructor(
    val giphyService: GiphyService
) : GiphyRemote {
    override fun getTrendingGifs(limit: Int): Single<List<GiphyGif>> {
        return giphyService.getTrendingGifs(limit).getData()
    }

    override fun searchGifs(query: String, limit: Int): Single<List<GiphyGif>> {
        return giphyService.searchGifs(query, limit).getData()
    }
}