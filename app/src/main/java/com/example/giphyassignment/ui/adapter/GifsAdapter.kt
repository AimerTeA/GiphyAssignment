package com.example.giphyassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyassignment.R
import com.example.giphyassignment.data.model.GiphyGif
import com.example.giphyassignment.ui.fragment.OnItemClickHandler
import com.example.giphyassignment.ui.holder.GifViewHolder
import com.example.giphyassignment.ui.viewModel.GiphyGifViewModel

class GifAdapter(
    val onItemClickHandler: OnItemClickHandler
) : RecyclerView.Adapter<GifViewHolder>(), BindableAdapter<List<GiphyGifViewModel>> {
    val gifs = mutableListOf<GiphyGifViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_gif,
                parent,
                false
            ),
            onItemClickHandler
        )
    }

    override fun getItemCount() = gifs.size

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(gifs[position])
    }

    override fun setData(data: List<GiphyGifViewModel>) {
        gifs.apply {
            clear()
            addAll(data)
        }

        notifyDataSetChanged()
    }
}