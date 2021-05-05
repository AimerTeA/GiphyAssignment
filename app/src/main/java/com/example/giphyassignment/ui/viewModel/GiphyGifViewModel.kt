package com.example.giphyassignment.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphyassignment.data.model.GiphyGif

class GiphyGifViewModel(val gif: GiphyGif) : ViewModel() {
    val isSavedLiveData = MutableLiveData<Boolean>(false)

    fun onItemClick() {
        isSavedLiveData.postValue(isSavedLiveData.value?.not())
    }
}