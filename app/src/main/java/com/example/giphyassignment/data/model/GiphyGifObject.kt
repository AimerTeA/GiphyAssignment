package com.example.giphyassignment.data.model

data class GiphyGifObject(
    val gifId: String,
    val url: String,
    val username: String,
    val title: String,
    var isSaved: Boolean = false
)