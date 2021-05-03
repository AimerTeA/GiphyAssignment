package com.example.giphyassignment.ui.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.giphyassignment.ui.fragment.FavoriteGifFragment
import com.example.giphyassignment.ui.fragment.SearchGifFragment
import javax.inject.Inject

class MainGiphyAdapter @Inject constructor(
    private val fm: FragmentManager,
    private val fragmentsTitle: Array<String>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int) = when (position) {
        0 -> SearchGifFragment()
        else -> FavoriteGifFragment()
    }

    override fun getCount() = fragmentsTitle.size

    override fun getPageTitle(position: Int) = fragmentsTitle[position]
}