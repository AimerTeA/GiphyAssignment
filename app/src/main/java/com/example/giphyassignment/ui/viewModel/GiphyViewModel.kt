package com.example.giphyassignment.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.chasecenter.ui.state.Resource
import com.example.giphyassignment.data.model.GiphyGif
import com.example.giphyassignment.data.repository.GiphyRepository
import com.example.giphyassignment.ui.viewModel.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GiphyViewModel @Inject constructor(
    val giphyRepository: GiphyRepository
) : BaseViewModel() {
    val gifs = MutableLiveData<Resource<List<GiphyGifViewModel>>>()

    fun getTrendingGifs() {
        gifs.postValue(Resource.loading())
        disposables.add(
            giphyRepository.getTrendingGifs()
                .subscribeOn(Schedulers.io())
                .subscribeWith(GetTrendingGifsSubscriber())
        )
    }

    fun searchGifs(query: String) {
        gifs.postValue(Resource.loading())
        disposables.add(
            giphyRepository.searchGifs(query)
                .subscribeOn(Schedulers.io())
                .subscribeWith(SearchGifSubscriber())
        )
    }

    inner class GetTrendingGifsSubscriber : DisposableSingleObserver<List<GiphyGif>>() {
        override fun onSuccess(t: List<GiphyGif>) {
            gifs.postValue(Resource.success(t.map { GiphyGifViewModel(it) }))
        }

        override fun onError(e: Throwable) {
            gifs.postValue(Resource.error(e))
        }
    }

    inner class SearchGifSubscriber : DisposableSingleObserver<List<GiphyGif>>() {
        override fun onSuccess(value: List<GiphyGif>?) {
            gifs.postValue(Resource.success(value?.map { GiphyGifViewModel(it) }))
        }

        override fun onError(e: Throwable?) {
            gifs.postValue(Resource.error(e))
        }
    }
}