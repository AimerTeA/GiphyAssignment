package com.example.giphyassignment.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyassignment.R
import com.example.giphyassignment.databinding.FragmentSearchGifBinding
import com.example.giphyassignment.ui.adapter.GifAdapter
import com.example.giphyassignment.ui.injection.ViewModelFactory
import com.example.giphyassignment.ui.viewModel.GiphyViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchGifFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var gifsRecyclerView: RecyclerView? = null
    private var isLoading: Boolean = false
    private var isInitLoading: Boolean = true

    private val giphyViewModel: GiphyViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)
            .get(GiphyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        giphyViewModel.run {
            getTrendingGifs(true)
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
            rvGifs.adapter = GifAdapter()
            rvGifs.setHasFixedSize(true)
            rvGifs.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val lastItemVisible =
                        (rvGifs.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()
                    if (lastItemVisible == (rvGifs.adapter)?.itemCount?.minus(1))
                        giphyViewModel.run {
                            offset += 20
                            if (searchVBar.query != null && searchVBar.query.isNotEmpty())
                                giphyViewModel.searchGifs(searchVBar.query.toString(), false)
                            else
                                giphyViewModel.getTrendingGifs(false)
                        }
                }
            })
            gifsRecyclerView = rvGifs

            searchVBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { giphyViewModel.searchGifs(it, true) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return if (newText.isNullOrEmpty()) {
                        giphyViewModel.getTrendingGifs(true)
                        true
                    } else
                        false
                }
            })

            root
        }
    }
}