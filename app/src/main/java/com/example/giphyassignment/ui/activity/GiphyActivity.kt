package com.example.giphyassignment.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.giphyassignment.R
import com.example.giphyassignment.databinding.ActivityGiphyBinding
import com.example.giphyassignment.ui.adapter.MainGiphyAdapter
import com.example.giphyassignment.ui.fragment.FavoriteGifFragment
import com.example.giphyassignment.ui.fragment.SearchGifFragment

class GiphyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting up the data binding, so we can use live data to update ui directly
        val binding: ActivityGiphyBinding = DataBindingUtil.setContentView(this, R.layout.activity_giphy)
        binding.run {
            viewPMain.adapter = MainGiphyAdapter(
                supportFragmentManager,
                resources.getStringArray(R.array.tabs_title)
            )
            tabLMain.setupWithViewPager(viewPMain)
        }
    }
}