package com.example.giphyassignment.data.cache.database.dao

import androidx.room.*
import com.example.giphyassignment.data.model.GiphyGifObject
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GiphyGifObjectDao {
    @Query("SELECT * FROM gif")
    fun getGifs(): Single<List<GiphyGifObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGif(gif: GiphyGifObject): Completable

    @Delete
    fun deleteGif(gif: GiphyGifObject): Completable
}