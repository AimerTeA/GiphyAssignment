package com.example.giphyassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyassignment.R
import com.example.giphyassignment.data.model.GiphyGif
import com.example.giphyassignment.ui.fragment.OnItemClickHandler
import com.example.giphyassignment.ui.holder.GifViewHolder
import com.example.giphyassignment.ui.viewModel.GiphyGifViewModel
import javax.inject.Inject

class GifAdapter @Inject constructor() : ListAdapter<GiphyGifViewModel, GifViewHolder>(GiphyGifDiffCallback()),
    BindableAdapter<List<GiphyGifViewModel>> {
    var gifs = listOf<GiphyGifViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_gif,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        return holder.bind(gifs[position])
    }

    override fun getItemCount() = gifs.size

    override fun setData(data: List<GiphyGifViewModel>) {
        gifs.apply {
            submitList(data)
            gifs = data
        }
    }
}

class GiphyGifDiffCallback : DiffUtil.ItemCallback<GiphyGifViewModel>() {
    override fun areItemsTheSame(
        oldItem: GiphyGifViewModel,
        newItem: GiphyGifViewModel
    ): Boolean {
        return oldItem.getId() == newItem.getId()
    }

    override fun areContentsTheSame(
        oldItem: GiphyGifViewModel,
        newItem: GiphyGifViewModel
    ): Boolean {
        return oldItem.gif.isSaved == newItem.gif.isSaved
    }
}