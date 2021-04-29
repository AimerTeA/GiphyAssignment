package com.example.giphyassignment.ui.injection

import android.app.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FragmentModule::class, ViewModelModule::class, RemoteModule::class, CacheModule::class])
interface ApplicationComponent {
    fun inject(app: Application)
}