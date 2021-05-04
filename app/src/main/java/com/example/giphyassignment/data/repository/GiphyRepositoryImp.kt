package com.example.giphyassignment.data.repository

import com.example.giphyassignment.data.cache.GiphyCache
import com.example.giphyassignment.data.model.GiphyGif
import com.example.giphyassignment.data.remote.GiphyRemote
import io.reactivex.Single
import javax.inject.Inject

class GiphyRepositoryImp @Inject constructor(
    val remote: GiphyRemote,
    val cache: GiphyCache
) : GiphyRepository {
    override fun getTrendingGifs(limit: Int): Single<List<GiphyGif>> {
        return remote.getTrendingGifs(limit)
    }
}