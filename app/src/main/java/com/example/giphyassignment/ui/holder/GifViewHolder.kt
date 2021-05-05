package com.example.giphyassignment.ui.holder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.giphyassignment.R
import com.example.giphyassignment.data.model.GiphyGif
import com.example.giphyassignment.databinding.ItemGifBinding
import com.example.giphyassignment.ui.fragment.OnItemClickHandler
import com.example.giphyassignment.ui.viewModel.GiphyGifViewModel

class GifViewHolder(
    val view: View,
    val onItemClickHandler: OnItemClickHandler
) : BaseHolder<GiphyGifViewModel>(view) {
    override fun bind(item: GiphyGifViewModel) {
        DataBindingUtil.bind<ItemGifBinding>(view)?.run {
            data = item
        }
    }
}