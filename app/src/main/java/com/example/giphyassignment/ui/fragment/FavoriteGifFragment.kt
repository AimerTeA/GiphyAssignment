package com.example.giphyassignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyassignment.R
import com.example.giphyassignment.databinding.FragmentFavoriteGifBinding

class FavoriteGifFragment : Fragment() {
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