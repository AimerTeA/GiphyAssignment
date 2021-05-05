package com.example.giphyassignment.data.model

import com.google.gson.annotations.SerializedName

data class GiphyGif(
    @SerializedName("id") val gifId: String,
    @SerializedName("url") val url: String,
    @SerializedName("bitly_url") val bitlyUrl: String,
    @SerializedName("embed_url") val embedUrl: String,
    @SerializedName("username") val username: String,
    @SerializedName("title") val title: String,
    @SerializedName("images") val images: Images
) {
    data class Images(
        @SerializedName("original") val original: ImageDetail,
        @SerializedName("downsized") val downsized: ImageDetail,
        @SerializedName("downsized_medium") val downsizedMedium: ImageDetail,
        @SerializedName("preview_gif") val previewGif: ImageDetail
    ) {
        data class ImageDetail(
            @SerializedName("height") val height: String,
            @SerializedName("width") val width: String,
            @SerializedName("size") val size: String,
            @SerializedName("url") val url: String
        )
    }
}