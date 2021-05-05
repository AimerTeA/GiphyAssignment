package com.example.giphyassignment.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyassignment.R
import com.example.giphyassignment.data.model.GiphyGif
import com.example.giphyassignment.databinding.FragmentSearchGifBinding
import com.example.giphyassignment.ui.adapter.GifAdapter
import com.example.giphyassignment.ui.injection.ViewModelFactory
import com.example.giphyassignment.ui.viewModel.GiphyViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchGifFragment : Fragment(), OnItemClickHandler {
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
        return DataBindingUtil.inflate<FragmentSearchGifBinding>(
            inflater,
            R.layout.fragment_search_gif,
            container,
            false
        )?.run {
            lifecycleOwner = this@SearchGifFragment
            vm = giphyViewModel

            val recyclerAdapter = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            rvGifs.layoutManager = recyclerAdapter
            rvGifs.adapter = GifAdapter(this@SearchGifFragment)

            searchVBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { giphyViewModel.searchGifs(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return if (newText.isNullOrEmpty()) {
                        giphyViewModel.getTrendingGifs()
                        true
                    } else
                        false
                }
            })

            root
        }
    }

    override fun onIntemClick(giphyGif: GiphyGif) {
        // TODO
    }
}

interface OnItemClickHandler {
    fun onIntemClick(giphyGif: GiphyGif)
}