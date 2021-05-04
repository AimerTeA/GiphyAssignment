package com.example.giphyassignment.ui.injection

import com.example.giphyassignment.BuildConfig
import com.example.giphyassignment.data.cache.GiphyCache
import com.example.giphyassignment.data.cache.GiphyCacheImp
import com.example.giphyassignment.data.remote.GiphyRemote
import com.example.giphyassignment.data.remote.GiphyRemoteImp
import com.example.giphyassignment.data.remote.GiphyService
import com.example.giphyassignment.data.remote.GiphyServiceFactory
import com.example.giphyassignment.data.remote.interceptor.NetworkConnectionInterceptor
import com.example.giphyassignment.data.repository.GiphyRepository
import com.example.giphyassignment.data.repository.GiphyRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {
    companion object {
        // Provides the service to be injected with custom parameters
        @Provides
        @JvmStatic
        fun provideGiphyService(networkConnectionInterceptor: NetworkConnectionInterceptor): GiphyService {
            return GiphyServiceFactory.makeGiphyService(BuildConfig.DEBUG, networkConnectionInterceptor)
        }
    }

    // Provides the class to be injected
    @Binds
    abstract fun bindGiphyRepository(giphyRepository: GiphyRepositoryImp): GiphyRepository

    @Binds
    abstract fun bindGiphyRemote(giphyRemote: GiphyRemoteImp): GiphyRemote

    @Binds
    abstract fun bindGiphyCache(giphyCache: GiphyCacheImp): GiphyCache
}