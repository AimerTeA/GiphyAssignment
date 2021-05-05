package com.example.giphyassignment.data.remote

import com.example.giphyassignment.data.model.GiphyGifObject
import com.example.giphyassignment.util.getData
import com.example.giphyassignment.util.toObject
import io.reactivex.Single
import javax.inject.Inject

class GiphyRemoteImp @Inject constructor(
    val giphyService: GiphyService
) : GiphyRemote {
    override fun getTrendingGifs(limit: Int, offset: Int): Single<List<GiphyGifObject>> {
        return giphyService.getTrendingGifs(limit, offset).getData().toObject()
    }

    override fun searchGifs(query: String, limit: Int, offset: Int): Single<List<GiphyGifObject>> {
        return giphyService.searchGifs(query, limit, offset).getData().toObject()
    }
}