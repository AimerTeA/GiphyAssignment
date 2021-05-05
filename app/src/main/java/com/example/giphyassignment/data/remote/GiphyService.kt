package com.example.giphyassignment.data.remote

import com.example.giphyassignment.data.model.BaseResponse
import com.example.giphyassignment.data.model.GiphyGif
import com.example.giphyassignment.util.GIPY_API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {
    @GET("/v1/gifs/trending")
    fun getTrendingGifs(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("api_key") apiKey: String = GIPY_API_KEY
    ): Single<BaseResponse<List<GiphyGif>>>

    @GET("/v1/gifs/search")
    fun searchGifs(
        @Query("q") q: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("api_key") apiKey: String = GIPY_API_KEY
    ): Single<BaseResponse<List<GiphyGif>>>
}