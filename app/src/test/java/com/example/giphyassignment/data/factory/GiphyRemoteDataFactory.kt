package com.example.giphyassignment.data.factory

import com.example.giphyassignment.data.model.BaseResponse
import com.example.giphyassignment.data.model.GiphyGif
import java.util.*

object GiphyRemoteDataFactory {
    fun makeGiphyResponse(): BaseResponse<List<GiphyGif>> {
        return BaseResponse(
            listOf(
                GiphyGif(
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    makeImages()
                )
            )
        )
    }

    fun makeImages(): GiphyGif.Images {
        return GiphyGif.Images(
            makeImageDetails(),
            makeImageDetails(),
            makeImageDetails(),
            makeImageDetails()
        )
    }

    fun makeImageDetails(): GiphyGif.Images.ImageDetail {
        return GiphyGif.Images.ImageDetail(
            randomString(),
            randomString(),
            randomString(),
            randomString()
        )
    }

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }
}