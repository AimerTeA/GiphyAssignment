package com.example.giphyassignment.data.repository

import com.example.giphyassignment.data.cache.GiphyCache
import com.example.giphyassignment.data.model.GiphyGif
import com.example.giphyassignment.data.model.GiphyGifObject
import com.example.giphyassignment.data.remote.GiphyRemote
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GiphyRepositoryImp @Inject constructor(
    val remote: GiphyRemote,
    val cache: GiphyCache
) : GiphyRepository {
    override fun getTrendingGifs(limit: Int, offset: Int): Single<List<GiphyGifObject>> {
        // Determine if the gif is saved, so we can show the right icon in the recyclerview
        return Single.zip(
            remote.getTrendingGifs(limit, offset),
            cache.getSavedGifs(),
            BiFunction<List<GiphyGifObject>, List<GiphyGifObject>, List<GiphyGifObject>> { remoteGifs, cacheGifs ->
                if (cacheGifs.isNotEmpty()) {
                    remoteGifs.forEach { remoteGif ->
                        val cGif =
                            cacheGifs.firstOrNull { cacheGif -> cacheGif.gifId == remoteGif.gifId }
                        if (cGif != null)
                            remoteGif.isSaved = true
                    }
                }
                remoteGifs
            }
        )
    }

    override fun searchGifs(query: String, limit: Int, offset: Int): Single<List<GiphyGifObject>> {
        return Single.zip(
            remote.searchGifs(query, limit, offset),
            cache.getSavedGifs(),
            BiFunction<List<GiphyGifObject>, List<GiphyGifObject>, List<GiphyGifObject>> { remoteGifs, cacheGifs ->
                if (cacheGifs.isNotEmpty()) {
                    remoteGifs.forEach { remoteGif ->
                        val cGif =
                            cacheGifs.firstOrNull { cacheGif -> cacheGif.gifId == remoteGif.gifId }
                        if (cGif != null)
                            remoteGif.isSaved = true
                    }
                }
                remoteGifs
            }
        )
    }

    override fun saveGif(gif: GiphyGifObject): Completable {
        return cache.saveGif(gif)
    }

    override fun getSavedGifs(): Single<List<GiphyGifObject>> {
        return cache.getSavedGifs()
    }

    override fun deleteGif(gif: GiphyGifObject): Completable {
        return cache.deleteGif(gif)
    }
}