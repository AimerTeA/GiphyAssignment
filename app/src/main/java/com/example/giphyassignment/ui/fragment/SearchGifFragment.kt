package com.example.giphyassignment.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyassignment.R
import com.example.giphyassignment.databinding.FragmentSearchGifBinding
import dagger.android.support.AndroidSupportInjection

class SearchGifFragment : Fragment() {
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
            val recyclerAdapter = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            rvGifs.layoutManager = recyclerAdapter
            root
        }
    }
}