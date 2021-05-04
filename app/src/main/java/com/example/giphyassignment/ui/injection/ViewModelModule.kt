package com.example.giphyassignment.ui.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giphyassignment.ui.activity.GiphyActivity
import com.example.giphyassignment.ui.viewModel.GiphyViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GiphyViewModel::class)
    abstract fun bindsGiphyViewModel(viewModel: GiphyViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @MustBeDocumented
    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}