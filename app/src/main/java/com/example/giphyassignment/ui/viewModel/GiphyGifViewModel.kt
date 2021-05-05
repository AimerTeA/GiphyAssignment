package com.example.giphyassignment.ui.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphyassignment.data.model.GiphyGifObject

class GiphyGifViewModel(val gif: GiphyGifObject, val onItemClickCallback:(GiphyGifObject) -> Unit) {
    val isSavedLiveData = ObservableField<Boolean>(false)

    init {
        isSavedLiveData.set(gif.isSaved)
    }

    fun onItemClick() {
        updateIsSaved()
        onItemClickCallback.invoke(gif)
    }

    private fun updateIsSaved() {
        gif.isSaved = gif.isSaved.not()
        isSavedLiveData.set(gif.isSaved)
    }

    fun getId() = gif.gifId
}