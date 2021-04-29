package com.example.giphyassignment.ui

import android.app.Application
import com.example.giphyassignment.ui.injection.DaggerApplicationComponent

class GiphyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.create().inject(this)
    }
}