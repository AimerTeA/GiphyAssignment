package com.example.giphyassignment

import android.app.Application
import com.example.giphyassignment.injection.DaggerApplicationComponent

class GyphyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.create().inject(this)
    }
}