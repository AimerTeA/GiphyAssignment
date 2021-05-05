package com.example.giphyassignment.data.model

import com.google.gson.annotations.SerializedName

open class BaseResponse<T>(
    @SerializedName("data") val data: T,
    @SerializedName("pagination") val pagination: Pagination? = null,
    @SerializedName("meta") val meta: Meta? = null
) {
    data class Pagination(
        @SerializedName("total_count") val totalCount: Int,
        @SerializedName("count") val count: Int,
        @SerializedName("offset") val offset: Int
    )

    data class Meta(
        @SerializedName("status") val status: Int,
        @SerializedName("msg") val msg: String,
        @SerializedName("response_id") val responseId: String
    )
}