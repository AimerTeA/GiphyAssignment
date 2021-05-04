package com.example.giphyassignment.data.model

import com.google.gson.annotations.SerializedName

data class GiphyGif(
    @SerializedName("id") val gifId: String,
    @SerializedName("url") val url: String,
    @SerializedName("bitly_url") val bitlyUrl: String,
    @SerializedName("embed_url") val embedUrl: String,
    @SerializedName("username") val username: String,
    @SerializedName("title") val title: String
)