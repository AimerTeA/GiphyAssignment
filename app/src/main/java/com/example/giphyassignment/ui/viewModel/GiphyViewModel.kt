package com.example.giphyassignment.ui.viewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.chasecenter.ui.state.Resource
import com.example.giphyassignment.data.model.GiphyGifObject
import com.example.giphyassignment.data.repository.GiphyRepository
import com.example.giphyassignment.ui.viewModel.base.BaseViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GiphyViewModel @Inject constructor(
    val giphyRepository: GiphyRepository
) : BaseViewModel() {
    val gifs = MutableLiveData<Resource<List<GiphyGifViewModel>>>()
    val cacheGifs = MutableLiveData<Resource<List<GiphyGifViewModel>>>()

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

    fun onGifClicked(gif: GiphyGifObject) {
        if (gif.isSaved.not()) {
            disposables.add(
                giphyRepository.deleteGif(gif)
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(SaveDeleteGifSubscriber())
            )
        } else {
            disposables.add(
                giphyRepository.saveGif(gif)
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(SaveDeleteGifSubscriber())
            )
        }
    }

    fun getSavedGifs() {
        disposables.add(
            giphyRepository.getSavedGifs()
                .subscribeOn(Schedulers.io())
                .subscribeWith(GetSavedGifsSubscriber())
        )
    }

    inner class GetTrendingGifsSubscriber : DisposableSingleObserver<List<GiphyGifObject>>() {
        override fun onSuccess(t: List<GiphyGifObject>) {
            gifs.postValue(Resource.success(t.map { GiphyGifViewModel(it, ::onGifClicked) }))
        }

        override fun onError(e: Throwable) {
            gifs.postValue(Resource.error(e))
        }
    }

    inner class SearchGifSubscriber : DisposableSingleObserver<List<GiphyGifObject>>() {
        override fun onSuccess(t: List<GiphyGifObject>) {
            gifs.postValue(Resource.success(t.map { GiphyGifViewModel(it, ::onGifClicked) }))
        }

        override fun onError(e: Throwable) {
            gifs.postValue(Resource.error(e))
        }
    }

    inner class SaveDeleteGifSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            getSavedGifs()
        }

        override fun onError(e: Throwable) {
            cacheGifs.postValue(Resource.error(e))
        }
    }

    inner class GetSavedGifsSubscriber : DisposableSingleObserver<List<GiphyGifObject>>() {
        override fun onSuccess(t: List<GiphyGifObject>) {
            cacheGifs.postValue(Resource.success(t.map { GiphyGifViewModel(it, ::onGifClicked) }))
        }

        override fun onError(e: Throwable) {
            cacheGifs.postValue(Resource.error(e))
        }
    }
}