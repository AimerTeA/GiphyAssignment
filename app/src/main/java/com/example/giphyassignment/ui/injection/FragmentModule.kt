package com.example.giphyassignment.ui.injection

import com.example.giphyassignment.ui.activity.GiphyActivity
import com.example.giphyassignment.ui.fragment.FavoriteGifFragment
import com.example.giphyassignment.ui.fragment.SearchGifFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesGSWActivity(): GiphyActivity

    @ContributesAndroidInjector
    abstract fun favoriteGifFragment(): FavoriteGifFragment

    @ContributesAndroidInjector
    abstract fun searchGifFragment(): SearchGifFragment
}