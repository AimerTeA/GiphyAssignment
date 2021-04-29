package com.example.giphyassignment.injection

import com.example.giphyassignment.GyphyApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface ApplicationComponent {
    fun inject(app: GyphyApplication)
}