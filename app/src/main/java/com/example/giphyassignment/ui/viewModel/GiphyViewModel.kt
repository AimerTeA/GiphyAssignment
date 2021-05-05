package com.example.giphyassignment.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.giphyassignment.data.model.GiphyGifObject
import com.example.giphyassignment.data.repository.GiphyRepository
import com.example.giphyassignment.ui.state.Resource
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
    var offset = 0

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun getTrendingGifs(isNewSearch: Boolean) {
        if (isNewSearch)
            gifs.postValue(Resource.loading())
        else
            gifs.postValue(Resource.loading(gifs.value?.data))

        disposables.add(
            giphyRepository.getTrendingGifs(offset = offset)
                .subscribeOn(Schedulers.io())
                .subscribeWith(GetTrendingGifsSubscriber(isNewSearch))
        )
    }

    fun searchGifs(query: String, isNewSearch: Boolean) {
        if (isNewSearch)
            gifs.postValue(Resource.loading())
        else
            gifs.postValue(Resource.loading(gifs.value?.data))
        disposables.add(
            giphyRepository.searchGifs(query = query, offset = offset)
                .subscribeOn(Schedulers.io())
                .subscribeWith(SearchGifSubscriber(isNewSearch))
        )
    }

    fun deleteGif(gif: GiphyGifObject) {
        disposables.add(
            giphyRepository.deleteGif(gif)
                .subscribeOn(Schedulers.io())
                .subscribeWith(SaveDeleteGifSubscriber())
        )
    }

    fun saveGif(gif: GiphyGifObject) {
        disposables.add(
            giphyRepository.saveGif(gif)
                .subscribeOn(Schedulers.io())
                .subscribeWith(SaveDeleteGifSubscriber())
        )
    }

    fun onGifClicked(gif: GiphyGifObject) {
        if (gif.isSaved.not())
            deleteGif(gif)
        else
            saveGif(gif)
    }

    fun getSavedGifs() {
        disposables.add(
            giphyRepository.getSavedGifs()
                .subscribeOn(Schedulers.io())
                .subscribeWith(GetSavedGifsSubscriber())
        )
    }

    inner class GetTrendingGifsSubscriber(
        val isNewSearch: Boolean
    ) : DisposableSingleObserver<List<GiphyGifObject>>() {
        override fun onSuccess(t: List<GiphyGifObject>) {
            if (isNewSearch)
                gifs.postValue(Resource.success(t.map { GiphyGifViewModel(it, ::onGifClicked) }))
            else {
                val resultList = mutableListOf<GiphyGifViewModel>()
                gifs.value?.data?.let { resultList.addAll(it) }
                resultList.addAll(t.map { GiphyGifViewModel(it, ::onGifClicked) })
                gifs.postValue(Resource.success(resultList))
            }
        }

        override fun onError(e: Throwable) {
            gifs.postValue(Resource.error(e))
        }
    }

    inner class SearchGifSubscriber(
        val isNewSearch: Boolean
    ) : DisposableSingleObserver<List<GiphyGifObject>>() {
        override fun onSuccess(t: List<GiphyGifObject>) {
           if (isNewSearch)
               gifs.postValue(Resource.success(t.map { GiphyGifViewModel(it, ::onGifClicked) }))
           else {
               val resultList = mutableListOf<GiphyGifViewModel>()
               gifs.value?.data?.let { resultList.addAll(it) }
               resultList.addAll(t.map { GiphyGifViewModel(it, ::onGifClicked) })
               gifs.postValue(Resource.success(resultList))
           }
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