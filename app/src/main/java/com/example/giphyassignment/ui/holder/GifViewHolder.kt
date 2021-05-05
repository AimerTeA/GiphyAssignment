package com.example.giphyassignment.ui.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.giphyassignment.databinding.ItemGifBinding
import com.example.giphyassignment.ui.viewModel.GiphyGifViewModel

class GifViewHolder(
    val view: View
) : BaseHolder<GiphyGifViewModel>(view) {
    override fun bind(item: GiphyGifViewModel) {
        DataBindingUtil.bind<ItemGifBinding>(view)?.run {
            data = item
            ivFavorite.setOnClickListener {
                item.onItemClick()
            }
        }
    }
}