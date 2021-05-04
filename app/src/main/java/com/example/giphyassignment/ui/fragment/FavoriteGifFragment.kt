package com.example.giphyassignment.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyassignment.R
import com.example.giphyassignment.databinding.FragmentFavoriteGifBinding
import com.example.giphyassignment.ui.injection.ViewModelFactory
import com.example.giphyassignment.ui.viewModel.GiphyViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FavoriteGifFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val giphyViewModel: GiphyViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(GiphyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        giphyViewModel.run {
            getTrendingGifs()

            gifs.observe(this@FavoriteGifFragment, Observer {
                it
            })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentFavoriteGifBinding>(
            inflater,
            R.layout.fragment_favorite_gif,
            container,
            false
        )?.run {
            val recyclerAdapter = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            rvGifs.layoutManager = recyclerAdapter
            root
        }
    }
}