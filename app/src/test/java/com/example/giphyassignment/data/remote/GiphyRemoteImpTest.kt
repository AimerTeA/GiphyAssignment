package com.example.giphyassignment.data.remote

import com.example.giphyassignment.data.factory.GiphyRemoteDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Response

@RunWith(JUnit4::class)
class GiphyRemoteImpTest {
    val service = mock(GiphyService::class.java)
    val remoteImp = GiphyRemoteImp(service)

    @Test
    fun `Get trending gifs calls server is success`() {
        val response = Single.just(GiphyRemoteDataFactory.makeGiphyResponse())
        whenever(service.getTrendingGifs(any(), any(), any())).thenReturn(response)
        val testObserver = remoteImp.getTrendingGifs(20, 0).test()
        verify(service).getTrendingGifs(any(), any(), any())
        testObserver.assertComplete()
    }


    @Test
    fun `Search gifs calls server is success`() {
        val response = Single.just(GiphyRemoteDataFactory.makeGiphyResponse())
        whenever(service.searchGifs(any(), any(), any(), any())).thenReturn(response)
        val testObserver = remoteImp.searchGifs("", 20, 0).test()
        verify(service).searchGifs(any(), any(), any(), any())
        testObserver.assertComplete()
    }

    //TODO(TEST FOR OTHER CLASSES)
}