package com.example.giphyassignment.ui.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyassignment.ui.adapter.BindableAdapter

@BindingAdapter("recyclerData")
@Suppress("UNCHECKED_CAST")
fun <T> setRecyclerData(
    recyclerView: RecyclerView,
    recyclerData: T?
) {
    recyclerData?.let {
        if (recyclerView.adapter is BindableAdapter<*>) {
            (recyclerView.adapter as BindableAdapter<T>).setData(it)
        }
    }
}

@BindingAdapter("gifUrl")
fun loadGifFromUrl(
    imageView: ImageView,
    gifUrl: String?
) {
    gifUrl?.let {
        Glide.with(imageView).load(it).into(imageView)
    }
}