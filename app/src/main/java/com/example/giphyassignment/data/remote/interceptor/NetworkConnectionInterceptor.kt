package com.example.giphyassignment.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        /*if (internetChecker.isConnected().not()) {
            throw Exception(Throwable("Network"))
        }*/
        return chain.proceed(request)
    }
}