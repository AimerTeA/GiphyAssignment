package com.example.giphyassignment.data.cache

import com.example.giphyassignment.data.cache.database.GiphyDatabase
import com.example.giphyassignment.data.model.GiphyGifObject
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class GiphyCacheImp @Inject constructor(
    val database: GiphyDatabase
) : GiphyCache {
    override fun saveGif(gif: GiphyGifObject): Completable {
        return database.giphyGifDao().saveGif(gif)
    }

    override fun getSavedGifs(): Single<List<GiphyGifObject>> {
        return database.giphyGifDao().getGifs()
    }

    override fun deleteGif(gif: GiphyGifObject): Completable {
        return database.giphyGifDao().deleteGif(gif)
    }
}