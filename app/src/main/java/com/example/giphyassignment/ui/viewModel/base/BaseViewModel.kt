package com.example.giphyassignment.ui.viewModel.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    val disposables = CompositeDisposable()
}