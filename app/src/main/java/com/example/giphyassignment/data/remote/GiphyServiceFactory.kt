package com.example.giphyassignment.data.remote

import com.example.giphyassignment.data.remote.interceptor.NetworkConnectionInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GiphyServiceFactory {
    fun makeGiphyService(
        isDebug: Boolean,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): GiphyService {
        val okHttpClient = makeOkHttpClient(networkConnectionInterceptor)
        return makeGiphyService(okHttpClient, Gson())
    }

    private fun makeGiphyService(okHttpClient: OkHttpClient, gson: Gson): GiphyService {
        val retrofit = makeService(
            okHttpClient,
            gson,
            "https://api.giphy.com"
        )
        return retrofit.create(GiphyService::class.java)
    }

    fun makeService(okHttpClient: OkHttpClient, gson: Gson, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun makeOkHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()
    }
}