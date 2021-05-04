package com.example.giphyassignment.ui.injection

import android.app.Application
import com.example.giphyassignment.ui.GiphyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, FragmentModule::class, ViewModelModule::class, DataModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: GiphyApplication)
}